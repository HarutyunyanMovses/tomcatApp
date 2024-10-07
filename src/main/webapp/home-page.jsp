<%@ page import="com.Tomcat.model.User" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome</title>
  <link rel="stylesheet" href="styles/welcom.css"> <!-- Link to your CSS file -->
</head>
<body>
<%
  User user = (User) session.getAttribute("user") == null ? new User():(User) session.getAttribute("user");
%>
<div class="container">
  <h1>Welcome</h1>
  <p class="greeting">Name is : <%=user.getName()%></p>
  <p class="greeting">Surname is : <%=user.getSurname()%></p>
  <p class="greeting">Age is : <%=LocalDate.now().getYear()-user.getYear()%></p>
  <a href="/" class="logout-button">Logout</a>
</div>
</body>
</html>

