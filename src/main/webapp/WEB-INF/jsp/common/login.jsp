<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/common/taglibs.jsp"%>
<%@ include file="/manage/common/meta.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="Mosaddek">
	<link rel="shortcut icon" href="${ctx}img/head.png">
	<title><spring:message code="TITLE"/></title>
</head>
<body class="login-body">
<span style="float: right;font-size: 15pt;margin-right: 10px">
	</span>
<div class="container">
	<div class="form-signin">
		<h2 class="form-signin-heading"><spring:message code="TITLE"/></h2>
		<div class="login-wrap">
			<div class="form-group">
				<input type="text" class="form-control small" id="loginName" name = "loginName" placeholder="<spring:message code='LOGIN_ACCOUNT'/>"  minlength="2" autofocus>
				<input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code='PASSWORD'/>" >
				<input type = "file" />
				<button class="btn btn-lg btn-login btn-block" id = "submitLogin" type="submit" ><spring:message code="LOGIN"/></button>
			</div>
		</div>
	</div>
</div>
<%@ include file="/manage/common/js.jsp"%>
<script type="text/javascript" src = "${ctx}/manage/js/basic/login.js"></script>
</body>
</html>
