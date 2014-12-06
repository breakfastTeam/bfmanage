var BelOrderPrint = function () {
    return {
        //主模块初始化
        init: function () {
            $("#print").click(function (e) {
                e.preventDefault();
                $("#printArea").jqprint();
            });
            calculateSum();

            function calculateSum(){
                var partSums = $("#orderList a[name='partSum']");
                var sum = 0;
                for(var i = 0; i<partSums.length; i++){
                    sum = sum+parseFloat($(partSums[i]).text());
                }
                $("#sum").text(sum);
            }
        }
    };
}();
