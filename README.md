# azabookprj

Legacy Spring MVC book project.

## Environment

- `web.xml` + `dispatcher-servlet.xml`
- JSP view resolver
- Java 8
- H2 database

## Before Run

1. Start H2 and make sure `jdbc:h2:tcp://localhost/~/test` is available.
2. Create the `books` table and test data.
3. Run `src/main/resources/sql/member-schema.sql` to create the `members` table.
4. Import the project into Eclipse as a Maven/Spring MVC project.
5. Run on Tomcat.

## Sample Login

- `admin / 1234`
- `user1 / 1234`

## Notes

- Eclipse personal files and build outputs are excluded by `.gitignore`.
- Main branch is intended for team sharing.
