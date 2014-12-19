var BelOrderCourier = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#orderCourierTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#confirm").click(function(){
                var orderId = $("#orderId").val();
                var userId = $("input[name=userId][checked]").val();
                if(userId != null && userId != undefined){
                    updateOrderStatus(orderId, userId, "DISTRIBUTION");
                }else{
                    $("#tipsRow span").text("请先选择快递员！");
                    $("#tipsRow").fadeIn("slow");
                    setTimeout(function(){
                        $("#tipsRow").fadeOut("slow");
                    },2000);
                }
            });

            function updateOrderStatus(orderId,userId, status){
                var map = new Map();
                map.put("orderId", orderId);
                map.put("userId", userId);
                map.put("status", status);
                var reqData = iReqMsg.getReqMsg(map);
                $.ajax({
                    url: "updateOrderStatus.do",
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
