var BelProvider = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#providerTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#toProviderAdd").click(function(){
                openNewUrl('basic/toProviderAdd.do')
            });

            $("button[name='providerEdit']").click(function(e){
                e.preventDefault();
                var providerId = $(this).parent().find("input").val();
                openNewUrl('basic/toProviderEdit.do?providerId='+providerId);
            });
        }
    };
}();
