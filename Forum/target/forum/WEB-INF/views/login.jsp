<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>
	<div align="center">
		<br /> <br /> <br />
		<div align="right">
			<a href="/forum">Home</a>
		</div>
		<div style="border: 1px solid black; width: 300px; padding-top: 10px;">
			<br /> Please enter your username and password to login ! <br /> <span
				style="color: red">${message}</span> <br />
			<form:form method="post" action="j_spring_security_check"
				modelAttribute="users">
				<table>
					<tr>
						<td>Username:</td>
						<td><form:input path="username" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><form:input path="password" type="password"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="submit" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>

</body>
</html>