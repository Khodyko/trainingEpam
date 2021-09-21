<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="by.hodyko.www.bean.User"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>REGISTRATION PAGE</title>
<link rel="stylesheet" href="resources/css/property.css" type="text/css">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />


<fmt:message bundle="${loc}" key="local.headline.button.name.register" var="register_button" />
<fmt:message bundle="${loc}" key="local.headline.button.name.addnews" var="add_news_button" />
<fmt:message bundle="${loc}" key="local.headline.button.name.login" var="login_button" />
<fmt:message bundle="${loc}" key="local.registration.text.head1" var="head1_text" />
<fmt:message bundle="${loc}" key="local.registration.text.head2" var="head2_text" />
<fmt:message bundle="${loc}" key="local.registration.link.login.page" var="link_login_page" />
<fmt:message bundle="${loc}" key="local.registration.field.login" var="field_login" />
<fmt:message bundle="${loc}" key="local.registration.field.password" var="field_password" />
<fmt:message bundle="${loc}" key="local.registration.button.send" var="send_button" />
</head>
<body>
	<div class="headline">

		<a href="Controller?commandToController=GO_TO_MAIN_PAGE" style="text-decoration: none;">
			<h1 style="margin: 20px; background-color: #cd0000">
				<span>News </span>
			</h1>
		</a>
		<div class="conteiner">

			<c:if test="${sessionScope.user != null}">
				<%
				String UserRole = (((User) request.getSession(false).getAttribute("user")).getRole()).toString();
				request.setAttribute("UserRole", UserRole);
				%>

				<c:if test="${UserRole == 'ADMIN'}">
					<form action="Controller" method="post">
						<input type="hidden" name="commandToController" value="ADD_NEWS_PAGE" />
						<button>${add_news_button}</button>
					</form>
				</c:if>
			</c:if>
			<form action="Controller" method="post">
				<input type="hidden" name="commandToController" value="REGISTRATION_PAGE" />
				<button>${register_button}</button>
			</form>
			<form action="Controller" method="post">
				<input type="hidden" name="commandToController" value="AUTHORIZATION_PAGE" />
				<button>${login_button}</button>
			</form>
		</div>
	</div>
	<div class="conteiner">
		<form action="Controller" method="post">
			<input type="hidden" name="commandToController" value="CHANGE_LOCAL" />
			<button type="submit" name="local" value="en"
				style="background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden; width: 60px; height: 60px;">
				<img src="resources/pictures/usFlag.png">
			</button>
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="commandToController" value="CHANGE_LOCAL" />
			<button type="submit" name="local" value="ru"
				style="background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden; width: 60px; height: 60px;">
				<img src="resources/pictures/ruFlag.png">
			</button>
		</form>

	</div>
	<div class="registrationDiv">
		<h2 style="font-weight: 600;">MD-JD2-76-21</h2>
		<font style="text-size: 16; font-weight: 600;">${head1_text}</font>
		<div style="display: flex; flex-direction: column;">
			<font style="text-size: 5;">${head2_text}</font>
			<form action="Controller" method="post">
				<input type="hidden" name="commandToController" value="AUTHORIZATION_PAGE" />
				<a href="http://localhost:8080/wow-project/Controller?commandToController=AUTHORIZATION_PAGE">${link_login_page}</a>
			</form>
		</div>
		<form action="Controller" method="post">
			<br />
			<br />
			<input type="hidden" name="commandToController" value="REGISTRATION_NEW_USER" />
			<br />
			<input type="text" name="login" value="" placeholder="${field_login}" />
			<br />
			<br />
			<input type="password" name="password" value="" placeholder="${field_password}" />
			<br />
			<br />
			<br />
			<input type="submit" class="redbutton1" value="${send_button}" />
			<br />
		</form>
		<font class="systemMessage"> <%
 String message = (String) request.getParameter("message");
 if (message != null) {
 	out.print(message);
 }
 %>
		</font>

	</div>

</body>
</html>