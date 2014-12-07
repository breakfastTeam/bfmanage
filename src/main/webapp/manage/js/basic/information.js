var BelInformation = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#informationTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#toInformationAdd").click(function(){
                openNewUrl('basic/toInformationAdd.do')
            });

            $("button[name='informationEdit']").click(function(e){
                e.preventDefault();
                var informationId = $(this).parent().find("input").val();
                openNewUrl('basic/toInformationEdit.do?informationId='+informationId);
            });
        }
    };
}();
