var BelMyOrder = function() {
	return {
		//主模块初始化
		init: function() {
			displayOrderInfo();
			$("#returnButton").click(function(){
				loadUrl("foodList.do");
			});

			/**
			 * 将我的订单信息展现出来
			 * **/
			function displayOrderInfo(){
				var map = new Map();
				var userId = sessionStorage.getItem("userId");
				map.put("userId", userId);
				var reqData = iReqMsg.getReqMsg(map);
				$.ajax({
					url: "getMyOrder.do",
					type: "POST",
					data: reqData,
					dataType: "json",
					contentType: "text/plain",
					success: function (data) {
						var results = data.body.results;
						if(results.length<=0){
							$("#iForm").append("<p style='text-align: center'>暂无订单信息</p>");
							return false;
						}
						for(var i = 0; i < results.length; i++){
							var info = '<div class="row border-bottom">'+
								         '<label class="col-lg-16 control-label">'+
											results[i].createTime+
										 '</label>'+
										 '<label class="col-lg-8 control-label">'+
										 '总计：<a id = "sumMoney" style = "font-weight: bold;font-size: large;color: red">'+results[i].money+'</a>元'+
										 '</label>'+
								         '</div>'+
										'<div id="orderList">';
							var foodInfo = "";
							for(var j = 0; j<results[i].foods.length; j++){
								foodInfo = foodInfo +'<div class="row border-bottom">'+
									'<div class="col-lg-24">'+
									'<div class="form-group">'+
									'<label class="col-lg-6 control-label">'+
									results[i].foods[j].foodName+
									'</label>'+
									'<label class="col-lg-6 control-label">'+
										results[i].foods[j].foodNum+
									'份</label>'+
									'<label class="col-lg-6 control-label">'+
									'<a style="font-weight: bold">￥'+results[i].foods[j].price+'</a>'+
									'</label>'+
									'</div>'+
									'</div>'+
									'</div>';
							}
							info = info + foodInfo + "</div>";
							$("#iForm").append(info);
						}
					}
				});

			}
		}
	};
} ();