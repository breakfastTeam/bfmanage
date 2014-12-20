var BelInformationAdd = function() {
    return {
        //主模块初始化
        init: function() {
            var um = UM.getEditor('contentEditor');
            var contentContent = $("#contentContent").val();
            um.execCommand('insertHtml', contentContent);
            bundleDatepicker();
            $("#save").click(function() {
                var content = um.getContent();
                $("#content").val(content);
                $("#contentContent").val(um.getContentTxt());

                $("#iForm").validate({
                    rules: {
                        title: {
                            required: true
                        },
                        briefIntro:{
                          required:true,
                          maxlength:50
                        },
                        content: {
                            required: true
                        }
                    },
                    messages: {
                        title: "请输入公告标题",
                        briefIntro:{
                            required:"请输入公告简介",
                            maxlength:"简介内容不能超过50字"
                        },
                        content:"请输入公告内容"
                    },
                    submitHandler: function(form) {
                        form.action = "saveInformation.do";
                        form.submit();
                    }
                });
            });
            function bundleDatepicker() {
                $('.default-date-picker').datepicker({
                    format: 'yyyy-mm-dd'
                });
            }
            $('#infoPicUpload').fileupload({
                url: "uploadInfoPic.do",
                dataType: 'json',
                done: function(e, data) {
                    if(data.result.head.rtnCode=="888888") {
                        var result = data.result;
                        var smallPicId = result.body.smallPicId;
                        var originPicName = result.body.originPicName;
                        $("#smallPicId").val(smallPicId);

                        $('<p/>').text(originPicName).appendTo('#infoPicName');
                        $("#infoPicDelButton").removeClass("display-none");
                    }else{
                        iDialog.iAlert("图片尺寸不符合要求");
                    }
                },
                progressall: function(e, data) {
                    var progress = parseInt(data.loaded / data.total * 100, 10);
                    $('#infoPicProgress .progress-bar').css('width', progress + '%');
                    if (progress == 100) {
                        setTimeout(function() {
                                $('#infoPicProgress .progress-bar').fadeOut();
                            },
                            500);
                    }
                }
            });
        }
    };
}();