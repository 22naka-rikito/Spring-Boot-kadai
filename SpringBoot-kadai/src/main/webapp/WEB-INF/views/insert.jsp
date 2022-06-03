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
<title>商品登録</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

	<div class="header">
		<h1 class="site_logo">
			<a href="menu.html">商品管理システム</a>
		</h1>
		<div class="user">
			<c:if test="${not empty user}">
				<p class="user_name">${user.name}さん、こんにちわ</p>
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
		<div class="discription">
			<p>
				登録情報を入力してください（<span class="required"></span>は必須です）
			</p>
		</div>

		<div class="form_body">
			<c:if test="${not empty msg}">
				<p class="error">${msg}</p>
			</c:if>
			<form:form action="menu" modelAttribute="insert" method="post">
				<fieldset class="label-130">
					<div>
						<label class="required">商品ID</label>
						<form:input path="productId" class="base-text" />
						<form:errors path="productId" cssStyle="color: red" />
					</div>
					<div>
						<label class="required">商品名</label>
						<form:input path="productName" class="base-text" />
						<form:errors path="productName" cssStyle="color: red" />
					</div>
					<div>
						<label class="required">単価</label>
						<form:input path="price" class="base-text" />
						<form:errors path="price" cssStyle="color: red" />
					</div>
					<div class="select_block">
						<label class="required">カテゴリ</label>
						<form:select path="categoryId">
							<form:options items="${categoryList}" itemLabel="name"
								itemValue="id" />
						</form:select>
					</div>
					<div>
						<label>商品説明</label>
						<form:textarea path="description" class="base-text" />
					</div>
					<!-- 					<div> -->
					<!-- 						<label>画像</label> <input type="file" name="file"> <span -->
					<!-- 							class="error">エラーメッセージ</span> -->
					<!-- 					</div> -->
				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" class="basic_btn">登録</button>
					<form:button name="back" class="cancel_btn">
						<fmt:message key="form.lbl.back" />
					</form:button>
				</div>
				<div id="modal">
					<p class="modal_message">登録しますか？</p>
					<div class="btns">
						<form:button name="insert" class="basic_btn">
							<fmt:message key="form.lbl.insert" />
						</form:button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div id="fadeLayer"></div>
	<script src="./js/commons.js"></script>
</body>
</html>