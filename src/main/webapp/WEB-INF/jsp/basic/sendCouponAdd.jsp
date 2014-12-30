<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/manage/common/taglibs.jsp" %>
<%@ include file="/manage/common/meta.jsp" %>
<!DOCTYPE html>
<html>
<body class="has-js">
<section class="wrapper">
    <div class="row">
        <div class="col-lg-24">
            <!-- 位置指示标志 开始 -->
            <ul class="breadcrumb">
                <li>
                    <a href="#"><i class="fa fa-home"></i> <spring:message
                            code="HOME"/> </a>
                </li>
                <li>
                    <spring:message code="SEND_COUPON_MANAGE"/>
                </li>
                <li class="active">
                    <c:choose>
                        <c:when test="${sendCoupon == null}">
                            <spring:message code="ADD"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="EDIT"/>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
            <!--位置指示标志 结束 -->
        </div>
    </div>
    <div class="row">
        <div class="col-lg-24">
            <section class="panel">
                <header class="panel-heading">
                    <c:choose>
                        <c:when test="${sendCoupon.sendCouponId == null}">
                            <spring:message code="ADD_SEND_COUPON"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="EDIT_SEND_COUPON"/>
                        </c:otherwise>
                    </c:choose>

                </header>
                <div class="panel-body">
                    <form id="iForm" method="POST" role="form" class="form-horizontal cmxform" novalidate="novalidate">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="MONEY"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="price" name="price"
                                               class="form-control" value="${sendCoupon.price}">
                                        <input type="hidden" id="sendCouponId" name="sendCouponId"
                                               class="form-control" value="${sendCoupon.sendCouponId}">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="NUM"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="num" name="num"
                                               class="form-control" value="${sendCoupon.num}">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-24">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label"><spring:message code="RECEIVE_PEOPLE"/>：</label>

                                    <div class="col-lg-20">
                                        <button class="btn btn-success pull-left" type="button" id="showSendCouponCustomerList">
                                            <i class="fa fa-plus"></i>
                                            <spring:message code="ADD"/>
                                        </button>
                                        <input id = "uids" name = "uids" type = "hidden" />
                                        <div id = "customerList">

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-lg-24">
                                <div class="pull-right">
                                    <button class="btn btn-success" type="submit" id="save">
                                        <spring:message code="SAVE"/>
                                    </button>

                                    <button class="btn btn-danger" type="button" onclick="javascript:history.go(-1)">
                                        <spring:message code="BACK"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>
</section>
<%@ include file="/manage/common/js.jsp" %>
<script src="${ctx }/manage/js/basic/sendCouponAdd.js"></script>
<script>
    jQuery(document).ready(function () {
        BelSendCouponAdd.init();
    });
</script>
</body>
</html>