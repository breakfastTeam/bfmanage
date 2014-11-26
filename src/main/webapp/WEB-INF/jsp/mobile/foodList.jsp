<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/common/taglibs.jsp"%>
<%@ include file="/mobile/common/meta.jsp"%>
<%@ include file="/mobile/common/js.jsp"%>

<!DOCTYPE html>
<html>
	<body class="has-js">
		<div class="sprocket-mosaic">
			<ul class="sprocket-mosaic-container sprocket-mosaic-columns-5"
				id="foodList">

			</ul>
		</div>

		<!--<div class = "row" style = "padding-bottom:10px">
			<div class="col-lg-10">
				<a href="#" class="btn btn-info" >上一页</a>
			</div>
			<div class="col-lg-4">
				<a href="#" class="" >4/8</a>
			</div>
			<div class="col-lg-10">
				<a href="#" class="btn btn-info pull-right" >下一页</a>
			</div>
		</div>-->
		<script src="${ctx }/mobile/js/mobile/foodList.js"></script>
		<script>
           jQuery(document).ready(function() {
                BelFoodList.init();
           });
        </script>
	</body>
</html>
