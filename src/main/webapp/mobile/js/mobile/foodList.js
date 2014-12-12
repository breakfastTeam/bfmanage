var BelFoodList = function() {
	return {
		//主模块初始化
		init: function() {
			function resetImgScrollLoading(){
				$("#foodList img[original]").lazyload({
					effect:"fadeIn"
				});
			}


			var map = new Map();
			map.put("pageSize", "12");
			map.put("pageNo", "1");
			var reqData = iReqMsg.getReqMsg(map);
			$.ajax({
				url: "getFood.do",
				type: "POST",
				data: reqData,
				dataType: "json",
				contentType: "text/plain",
				success: function(data) {
					var results = data.body.results;
					localStorage.setItem("foodList", JSON.stringify(results));
					var lgh = results.length;
					var $foodList = $("#foodList");
					if(lgh<=0){
						$foodList.append("美食马上回来，敬请期待！");
					}else{
						var lis = "";
						var id,foodName, originPicPath, briefIntro, price, saleTime, foodCount, unit;
						for(var i = 0; i<lgh; i++){
							saleTime = results[i].saleTime;
							saleTime = saleTime.substring(5,saleTime.length);
							id = results[i].foodId;
							foodName = results[i].foodName;
							originPicPath = "../"+results[i].orginPicPath;
							price = results[i].price;
							foodCount = results[i].foodCount;
							briefIntro = results[i].briefIntro;
							unit = results[i].unit;
							//lis += '<li>' + '<div class="sprocket-mosaic-item">' + '<div class="sprocket-padding">' + '<div class="sprocket-mosaic-image-container">' + '<a href="javascript:void(0)" id = "'+results[i].foodId+'" foodName = "'+results[i].foodName+'"  originPicPath = "../'+results[i].orginPicPath+'" price = "'+results[i].price+'" unit = "'+results[i].unit+'" saleTime = "'+saleTime+'", foodCount = "'+results[i].foodCount+'"><textarea id = "briefIntro" style = "display:none">'+results[i].briefIntro+'</textarea><img original = "../'+results[i].smallPicPath+'" src="skin/default/images/grey.gif" alt=""	class="sprocket-mosaic-image" /> </a>' + '</div>' + '<div class="sprocket-mosaic-head">' + '<div class="sprocket-mosaic-title">' + '<a href="#">'+saleTime+'</a>' + '<a href="#" style = "padding-left:5px;float:right;">' + results[i].foodName + '</a>' + '</div>' + '</div>' + '</div>' + '</div>' + '</li>';
						}
						//$foodList.append(lis);
						//resetImgScrollLoading();
						//$foodList.find("a").click(function(){
							localStorage.setItem("currentFoodId", id);
							localStorage.setItem("currentFoodName", foodName);
							localStorage.setItem("currentOriginPicPath", originPicPath);
							localStorage.setItem("currentFoodBriefIntro", briefIntro);
							localStorage.setItem("price", price);
							localStorage.setItem("unit", $(this).attr("unit"));
							localStorage.setItem("saleTime", saleTime);
							localStorage.setItem("foodCount", $(this).attr("foodCount"))
							loadUrl("foodDetail.do");
						//});
					}
				}
			});
		}
	};
} ();