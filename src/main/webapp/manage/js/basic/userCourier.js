var BelUserCourier = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#userCourierTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#toUserCourierAdd").click(function(){
                openNewUrl('basic/toUserCourierAdd.do')
            });

            $("button[name='userCourierEdit']").click(function(e){
                e.preventDefault();
                var userId = $(this).parent().find("input").val();
                openNewUrl('basic/toUserCourierEdit.do?userId='+userId);
            });
        }
    };
}();
