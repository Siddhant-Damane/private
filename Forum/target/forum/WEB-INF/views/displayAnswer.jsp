
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="test" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	
<!-- <script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script> -->
	
<script type="text/javascript">	
$(document).ready(function(event){
		$(".editButton").on("click", function(){
		$(this).siblings(".updateForm").show();
		$(".editButton").hide();
	})
}		
);

$(document).ready(function(){
	$(".updateForm").hide();	
});

</script>

<spring:url value="/resources/css/Forum.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Answers</title>
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
		
	<div class="container">
		<div class="wrapper">
			<h3>${message}</h3>
		</div>
		<div class="banner">
			<table>
				<thead>
					<tr>
						<th>${question.question}
							<div>
								<b>Asked By</b>: ${question.user.username}
							</div> 
							<test:if test="${userName=='admin' || userName==question.user.username}">
								<form:form method="DELETE"
									action="/forum/question/${question.questionId}">
									<input type="submit" value="Delete Question">
								</form:form>
							</test:if>
							<hr>
							<hr>
						</th>
					</tr>
				</thead>
				<tbody>
				
				<tr>
				<td>Answers :</td>
				</tr>
					<c:forEach var="answer" items="${question.answers}">
						<tr>
							<td>
							<div >${answer.answer}</div><br>
							
								
									<form:form class="updateForm" method="PUT"
										action="/forum/question/${question.questionId}/answers/${answer.answerId}"
										modelAttribute="answers">
										<form:input path="answer" type="text" 
											value="${answer.answer}" required="required" maxlength="1000" />
										<input id="updateButton" type="submit" value="Update Answer"  />
									</form:form>
								
							
							<test:if test="${userName=='admin' || userName==answer.user.username}">
								<input class="editButton" type="button" value="Edit Answer"/>
							</test:if>
							
								
							
								<div>
									<b>Posted By</b>: ${answer.user.username}
								</div>
								
									<form:form class="updateForm" method="DELETE"
									action="/forum/question/${question.questionId}/answers/${answer.answerId}">
									<input type="submit" value="Delete Answer">
									</form:form> 
	
								
								
							<hr>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td><form:form method="POST"
								action="/forum/question/${question.questionId}/answers/"
								modelAttribute="answers">
								<form:textarea cols="50" rows="5" path="answer" type="text"
									placeholder="Enter Your Answer" required="required"  maxlength="1000"/>
								<form:input type="submit" value="Add Answer" path="" />
						</form:form>
						
							<hr>
						<hr></td>
					
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>