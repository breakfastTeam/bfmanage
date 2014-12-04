var BelLogin = function() {
	return {
		//主模块初始化
		init: function() {
			refresValidateCode();

			$("#login").click(function(){
				var myValidateCode = $("#myValidateCode").val();
				var validateCode = $("#validateCode").val();
				if(myValidateCode == validateCode){
					var phone = $("#phone").val();
					var isPhone = testMobilePhone(phone);
					if(!isPhone){
						iDialog.iTip("手机号格式不正确");
						return false;
					}else{
						var map = new Map();
						map.put("phone", phone);
						var reqData = iReqMsg.getReqMsg(map);
						$.ajax({
							url: "regist.do",
							type: "POST",
							data: reqData,
							dataType: "json",
							contentType: "text/plain",
							success: function (data) {
								console.log(data);
								var rtnCode = data.head.rtnCode;
								if(rtnCode == "888888"){
									var userId = data.body.userId;
									localStorage.setItem("userId", userId);
									localStorage.setItem("phone", phone);
									loadUrl("toBuyNow.do")
								}
							}
						});
					}
				}else{
					refresValidateCode();
					iDialog.iTip("您输入的验证码有误，注意区分大小写！");
					return;
				}
			});

			/**
			 刷新验证码图片信息
			 author: qingfeilee
			 date:2013-12-07
			 **/
			function refresValidateCode(){
				$("#loadValidateCode").show();
				$("#validateCodeImage").hide();
				$("#validateCode").val("");
				$("#myValidateCode").val("");
				$.ajax({
					url:"getValidateCodeImage.do",
					type:"POST",
					dataType:"text",
					success:function(data){
						$.ajax({
							url:"getValidateCode.do?random="+Math.random(),
							dataType:"text",
							success:function(data){
								$("#loadValidateCode").hide();
								$("#validateCodeImage").show();
								$("#validateCode").val(data);
							}
						});
						$("#validateCodeImage").attr("src","data:image/jpg;base64,"+data);
					}
				});
			}
			/**
			 * 校验手机号是否合法
			 * **/
			function testMobilePhone(value){
				var length = value.length;
				var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
				return (length == 11 && mobile.test(value));
			}
		}
	};
} ();