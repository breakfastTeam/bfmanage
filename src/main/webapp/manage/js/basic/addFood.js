var BelAddFood = function() {
    return {
        //主模块初始化
        init: function() {
            var um = UM.getEditor('briefIntroEditor');
            var briefIntroContent = $("#briefIntroContent").val();
            um.execCommand('insertHtml', briefIntroContent)
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
                    if(data.result.head.rtnCode=="888888") {
                        var result = data.result;
                        var filePath = result.body.filePath;
                        var originalFileName = result.body.originalFileName;
                        var orginPicPath = result.body.orginPicPath;
                        var smallPicPath = result.body.smallPicPath;
                        var diskPath = result.body.diskPath;
                        $("#fileName").val(originalFileName);
                        $("#filePath").val(filePath);
                        $("#orginPicPath").val(orginPicPath);
                        $("#smallPicPath").val(smallPicPath);
                        $("#diskPath").val(diskPath);


                        $('<p/>').text(originalFileName).appendTo('#foodPicName');
                        $("#foodPicDelButton").removeClass("display-none");
                        $("#previewSmallPic").hide();
                        openImageCrop(filePath);
                    }else{
                        iDialog.iAlert("图片尺寸不符合要求");
                    }
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

            $("#foodPicDelButton").click(function() {
                var btnObj = this;
                var orginPicPath = $("#orginPicPath").val();
                orginPicPath = orginPicPath.replace(/\\/g, "\\\\");
                var map = new Map();
                map.put("orginPicPath", orginPicPath);
                var reqData = iReqMsg.getReqMsg(map);
                console.log(reqData);
                $.ajax({
                    url: "deleteFoodPic.do",
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
                            $("#diskPath").val("");
                            $("#orginPicPath").val("");
                            $("#smallPicPath").val("");
                            $("#foodPicDelButton").hide();
                            $("#foodPicName").remove();
                        } else {
                            iDialog.iAlert(FAIL);
                        }
                    }
                });
            });

            function openImageCrop(path) {
                iDialog.iWindow("../openImageCrop.do?filePath=" + path, IMAGE_CROP);
            }
        }
    };
}();