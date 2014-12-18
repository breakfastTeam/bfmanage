var BelProviderAdd = function() {
    return {
        //主模块初始化
        init: function() {

            $("#save").click(function() {
                $("#iForm").validate({
                    rules: {
                        providerName: {
                            required: true
                        },
                        phone: {
                            required: true
                        }
                    },
                    submitHandler: function(form) {
                        form.action = "saveProvider.do";
                        form.submit();
                    }
                });
            });
        }
    };
}();