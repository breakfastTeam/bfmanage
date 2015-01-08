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
                    <spring:message code="ELEMENT_MANAGE"/>
                </li>
                <li class="active">
                    <c:choose>
                        <c:when test="${element.elementId == null}">
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
                        <c:when test="${element.elementId == null}">
                            <spring:message code="ADD_ELEMENT"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="EDIT_ELEMENT"/>
                        </c:otherwise>
                    </c:choose>

                </header>
                <div class="panel-body">

                    <form id="iForm" method="POST" role="form" class="form-horizontal cmxform" novalidate="novalidate">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="ELEMENT_NAME"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="elementName" name="elementName"
                                               class="form-control" value="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="UNIT"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="unit" name="unit"
                                               class="form-control"
                                               value="毫克">
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
<script src="${ctx }/manage/js/basic/elementAdd.js"></script>
<script>
    jQuery(document).ready(function () {
        BelElementAdd.init();
    });
</script>
</body>
</html>