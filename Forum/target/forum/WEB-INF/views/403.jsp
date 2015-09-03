<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Access Denied</title>
</head>
<body>
	<div align="center">
		<br /> <br /> <br />
		<div align="right">
			<a href="/forum">Home</a>
		</div>
		<h1>
			Access Denied for User : <span style="color: red;">${userName}</span>
		</h1>
		<c:url var="logoutUrl" value="/j_spring_security_logout" />
		<form action="${logoutUrl}" method="post">
			<input type="submit" value="Log out" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>

</body>
</html>