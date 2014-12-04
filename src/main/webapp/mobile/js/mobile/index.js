$(function(){
	var windowHeight = $(window).height();
	var Obj = $("#showMenu");
	Obj.click(function(){
		$("#menuContainer").addClass("st-menu-open");
	});
	Obj.hover(function(){
 		$('body').unbind('mousedown');
	}, function(){
 		$('body').bind('mousedown', function(){
 			$("#menuContainer").removeClass("st-menu-open");
 		});
	});

    loadUrl("foodList.do");
    //打开菜单页面
    $("#food").click(function(){
    	 loadUrl("foodList.do");
    });
    //打开购物车列表
    $("#shopCart").click(function(){
		var phone = localStorage.getItem("phone");
		//if(phone != null && phone != "" && phone != undefined){
			loadUrl("toBuyNow.do");
		//}else{
		//	loadUrl("toLogin.do");
		//}
    });
//打开购物车列表
	$("#myOrders").click(function(){
		loadUrl("toMyOrders.do");
	});

	getUserInfo();

	function getUserInfo(){
		var phone = localStorage.getItem("phone");
		var userId = localStorage.getItem("userId");

		if(phone != "" && phone != null && phone != undefined ){
			if(userId == "" || userId == null || userId == undefined){
				var map = new Map();
				map.put("weixin","805404898");
				var reqData = iReqMsg.getReqMsg(map);
				$.ajax({
					url: "getUserInfo.do",
					type: "POST",
					data: reqData,
					dataType: "json",
					contentType: "text/plain",
					success: function (data) {
						if(data.head.rtnCode == "888888"){
							var userId = data.body.userId;
							var userName = data.body.userName;
							var phone = data.body.phone;
							localStorage.setItem("userId", userId);
						}
					}
				})
			}

		}
	}
});

function loadUrl(url){
	var obj = $("#content");
	iDialog.iLoading("SHOW",obj);
	$("#content").empty();
	$("#content").load(url,"", function(){
		iDialog.iLoading("HIDE",obj);
	});
}