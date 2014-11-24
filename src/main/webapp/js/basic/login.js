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
		var data = {"head":{"rtnCode":"888888","rtnMsg":"success"},"body":{"cropFileName":"1416792942542.JPG","fileName":"DSCF0736.JPG","filePath":"upload\\\\cookbook\\\\2014-11-24\\\\","saveDiskPath":"2014-11-24\\\\1416792942542.JPG"}}
		alert(data.body.fileName);
		window.open("home.do",'_self');
	});
});
