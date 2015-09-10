<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<spring:url value="/resources/css/login.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
</head>
<body>
	<header>
		<div class="container">
			<div class="toplogo">
				<a class="name" href="/forum">Home</a>
			</div>
			
		</div>
	</header>

	<div class="container">

		<div class="banner">
			<form:form method="post" action="j_spring_security_check"
				modelAttribute="users">

				<h3>Forum Login</h3>

				<span style="color: red">${message}</span>

				<table>
					<tr>
						<td>Username:</td>
						<td><form:input path="username" required="required" maxlength="10"/></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><form:input path="password" type="password"
								required="required" maxlength="10"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Submit" /></td>
					</tr>
				</table>
			</form:form>

			<span>New User ?</span> <a href="/forum/signup">Signup</a>
		</div>
	</div>
</body>
</html>