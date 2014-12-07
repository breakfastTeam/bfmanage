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
                    <spring:message code="INFORMATION_MANAGE"/>
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
                    <spring:message code="INFORMATION_LIST"/>
                </header>
                <div class="panel-body">
                    <div class="adv-table editable-table ">
                        <form action="toInformation.do" id="iForm" method="GET">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div>
                                        <spring:message code="INFORMATION_TITLE"/>
                                        ：
                                        <label>
                                            <div>
                                                <input type="text" name="title" id="title"
                                                       aria-controls="editable-sample" class="form-control medium"
                                                       value="${title }">
                                            </div>
                                        </label>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="pull-right">
                                        <button class="btn btn-success" type="button" id="toInformationAdd">
                                            <i class="fa fa-plus"></i>
                                            <spring:message code="ADD"/>
                                        </button>

                                        <button class="btn btn-primary">
                                            <i class="fa fa-search"></i>
                                            <spring:message code="QUERY"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-striped table-hover table-bordered table-advance"
                                    id="informationTable">
                                <thead>
                                <tr>
                                    <th class="first-td">
                                        <label class="label_check" for="selectAll">
                                            <input name="selectAll" id="selectAll" type="checkbox">
                                        </label>
                                    </th>
                                    <th>
                                        <spring:message code="INFORMATION_TITLE"/>
                                    </th>
                                    <th>
                                        <spring:message code="PUBLIS_TIME"/>
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
                                                <input value="${item.informationId}" name="informationId"
                                                       id="checkbox${item.informationId}" type="checkbox">
                                            </label>
                                        </td>
                                        <td>
                                                ${item.title }
                                        </td>
                                        <td>
                                                ${item.postTime }
                                        </td>

                                        <td>
                                                ${item.status}
                                        </td>
                                        <td>
                                            <input type = "hidden" value = "${item.informationId}">
                                            <button class="btn btn-primary btn-xs" name = "informationEdit">
                                                <i class="fa fa-pencil"></i>
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
<script src="${ctx }/manage/js/basic/information.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function () {
        BelInformation.init();
    });
</script>
</body>
</html>
