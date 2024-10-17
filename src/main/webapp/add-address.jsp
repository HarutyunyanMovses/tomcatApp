<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Address</title>
    <link rel="stylesheet" href="styles/addAddres.css">
</head>
<body>

<h2>Add Address</h2>

<form action="/add-address" method="post">
    <label for="country">Country:</label>
    <input type="text" id="country" name="country" required>

    <label for="city">City:</label>
    <input type="text" id="city" name="city" required>

    <label for="street">Street:</label>
    <input type="text" id="street" name="street" required>

    <input type="submit" value="Submit">
</form>

</body>
</html>
