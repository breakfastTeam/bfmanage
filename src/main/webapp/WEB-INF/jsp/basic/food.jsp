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
                    <spring:message code="FOOD_MANAGE"/>
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
                    <spring:message code="FOOD_LIST"/>
                </header>
                <div class="panel-body">
                    <div class="adv-table editable-table ">
                        <form action="toFood.do" id="iForm" method="GET">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div>
                                        <spring:message code="FOOD_NAME"/>
                                        ：
                                        <label>
                                            <div>
                                                <input type="text" name="foodName" id="foodName"
                                                       aria-controls="editable-sample" class="form-control medium"
                                                       value="${foodName }">
                                            </div>
                                        </label>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="pull-right">
                                        <button class="btn btn-success" type="button" id="toAddFood">
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
                            <table
                                    class="table table-striped table-hover table-bordered table-advance"
                                    id="foodTable">
                                <thead>
                                <tr>
                                    <th class="first-td">
                                        <label class="label_check" for="selectAll">
                                            <input name="selectAll" id="selectAll" type="checkbox">
                                        </label>
                                    </th>
                                    <th>
                                        <spring:message code="FOOD_NAME"/>
                                    </th>
                                    <th>
                                        <spring:message code="PRICE"/>
                                    </th>
                                    <th>
                                        <spring:message code="FOOD_COUNT"/>
                                    </th>
                                    <th>
                                        <spring:message code="REAL_FOOD_COUNT"/>
                                    </th>
                                    <th>
                                        <spring:message code="SALE_TIME"/>
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
                                                <input value="${item.foodId}" name="foodId"
                                                       id="checkbox${item.foodId}" type="checkbox">
                                            </label>
                                        </td>
                                        <td>
                                                ${item.foodName }
                                        </td>
                                        <td>
                                                ${item.price }
                                        </td>
                                        <td>
                                                ${item.foodCount}
                                        </td>
                                        <td>
                                                ${item.realFoodCount}
                                        </td>

                                        <td>
                                                ${item.saleTime}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.status eq 'PUTAWAY'}">
                                                    <a style="color:#00CC00">
                                                        <spring:message code="PUTAWAY" />
                                                    </a>
                                                </c:when>
                                                <c:when test="${item.status eq 'SOLD_PUT'}">
                                                    <a style="color:#CC3300">
                                                        <spring:message code="SOLD_PUT" />
                                                    </a>
                                                </c:when>
                                                <c:when test="${item.status eq 'DISCARD'}">
                                                    <a style="color:#CCCCCC">
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
                                            <input type = "hidden" value = "${item.foodId}">
                                            <button class="btn btn-primary btn-xs" name = "editFood">
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
<script src="${ctx }/manage/js/basic/food.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function () {
        BelFood.init();
    });
</script>
</body>
</html>
