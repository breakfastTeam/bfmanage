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
                    <spring:message code="SET_MEAL_MANAGE"/>
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
                    <spring:message code="SET_MEAL_LIST"/>
                </header>
                <div class="panel-body">
                    <div class="adv-table editable-table ">
                        <form action="toSetMeal.do" id="iForm" method="GET">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div>
                                        <spring:message code="SET_MEAL_NAME"/>
                                        ：
                                        <label>
                                            <div>
                                                <input type="text" name="setName" id="setName"
                                                       aria-controls="editable-sample" class="form-control medium"
                                                       value="${setName }">
                                            </div>
                                        </label>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="pull-right">
                                        <button class="btn btn-success" type="button" id="toSetMealAdd">
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
                                    id="setMealTable">
                                <thead>
                                <tr>
                                    <th class="first-td">
                                        <label class="label_check" for="selectAll">
                                            <input name="selectAll" id="selectAll" type="checkbox">
                                        </label>
                                    </th>
                                    <th>
                                        <spring:message code="SET_MEAL_NAME"/>
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
                                                <input value="${item.setMealId}" name="setMealId"
                                                       id="checkbox${item.setMealId}" type="checkbox">
                                            </label>
                                        </td>
                                        <td>
                                                ${item.setName }
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
                                            <c:choose>
                                                <c:when test="${item.status eq 'PUTAWAY'}">
                                                    <a style="color:#00CC00">
                                                        <spring:message code="PUTAWAY" />
                                                    </a>
                                                </c:when>
                                                <c:when test="${item.status eq 'SOLDOUT'}">
                                                    <a style="color:#CC3300">
                                                        <spring:message code="SOLDOUT" />
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
                                            <input type = "hidden" value = "${item.setMealId}">
                                            <c:choose>
                                                <c:when test="${item.status eq 'PUTAWAY'}">
                                                    <button class="btn btn-danger btn-xs" name = "soldout">
                                                        <i class="fa fa-arrow-down"></i>
                                                    </button>
                                                </c:when>
                                                <c:when test="${item.status eq 'SOLDOUT'}">
                                                    <button class="btn btn-success btn-xs" name = "putaway">
                                                        <i class="fa fa-arrow-up"></i>
                                                    </button>
                                                </c:when>
                                            </c:choose>
                                            <button class="btn btn-primary btn-xs" name = "setMealEdit">
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
<script src="${ctx }/manage/js/basic/setMeal.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function () {
        BelSetMeal.init();
    });
</script>
</body>
</html>
