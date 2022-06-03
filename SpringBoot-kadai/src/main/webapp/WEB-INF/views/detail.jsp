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
<title>商品詳細</title>
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

	<div class="update">
		<div class="form_body">

			<div class="img_block">
				<img src="images/マッキー.png" class="product_img"><br>
			</div>
			<form:form action="detail" modelAttribute="detail">
				<fieldset class="label-130 product_block">
					<div>
						<label>商品ID</label>
						<form:input path="productId" class="base-text" readonly="true" />
					</div>
					<div>
						<label>商品名</label>
						<form:input path="productName" class="base-text" readonly="true" />
					</div>
					<div>
						<label>単価</label>
						<form:input path="price" class="base-text" readonly="true" />
					</div>
					<div>
						<label>カテゴリ</label>
						<form:input path="categoryName" class="base-text" readonly="true" />
					</div>
					<div>
						<label>商品説明</label>
						<form:textarea path="description" class="base-text"
							style="background-color: rgb(209, 209, 209);" readonly="true" />
					</div>
				</fieldset>
				<div>
					<div class="btns">
						<c:if test="${user.role == 1}">
							<input type="button" onclick="openModal()" value="削除"
								class="basic_btn">
							<form:button name="update" class="basic_btn">
								<fmt:message key="form.lbl.edit" />
							</form:button>
						</c:if>
						<form:button name="back" class="cancel_btn">
							<fmt:message key="form.lbl.back" />
						</form:button>
					</div>
					<div id="modal">
						<p class="modal_message">削除しますか？</p>
						<div class="btns">
							<form:button name="delete" class="basic_btn">
								<fmt:message key="form.lbl.delete" />
							</form:button>
							<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
						</div>
					</div>

				</div>
			</form:form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>