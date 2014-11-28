var BelFoodList = function() {
	return {
		//主模块初始化
		init: function() {
		
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
					if(lgh<=0){
						$("#foodList").append("美食马上回来，敬请期待！");
					}else{
						console.log(results);
						$(results).each(function(i) {
							var smallPic = results[i].smallPicPath;
							var foodName = results[i].foodName;
							var price = results[i].price;
							var foodId = results[i].foodId;
							var originPicPath = results[i].orginPicPath;
							var briefIntro = results[i].briefIntro;
							var unit = results[i].unit;
							var saleTime = results[i].saleTime;
							var foodCount = results[i].foodCount;
							var lis = '<li>' + '<div class="sprocket-mosaic-item">' + '<div class="sprocket-padding">' + '<div class="sprocket-mosaic-image-container">' + '<a href="javascript:void(0)" id = "'+foodId+'" foodName = "'+foodName+'"  originPicPath = "'+originPicPath+'" price = "'+price+'" unit = "'+unit+'" saleTime = "'+saleTime+'", foodCount = "'+foodCount+'"><textarea id = "briefIntro" style = "display:none">"'+briefIntro+'"</textarea><img src="'+smallPic + '" alt=""	class="sprocket-mosaic-image" /> </a>' + '</div>' + '<div class="sprocket-mosaic-head">' + '<div class="sprocket-mosaic-title">' + '<a href="#">'+saleTime+'</a>' + '<a href="#" style = "padding-left:5px;float:right;">' + foodName + '</a>' + '</div>' + '</div>' + '</div>' + '</div>' + '</li>';
							$("#foodList").append(lis);
						});

						$("#foodList").find("a").click(function(){
							localStorage.setItem("currentFoodId", $(this).attr("id"));
							localStorage.setItem("currentFoodName", $(this).attr("foodName"));
							localStorage.setItem("currentOriginPicPath", $(this).attr("originPicPath"));
							localStorage.setItem("currentFoodBriefIntro", $(this).find("#briefIntro").val());
							localStorage.setItem("price", $(this).attr("price"));
							localStorage.setItem("unit", $(this).attr("unit"));
							localStorage.setItem("saleTime", $(this).attr("saleTime"));
							localStorage.setItem("foodCount", $(this).attr("foodCount"))
							loadUrl("foodDetail.do");
						});
					}
				}
			});
		}
	};
} ();