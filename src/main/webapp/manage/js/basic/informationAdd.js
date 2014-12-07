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
                        content: {
                            required: true
                        }
                    },
                    messages: {
                        foodName: "请输入公告标题",
                        privilege:"请输入公告内容"
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

        }
    };
}();