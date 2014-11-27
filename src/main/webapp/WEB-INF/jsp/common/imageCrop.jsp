<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/common/taglibs.jsp"%>
<%@ include file="/manage/common/meta.jsp"%>
<!DOCTYPE html>
<html>
	<body class="has-js">
		<section class="wrapper">
		<div class="panel-body">
			<div class="row">
				<img class = "col-md-24" src="../${path }${cropFileName }"
						id="showImg" style="max-width: 400px;max-height:300px;" />
				<div class="col-md-6">
					<div id="preview-pane">
						<div class="preview-container">
							<img src="../${path }${cropFileName }"
								class="jcrop-preview" />
						</div>
					</div>
				</div>
			</div>
			<img class = "col-md-24" src="../${path }${cropFileName }"
						id="orginImg" style="display:none;" />
			<div class="row">
				<div class="col-lg-24">
					<div class="pull-right">
						<button class="btn btn-success" type="button" id = "cropImg">
							<spring:message code="SAVE" />
						</button>
					</div>
				</div>
			</div>
		</div>
		</section>
		<%@ include file="/manage/common/js.jsp"%>
		<script src="${ctx}/manage/js/common/imageCrop.js"></script>
		<script>
           jQuery(document).ready(function() {
               BelImageCrop.init();
           });
        </script>
	</body>
</html>