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
                    <spring:message code="INFORMATION_MANAGE"/>
                </li>
                <li class="active">
                    <c:choose>
                        <c:when test="${information.informationId == null}">
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
                        <c:when test="${information.informationId == null}">
                            <spring:message code="ADD_INFORMATION"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="EDIT_INFORMATION"/>
                        </c:otherwise>
                    </c:choose>

                </header>
                <div class="panel-body">

                    <form id="iForm" method="POST" role="form" class="form-horizontal cmxform" novalidate="novalidate">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="INFORMATION_TITLE"/>：</label>
                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="title" name="title"
                                               class="form-control" value="${information.title}">
                                        <input type="hidden" id="informationId" name="informationId"
                                               class="form-control" value="${information.informationId}">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="PUBLIS_TIME"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="postTime" name="postTime"
                                               class="form-control default-date-picker"
                                               value="${information.postTime}">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-24">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label"><spring:message code="BRIEF_INTRO"/>：</label>

                                    <div class="col-lg-21">
                                        <!--style给定宽度可以影响编辑器的最终宽度-->
                                        <script type="text/plain" id="contentEditor" style="width:99%;height:100px;"></script>
                                        <textarea rows="0" cols="0" class="form-control" id="content"
                                                  name="content" style="display:none;"></textarea>
                                        <textarea rows="0" cols="0" class="form-control" id="contentContent"
                                                  name="contentContent" style="display:none;">${information.content}</textarea>
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
<script src="${ctx }/manage/js/basic/informationAdd.js"></script>
<script>
    jQuery(document).ready(function () {
        BelInformationAdd.init();
    });
</script>
</body>
</html>