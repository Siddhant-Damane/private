<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/Forum.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Access Denied</title>
</head>
<body>
	<header>
		<div class="container">
			<div class="toplogo">
				<a class="name" href="/forum">Home</a>
			</div>
			<div class="home">
				<c:choose>
					<c:when test="${userName=='anonymousUser'}">
						<span>${message}</span>
						<a href="/forum/login">Login</a>
						<a href="/forum/signup">Signup</a>
					</c:when>
					<c:otherwise>
						<h2>Logged In As : ${userName}</h2>
						<c:url var="logoutUrl" value="/j_spring_security_logout" />
						<form action="${logoutUrl}" method="post">
							<input type="submit" value="Log out" /> <input type="hidden"
								name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
	<div class="wrapper"></div>
	<div class= "container" >
	<div class= "banner" >
	<hr>
	<hr>
	<h1>
		404! Page Not Found 
	</h1>
	<hr>
	<hr>
	</div>
	</div>
</body>
</html>