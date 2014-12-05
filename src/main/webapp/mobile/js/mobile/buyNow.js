var BelOrderNow = function() {
	return {
		//主模块初始化
		init: function() {
			displayCartInfo();
			initUserInfo();
			resetPreSendTime();
			$("#buyNowButton").click(function(){
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
				var preSendTime = $("#preSendTime option:selected").val();
				if(area == "-1" || area == -1){
					iDialog.iTip("请选择送餐区域");
					return false;
				}
				if(preSendTime == "-1" || preSendTime == -1){
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
						var phone = $("#phone").val();
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
								var rtnCode = data.head.rtnCode;
								if(rtnCode == "888888"){
									var userId = data.body.userId;
									localStorage.setItem("userId", userId);
									localStorage.setItem("phone", phone);
									buyNow();
								}
							}
						});

					}
				});
			});
			/**
			 * 下订单
			 * **/
			function buyNow(){
				var customerId = localStorage.getItem("userId");
				var consigneeName = $("#userName").val();
				var city = $("#city option:selected").text();
				var area = $("#area option:selected").text();
				var consigneeAddr = city+"-"+area+"-"+$("#address").val();
				var consigneeMobile = $("#phone").val();
				var orderPrice = $("#sumMoney").text();
				var	remark = $("#remark").val();
				var preSendTime = $("#preSendTime option:selected").val();
				var preSendDate = $("#preSendDate").val();

				var map = new Map();
				map.put("customerId", customerId);
				map.put("consigneeName", consigneeName);
				map.put("consigneeAddr", consigneeAddr);
				map.put("consigneeMobile", consigneeMobile);
				map.put("orderPrice", orderPrice);
				map.put("exccreaditCount", "0");
				map.put("remark", remark);
				map.put("preSendTime", preSendTime);
				map.put("preSendDate", preSendDate);
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
						var rtnCode = data.head.rtnCode;
						var rtnMsg = data.head.rtnMsg;
						if(rtnCode == "888888"){
							iDialog.iAlert("订单成功，坐等美食吧！");
							localStorage.removeItem("shopCart");
							setTimeout(function(){
								loadUrl("foodList.do")
							},3000);
						}else{
							iDialog.iAlert(rtnMsg);
						}
					}
				});
			}
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
				var userId = localStorage.getItem("userId");
				$("#userName").val(localStorage.getItem("userName"));
				$("#phone").val(localStorage.getItem("phone"));
				var year = new Date().getFullYear();
				$("#preSendDate").val(year+"-"+localStorage.getItem("saleTime"));
				$("#preSendDateShow").val(year+"-"+localStorage.getItem("saleTime"));

				if(userId != null && userId != "" && userId != undefined ){
					var map = new Map();
					map.put("userId", userId);
					var reqData = iReqMsg.getReqMsg(map);
					$.ajax({
						url: "getMyLatestOrder.do",
						type: "POST",
						data: reqData,
						dataType: "json",
						contentType: "text/plain",
						success: function (data) {
							var rtnCode = data.head.rtnCode;
							if(rtnCode == "888888"){
								var comments = data.body.comments;
								var consigneeName = data.body.consigneeName;
								var consigneeAddress = data.body.consigneeAddress;
								var consigneeMobile = data.body.consigneeMobile;
								var addressInfos = consigneeAddress.split("-")
								var city = addressInfos[0];
								var area = addressInfos[1];
								var address = addressInfos[2];
								$("#userName").val(consigneeName);
								$("#phone").val(consigneeMobile);
								$("#remark").val(comments);
								$("#address").val(address);
								$("#city option[value="+city+"]").attr("selected", "selected");
								$("#area option[value="+area+"]").attr("selected", "selected");
							}

						}
					});
				}
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
					iDialog.iAlert("购物车内空荡荡的,赶快选购吧");
					setTimeout(function(){
						loadUrl("foodList.do");
					},3000);
				}else{
					var info = "";
					for(var i = 0; i < shopCartInfos.length; i++){
						info += '<div class="row border-bottom">'+
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
						sumMoney = sumMoney + parseInt(shopCartInfos[i].count)*parseFloat(shopCartInfos[i].price);
					}
					$("#shopCartList").append(info);
					$("#sumMoney").text(sumMoney);
				}
			}

			function resetPreSendTime(){
				var date = new Date();
				var hour = date.getHours()
				var minute = date.getMinutes();
				var now = hour*60+minute;
				var seven = 7*60;
				var fiftyAndHalf = 15*60+30;
				var twentySecond = 22*60;
				var preSendTimeObj = $("#preSendTime");
				if(now<seven){
					/*$("#buyNowButton").text("马上开门");
					$("#buyNowButton").css({"color":"black"});
					$("#buyNowButton").attr("disabled", true);

					var info = '<option value = "-1">马上开门</option>';
					preSendTimeObj.append(info);*/
				}else if(now >= seven && now <fiftyAndHalf){
					var info = '<option value = "-1">请选择</option><option value = "17:30-18:00">17:30-18:00</option><option value = "18:00-18:30">18:00-18:30</option><option value = "18:30-19:00">18:30-19:00</option><option value = "19:00-19:30">19:00-19:30</option><option value = "19:30-20:00">19:30-20:00</option><option value = "07:00-07:30">07:00-07:30(次日)</option><option value = "07:30-08:00">07:30-08:00(次日)</option><option value = "08:00-08:30">08:00-08:30(次日)</option><option value = "08:30-09:00">08:30-09:00(次日)</option><option value = "09:00-09:30">09:00-09:30(次日)</option><option value = "09:30-10:00">09:30-10:00(次日)</option>'
					preSendTimeObj.append(info);
				}else if(now >= fiftyAndHalf && now < twentySecond){
					var info = '<option value = "-1">请选择</option><option value = "07:00-07:30">07:00-07:30(次日)</option><option value = "07:30-08:00">07:30-08:00(次日)</option><option value = "08:00-08:30">08:00-08:30(次日)</option><option value = "08:30-09:00">08:30-09:00(次日)</option><option value = "09:00-09:30">09:00-09:30(次日)</option><option value = "09:30-10:00">09:30-10:00(次日)</option>'
					preSendTimeObj.append(info);
				}else if(now >= twentySecond){
					var info = '<option value = "-1">(～﹃～)~zZ明天见</option>';
					preSendTimeObj.append(info);
					$("#buyNowButton").text("(～﹃～)~zZ明天见");
					$("#buyNowButton").css({"color":"black"});
					$("#buyNowButton").attr("disabled", true);
				}
			}
		}
	};
} ();