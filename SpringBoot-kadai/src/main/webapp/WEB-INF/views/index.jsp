<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body class="login_body">
	<div class="header">
		<h1 class="site_logo">商品管理システム</h1>
	</div>

	<div class="login_form">
		<img src="./images/logo.png" class="login_logo">
		<c:if test="${not empty msg}">
			<p class="error">${msg}</p>
		</c:if>
		<form:form action="menu" modelAttribute="index" method="post">
			<fieldset>
				<div class="cp_iptxt">
					<form:input path="loginId" class="base_input" placeholder="ID" value="admin" />
					<i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
					<form:errors path="loginId" cssStyle="color: red" />
				</div>

				<div>
					<form:password path="pass" class="base_input" placeholder="PASS" value="admin"/>
					<form:errors path="pass" cssStyle="color: red" />
				</div>
			</fieldset>
			<form:button name="login" class="logout_btn" value="login">
				<fmt:message key="form.lbl.login" />
			</form:button>
		</form:form>
	</div>
</body>
</html>