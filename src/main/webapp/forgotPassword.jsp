<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
    <link rel="stylesheet" href="styles/forgotStyle.css">
    </head>
<body>
<h2>Forgot Password</h2>
<form action="/forgot" method="get">
    <label for="email">Enter your email:</label>
    <input type="email" id="email" name="email" required>
    <input type="submit" value="Send Reset Token">
    <h4><%= request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage")%></h4>
</form>
</body>
</html>
