<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div align="right">
		<a href="/forum">Home</a>
	</div>

	<c:choose>
		<c:when test="${userName=='anonymousUser'}">
			<a href="/forum/login">Login</a>
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

	<table border="1" align="center" style="width: 50%">
		<thead>
			<tr>
				<th>Question</th>

			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty questions}">
					<tr>
						<td><b>Sorry, No Question Found for query :
								${userQuestion}</b></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="question" items="${questions}">
						<tr>
							<td>
							<a href="/forum/question/${question.questionId}/answers">${question.question}</a>
							<div><b>Asked By</b>: ${question.user.username}</div>
							<form:form method="DELETE" action="/forum/question/${question.questionId}">
							<input type="submit" value="Delete Question">
						</form:form>
							</td>
						</tr>
					</c:forEach>

				</c:otherwise>
			</c:choose>

			<tr>

			</tr>
		</tbody>
	</table>

</body>
</html>