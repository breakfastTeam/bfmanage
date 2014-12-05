var BelOrderPrint = function () {
    return {
        //主模块初始化
        init: function () {
            $("#print").click(function (e) {
                e.preventDefault();
                $("#printArea").jqprint();
            });
        }
    };
}();
