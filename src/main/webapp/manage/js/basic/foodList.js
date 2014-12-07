var BelFoodList = function () {
    return {
        //主模块初始化
        init: function () {
            var oTable = $('#foodListTable').dataTable({
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
                var foodIds = $("input[name='foodId'][checked]");
                var fids = "";
                var info="";
                for(var i = 0; i<foodIds.length; i++){
                    info = info + '<div class="col-lg-4">'+
                                        '<div class="alert alert-success alert-block fade in">'+
                                            '<button data-dismiss="alert" class="close close-sm" type="button">'+
                                                '<i class="fa fa-times"></i>'+
                                            '</button>'+
                                            '<input type = "hidden" name="foodId" value="'+$(foodIds).attr("id")+'"/>'+
                                            '<p>'+$(foodIds[i]).val()+'</p>'+
                                        '</div>'+
                                    '</div>';
                    fids = fids + $(foodIds).attr("id") +",";
                }
                $("#foodList").append(info);

                fids = fids.substring(0, fids.length-1);
                $("#foodIds").val(fids);
                iDialog.iHide();
            });

            $("#close").click(function(){
                iDialog.iHide();
            });
        }
    };
}();
