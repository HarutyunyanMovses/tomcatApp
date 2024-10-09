<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Verification</title>
    <link rel="stylesheet" type="text/css" href="styles/verify.css"> <!-- Link to your CSS file -->
</head>
<body>
<div class="container">
    <h1>Verify Your Email</h1>
    <p>A verification code has been sent to <strong><%=request.getSession().getAttribute("email")%></strong>.</p>
    <form action="/verify" method="post">
        <label for="code">Enter the verification code:</label>
        <input type="text" id="code" name="code" required />
        <input type="hidden" name="email" value="${request.getSession().getAttribute("email")}" />
        <h4><%= request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage")%></h4>
        <button type="submit">Verify</button>
    </form>
</div>
</body>
</html>
