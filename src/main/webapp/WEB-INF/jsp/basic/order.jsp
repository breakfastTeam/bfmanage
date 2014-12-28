<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/manage/common/taglibs.jsp" %>
<%@ include file="/manage/common/meta.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="FOOD_MANAGE"/>
    </title>
</head>
<body class="has-js">
<!--main content start-->
<section class="wrapper">
    <div class="row">
        <div class="col-lg-24">
            <!-- 位置指示标志 开始 -->
            <ul class="breadcrumb">
                <li>
                    <a href="#"><i class="fa fa-home"></i>
                        <spring:message code="HOME"/>
                    </a>
                </li>
                <li class="active">
                    <spring:message code="ORDER_MANAGE"/>
                </li>
            </ul>
            <!--位置指示标志 结束 -->
        </div>
    </div>
    <div class="row">
        <div class="col-lg-24">
            <!-- 通知消息类效果 开始-->
            <section class="panel">
                <header class="panel-heading">
                    <spring:message code="ORDER_LIST"/>
                </header>
                <div class="panel-body">
                    <div class="adv-table editable-table ">
                        <form action="toOrder.do" id="iForm" method="GET">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div>
                                        <spring:message code="PHONE"/>
                                        ：
                                        <label>
                                                <input type="text" name="consigneeMobile" id="consigneeMobile"
                                                       aria-controls="editable-sample" class="form-control medium"
                                                       value="${consigneeMobile }">
                                        </label>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div>
                                        <spring:message code="NAME"/>
                                        ：
                                        <label>
                                                <input type="text" name="consigneeName" id="consigneeName"
                                                       aria-controls="editable-sample" class="form-control medium"
                                                       value="${consigneeName }">
                                        </label>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <spring:message code="ADDRESS"/>：
                                    <label>
                                            <input type="text" name="consigneeAddress" id="consigneeAddress"
                                                   aria-controls="editable-sample" class="form-control medium"
                                                   value="${consigneeAddress }">
                                    </label>
                                </div>
                                <div class="col-lg-6">
                                    <div class="pull-right">
                                        <button class="btn btn-primary">
                                            <i class="fa fa-search"></i>
                                            <spring:message code="QUERY"/>
                                        </button>
                                    </div>
                                </div>

                            </div>

                            <table
                                    class="table table-striped table-hover table-bordered table-advance"
                                    id="orderTable">
                                <thead>
                                <tr>
                                    <th class="first-td">
                                        <label class="label_check" for="selectAll">
                                            <input name="selectAll" id="selectAll" type="checkbox">
                                        </label>
                                    </th>
                                    <th>
                                        <spring:message code="INDEX"/>
                                    </th>
                                    <th>
                                        <spring:message code="PHONE"/>
                                    </th>
                                    <th>
                                        <spring:message code="NAME"/>
                                    </th>
                                    <th>
                                        <spring:message code="ADDRESS"/>
                                    </th>
                                    <th>
                                        <spring:message code="MONEY"/>
                                    </th>
                                    <th>
                                        <spring:message code="PRE_SEND_DATE"/>
                                    </th>
                                    <th>
                                        <spring:message code="PRE_SEND_TIME"/>
                                    </th>
                                    <th>
                                        <spring:message code="STAT"/>
                                    </th>
                                    <th>
                                        <spring:message code="OPERATE"/>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result}" var="item" varStatus="status">
                                    <tr class="">
                                        <td>
                                            <label class="label_check">
                                                <input value="${item.orderId}" name="foodId"
                                                       id="checkbox${item.orderId}" type="checkbox">
                                            </label>
                                        </td>
                                        <td>
                                            ${status.index+1}
                                        </td>
                                        <td>
                                                ${item.consigneePhone }
                                        </td>
                                        <td>
                                                ${item.consigneeName }
                                        </td>
                                        <td>
                                                ${item.consigneeAddr}
                                        </td>
                                        <td>
                                                ${item.money}
                                        </td>
                                        <td>
                                                ${item.preSendDate}
                                        </td>

                                        <td>
                                                ${item.preSendTime}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.status eq 'DRAFT'}">
                                                    <a style="color:#00CC00"><spring:message  code="ORDER_DRAFT" /></a>
                                                </c:when>
                                                <c:when test="${item.status eq 'DISTRIBUTION'}">
                                                <a style="color:#CC3300"><spring:message  code="ORDER_DISTRIBUTION" /></a>
                                                </c:when>
                                                <c:when test="${item.status eq 'FINISH'}">
                                                <a style="color:#CCCCCC"><spring:message  code="ORDER_FINISH" /></a>
                                                </c:when>
                                                <c:otherwise>
                                                <a style="color:#CCCCCC"><spring:message code="UNKNOWN"/></a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <input type = "hidden" name = "orderId" value = "${item.orderId}"/>
                                            <input type = "hidden" name = "index" value  = "${status.index+1}" />
                                            <input type = "hidden" name = "orderNo" value  = "${item.orderNo}" />

                                            <c:choose>
                                                <c:when test="${item.status eq 'DRAFT'}">
                                                    <button title="<spring:message code="ORDER_ACCEPT"/>" class="btn btn-success btn-xs" name="orderAccept">
                                                        <i class="fa fa-fighter-jet"></i>
                                                    </button>
                                                    <button title="<spring:message code="ORDER_CANCEL"/>" class="btn btn-danger btn-xs" name="orderCancel">
                                                        <i class="fa fa-undo"></i>
                                                    </button>
                                                </c:when>
                                                <c:when test="${item.status eq 'DISTRIBUTION'}">
                                                    <button title="<spring:message code="ORDER_FINISH"/>" class="btn btn-danger btn-xs" name="orderFinish">
                                                        <i class="fa fa-power-off"></i>
                                                    </button>
                                                </c:when>
                                            </c:choose>
                                            <button title="<spring:message code="ORDER_DETAIL"/>" class="btn btn-primary btn-xs" name="showDetail">
                                                <i class="fa fa-info-circle"></i>
                                            </button>
                                            <button title="<spring:message code="PRINT"/>" class="btn btn-info btn-xs" name="showPrint">
                                                <i class="fa fa-print"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <%@ include file="/manage/common/page.jsp" %>
                        </form>
                    </div>
                </div>
            </section>
        </div>
    </div>
</section>
<!--仅仅使用于改页面的JS-->
<%@ include file="/manage/common/js.jsp" %>

<!--script for this page only-->
<script src="${ctx }/manage/skin/default/js/table/jquery.dataTables.js"></script>
<script src="${ctx }/manage/skin/default/js/table/DT_bootstrap.js"></script>
<script src="${ctx }/manage/js/basic/order.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function () {
        BelOrder.init();
    });
</script>
</body>
</html>
