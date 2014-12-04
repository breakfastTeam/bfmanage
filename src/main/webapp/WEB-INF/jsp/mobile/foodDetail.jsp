<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/mobile/common/taglibs.jsp" %>
<%@ include file="/mobile/common/meta.jsp" %>
<%@ include file="/mobile/common/js.jsp" %>

<!DOCTYPE html>
<html>
<body class="has-js">
<form id="iForm" method="POST" role="form"
      class="form-horizontal cmxform" novalidate="novalidate">
    <div class="row">
        <div class="sprocket-mosaic-item">
            <div class="sprocket-padding">
                <div class="sprocket-mosaic-image-container">
                    <a href="javascript:void(0)"> <img id="originPicPath" original = "" src="${ctx}/mobile/skin/default/images/grey.gif" alt=""
                                                       class="sprocket-mosaic-image"> </a>
                </div>
                <div class="sprocket-mosaic-head">
                    <div class="sprocket-mosaic-title">
                        <a id = "foodName"></a>
                        <a id = "price" style = "float:right"></a>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-lg-24">
            <div class="form-group">
                <label class="col-lg-4 control-label">
                    <a href="javascript:void(0)" id = "plus" class="btn btn-primary btn-lg">+</a>
                </label>

                <div class="col-lg-16">
                    <input type="number" placeholder="" id="count"
                           name="count" class="form-control" style="margin:0px;text-align:center;" value="1">
                </div>

                <label class="col-lg-4 control-label">
                    <a href="javascript:void(0)" id = "minus" class="btn btn-primary btn-lg">-</a>
                </label>

            </div>
        </div>
    </div>

    <div class="row" style="margin: 0 auto;">
        <div class="col-lg-12">
            <a href="javascript:void(0)" sale-out="false" id="shopCartButton" class="col-lg-24 btn btn-primary btn-large">加入购物车</a>
        </div>
        <div class="col-lg-12">
            <a href="javascript:void(0)" sale-out="false" id="buyNowButton" class="col-lg-24 btn btn-warning btn-large">立即购买</a>
        </div>

    </div>
    <div class="row border-top" style="margin-top:10px;" id="foodBriefIntro">

    </div>
</form>
<script src="${ctx }/mobile/js/mobile/foodDetail.js"></script>
<script>
    jQuery(document).ready(function () {
        BelFoodDetail.init();
    });
</script>
</body>
</html>
