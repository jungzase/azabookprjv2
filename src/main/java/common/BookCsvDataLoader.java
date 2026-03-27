package common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@DependsOn("dataSourceInitializer")
public class BookCsvDataLoader implements InitializingBean {

    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    public BookCsvDataLoader(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void afterPropertiesSet() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM books", Integer.class);
        if (count != null && count > 0) {
            return;
        }

        Resource resource = resourceLoader.getResource("classpath:books.csv");
        Path tempFile = null;

        try (InputStream inputStream = resource.getInputStream()) {
            tempFile = Files.createTempFile("azabook-books-", ".csv");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

            String csvPath = tempFile.toAbsolutePath().toString()
                .replace("\\", "/")
                .replace("'", "''");

            jdbcTemplate.execute(
                "INSERT INTO books ("
                    + "isbn, book_name, author, publisher, publication_date, "
                    + "price, stock, category_id, img_link, description, rank_num"
                    + ") "
                    + "SELECT "
                    + "isbn, "
                    + "book_name, "
                    + "author, "
                    + "publisher, "
                    + "PARSEDATETIME(publication_date, 'yyyy-MM-dd'), "
                    + "CAST(price AS INT), "
                    + "CAST(stock AS INT), "
                    + "CAST(category_id AS BIGINT), "
                    + "img_link, "
                    + "description, "
                    + "CAST(rank_num AS INT) "
                    + "FROM CSVREAD('" + csvPath + "', null, 'charset=UTF-8')"
            );
        } catch (IOException e) {
            throw new IllegalStateException("Failed to prepare books.csv for database initialization.", e);
        } finally {
            if (tempFile != null) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException ignored) {
                    // Temporary CSV cleanup failure should not break application startup.
                }
            }
        }
    }
}


