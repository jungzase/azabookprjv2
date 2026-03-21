package common.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
	
	private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:tcp://localhost/~/test";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName(DRIVER);
            System.out.println("H2 드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("H2 드라이버 로드 실패");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

