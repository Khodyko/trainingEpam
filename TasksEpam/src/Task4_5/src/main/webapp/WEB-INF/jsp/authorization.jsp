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
<title>AUTHORIZATION_PAGE</title>
<link rel="stylesheet" href="resources/css/registration.css" type="text/css">
<link rel="stylesheet" href="resources/css/header.css" type="text/css">
</head>
<body>
	<%@include file="header.jsp"%>

	<div class="registrationDiv">
		<div>
			<img src="resources/pictures/circle_logo.png" style="margin: 20px; margin-bottom: 10px; height: 90px; width: 90px;" />
		</div>
		<h1 class="bluetxtcolor">Войти на сайт</h1>
		<div style="display: flex; flex-direction: column;">
			<font style="text-size: 5;">Еще нет аккаунта?</font>
			<a href="Controller?commandToController=GO_TO_REGISTRATION_1_PAGE">Страница регистрации</a>
			<br>
		</div>
		<form action="Controller" method="post" style="color: white">
							<input type="hidden" name="commandToController" value="AUTHORIZATION" />
				
				<input type="text" name="email" value="" placeholder="Email" />
				<input type="password" name="password" value="" placeholder="Пароль" />
				

				<input type="submit" value="Войти" class="bluebutton" style="width: 220px; height: 40px;" />
				<br />
		</form>
		

		</font>
	</div>

</body>
</html>