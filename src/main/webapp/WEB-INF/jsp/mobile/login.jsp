<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/common/taglibs.jsp" %>
<%@ include file="/mobile/common/meta.jsp" %>
<%@ include file="/mobile/common/js.jsp"%>
<!DOCTYPE html>
<html>
	<body class="has-js">
		<form id="iForm" method="POST" role="form"
			class="form-horizontal cmxform" novalidate="novalidate">
			<div class="row">
				<div class="col-lg-24">
					<div class="form-group">
						<label class="col-lg-6 control-label">
							手机：
						</label>
						<div class="col-lg-17">
							<input type="text" placeholder="" id="phone"
								name="phone" class="form-control" style = "margin:0px;" value="">
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-24">
					<div class="form-group">
						<label class="col-lg-6 control-label">
							验证码：
						</label>
						<div class="col-lg-11">
							<input type="text" placeholder="" id="myValidateCode"
								name="myValidateCode" class="form-control" style = "margin:0px;" value="">
						</div>

						<img id="validateCodeImage" title="点击更换" class=""
							 onclick="refresValidateCode()" src="">
						<img id="loadValidateCode" src="${ctx}/mobile/skin/default/images/input-spinner.gif">
						<input type="hidden" id="validateCode" value="">
					</div>
				</div>
			</div>
			<div class="row" style="margin: 0 auto 10px auto;">
			<a href="javascript:void(0)" id = "login" class="col-lg-24 btn btn-primary btn-large">登录</a>
		</div>
		</form>
		<script src="${ctx }/mobile/js/mobile/login.js"></script>
		<script>
           jQuery(document).ready(function() {
                BelLogin.init();
           });
        </script>
	</body>
</html>
