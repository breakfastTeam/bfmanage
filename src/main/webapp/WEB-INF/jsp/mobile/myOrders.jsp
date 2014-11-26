<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/mobile/common/taglibs.jsp" %>
<%@ include file="/mobile/common/meta.jsp" %>
<%@ include file="/mobile/common/js.jsp" %>

<!DOCTYPE html>
<html>
<body class="has-js">

<section class="panel">
<div class="panel-body">
<form id="iForm" role="form" class="form-horizontal cmxform" novalidate="novalidate">

</form>
    <div class="row" style="margin: 0 auto 10px auto;">
        <button class="col-lg-24 btn btn-primary btn-large" type="submit" id="returnButton">
            返回首页
        </button>
    </div>
    </div>
</section>

<script src="${ctx }/mobile/js/mobile/myOrders.js"></script>
<script>
    jQuery(document).ready(function () {
        BelMyOrder.init();
    });
</script>
</body>
</html>
