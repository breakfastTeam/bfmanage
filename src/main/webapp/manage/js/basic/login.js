$(function(){


	/**
	 * 用户登录事件处理并在登录前做好校验
	 * rules是校验规则
	 * messages是校验规则下的提示信息
	 * submitHandler是校验通过后的处理函数
	 */
	$("#submitLogin").click(function(){
		var btnObj = this;
		$("#loginForm").validate({
			rules: {
				loginName: {
					required:true
				},
				password: {
					required: true
				}
			},
			messages: {
				loginName: {
					required:"请输入登录账号"
				},
				password:{
					required:"请输入密码"
				}
			},
			submitHandler: function(form) {
				var loginNameVal = $(loginName).val();
				var passwordVal = $(password).val();
				$.ajax({
					url:"basic/login.do",
					type:"POST",
					data:$("#loginForm").serialize(),
					dataType:"json",
					beforeSend:function(){
						iDialog.iLoading("yes",btnObj);
					},
					success:function(data){
						iDialog.iLoading("no",btnObj);
						if(data.head.rtnCode == "888888"){
							window.open("home.do",'_self');
						}else{
							iDialog.iAlert(data.head.rtnMsg);
						}
					}
				});
			}
		});
	});
});
