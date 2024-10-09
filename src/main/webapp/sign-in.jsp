<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="styles/index.css">
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="login" method="POST">
        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="error-message" id="error-message">
            <%= request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage")%>
        </div>
        <button type="submit">Login</button>
    </form>
    <p style="text-align:center; margin-top: 20px;">
        Don't have an account? <a href="sign-up.jsp">Register here</a>
    </p>
    <p style="text-align:center; margin-top: 20px;">
        <a href="send-reset-token.jsp">Forgot Password</a>
    </p>
</div>
</body>
</html>