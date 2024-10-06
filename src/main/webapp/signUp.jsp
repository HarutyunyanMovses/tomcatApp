<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="styles/index.css">
</head>
<body>

<%
    String errorMess = (String) request.getAttribute("errorMess");
%>
<%!
    // Define the displayError method
    public static String displayError(String fieldName, String errorMessage) {
    if (errorMessage != null && errorMessage.startsWith(fieldName + ":")) {
    return errorMessage.split(":")[1];
    }
    return "";
    }
%>

<div class="login-container">
    <h2>Register</h2>
    <form action="register" method="POST">
        <div class="form-group">
            <label for="name">First Name</label>
            <input type="text" id="name" name="name" required>
            <span class="valid"><%= displayError("Name", errorMess) %></span>
        </div>
        <div class="form-group">
            <label for="surname">Surname</label>
            <input type="text" id="surname" name="surname" required>
            <span class="valid"><%= displayError("Surname", errorMess) %></span>
        </div>
        <div class="form-group">
            <label for="year">Year</label>
            <input type="number" id="year" name="year" required min="1">
            <span class="valid"><%= displayError("year", errorMess) %></span>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
            <span class="valid"><%= displayError("Email", errorMess) %></span>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
            <span class="valid"><%= displayError("Password", errorMess) %></span>
        </div>
        <div class="form-group">
            <label for="confirm-password">Confirm Password</label>
            <input type="password" id="confirm-password" name="confirm-password" required>
        </div>
        <button type="submit">Register</button>
        <span class="valid"><%= displayError("Exist", errorMess) %></span>
    </form>
    <p style="text-align:center; margin-top: 20px;">
        Already have an account? <a href="index.jsp">Login here</a>
    </p>
</div>

</body>
</html>
