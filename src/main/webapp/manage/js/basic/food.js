var BelCookBook = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#cookBookTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();
			
			$("#toAddFood").click(function(){
        		openNewUrl('basic/toAddFood.do')
        	});

            $("button[name='editFood']").click(function(e){
                e.preventDefault();
                var foodId = $(this).parent().find("input").val();
                openNewUrl('basic/toEditFood.do?foodId='+foodId);
            });
        }
    };
}();
