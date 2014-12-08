var BelUserAdd = function() {
    return {
        //主模块初始化
        init: function() {

            $("#save").click(function() {
                $("#iForm").validate({
                    rules: {
                        loginName: {
                            required: true
                        },
                        mobile:{
                            mobilePhone: true,
                            required: true
                        },
                        password: {
                            required: true
                        },
                        userName:{
                            required: true
                        }
                    },
                    messages: {
                        loginName: "请输入公告标题",
                        mobile:{
                            required:"请输入公告内容",
                            mobilePhone:"手机号码格式不正确"
                        },
                        password:"请输入登录密码",
                        userName:"请输入姓名"
                    },
                    submitHandler: function(form) {
                        form.action = "saveUser.do";
                        form.submit();
                    }
                });
            });
        }
    };
}();