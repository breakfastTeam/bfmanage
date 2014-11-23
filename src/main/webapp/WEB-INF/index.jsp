<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title><spring:message code="TITLE" />
		</title>
	</head>
	<body>
		<section id="container" class="">
		<!--header start-->
		<header class="header white-bg">
		<div class="sidebar-toggle-box">
			<div data-original-title="展开隐藏菜单" data-placement="right"
				class="fa fa-bars tooltips"></div>
		</div>
		<!--logo start-->
		<a href="${ctx}" class="logo"><span><spring:message
					code="TITLE" />
		</span>
		</a>
		<!--logo end-->
		<div class="top-nav ">
			<ul class="nav pull-right top-menu">
				<!-- user login dropdown start-->
				<li class="dropdown">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#"> <img
							alt="" src="img/head.png" /> <span class="username">Bel</span> <b
						class="caret"></b> </a>
					<ul class="dropdown-menu extended logout">
						<div class="log-arrow-up"></div>
						<li>
							<a href="#"><i class=" fa fa-suitcase"></i>资料</a>
						</li>
						<li>
							<a href="#"><i class="fa fa-cog"></i>设置</a>
						</li>
						<li>
							<a href="#"><i class="fa fa-bell-o"></i>通知</a>
						</li>
						<li>
							<a href="login.html"><i class="fa fa-key"></i>退出</a>
						</li>
					</ul>
				</li>
				<!-- user login dropdown end -->
			</ul>
		</div>
		</header>
		<!--header end-->
		<!--sidebar start-->
		<aside>
		<div id="sidebar" class="nav-collapse ">
			<!-- sidebar menu start-->
			<ul class="sidebar-menu" id="nav-accordion">
				<li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="fa fa-dashboard"></i>
                          <span><spring:message code="BASIC_FUN"/></span>
                      </a>
                      <ul class="sub">
                          <li class=""><a href="javascript:void(0)"  onclick = "openNewUrl('${ctx}/basic/toCookBook.do')"><spring:message code="COOK_BOOK_MANAGE"/></a></li>
                          <li class=""><a  href="javascript:void(0)" onclick = "openNewUrl('${ctx}/basic/toOrder.do')"><spring:message code="ORDER_MANAGE"/></a></li>
                          <li class=""><a  href="javascript:void(0)" onclick = "openNewUrl('${ctx}/basic/toCustomer.do')"><spring:message code="CUSTOMER_MANAGE"/></a></li>
                          <li class=""><a  href="javascript:void(0)" onclick = "openNewUrl('${ctx}/basic/toExpress.do')"><spring:message code="EXPRESS_MANAGE"/></a></li>
					  </ul>
                  </li>
			</ul>
			<!-- sidebar menu end-->
		</div>
		</aside>
		<!--sidebar end-->
		<iframe id="main-content" src="${ctx}/basic/toCookBook.do"
			frameborder="0" border="0" marginwidth="0" marginheight="0"></iframe>
		<!--footer start-->
		<footer class="site-footer">
		<div class="text-center">
			2014 &copy;
			<spring:message code="TITLE" />
		</div>
		</footer>
		<!--footer end-->
		</section>

		<script src="${ctx }/skin/default/js/jquery-1.8.3.js"></script>
		<script src="${ctx }/skin/default/js/bootstrap.js"></script>

		<script class="include" type="text/javascript"
			src="${ctx }/skin/default/js/jquery.dcjqaccordion.2.7.js"></script>
		<script src="${ctx }/skin/default/js/jquery.scrollTo.min.js"></script>
		<script src="${ctx }/skin/default/js/jquery.nicescroll.js"></script>
		<!--仅仅使用于该页面的JS-->
		<script src="${ctx }/js/basic/index.js"></script>
	</body>
</html>
