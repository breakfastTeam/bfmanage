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

    <div class="row">
        <div class="col-lg-24">
            <!-- 通知消息类效果 开始-->
            <section class="panel">

                <div class="panel-body">
                    <table
                            class="table table-striped table-hover table-bordered table-advance"
                            id="foodListTable">
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
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${foods}" var="item" varStatus="status">
                            <tr class="">
                                <td>
                                    <label class="label_check">
                                        <input value="${item.foodName}" name="foodId"
                                               id="${item.foodId}" type="checkbox">
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
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="col-lg-24">
                        <div class="pull-right">
                            <button class="btn btn-success" id="OK">
                                <spring:message code="CONFIRM"/>
                            </button>

                            <button class="btn btn-danger" id = "close">
                                <spring:message code="CLOSE"/>
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
<script src="${ctx }/manage/js/basic/foodList.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function () {
        BelFoodList.init();
    });
</script>
</body>
</html>
