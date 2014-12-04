<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/mobile/common/taglibs.jsp" %>
<%@ include file="/mobile/common/meta.jsp" %>
<%@ include file="/mobile/common/js.jsp" %>

<!DOCTYPE html>
<html>
<body class="has-js">

<section class="panel">
<div class="panel-body">
<form id="iForm" method="POST" role="form" class="form-horizontal cmxform" novalidate="novalidate">
    <div class="row border-bottom">
             <label class="col-lg-16 control-label">
                 满满的一车子美食
             </label>
             <label class="col-lg-8 control-label">
                    总计：<a id = "sumMoney" style = "font-weight: bold;font-size: large;color: red">0</a>元
             </label>
    </div>

    <div id="shopCartList">

    </div>

    <div class="row">
        <div class="col-lg-24">
            <div class="form-group">
                <label class="col-lg-5 control-label">
                    手机：
                </label>

                <div class="col-lg-19">
                    <input type="text" placeholder="" id="phone"
                           name="phone" class="form-control" style="margin:0px;" value="">
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-24">
            <div class="form-group">
                <label class="col-lg-5 control-label">
                    姓名：
                </label>

                <div class="col-lg-19">
                    <input type="text" placeholder="" id="userName"
                           name="userName" class="form-control" style="margin:0px;" value="">
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-24">
            <div class="form-group">
                <label class="col-lg-5 control-label">
                    城市：
                </label>

                <div class="col-lg-19">
                    <select class="form-control" id = "city" name = "city">
                        <option value = "0">郑州市</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-24">
            <div class="form-group">
                <label class="col-lg-5 control-label">
                    区域：
                </label>

                <div class="col-lg-19">
                    <select class="form-control" id = "area" name = "area">
                        <option value = "-1">请选择</option>
                        <option value = "金城国际">金城国际</option>
                        <option value = "财富广场">财富广场</option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-24">
            <div class="form-group">
                <label class="col-lg-5 control-label">
                    地址：
                </label>

                <div class="col-lg-19">
                    <textarea type="text" placeholder="" id="address"
                           name="address" class="form-control" style="margin:0px;" value=""></textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-24">
            <div class="form-group">
                <label class="col-lg-5 control-label">
                   日期：
                </label>
                <div class="col-lg-19">
                    <input type="text" placeholder="" id="preSendDateShow"
                           name="preSendDateShow" class="form-control" style="margin:0px;" value="">
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-24">
            <div class="form-group">
                <label class="col-lg-5 control-label">
                    送达：
                </label>
                <input type = "hidden" id = "preSendDate" name = "preSendDate" />
                <div class="col-lg-19">
                    <select class="form-control" id = "preSendTime" name = "preSendTime">

                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-24">
            <div class="form-group">
                <label class="col-lg-5 control-label">
                    备注：
                </label>
                <div class="col-lg-19">
                    <textarea type="text" placeholder="" id="remark"
                              name="remark" class="form-control" style="margin:0px;" value=""></textarea>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="margin: 0 auto 10px auto;">
        <button class="col-lg-24 btn btn-primary btn-large" type="submit" id="buyNowButton">
            提交
        </button>
    </div>
</form>
    </div>
</section>

<script src="${ctx }/mobile/js/mobile/buyNow.js"></script>
<script>
    jQuery(document).ready(function () {
        BelOrderNow.init();
    });
</script>
</body>
</html>
