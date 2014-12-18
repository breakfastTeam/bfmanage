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
                    <spring:message code="FEEDBACK_MANAGE"/>
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
                    <spring:message code="FEEDBACK_LIST"/>
                </header>
                <div class="panel-body">
                    <div class="adv-table editable-table ">
                        <form action="toFeedback.do" id="iForm" method="GET">

                            <table class="table table-striped table-hover table-bordered table-advance"
                                    id="feedbackTable">
                                <thead>
                                <tr>
                                    <th class="first-td">
                                        <label class="label_check" for="selectAll">
                                            <input name="selectAll" id="selectAll" type="checkbox">
                                        </label>
                                    </th>
                                    <th>
                                        <spring:message code="CONTENT"/>
                                    </th>
                                    <th>
                                        <spring:message code="TIME"/>
                                    </th>

                                    <th>
                                        <spring:message code="STAT"/>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.result}" var="item" varStatus="status">
                                    <tr class="">
                                        <td>
                                            <label class="label_check">
                                                <input value="${item.feedbackId}" name="feedbackId"
                                                       id="checkbox${item.feedbackId}" type="checkbox">
                                            </label>
                                        </td>
                                        <td>
                                                ${item.content }
                                        </td>
                                        <td>
                                                ${item.createTime }
                                        </td>

                                        <td>
                                            <c:choose>
                                                <c:when test="${item.status eq 'ENABLE'}">
                                                    <a style="color:#00CC00">
                                                        <spring:message code="ENABLE" />
                                                    </a>
                                                </c:when>
                                                <c:when test="${item.status eq 'DISABLE'}">
                                                    <a style="color:#CC3300">
                                                        <spring:message code="DISABLE" />
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
<script src="${ctx }/manage/js/basic/feedback.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function () {
        BelFeedback.init();
    });
</script>
</body>
</html>
