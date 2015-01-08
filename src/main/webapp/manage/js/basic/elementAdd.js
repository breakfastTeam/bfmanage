var BelElementAdd = function() {
    return {
        //主模块初始化
        init: function() {

            $("#save").click(function() {
                $("#iForm").validate({
                    rules: {
                        elementName: {
                            required: true
                        },
                        unit: {
                            required: true
                        }
                    },
                    messages: {
                        elementName: "请输入元素名称",
                        unit:"请输入元素单位"
                    },
                    submitHandler: function(form) {
                        form.action = "saveElement.do";
                        form.submit();
                    }
                });
            });
        }
    };
}();