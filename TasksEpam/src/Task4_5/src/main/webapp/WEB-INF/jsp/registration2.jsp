<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="by.mycloud_zapchast.www.entity.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>REGISTRATION_2_PAGE</title>
<link rel="stylesheet" href="resources/css/registration.css" type="text/css">
<link rel="stylesheet" href="resources/css/header.css" type="text/css">
</head>
<body>
	<%@include file="header.jsp"%>
	
	<div class="registrationDiv">
		<div>
			<img src="resources/pictures/circle_logo.png" style="margin: 20px; margin-bottom: 10px; height: 90px; width: 90px;" />
		</div>
		<h1 class="bluetxtcolor" style="margin-bottom:0px;":>Регистрация</h1>
		<h4 class="bluetxtcolor" style="margin-top:0px;">шаг 2 из 2</h4>
		<div style="display: flex; flex-direction: column;">
			<font style="text-size: 5;">Уже есть аккаунт?</font>
			<a href="Controller?commandToController=GO_TO_AUTHORIZATION_PAGE">Страница авторизации</a>
			<br>
		</div>
		<form action="Controller" method="post" style="color: white">
			<select name="sector"
				style="margin:5px; border-color: #35B9FF; border-width: 1px; border-style: solid; border-radius: 9px; width: 212px; height: 45px; color: #809EAE; padding-left: 4px;">

				<option name="sector" value="";"><a>Выберите участок</a></option>
				<c:if test="${sectorsDb != null}">
					<c:forEach var="sector" items="${sectorsDb}">
						<option>${sector}</option>
					</c:forEach>
				</c:if>
				<input type="hidden" name="depo" value=${depo}  />
				<input type="hidden" name="name" value=${name}  />
				<input type="hidden" name="second_name" value=${second_name}  />
				<input type="hidden" name="commandToController" value="REGISTRATION_NEW_USER" />
				<input type="text" name="email" value="" placeholder="Email" />
				<input type="password" name="password" value="" placeholder="Пароль" />
				<input type="password" name="password2" value="" placeholder="Повторите пароль" />
				

				<input type="submit" value="Отправить" class="bluebutton" style="width: 220px; height: 40px;" />
				<br />
		</form>
		

		</font>
	</div>

</body>
</html>