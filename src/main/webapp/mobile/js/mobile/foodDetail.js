var BelFoodDetail = function() {
	return {
		//主模块初始化
		init: function() {
			var foodId = localStorage.getItem("currentFoodId");
			var foodName = localStorage.getItem("currentFoodName");
			var originPicPath = localStorage.getItem("currentOriginPicPath");
			var foodBriefIntro = localStorage.getItem("currentFoodBriefIntro");
			var price = localStorage.getItem("price");
			var unit = localStorage.getItem("unit");
			var saleTime = localStorage.getItem("saleTime");
			$("#originPicPath").attr("original", originPicPath);
			$("#foodName").text(foodName);
			$("#price").text(price+""+unit);

			$("#foodBriefIntro").html(foodBriefIntro);
			resetImgScrollLoading();
			checkFoodIsAvailable();

			$("#returnToHome").click(function(){
				loadUrl("foodList.do");
			});

			$("#plus").click(function(){
				var count = parseInt($("#count").val())+1;
				if(count > 10){
					iDialog.iTip("一次订购不能超过10份");
				}else{
					$("#count").val(count);
				}
			});

			$("#minus").click(function(){
				var count = parseInt($("#count").val())-1;
				if(count<=0){
					iDialog.iTip("订购1份起");
				}else{
					$("#count").val(count);
				}
			});

			$("#shopCartButton").click(function(){
				var saleOut = $("#buyNowButton").attr("sale-out");
				if(saleOut == true || saleOut == "true"){
					iDialog.iAlert("亲，已经售完了，下次记得早点儿来哦");
					return false;
				}else{
					addFoodToCart(true);
				}

			});
			function addFoodToCart(showTip){
				var count = $("#count").val();
				var shopCartInfo = localStorage.getItem("shopCart");
				if(shopCartInfo == null || shopCartInfo == undefined){
					shopCartInfo = '{"foodId":"'+foodId+'", "foodName":"'+foodName+'", "price":"'+price+'", "count":"'+count+'", "saleTime":"'+saleTime+'"}';
				}else{
					var shopCartInfos = $.parseJSON('{"shopCartInfos":['+shopCartInfo+']}').shopCartInfos;
					var isExist = false;
					for(var i = 0; i < shopCartInfos.length; i++){
						if(foodId == shopCartInfos[i].foodId){
							var buyFoodCount = (parseInt(shopCartInfos[i].count) + parseInt(count));
							if(buyFoodCount>10){
								iDialog.iAlert("该商品一次订购不能超过10份哦");
								return false;
							}else{
								shopCartInfos[i].count = buyFoodCount+"";
							}
							isExist = true;
							break;
						}
					}
					if(isExist){
						shopCartInfo = JSON.stringify(shopCartInfos).replace("[","").replace("]","");
					}else{
						shopCartInfo = shopCartInfo + ',{"foodId":"'+foodId+'", "foodName":"'+foodName+'", "price":"'+price+'", "count":"'+count+'", "saleTime":"'+saleTime+'"}';
					}
				}
				shopCartInfo = shopCartInfo.substring(0, shopCartInfo.length);
				localStorage.setItem("shopCart", shopCartInfo);
				if(showTip){
					iDialog.iTip("已加入购物车");
				}
			}
			$("#buyNowButton").click(function(){
				var saleOut = $("#buyNowButton").attr("sale-out");
				if(saleOut == true || saleOut == "true"){
					iDialog.iAlert("亲，已经卖完了，下次记得早点儿来粥妹哦");
					return false;
				}else{
					var phone = localStorage.getItem("phone");
					//if(phone != null && phone != "" && phone != undefined){
					addFoodToCart(false);
					loadUrl("toBuyNow.do");
					//}else{
					//	loadUrl("toLogin.do");
					//}
				}

			});
			function checkFoodIsAvailable(){
				var d = new Date();
				var monthInfo = d.getMonth()+1;
				var dateInfo = d.getDate();
				if(monthInfo<10){
					monthInfo = "0"+monthInfo;
				}
				if(dateInfo<10){
					dateInfo = "0"+dateInfo;
				}

				var now = monthInfo+"-"+dateInfo;
				var saleTime = localStorage.getItem("saleTime");
				var foodCount = parseInt(localStorage.getItem("foodCount"));
				/**if(saleTime != now) {
					$("#buyNowButton").text("("+saleTime+")开始预定");
					$("#buyNowButton").text("("+saleTime+")开始预定");
					$("#buyNowButton").attr("disable", true);
					$("#shopCartButton").attr("disable", true);
				}else{**/
					if(foodCount<=0){
						$("#buyNowButton").text("售罄");
						$("#shopCartButton").text("售罄");
						$("#buyNowButton").attr("sale-out", true);
						$("#shopCartButton").attr("sale-out", true);

					}
				//}
			}

			function resetImgScrollLoading(){
				$("img[original]").lazyload({
					effect:"fadeIn"
				});
			}
		}
	};
} ();