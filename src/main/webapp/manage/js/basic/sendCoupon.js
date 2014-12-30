var BelSendCoupon = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#sendCouponTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#toSendCouponAdd").click(function(){
                openNewUrl('basic/toSendCouponAdd.do')
            });

            $("button[name='sendCouponDiscard']").click(function(e){
                e.preventDefault();
                var sendCouponId = $(this).parent().find("input").val();
                openNewUrl('basic/toSendCouponDelete.do?sendCouponId='+sendCouponId);
            });
        }
    };
}();

