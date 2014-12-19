var BelFeedback = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#feedbackTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();
        }
    };
}();
