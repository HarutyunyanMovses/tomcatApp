<%@ page import="com.Tomcat.model.enttis.User" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.Tomcat.model.enttis.Address" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link rel="stylesheet" href="styles/welcom.css">
</head>
<body>
<%
    User user = (User) session.getAttribute("user") == null ? new User() : (User) session.getAttribute("user");
    Address address = (Address) session.getAttribute("address");
%>

<!-- Navigation Bar -->
<nav class="navbar">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/add-address.jsp">Add Address</a></li>
        <li><a href="/Update profile">Update Profile</a></li>
        <li><a href="/sign-in.jsp">Logout</a></li>
    </ul>
</nav>

<div class="container">
    <h1>Welcome</h1>
    <p class="greeting">Name: <%= user.getName() %></p>
    <p class="greeting">Surname: <%= user.getSurname() %></p>
    <p class="greeting">Age: <%= LocalDate.now().getYear() - user.getYear() %></p>

    <% if (address != null) { %>
    <p class="greeting">Country: <%= address.getCountry() %></p>
    <p class="greeting">City: <%= address.getCity() %></p>
    <p class="greeting">Street: <%= address.getStreet() %></p>
    <% } else { %>
    <p class="greeting">No address available.</p>
    <% } %>
</div>

</body>
</html>
