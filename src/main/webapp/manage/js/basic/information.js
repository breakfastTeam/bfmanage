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
            $("button[name='infoDiscard']").click(function(e){
                e.preventDefault();
                var infoId = $(this).parent().find("input").val();
                iDialog.iConfirm(SURE_DELETE, {callBackHandler:function(){
                    updateInfoStatus(infoId, "DISCARD");
                }});
            });
            function updateInfoStatus(infoId, status){
                var map = new Map();
                map.put("infoId", infoId);
                map.put("status", status);
                var reqData = iReqMsg.getReqMsg(map);
                $.ajax({
                    url: "updateInfoStatus.do",
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
