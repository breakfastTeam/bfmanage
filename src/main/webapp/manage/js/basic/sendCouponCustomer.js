var BelSendCouponCustomer = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#sendCouponCustomerTable').dataTable({
                "bInfo":false,
                "bFilter":false,
                "bLengthChange":false,
                "bPaginate":false,
                "oLanguage": {
                	"sEmptyTable":EMPTY_CONTENT
			     }
            });
			oTable.fnDraw();

            $("#OK").click(function(){
                var userIds = $("input[name='userId'][checked]");
                var uids = "";
                var info="";
                for(var i = 0; i<userIds.length; i++){
                    info = info + '<div class="col-lg-4">'+
                                        '<div class="alert alert-success alert-block fade in">'+
                                            '<button data-dismiss="alert" class="close close-sm" type="button">'+
                                                '<i class="fa fa-times"></i>'+
                                            '</button>'+
                                            '<input type = "hidden" name="userId" value="'+$(userIds).attr("id")+'"/>'+
                                            '<p>'+$(userIds[i]).attr("userName")+'</p>'+
                                        '</div>'+
                                    '</div>';
                    uids = uids + $(userIds).attr("id") +",";
                }
                $("#customerList").append(info);

                uids = uids.substring(0, uids.length-1);
                $("#uids").val(uids);
                iDialog.iHide();
            });

            $("#close").click(function(){
                iDialog.iHide();
            });
        }
    };
}();
