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
			<br /> Please enter your username and password to Signup ! <br />
			<table>
				<form:form method="POST" action="/forum/signupprocess"
					modelAttribute="user">
					<tr>
						<td><form:label path="">User Name</form:label></td>
						<td><form:input path="username" type="text"
								placeholder="User" required="required" /></td>
					</tr>
					<tr>
		
						<td><form:label path="">Password</form:label></td>
						<td><form:input path="password" type="password" placeholder="Password"
							required="required" />
						</td>
					</tr>
					<tr>
						<td><form:input type="submit" value=" Sign Up " path="" /></td>
					</tr>
				</form:form>
			</table>
		</div>
	</div>

</body>
</html>