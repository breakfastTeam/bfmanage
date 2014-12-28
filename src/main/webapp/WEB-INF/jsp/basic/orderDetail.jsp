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
                            id="orderDetailTable">
                        <thead>
                        <tr>
                            <th>
                                <spring:message code="ORDER_NO"/>
                            </th>

                            <th>
                                <spring:message code="FOOD_NAME"/>
                            </th>
                            <th>
                                <spring:message code="PRICE"/>
                            </th>
                            <th>
                                <spring:message code="NUM"/>
                            </th>

                            <th>
                                <spring:message code="SUM"/>
                            </th>
                            <th>
                                <spring:message code="ORDER_TIME"/>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orderDetails}" var="item" varStatus="status">
                            <tr class="">
                                <td>${orderNo}</td>
                                <c:choose>
                                <c:when test="${item.food.foodName eq null}">
                                    <td>
                                                    ${item.setMeal.setName }
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                            ${item.food.foodName }
                                    </td>
                                </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${item.food.price eq null}">
                                        <td>

                                                        ${item.setMeal.price }
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                                ${item.food.price }
                                        </td>
                                    </c:otherwise>
                                </c:choose>

                                <td>
                                        ${item.orderDetail.foodObjCount}
                                </td>

                                <c:choose>
                                    <c:when test="${item.food.price eq null}">
                                        <td>
                                                ${item.setMeal.price*item.orderDetail.foodObjCount}
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                                ${item.food.price*item.orderDetail.foodObjCount}
                                        </td>

                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    ${item.orderDetail.createTime}
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
