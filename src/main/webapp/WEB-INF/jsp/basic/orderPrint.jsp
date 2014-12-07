<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/manage/common/taglibs.jsp" %>
<%@ include file="/manage/common/meta.jsp" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body class="has-js">

<!--main content start-->
    <div class="row"  id="printArea">

        <div class="col-lg-24" style="margin:5px;">
            <div class="row">
                <label class="col-lg-24" style="font-size: 15pt">粥妹（公众号：izhoumei）</label>
            </div>
            <div class="row">
                <label class="col-lg-24"  style="font-size: 13pt">&nbsp;&nbsp;&nbsp;寒冬下的那一抹温度</label>
            </div>
            <div class="row">
                <label class="col-lg-24 control-label" style="font-size: xx-small"><spring:message code="ORDER_TIME"/>：${order.createTime}</label>
            </div>
            <div class="row">
                <label class="col-lg-24 control-label">*************************************</label>
            </div>

            <div class="row">
                <table class="col-lg-24" style="font-size:1pt" id = "orderList">
                    <tr>
                        <td style="width:50%;padding-left: 15px;"><spring:message code="FOOD_NAME"/></td>
                        <td style="width:16%"><spring:message code="PRICE"/>&nbsp;&nbsp;&nbsp;</td>
                        <td style="width:16%"><spring:message code="NUM"/>&nbsp;&nbsp;&nbsp;</td>
                        <td style="width:16%"><spring:message code="SUM"/>&nbsp;&nbsp;&nbsp;</td>
                    </tr>
                    <c:forEach items="${orderDetails}" var="item" varStatus="status">
                    <tr >
                        <td style="padding-left: 15px;padding-top:5px;">${item.food.foodName }</td>
                        <td style = "padding-top:5px;">${item.food.price }</td>
                        <td style = "padding-top:5px;">${item.orderDetail.foodObjCount}</td>
                        <td style = "padding-top:5px;"><a  name="partSum">${item.food.price*item.orderDetail.foodObjCount}</a></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="row">
                <label class="col-lg-24 control-label">*************************************</label>
            </div>
            <div class="row" style="font-size: 13pt">
                <label class="col-lg-24 control-label"><spring:message
                        code="SUM"/>：<a id = "sum"></a></label>
            </div>
            <div class="row" style="font-size: 13pt">
               <label class="col-lg-24 control-label"><spring:message
                            code="NAME"/>：${order.consigneeName}</label>
            </div>
            <div class="row" style="font-size: 13pt">
                    <label class="col-lg-24 control-label"><spring:message
                            code="PHONE"/>：${order.consigneeMobile}</label>
            </div>
            <div class="row" style="font-size: 13pt">
                    <label class="col-lg-24 control-label"><spring:message
                            code="ADDRESS"/>：${order.consigneeAddress}</label>
            </div>
            <div class="row">
                <img src = "${ctx}/manage/img/zhoumeiweixin.jpg">
            </div>
        </div>
    </div>
<div class="row">
    <div class="col-lg-24">
        <div class="pull-right">
            <button class="btn btn-success" id="print">
                <spring:message code="PRINT"/>
            </button>

        </div>
    </div>
</div>
<!--script for this page only-->
<script src="${ctx }/manage/skin/default/js/table/jquery.dataTables.js"></script>
<script src="${ctx }/manage/skin/default/js/table/DT_bootstrap.js"></script>
<script language="javascript" src="${ctx }/manage/js/plugin/print/jquery.jqprint-0.3.js"></script>
<script src="${ctx }/manage/js/basic/orderPrint.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function () {
        BelOrderPrint.init();
    });
</script>
</body>
</html>
