<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="by.hodyko.www.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unknown Page</title>
<link rel="stylesheet" href="resources/css/property.css" type="text/css">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.unknownpage.field.smthwrong" var="smth_wrong_title" />
<fmt:message bundle="${loc}" key="local.unknownpage.button.tomainpage" var="to_main_page_btn" />
<fmt:message bundle="${loc}" key="local.headline.button.name.register" var="register_button" />
<fmt:message bundle="${loc}" key="local.headline.button.name.addnews" var="add_news_button" />
<fmt:message bundle="${loc}" key="local.headline.button.name.login" var="login_button" />
</head>
<body>
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
	<div style="justify-content: center;">
		<div style="width: 50%; margin: 0 auto; text-align: center;">
			<img alt="image" src="resources/pictures/smthWrongImg.png" />
			<br />
			<h1>${smth_wrong_title}</h1>
			
			<form action="Controller" method="post">
				<input type="hidden" name="commandToController" value="GO_TO_MAIN_PAGE" />
				<button>${to_main_page_btn}</button>
			</form>



		</div>

	</div>

</body>
</html>