var BelSetMeal = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#setMealTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#toSetMealAdd").click(function(){
                openNewUrl('basic/toSetMealAdd.do')
            });

            $("button[name='setMealEdit']").click(function(e){
                e.preventDefault();
                var setMealId = $(this).parent().find("input").val();
                openNewUrl('basic/toSetMealEdit.do?setMealId='+setMealId);
            });
        }
    };
}();
