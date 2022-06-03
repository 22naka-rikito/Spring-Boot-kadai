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
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<div class="header">
		<h1 class="site_logo">
			<a href="menu.html">商品管理システム</a>
		</h1>
		<div class="user">
			<c:if test="${not empty user}">
				<p class="user_name">${user.name}</p>
			</c:if>
			<form class="logout_form" action="servlet" method="get">
				<button class="logout_btn" type="submit" name="btn" value="logout">
					<img src="images/ドアアイコン.png">ログアウト
				</button>
			</form>
		</div>
	</div>

	<hr>

	<div class="insert">
		<div class="form_body">
			<c:if test="${not empty errorMsg[3]}">
				<p class="error">${errorMsg[3]}</p>
			</c:if>
			<form:form action="update" modelAttribute="update">
				<fieldset class="label-130">
					<form:input path="id" class="base-text" type="hidden" />
					<div>
						<label>商品ID</label>
						<form:input path="productId" class="base-text" />
						<form:errors path="productId" cssStyle="color: red" />
					</div>
					<div>
						<label>商品名</label>
						<form:input path="productName" class="base-text" />
						<form:errors path="productName" cssStyle="color: red" />
					</div>
					<div>
						<label>単価</label>
						<form:input path="price" class="base-text" />
						<form:errors path="price" cssStyle="color: red" />
					</div>
					<div>
						<label>カテゴリ</label>
						<form:select path="categoryId">
							<form:options items="${categoryList}" itemLabel="name"
								itemValue="id" />
						</form:select>
					</div>
					<div>
						<label>商品説明</label>
						<form:textarea path="description" class="base-text" />
					</div>
				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" class="basic_btn">更新</button>
					<form:button name="back" class="cancel_btn">
						<fmt:message key="form.lbl.backMenu" />
					</form:button>
				</div>
				<div id="modal">
					<p class="modal_message">更新しますか？</p>
					<div class="btns">
						<form:button name="update" class="basic_btn">
							<fmt:message key="form.lbl.update" />
						</form:button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>