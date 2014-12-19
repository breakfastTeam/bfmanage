var BelFood = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#foodTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();
			
			$("#toAddFood").click(function(){
        		openNewUrl('basic/toAddFood.do')
        	});

            $("button[name='editFood']").click(function(e){
                e.preventDefault();
                var foodId = $(this).parent().find("input").val();
                openNewUrl('basic/toEditFood.do?foodId='+foodId);
            });
            $("button[name='soldout']").click(function (e) {
                e.preventDefault();
                var foodId = $(this).parent().find("input").val();
                updateFoodStatus(foodId, "SOLDOUT");
            });
            $("button[name='putaway']").click(function (e) {
                e.preventDefault();
                var foodId = $(this).parent().find("input").val();
                updateFoodStatus(foodId, "PUTAWAY");
            });
            function updateFoodStatus(foodId, status){
                var map = new Map();
                map.put("foodId", foodId);
                map.put("status", status);
                var reqData = iReqMsg.getReqMsg(map);
                $.ajax({
                    url: "updateFoodStatus.do",
                    type: "POST",
                    data: reqData,
                    dataType: "json",
                    contentType: "text/plain",
                    success: function (data) {
                        var rtnCode = data.head.rtnCode;
                        if(rtnCode == "888888"){
                            $("#iForm").submit();
                        }
                    }
                });
            }
        }
    };
}();
