var BelSetMealAdd = function() {
    return {
        //主模块初始化
        init: function() {
            var um = UM.getEditor('introduceEditor');
            var introduceContent = $("#introduceContent").val();
            um.execCommand('insertHtml', introduceContent);
            bundleDatepicker();

            $("#openFoodList").click(function(e){
                e.preventDefault();
                iDialog.iWindow("openFoodList.do", FOOD_LIST);
            });

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
                        setName: {
                            required: true
                        },
                        privilege: {
                            required: true,
                            number: true
                        },
                        price: {
                            required: true,
                            number: true
                        },
                        foodCount: {
                            required: true,
                            number: true
                        },
                        realFoodCount: {
                            required: true,
                            number: true
                        },
                        introduceContent: {
                            maxlength: 400
                        },
                        saleTime:{
                            required: true
                        }
                    },
                    messages: {
                        foodName: "请输入菜谱名称",
                        privilege: {
                            required:"请输入优惠价格",
                            number:"请输入数字"
                        },
                        price:{
                            required:"请输入价格",
                            number:"请输入数字"   
                        },
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
                        form.action = "saveSetMeal.do";
                        form.submit();
                    }
                });
            });

            $('#setMealPicUpload').fileupload({
                url: "uploadSetMealPic.do",
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


                        $('<p/>').text(originalFileName).appendTo('#setMealPicName');
                        $("#setMealPicDelButton").removeClass("display-none");
                        $("#previewSmallPic").hide();
                        openImageCrop(filePath);
                    }else{
                        iDialog.iAlert("图片尺寸不符合要求");
                    }
                },
                progressall: function(e, data) {
                    var progress = parseInt(data.loaded / data.total * 100, 10);
                    $('#setMealPicProgress .progress-bar').css('width', progress + '%');
                    if (progress == 100) {
                        setTimeout(function() {
                                $('#setMealPicProgress .progress-bar').fadeOut();
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

            function openImageCrop(path) {
                iDialog.iWindow("../openImageCrop.do?filePath=" + path, IMAGE_CROP);
            }
        }
    };
}();