<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="by.hodyko.www.bean.User"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>ADD NEWS PAGE</title>
<link rel="stylesheet" href="resources/css/property.css" type="text/css">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />


<fmt:message bundle="${loc}" key="local.headline.button.name.register" var="register_button" />
<fmt:message bundle="${loc}" key="local.headline.button.name.addnews" var="add_news_button" />
<fmt:message bundle="${loc}" key="local.headline.button.name.login" var="login_button" />
<fmt:message bundle="${loc}" key="local.add.news.h.header" var="addtable_head" />
<fmt:message bundle="${loc}" key="local.add.news.textarea.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.add.news.textarea.brief" var="news_brief" />
<fmt:message bundle="${loc}" key="local.add.news.textarea.fulltext" var="news_fulltext" />
<fmt:message bundle="${loc}" key="local.add.news.button.send" var="news_send" />

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
				String userRole = (((User) request.getSession(false).getAttribute("user")).getRole()).toString();
				request.setAttribute("userRole", userRole);
				%>

				<c:if test="${userRole == 'ADMIN'}">
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
				<input type="hidden" name="commandToController" value="2	AUTHORIZATION_PAGE" />
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
	<div class="registrationDiv" style="width: 400px; height:510px">
		<h2 style="font-weight: 600;">${addtable_head}</h2>
		<form action="Controller" method="post" enctype="multipart/form-data">
			<textarea name="title"  placeholder=${news_title} style="size: 80px; width: 350px;" ></textarea>
			<br />
			<br />
			<textarea name="brief"  placeholder=${news_brief} style="size: 80px; width: 350px; height:60px" ></textarea>
			<br />
			<br />
			<textarea name="full_text"  placeholder=${news_fulltext} style="size: 80px; width: 350px; height:200px" ></textarea>
			<br />
			<br />
			<!--<label style="color: white;"> 
				Load Image <input type="file" name="file" style="visibility: hidden;" multiple accept="image/*" />
			</label> 
			-->
			<input type="hidden" name="commandToController" value="ADD_NEWS" />
			
			<button>${news_send}</button>
		</form>
	</div>


</body>
</html>