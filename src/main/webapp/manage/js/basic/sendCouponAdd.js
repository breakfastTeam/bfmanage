var BelSendCouponAdd = function() {
    return {
        //主模块初始化
        init: function() {

            $("#save").click(function() {
                $("#iForm").validate({
                    rules: {
                        price: {
                            required: true,
                            number:true,
                            min:1
                        },
                        num: {
                            required: true,
                            number:true,
                            min:1
                        },
                        source: {
                            required: true
                        }
                    },
                    messages: {
                        price:{
                            required : "请输入红包额度",
                            number: "红包额的要求输入数字",
                            min:"红包额度最少为1元"
                        },
                        num: {
                            required: "请输入份数",
                            number: "份数要求输入数字",
                            min:"份数最少为1份"
                        },
                        source: {
                            required: "请输入红包接收人"
                        }
                    },
                    submitHandler: function(form) {
                        form.action = "saveSendCoupon.do";
                        form.submit();
                    }
                });
            });
            $("#showSendCouponCustomerList").click(function (e) {
                e.preventDefault();
                iDialog.iWindow("toSendCouponCustomer.do", CUSTOMER_LIST);
            });
        }
    };
}();