var BelUserAdd = function() {
    return {
        //主模块初始化
        init: function() {
            $("#loginName").blur(function () {
                var loginName = $("#loginName").val();
                var phone = $("#mobile").val();
                checkLoginNameAndPhone(loginName, phone,"loginName");
            });

            $("#mobile").blur(function(){
                var loginName = $("#loginName").val();
                var phone = $("#mobile").val();
                checkLoginNameAndPhone(loginName, phone,"mobile");
            });

            function checkLoginNameAndPhone(loginName, phone, blurId){
                var map = new Map();
                map.put("loginName", loginName);
                map.put("phone", phone);
                var reqData = iReqMsg.getReqMsg(map);
                $.ajax({
                    url: "checkLoginNameAndPhone.do",
                    type: "POST",
                    data: reqData,
                    dataType: "json",
                    contentType: "text/plain",
                    success: function (data) {
                        var rtnCode = data.head.rtnCode;
                        if(rtnCode != "888888"){
                            $("#"+blurId).focus();
                            $("#"+blurId).val("");
                            iDialog.iAlert(data.head.rtnMsg);
                        }
                    }
                });
            }

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
                        loginName: "请输入登录名",
                        mobile:{
                            required:"请输入手机号",
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