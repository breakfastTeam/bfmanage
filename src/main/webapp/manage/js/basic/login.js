$(function(){
	/**var map = new Map();
	map.put("deviceIds", "ccc");
    var reqData = iReqMsg.getReqMsg(map);//生成请求报文
	$.ajax({
            url:"BEL001.do",
			type:"POST",
			data:reqData,
			dataType:"json",
			contentType:"text/plain",
			success:function(data){	
				alert(data);
			}
    });**/

	/**
	 * 用户登录事件处理并在登录前做好校验
	 * rules是校验规则
	 * messages是校验规则下的提示信息
	 * submitHandler是校验通过后的处理函数
	 */
	$("#submitLogin").click(function(){
		window.open("home.do",'_self');
	});
});
