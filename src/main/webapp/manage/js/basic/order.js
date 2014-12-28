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
                var orderId = $(this).parent().find("input[name='orderId']").val();
                var orderNo = $(this).parent().find("input[name='orderNo']").val();
                showOrderDetail(orderId, orderNo);
            });

            $("button[name='showPrint']").click(function (e) {
                e.preventDefault();
                var orderId = $(this).parent().find("input[name='orderId']").val();
                var index= $(this).parent().find("input[name='index']").val();

                showOrderPrint(orderId, index);
            });

            $("button[name='orderAccept']").click(function (e) {
                e.preventDefault();
                var orderId = $(this).parent().find("input[name='orderId']").val();
                showOrderCourier(orderId);
            });
            $("button[name='orderCancel']").click(function (e) {
                e.preventDefault();
                var orderId = $(this).parent().find("input[name='orderId']").val();
                updateOrderStatus(orderId, "CANCEL");
            });

            $("button[name='orderFinish']").click(function (e) {
                e.preventDefault();
                var orderId = $(this).parent().find("input[name='orderId']").val();
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

            function showOrderDetail(orderId, orderNo) {
                iDialog.iWindow("toOrderDetail.do?orderId="+orderId+"&orderNo="+orderNo, ORDER_DETAIL);
            }
            function showOrderPrint(orderId, index) {
                iDialog.iWindow("toOrderPrint.do?orderId="+orderId+"&index="+index, PRINT);
            }
            function showOrderCourier(orderId) {
                iDialog.iWindow("toOrderCourier.do?orderId="+orderId, EXPRESS_LIST);
            }
        }
    };
}();
