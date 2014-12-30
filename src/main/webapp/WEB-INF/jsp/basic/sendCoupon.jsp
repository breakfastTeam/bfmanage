<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/manage/common/taglibs.jsp" %>
<%@ include file="/manage/common/meta.jsp" %>
<!DOCTYPE html>
<html>

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
                    <spring:message code="SEND_COUPON_MANAGE"/>
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
                    <spring:message code="SEND_COUPON_LIST"/>
                </header>
                <div class="panel-body">
                    <div class="adv-table editable-table ">
                        <form action="toSendCoupon.do" id="iForm" method="GET">
                            <div class="row">
                                <div class="col-lg-24">
                                    <div class="pull-right">
                                        <button class="btn btn-success" type="button" id="toSendCouponAdd">
                                            <i class="fa fa-plus"></i>
                                            <spring:message code="ADD"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            </br>
                            <table class="table table-striped table-hover table-bordered table-advance"
                                    id="sendCouponTable">
                                <thead>
                                <tr>
                                    <th class="first-td">
                                        <label class="label_check" for="selectAll">
                                            <input name="selectAll" id="selectAll" type="checkbox">
                                        </label>
                                    </th>
                                    <th>
                                        <spring:message code="OWNER"/>
                                    </th>
                                    <th>
                                        <spring:message code="MONEY_NUM"/>
                                    </th>
                                    <th>
                                        <spring:message code="NUM"/>
                                    </th>
                                    <th>
                                        <spring:message code="AVERAGE_MONEY"/>
                                    </th>

                                    <th>
                                        <spring:message code="END_TIME"/>
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
                                                <input value="${item.sendCoupon.sendCouponId}" name="sendCouponId"
                                                       id="checkbox${item.sendCoupon.sendCouponId}" type="checkbox">
                                            </label>
                                        </td>
                                        <td>
                                                ${item.customerDTO.user.userName }
                                        </td>
                                        <td>
                                                ${item.sendCoupon.price }
                                        </td>
                                        <td>
                                                ${item.sendCoupon.num }
                                        </td>
                                        <td>
                                                ${item.sendCoupon.price/item.sendCoupon.num }
                                        </td>

                                        <td>
                                                ${item.sendCoupon.endTime }
                                        </td>

                                        <td>
                                            <c:choose>
                                                <c:when test="${item.sendCoupon.status eq 'ENABLE'}">
                                                    <a style="color:#00CC00">
                                                        <spring:message code="ENABLE" />
                                                    </a>
                                                </c:when>
                                                <c:when test="${item.sendCoupon.status eq 'DISCARD'}">
                                                    <a style="color:#CC3300">
                                                        <spring:message code="DISCARD" />
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a style="color:#CCCCCC">
                                                        <spring:message code="UNKNOWN" />
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <input type = "hidden" value = "${item.sendCoupon.sendCouponId}">

                                            <button class="btn btn-danger btn-xs" title="<spring:message code="DELETE" />" name = "sendCouponDiscard">
                                                <i class="fa fa-times-circle"></i>
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
<script src="${ctx }/manage/js/basic/sendCoupon.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function () {
        BelSendCoupon.init();
    });
</script>
</body>
</html>
