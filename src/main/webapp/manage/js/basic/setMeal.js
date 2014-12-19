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
            $("button[name='soldout']").click(function (e) {
                e.preventDefault();
                var setMealId = $(this).parent().find("input").val();
                updateSetMealStatus(setMealId, "SOLDOUT");
            });
            $("button[name='putaway']").click(function (e) {
                e.preventDefault();
                var setMealId = $(this).parent().find("input").val();
                updateSetMealStatus(setMealId, "PUTAWAY");
            });
            function updateSetMealStatus(setMealId, status){
                var map = new Map();
                map.put("setMealId", setMealId);
                map.put("status", status);
                var reqData = iReqMsg.getReqMsg(map);
                $.ajax({
                    url: "updateSetMealStatus.do",
                    type: "POST",
                    data: reqData,
                    dataType: "json",
                    contentType: "text/plain",
                    success: function (data) {
                        var rtnCode = data.head.rtnCode;
                        if(rtnCode == "888888"){
                            $("#iForm").submit();
                        }
                    }
                });
            }
        }
    };
}();
