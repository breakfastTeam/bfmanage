<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/manage/common/taglibs.jsp" %>
<%@ include file="/manage/common/meta.jsp" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body class="has-js">
<!--main content start-->
<section class="wrapper">
    <div class="row" id = "tipsRow">
        <p class="text-center"><span id = "tips" style="color:red"></span></p>
    </div>
    <div class="row">
        <div class="col-lg-24">
            <!-- 通知消息类效果 开始-->
            <section class="panel">
                <div class="panel-body">
                    <input type="hidden" id = "orderId" name = "orderId" value="${orderId}">
                    <table
                            class="table table-striped table-hover table-bordered table-advance"
                            id="orderCourierTable">
                        <thead>
                        <tr>
                            <th class="first-td">

                            </th>
                            <th>
                                <spring:message code="LOGIN_ACCOUNT"/>
                            </th>
                            <th>
                                <spring:message code="PHONE"/>
                            </th>
                            <th>
                                <spring:message code="NAME"/>
                            </th>
                            <th>
                                <spring:message code="WEIXIN"/>
                            </th>
                            <th>
                                <spring:message code="REGIST_ITME"/>
                            </th>
                            <th>
                                <spring:message code="STAT"/>
                            </th>
                            <th>
                                <spring:message code="SOURCE"/>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userCouriers}" var="item" varStatus="status">
                            <tr class="">
                                <td>
                                    <label class="label_radio" for="radio${item.user.userId}">
                                        <input value="${item.user.userId}" name="userId"
                                               id="radio${item.user.userId}" type="radio">
                                    </label>
                                </td>
                                <td>
                                        ${item.user.loginName }
                                </td>
                                <td>
                                        ${item.user.mobile }
                                </td>
                                <td>
                                        ${item.user.userName }
                                </td>

                                <td>
                                        ${item.user.weixin }
                                </td>
                                <td>
                                        ${item.user.registerTime }
                                </td>
                                <td>
                                        ${item.courier.source}
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.user.status eq 'ENABLE'}">
                                            <a style="color:#00CC00">
                                                <spring:message code="ENABLE" />
                                            </a>
                                        </c:when>
                                        <c:when test="${item.user.status eq 'DISCARD'}">
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

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="col-lg-24">
                        <div class="pull-right">
                            <button class="btn btn-success" id="confirm">
                                <spring:message code="CONFIRM"/>
                            </button>

                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</section>
<!--script for this page only-->
<script src="${ctx }/manage/skin/default/js/table/jquery.dataTables.js"></script>
<script src="${ctx }/manage/skin/default/js/table/DT_bootstrap.js"></script>
<script src="${ctx }/manage/js/basic/orderCourier.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function () {
        BelOrderCourier.init();
    });
</script>
</body>
</html>
