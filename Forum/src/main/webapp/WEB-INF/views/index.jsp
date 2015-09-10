<%@page import="org.springframework.ui.Model"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="test" uri="http://java.sun.com/jstl/core_rt" %>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<spring:url value="/resources/css/Forum.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Forum</title>

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
						<span style="color: white; margin-top: auto;">${messageSignUp}</span>
						<a href="/forum/login">Login</a>
						<a href="/forum/signup"> Sign Up</a>
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

	<div class="container">
		<h3 >${message}</h3>
		<table>
			<tbody>
				<tr>
					<td><form:form method="POST" action="/forum/question"
							modelAttribute="question">
							<form:input path="question" type="text"
								placeholder="Add Question" required="required" maxlength="255"/>
							<form:input type="submit" value="Add Question" path="" />

						</form:form></td>
				</tr>
				<tr>
					<td><form:form method="GET" action="/forum/searchQuestion"
							modelAttribute="question">
							<form:input path="question" type="text"
								placeholder="Search Question" required="required" maxlength="50"/>
							<form:input type="submit" value="Search Question" path="" />
						</form:form>
					<td>
				</tr>
				<tr>
					<td><form:form method="GET" action="/forum/question"
							modelAttribute="question">
							<form:input type="submit" value="Show All Questions" path="" />
						</form:form>
					<td>
				</tr>
			</tbody>
		</table>
		<div class="clear"></div>

		<div class="banner">
			<div>
				<table>
					<thead>
						<tr>
							<th>Question And Answer</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listOfQuestionAnswer}" var="entry">
							<tr>
								<td><a href="/forum/question/${entry.questionId}/answers">Question
										: ${entry.question} </a> <br>
									<div>
										<b>Asked By:</b> ${entry.user.username}
									</div> 															
										
									<test:if test="${userName=='admin' || userName==entry.user.username}">
										<form:form method="DELETE"
														action="/forum/question/${entry.questionId}">
														<input type="submit" value="Delete Question">
										</form:form>
									</test:if>
									<hr>
										<br> 
										<b> Answers </b>: <br> 
										
										<c:forEach items="${entry.answers}" var="answer">${answer.answer}<br><br>
										<div>
											<b>Posted By:</b> ${answer.user.username}
										</div>
	
										<test:if test="${userName=='admin' || userName==answer.user.username}">
											<form:form
												action="/forum/question/${entry.questionId}/answers/${answer.answerId}"
												method="DELETE">
												<input type="submit" value="Delete Answer"></input>
											</form:form>
	
										</test:if>
									<hr>
									
									</c:forEach> <form:form method="POST"
										action="/forum/question/${entry.questionId}/answers/"
										commandName="answers">
										<form:textarea cols="50" rows="5" path="answer" type="text"
											placeholder="Enter Your Answer" required="required" maxlength="1000"/>
										<form:input type="submit" value="Add Answer" path="" />
										<br>
									</form:form>
									<hr>
									<hr>
									</td>
								</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>