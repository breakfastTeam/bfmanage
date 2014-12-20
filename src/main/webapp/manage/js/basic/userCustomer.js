var BelUserCustomer = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#userCustomerTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#toUserCustomerAdd").click(function(){
                openNewUrl('basic/toUserCustomerAdd.do')
            });

            $("button[name='userCustomerEdit']").click(function(e){
                e.preventDefault();
                var userId = $(this).parent().find("input").val();
                openNewUrl('basic/toUserCustomerEdit.do?userId='+userId);
            });

            $("button[name='userDiscard']").click(function(e){
                e.preventDefault();
                var userId = $(this).parent().find("input").val();
                iDialog.iConfirm(SURE_DELETE, {callBackHandler:function(){
                    updateUserCustomerStatus(userId, "DISCARD");
                }});
            });

            function updateUserCustomerStatus(userId, status){
                var map = new Map();
                map.put("userId", userId);
                map.put("status", status);
                var reqData = iReqMsg.getReqMsg(map);
                $.ajax({
                    url: "updateUserCustomerStatus.do",
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
