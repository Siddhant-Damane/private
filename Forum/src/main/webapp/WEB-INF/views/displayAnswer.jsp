
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Answers</title>


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
				<th>${question.question}
					<div>
						<b>Asked By</b>: ${question.user.username}
					</div>
					 <a	href="/forum/deleteQuestion/${question.questionId}"><button
							name="delete">Delete Question</button></a>
				</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="answer" items="${question.answers}">
				<tr>
					<td>${answer.answer}<br> 
					<div><b>Posted By</b>: ${answer.user.username}</div>
					<a
						href="/forum/deleteAnswer/${answer.answerId}">
							<button name="delete">Delete Answer</button>
					</a> <a href="/forum/updateAnswer/${answer.answerId}"><button>Update</button></a>

					</td>

				</tr>
			</c:forEach>
			<tr>
				<td><form:form method="POST"
						action="/forum/question/${question.questionId}/answers/addAnswer"
						commandName="answer">
						<form:input path="answer" type="text"
							placeholder="Enter Your Answer" required="required" />
						<form:input type="submit" value="Add Answer" path="" />
					</form:form></td>

			</tr>
		</tbody>
	</table>
</body>
</html>