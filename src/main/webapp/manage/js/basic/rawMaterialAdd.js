var BelRawMaterialAdd = function() {
    return {
        //主模块初始化
        init: function() {

            $("#save").click(function() {
                $("#iForm").validate({
                    rules: {
                        rawMaterialName: {
                            required: true
                        },
                        price: {
                            required: true,
                            number:true
                        },
                        unit: {
                            required: true
                        }
                    },
                    messages: {
                        rawMaterialName: "请输入原材料名称",
                        price: {
                            required: "请输入价格",
                            number: "价格要求输入数字"
                        }
                    },
                    submitHandler: function(form) {
                        form.action = "saveRawMaterial.do";
                        form.submit();
                    }
                });
            });
        }
    };
}();