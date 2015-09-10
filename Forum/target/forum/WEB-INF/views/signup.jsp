<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/login.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>SignUp</title>

<script type="text/javascript">
        function blockSpecialChar(e) {
            var k = e.keyCode;
            return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8   || (k >= 48 && k <= 57));
        }
    </script>
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

			<form:form method="POST" action="/forum/signupprocess"
				modelAttribute="users">

				<h3>Forum Sign Up</h3>
				<span style="color: red">${message}</span>
				<table>
					<tr>
						<td>Username</td>
						<td><form:input path="username" type="text"
								placeholder="User" required="required" onkeypress="return blockSpecialChar(event)" maxlength="10"/></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><form:input path="password" type="password"
								placeholder="Password" required="required" maxlength="10"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><form:input type="submit" value=" Sign Up " path=""  /></td>
					</tr>
				</table>
			</form:form>

		</div>
	</div>

</body>
</html>