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
                    <spring:message code="FOOD_MANAGE"/>
                </li>
                <li class="active">
                    <c:choose>
                        <c:when test="${setMealDTO.setMeal.setMealId == null}">
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
                        <c:when test="${setMealDTO.setMeal.setMealId == null}">
                            <spring:message code="ADD_SET_MEAL"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="EDIT_SET_MEAL"/>
                        </c:otherwise>
                    </c:choose>

                </header>
                <div class="panel-body">

                    <form id="iForm" method="POST" role="form" class="form-horizontal cmxform" novalidate="novalidate">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="SET_MEAL_NAME"/>：</label>

                                    <div class="col-lg-18">
                                        <input type = "hidden" id = "setMealId" name = "setMealId" value ="${setMealDTO.setMeal.setMealId}">
                                        <input type="text" placeholder="" id="setName" name="setName"
                                               class="form-control" value="${setMealDTO.setMeal.setName}">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="PRICE"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" accuracy="2" id="price" name="price" class="form-control"
                                               value="${setMealDTO.setMeal.price}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="PRIVILEGE"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" accuracy="2" id="privilege" name="privilege" class="form-control"
                                               value="${setMealDTO.setMeal.privilege}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="FOOD_COUNT"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" id="foodCount" name="foodCount" class="form-control"
                                               value="${setMealDTO.setMeal.foodCount}">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message
                                            code="REAL_FOOD_COUNT"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="realFoodCount" name="realFoodCount"
                                               class="form-control" value="${setMealDTO.setMeal.realFoodCount}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <!--<div class="col-lg-12" style = "display: none;">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="SALE_TIME"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="saleTime" name="saleTime"
                                               class="form-control default-date-picker"
                                               value="${setMealDTO.setMeal.saleTime }">
                                    </div>
                                </div>
                            </div>-->
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="ORDER_NUM"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="showOrder" name="showOrder"
                                               class="form-control" value="${setMealDTO.setMeal.showOrder }">
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message
                                            code="EXCHANGE_MONEY"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="exchangePrice" name="exchangePrice"
                                               class="form-control" value="${setMealDTO.setMeal.exchangeCount}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="PUBLISH_TIME"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="startTime" name="startTime"
                                               class="form-control default-date-picker"
                                               value="${setMealDTO.setMeal.startTime }">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message code="PUT_OFF_TIME"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="endTime" name="endTime"
                                               class="form-control default-date-picker" value="${setMealDTO.setMeal.endTime }">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-24">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label"><spring:message code="FOOD_LIST"/>：</label>

                                    <div class="col-lg-20">
                                        <button class="btn btn-success pull-left" type="button" id="openFoodList">
                                            <i class="fa fa-plus"></i>
                                            <spring:message code="ADD"/>
                                        </button>
                                        <input id = "foodIds" name = "foodIds" type = "hidden" />
                                        <div id = "foodList">

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="rowle" style="display: none">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message
                                            code="IS_SUPPORT_SNAP_UP"/>：</label>

                                    <div class="col-lg-18">
                                        <div class="btn-group" data-toggle="buttons">
                                            <label class="btn btn-primary" id="isSupportSnapUpYes">
                                                <input type="radio" name="isSupportSnapUp" id="isSupportSnapYesInput"
                                                       value="off"><spring:message code="YES"/>
                                            </label>
                                            <label class="btn btn-primary active" id="isSupportSnapUpNo">
                                                <input type="radio" name="isSupportSnapUp" id="isSupportSnapUpNoInput"
                                                       value="off"><spring:message code="NO"/>
                                            </label>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message
                                            code="SNAP_UP_PRICE"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="snapUpPrice" name="snapUpPrice"
                                               class="form-control" value="" disabled>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="display:none;">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message
                                            code="IS_SUPPORT_EXCHANGE"/>：</label>

                                    <div class="col-lg-18">
                                        <div class="btn-group" data-toggle="buttons">
                                            <label class="btn btn-primary" id="isSupportExchangeYes">
                                                <input type="radio" name="isSupportExchange"
                                                       id="isSupportExchangeYesInput"
                                                       value="off"><spring:message code="YES"/>
                                            </label>
                                            <label class="btn btn-primary active" id="isSupportExchangeNo">
                                                <input type="radio" name="isSupportExchange"
                                                       id="isSupportExchangeNoInput"
                                                       value="off"><spring:message code="NO"/>
                                            </label>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--<div class="col-lg-12">
                                <div class="form-group">
                                    <label class="col-lg-6 control-label"><spring:message
                                            code="EXCHANGE_MONEY"/>：</label>

                                    <div class="col-lg-18">
                                        <input type="text" placeholder="" id="exchangePrice" name="exchangePrice"
                                               class="form-control" value="" disabled>
                                    </div>
                                </div>
                            </div>-->
                        </div>

                        <div class="row">
                            <div class="col-lg-24">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label"><spring:message code="PIC"/>(8:5)：</label>

                                    <div class="col-lg-20">
			                                      		<span class="btn btn-success fileinput-button pull-left">
										       				<i class="glyphicon glyphicon-plus"></i>
										       				<span><spring:message code="SELECT_FILE"/></span>
										       				<input id="setMealPicUpload" type="file" name="files"
                                                                   multiple>
										  				</span>
                                        <c:if test="${setMealDTO.smallPicPath != null}">
                                            <img id="previewSmallPic" style="width:55px;"
                                                 src="${ctx}/${setMealDTO.smallPicPath}">
                                        </c:if>

                                        <div id="setMealPicName" class="control-label pull-left"></div>
                                        <input id="diskPath" name="diskPath" type="hidden"/>
                                        <input id="fileName" name="fileName" type="hidden"/>
                                        <input id="orginPicPath" name="orginPicPath" type="hidden"
                                               value="${setMealDTO.orginPicPath}"/>
                                        <input id="orginPicId" name="orginPicId" type="hidden"
                                               value="${setMealDTO.orginPicId}"/>

                                        <input id="smallPicPath" name="smallPicPath" type="hidden"
                                               value="${setMealDTO.smallPicPath}"/>
                                        <input id="smallPicId" name="smallPicId" type="hidden"
                                               value="${setMealDTO.smallPicId}"/>

                                        <button id="setMealPicDelButton" type="button"
                                                class="btn btn-danger display-none">
                                            <i class="glyphicon glyphicon-trash"></i>
                                            <span><spring:message code="DELETE"/></span>
                                        </button>

                                        <div id="setMealPicProgress" class="progress control-label white-bg col-lg-10">
                                            <div class="progress-bar progress-bar-success"></div>
                                        </div>
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
                                        <script type="text/plain" id="introduceEditor" style="width:99%;height:100px;"></script>
                                        <textarea rows="0" cols="0" class="form-control" id="introduce"
                                                  name="introduce" style="display:none;"></textarea>
                                        <textarea rows="0" cols="0" class="form-control" id="introduceContent"
                                                  name="introduceContent" style="display:none;">${setMealDTO.setMeal.introduce}</textarea>
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
<script src="${ctx }/manage/js/basic/setMealAdd.js"></script>
<script>
    jQuery(document).ready(function () {
        BelSetMealAdd.init();
    });
</script>
</body>
</html>