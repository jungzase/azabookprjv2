Legacy Spring MVC 기반의 도서 관리 프로젝트입니다.

## 개발 환경

- `web.xml` + `dispatcher-servlet.xml` 기반 설정
- JSP View Resolver
- Java 8
- H2 Database

## 실행 전 확인 사항

1. H2를 실행한 뒤 `jdbc:h2:tcp://localhost/~/test` 에 정상적으로 접속되는지 확인합니다.
2. `books` 테이블과 테스트용 데이터를 생성합니다.
3. Eclipse에서 해당 프로젝트를 Maven/Spring MVC 프로젝트로 가져옵니다.
4. Tomcat 서버에서 프로젝트를 실행합니다.

## 참고

- Eclipse 관련 개인 설정 파일 및 빌드 결과물은 `.gitignore`에 포함되어 제외됩니다.
- `main` 브랜치는 팀원 간 공유를 위한 기본 브랜치입니다.