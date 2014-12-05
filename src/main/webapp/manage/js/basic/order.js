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

            function showOrderDetail(orderId) {
                iDialog.iWindow("toOrderDetail.do?orderId="+orderId, ORDER_DETAIL);
            }
        }
    };
}();
