var BelAddFood = function() {
    return {
        //主模块初始化
        init: function() {
            var um = UM.getEditor('briefIntroEditor');
            bundleDatepicker();
            $("#save").click(function() {
                    var content = um.getContent();
                    $("#briefIntro").val(content);
                    $("#briefIntroContent").val(um.getContentTxt());
                    if (um.getContentTxt().length > 400) {
                        iDialog.iAlert("简介内容不能够超过400个字");
                        return;
                    }
                    $("#iForm").validate({
                        rules: {
                            foodName: {
                                required: true
                            },
                            cost: {
                                required: true
                            },
                            price: {
                                required: true
                            },
                            foodCount: {
                                required: true,
                                number: true
                            },
                            realFoodCount: {
                                required: true,
                                number: true
                            },
                            briefIntroContent: {
                                maxlength: 400
                            },
                            saleTime:{
                                required: true
                            }
                        },
                        messages: {
                            foodName: "请输入菜谱名称",
                            cost: "请输入数字",
                            price: "开始时间不能为空",
                            saleTime:"请输入销售日期",
                            foodCount: {
                                required: "请输入库存数",
                                number: "库存要求输入数字"
                            },
                            realFoodCount: {
                                required: "请输入库存数",
                                number: "库存要求输入数字"
                            },
                            briefIntroConten: {
                                maxlength: "内容不允许超过400字"
                            }
                        },
                        submitHandler: function(form) {
                            form.action = "saveFood.do";
                            form.submit();
                        }
                    });
            });

            $('#foodPicUpload').fileupload({
                url: "uploadFoodPic.do",
                dataType: 'json',
                done: function(e, data) {
                    var result = data.result;
                    var fileName = result.body.fileName;
                    var filePath = result.body.filePath;
                    var saveDiskPath = result.body.saveDiskPath;
                    var cropFileName = result.body.cropFileName;
                    $("#fileName").val(fileName);
                    $("#filePath").val(filePath);
                    $("#saveDiskPath").val(saveDiskPath);
                    $("#cropFileName").val(cropFileName);

                    $('<p/>').text(fileName).appendTo('#foodPicName');
                    $("#foodPicDelButton").removeClass("display-none");
                    $("#foodPicEditButton").removeClass("display-none");
                    openImageCrop(filePath, cropFileName);
                },
                progressall: function(e, data) {
                    var progress = parseInt(data.loaded / data.total * 100, 10);
                    $('#foodPicProgress .progress-bar').css('width', progress + '%');
                    if (progress == 100) {
                        setTimeout(function() {
                                $('#foodPicProgress .progress-bar').fadeOut();
                            },
                            500);
                    }
                }
            });

            function bundleDatepicker() {
                $('.default-date-picker').datepicker({
                    format: 'yyyy-mm-dd'
                });
            }

            $("#isSupportExchangeYes").click(function() {
                $("#isSupportExchangeYesInput").val("on");
                $("#isSupportExchangeNoInput").val("on");
                $("#exchangePrice").removeAttr("disabled");
            });
            $("#isSupportExchangeNo").click(function() {
                $("#isSupportExchangeNoInput").val("off");
                $("#isSupportExchangeYesInput").val("off");
                $("#exchangePrice").attr("disabled", true);
            });

            $("#isSupportSnapUpYes").click(function() {
                $("#isSupportSnapUpYesInput").val("on");
                $("#isSupportSnapUpNoInput").val("on");
                $("#snapUpPrice").removeAttr("disabled");
            });
            $("#isSupportSnapUpNo").click(function() {
                $("#isSupportSnapUpNoInput").val("off");
                $("#isSupportSnapUpYesInput").val("off");
                $("#snapupPrice").attr("disabled", true);
            });
            $("#foodPicEditButton").click(function() {
                var filePath = $("#filePath").val();
                var cropFileName = $("#cropFileName").val(cropFileName);
                openImageCrop(filePath, cropFileName);
            });

            $("#foodPicDelButton").click(function() {
                var btnObj = this;
                var saveDiskPath = $("#saveDiskPath").val();
                var cropFileName = $("#cropFileName").val();

                var map = new Map();
                map.put("filePath", saveDiskPath);
                map.put("cropFileName", cropFileName);
                var reqData = iReqMsg.getReqMsg(map);
                $.ajax({
                    url: "deleteFood.do",
                    type: "POST",
                    data: reqData,
                    dataType: "json",
                    contentType: "text/plain",
                    beforeSend: function() {
                        iDialog.iLoading("yes", btnObj);
                    },
                    success: function(data) {
                        iDialog.iLoading("no", btnObj);
                        if (data.head.rtnCode == "888888") {
                            iDialog.iAlert(SUCCESS);
                            $("#fileName").val("");
                            $("#filePath").val("");
                            $("#saveDiskPath").val("");
                            $("#foodPicName").remove();
                        } else {
                            iDialog.iAlert(FAIL);
                        }
                    }
                });
            });

            function openImageCrop(path, cropFileName) {
                iDialog.iWindow("../openImageCrop.do?path=" + path + "&cropFileName=" + cropFileName, IMAGE_CROP);
            }
        }
    };
}();