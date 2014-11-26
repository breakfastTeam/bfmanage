var BelOrderNow = function() {
	return {
		//主模块初始化
		init: function() {
			displayCartInfo();
			initUserInfo();

			$("#byNowButton").click(function(){
				var mobilePhone = $("#phone").val();
				if(mobilePhone == "" || mobilePhone == null || mobilePhone == undefined){
					iDialog.iTip("请输入手机号");
					return false;
				}
				var isPhone = testMobilePhone(mobilePhone);
				if(!isPhone){
					iDialog.iTip("手机号格式不正确");
					return false;
				}
				var area = $("#area option:selected").val();
				var sendTime = $("#sendTime option:selected").val();
				if(area == "-1" || area == -1){
					iDialog.iTip("请选择送餐区域");
					return false;
				}
				if(sendTime == "-1" || sendTime == -1){
					iDialog.iTip("请选择送餐时间");
					return false;
				}
				$("#iForm").validate({
					rules: {
						userName: {
							required: true
						},
						phone:{
							required: true
						},
						address:{
							required: true
						}
					},
					messages: {
						userName:"请输入收货人姓名",
						phone:"请输入手机号",
						address:"请填写您的收获地址"
					},
					submitHandler: function(form) {
						var customerId = sessionStorage.getItem("userId");
						var consigneeName = $("#userName").val();
						var city = $("#city option:selected").text();
						var area = $("#area option:selected").text();
						var consigneeAddr = city+""+area+$("#address").val();
						var consigneeMobile = $("#phone").val();
						var orderPrice = $("#sumMoney").text();
						var	remark = $("#remark").val();

						var map = new Map();
						map.put("customerId", customerId);
						map.put("consigneeName", consigneeName);
						map.put("consigneeAddr", consigneeAddr);
						map.put("consigneeMobile", consigneeMobile);
						map.put("orderPrice", orderPrice);
						map.put("exccreaditCount", "0");
						map.put("remark", remark);
						var orderDetails = new Array();
						var shopCartInfo = localStorage.getItem("shopCart");
						var shopCartInfos = $.parseJSON('{"shopCartInfos":['+shopCartInfo+']}').shopCartInfos;
						for(var i = 0; i < shopCartInfos.length; i++) {
							var orderDetail = new Map();
							orderDetail.put("foodId", shopCartInfos[i].foodId);
							orderDetail.put("foodCount", shopCartInfos[i].count);
							orderDetails.push(orderDetail);
						}
						map.put("orderDetail", orderDetails);

						var reqData = iReqMsg.getReqMsg(map);
						$.ajax({
							url: "saveOrder.do",
							type: "POST",
							data: reqData,
							dataType: "json",
							contentType: "text/plain",
							success: function (data) {
								data = jQuery.parseJSON(data);
								var rtnCode = data.head.rtnCode;
								if(rtnCode == "888888"){
									iDialog.iTip("订单成功，等待享受美食吧！");
									localStorage.removeItem("shopCart");
									loadUrl("foodList.do");
								}else{
									iDialog.iTip("提交失败，请稍后重试！");
								}
							}
						});
					}
				});
			});

			$("#shopCartList").find("a").click(function(){
				var obj = $(this);
				var foodId = $(obj).attr("id");
				iDialog.iConfirm("您确定要移除吗？",{callBackHandler:function(){
					removeDataFromShopCart(foodId);
					removeFromShopCart(obj)
					}
				});
			});
			function removeFromShopCart(obj){
				var parent = $(obj).parent().parent().parent().parent();
				$(parent).fadeOut(function(){
					$(obj).remove();
				})
			}
			function removeDataFromShopCart(foodId){
				var shopCartInfo = localStorage.getItem("shopCart");
				var shopCartInfos = $.parseJSON('{"shopCartInfos":['+shopCartInfo+']}').shopCartInfos;
				var isDelete = false;
				for(var i = 0; i < shopCartInfos.length; i++){
					if(foodId == shopCartInfos[i].foodId){
						shopCartInfos[i] = null;
						isDelete = true;
						break;
					}
				}
				if(isDelete){
					shopCartInfo = JSON.stringify(shopCartInfos).replace("[","").replace("]","");
				}
				shopCartInfo = shopCartInfo.substring(0, shopCartInfo.length);
				if(shopCartInfo == "" || shopCartInfo == "null"){
					localStorage.removeItem("shopCart");
					loadUrl("foodList.do");
				}else{
					localStorage.setItem("shopCart", shopCartInfo);
				}
			}
			function initUserInfo(){
				$("#userName").val(sessionStorage.getItem("userName"));
				$("#phone").val(sessionStorage.getItem("phone"));
			}
			/**
			 * 校验手机号是否合法
			 * **/
			function testMobilePhone(value){
				var length = value.length;
				var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
				return (length == 11 && mobile.test(value));
			}

			/**
			 * 将购物车内的商品站展现出来
			 * **/
			function displayCartInfo(){
				var sumMoney = 0;
				var shopCartInfo = localStorage.getItem("shopCart");
				var shopCartInfos = $.parseJSON('{"shopCartInfos":['+shopCartInfo+']}').shopCartInfos;
				if(shopCartInfos == null || shopCartInfos.length<=0 || shopCartInfos[0] == null){
					iDialog.iTip("购物车内空荡荡的");
					setTimeout(function(){
						loadUrl("foodList.do");
					},2000);
				}else{
					for(var i = 0; i < shopCartInfos.length; i++){
						var info = '<div class="row border-bottom">'+
							'<div class="col-lg-24">'+
							'<div class="form-group">'+
							'<label class="col-lg-6 control-label">'+
							shopCartInfos[i].foodName+
							'</label>'+
							'<label class="col-lg-6 control-label">'+
							shopCartInfos[i].count+
							'份</label>'+
							'<label class="col-lg-6 control-label">'+
							'<a style="font-weight: bold">￥'+parseInt(shopCartInfos[i].count)*parseFloat(shopCartInfos[i].price)+'</a>'+
							'</label>'+
							'<label class="col-lg-6 control-label">'+
							'<a href="javascript:void(0)" id="'+shopCartInfos[i].foodId+'" class="btn btn-danger btn-small">移除</a>'+
							'</label>'+
							'</div>'+
							'</div>'+
							'</div>';
						$("#shopCartList").append(info);
						sumMoney = sumMoney + parseInt(shopCartInfos[i].count)*parseFloat(shopCartInfos[i].price);
					}
					$("#sumMoney").text(sumMoney);
				}
			}
		}
	};
} ();