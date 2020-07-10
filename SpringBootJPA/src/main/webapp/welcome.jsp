<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Spring Boot with JPA</title>
</head>
<body>
<form action="addMember">

ID:<input type="text" name="id"><br>
Name:<input type="text" name="name"><br>
Country:<input type="text" name="country"><br>
<input type="submit"><br>
</form>

<form action="getMemberById">
ID:<input type="text" name="id"><br>
<input type="submit"><br>
</form>

</body>
</html>