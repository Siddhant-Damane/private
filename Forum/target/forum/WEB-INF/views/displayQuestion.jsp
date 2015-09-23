<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question</title>
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
			<tr>
				<td><a href="/forum/question/${questionId}/answers">${question.question}
				</a><div> <b>Asked By</b>: ${question.user.username}</div>
				 <a	href="/forum/deleteQuestion/${questionId}"><button
							name="delete">Delete Question</button></a> <br></td>
			</tr>
		</tbody>
	</table>
</body>
</html>