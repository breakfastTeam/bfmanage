var BelOrder = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#orderTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("button[name='showDetail']").click(function (e) {
                e.preventDefault();
                var orderId = $(this).parent().find("input").val();
                showOrderDetail(orderId);
            });

            $("button[name='showPrint']").click(function (e) {
                e.preventDefault();
                var orderId = $(this).parent().find("input").val();
                showOrderPrint(orderId);
            });

            $("button[name='orderAccept']").click(function (e) {
                e.preventDefault();
                var orderId = $(this).parent().find("input").val();
                updateOrderStatus(orderId, "DISTRIBUTION");
            });
            $("button[name='orderCancel']").click(function (e) {
                e.preventDefault();
                var orderId = $(this).parent().find("input").val();
                updateOrderStatus(orderId, "CANCEL");
            });

            $("button[name='orderFinish']").click(function (e) {
                e.preventDefault();
                var orderId = $(this).parent().find("input").val();
                updateOrderStatus(orderId, "FINISH");
            });


            function updateOrderStatus(orderId, status){
                var map = new Map();
                map.put("orderId", orderId);
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

            function showOrderDetail(orderId) {
                iDialog.iWindow("toOrderDetail.do?orderId="+orderId, ORDER_DETAIL);
            }
            function showOrderPrint(orderId) {
                iDialog.iWindow("toOrderPrint.do?orderId="+orderId, PRINT);
            }
        }
    };
}();
