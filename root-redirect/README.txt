Place this index.jsp into your Tomcat ROOT web application.

Expected behavior:
- http://localhost:8888/ redirects to /azabook
- http://localhost:8888/azabook serves this project

Typical Eclipse/Tomcat options:
1. Open the Tomcat server configuration or deployment folder.
2. Put root-redirect/index.jsp into the ROOT application.
3. Keep this project's context root as azabook.
4. Clean and restart the server.
