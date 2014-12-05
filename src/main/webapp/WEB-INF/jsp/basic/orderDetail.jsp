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
            <!-- 通知消息类效果 开始-->
            <section class="panel">

                <div class="panel-body">
                    <table
                            class="table table-striped table-hover table-bordered table-advance"
                            id="orderDetailTable">
                        <thead>
                        <tr>

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

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${foods}" var="item" varStatus="status">
                            <tr class="">

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

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </div>
    </div>
</section>
<!--script for this page only-->
<script src="${ctx }/manage/skin/default/js/table/jquery.dataTables.js"></script>
<script src="${ctx }/manage/skin/default/js/table/DT_bootstrap.js"></script>
<script src="${ctx }/manage/js/basic/orderDetail.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function () {
        BelOrderDetail.init();
    });
</script>
</body>
</html>
