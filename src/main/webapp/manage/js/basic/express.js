var BelExpress = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#expressTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMTPY_CONENT
			     }
            });
			oTable.fnDraw();
        }
    };
}();
