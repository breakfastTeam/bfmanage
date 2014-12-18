var BelRawMaterial = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#rawMaterialTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#toRawMaterialAdd").click(function(){
                openNewUrl('basic/toRawMaterialAdd.do')
            });

            $("button[name='rawMaterialEdit']").click(function(e){
                e.preventDefault();
                var rawMaterialId = $(this).parent().find("input").val();
                openNewUrl('basic/toRawMaterialEdit.do?rawMaterialId='+rawMaterialId);
            });
        }
    };
}();
