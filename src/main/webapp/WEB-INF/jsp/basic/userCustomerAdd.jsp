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
                    <spring:message code="USER_CUSTOMER_MANAGE"/>
                </li>
                <li class="active">
                    <c:choose>
                        <c:when test="${userCustomerDTO.user.userId == null}">
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
                        <c:when test="${userCustomerDTO.user.userId == null}">
                            <spring:message code="ADD_USER_CUSTOMER"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="EDIT_USER_CUSTOMER"/>
                        </c:otherwise>
                    </c:choose>

                </header>
                <div class="panel-body">

                    <form id="iForm" method="POST" role="form" class="form-horizontal cmxform" novalidate="novalidate">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="LOGIN_ACCOUNT"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="loginName" name="loginName"
                                               class="form-control" value="${userCustomerDTO.user.loginName}">
                                        <input type="hidden" id="userId" name="userId"
                                               class="form-control" value="${userCustomerDTO.user.userId}">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="PHONE"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="mobile" name="mobile"
                                               class="form-control default-date-picker"
                                               value="${userCustomerDTO.user.mobile}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="PASSWORD"/>：</label>
                                    <div class="col-lg-18">
                                        <input id="password" name="password" type = "password"
                                               class="form-control" value="${userCustomerDTO.user.password}">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="CONRIM_PASSWORD"/>：</label>

                                    <div class="col-lg-18">
                                        <input placeholder="" id="confirmPassword" name="confirmPassword" type = "password"
                                               class="form-control"
                                               value="${userCustomerDTO.user.password}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="NAME"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="userName" name="userName"
                                               class="form-control" value="${userCustomerDTO.user.userName}">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="QQ"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="qq" name="qq"
                                               class="form-control default-date-picker"
                                               value="${userCustomerDTO.user.qq}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="WEIXIN"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="weixin" name="weixin"
                                               class="form-control" value="${userCustomerDTO.user.weixin}">
                                    </div>
                                </div>
                            </div>
                            <%--<div class="col-lg-12">--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-lg-6 control-label"><spring:message code="REGISTER_WAY"/>：</label>--%>

                                    <%--<div class="col-lg-18">--%>
                                        <%--<input type="text" placeholder="" id="registerWay" name="registerWay"--%>
                                               <%--class="form-control default-date-picker"--%>
                                               <%--value="${user.registerWay}">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="ADDRESS_ONE"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="address1" name="address1"
                                               class="form-control" value="${userCustomerDTO.customer.address1}">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="ADDRESS_TWO"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="address2" name="address2"
                                               class="form-control" value="${userCustomerDTO.customer.address2}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="ADDRESS_THREE"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="address3" name="address3"
                                               class="form-control" value="${userCustomerDTO.customer.address3}">
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="CUSTOMER_LEVEL"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="customerLevel" name="customerLevel"
                                               class="form-control" value="${userCustomerDTO.customer.customerLevel}">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="CUSTOMER_CREDIT"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="credits" name="credits"
                                               class="form-control" value="${userCustomerDTO.customer.credits}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="REFERRER"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="referrer" name="referrer"
                                               class="form-control" value="${userCustomerDTO.customer.referrer}">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="RECOMMEND_TIME"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="recommendTime" name="recommendTime"
                                               class="form-control" value="${userCustomerDTO.customer.recommendTime}">
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
<script src="${ctx }/manage/js/basic/userCustomerAdd.js"></script>
<script>
    jQuery(document).ready(function () {
        BelUserCustomerAdd.init();
    });
</script>
</body>
</html>