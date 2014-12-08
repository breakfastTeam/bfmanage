var BelUserCustomer = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#userCustomerTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#toUserCustomerAdd").click(function(){
                openNewUrl('basic/toUserCustomerAdd.do')
            });

            $("button[name='userCustomerEdit']").click(function(e){
                e.preventDefault();
                var userId = $(this).parent().find("input").val();
                openNewUrl('basic/toUserCustomerEdit.do?userId='+userId);
            });
        }
    };
}();
