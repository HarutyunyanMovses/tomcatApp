<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Reset Password</title>
  <link rel="stylesheet" type="text/css" href="styles/forgotStyle.css">
</head>
<body>
<h2>Reset Your Password</h2>
<form action="/forgot" method="post">
  <label for="token">Reset Token:</label>
  <input type="text" id="token" name="token" required>

  <label for="newPassword">New Password:</label>
  <input type="password" id="newPassword" name="newPassword" required>

  <label for="confirmPassword">Confirm Password:</label>
  <input type="password" id="confirmPassword" name="confirmPassword" required>

  <input type="submit" value="Reset Password">
  <h4><%= request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage")%></h4>
</form>
</body>
</html>
