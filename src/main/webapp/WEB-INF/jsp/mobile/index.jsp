<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/mobile/common/taglibs.jsp" %>
<%@ include file="/mobile/common/meta.jsp" %>
<%@ include file="/mobile/common/js.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>粥妹只为温暖你的心</title>
</head>
<body class="has-js">
<div id="menuContainer" class="st-container st-effect-9" style = "overflow-y:auto">
    <nav class="st-menu st-effect-9" id="menu-9">
        <div class="logo"></div>
        <ul>
            <li>
                <a id="food" href="javascript:void(0)">
                    <div class="nav-icon nav-icon-home"></div>
                    首页</a>
            </li>
            <li>
                <a id = "myOrders" href = "javascript:void(0)">
                    <div class="nav-icon nav-icon-order"></div>订单</a>
            </li>
            <!--
            <li>
                <a id = "personalCenter" href = "javascript:void(0)">
                    <div class="nav-icon nav-icon-people"></div>我</a>
            </li>-->
        </ul>
    </nav>
    <section class="wrapper" style="background-color:#efefef" id="header">
        <div class="row">
            <div id="showMenu" class="col-lg-5">
                <button data-effect="st-effect-9" id="i-nav"></button>
            </div>
            <div id="header-content-mid" class="col-lg-14">
            </div>
            <div id="shopCart" class="col-lg-5">
                <button data-effect="st-effect-9" id="i-shop-cart"></button>
            </div>
        </div>
    </section>
    <div class="container" id="content" style="overflow-y:auto;padding-top: 10px;margin-top: 10px;">

    </div>
</div>
<%@ include file="/mobile/common/js.jsp" %>

<script src="${ctx }/mobile/js/mobile/index.js"></script>
</body>
</html>
