var BelUser = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#userTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#toUserAdd").click(function(){
                openNewUrl('basic/toUserAdd.do')
            });

            $("button[name='userEdit']").click(function(e){
                e.preventDefault();
                var userId = $(this).parent().find("input").val();
                openNewUrl('basic/toUserEdit.do?userId='+userId);
            });
        }
    };
}();
