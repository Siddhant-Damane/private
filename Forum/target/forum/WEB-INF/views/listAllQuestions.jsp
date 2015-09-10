<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="test" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/Forum.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Questions</title>
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
	<div class="container">
	<div class="banner">
		<table >
			<thead>
				<tr>
					<th>Question</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="question" items="${questions}">
					<tr>
						<td><a href="/forum/question/${question.questionId}/answers">${question.question}</a>
							<div>
								<b>Asked By</b>: ${question.user.username}
							</div>
							
							<test:if test="${userName=='admin' || userName==question.user.username}">
								<form:form method="DELETE" action="/forum/question/${question.questionId}">
									<input type="submit" value="Delete Question">
								</form:form>
							</test:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		</div>
	</body>
</html>