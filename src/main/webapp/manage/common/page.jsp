<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/common/taglibs.jsp"%>
<%@ include file="/manage/common/meta.jsp"%>
<%@ include file="/manage/common/js.jsp"%>

<html>
	<head>
		<title>分页效果</title>
		<script>
			$(function(){
				var pageNo = parseInt($("#pageNo").val());//当前页数
				var totalCount = parseInt($("#totalCount").val());//总数据量
				var pageSize = parseInt($("#pageSize").val());//页码大小
				var totalPages = parseInt($("#totalPages").val());//总页数
				
				var pageStart = (pageNo - 1)*pageSize + 1;//开始条数
				var pageEnd = 1; //结束条数
				if(totalCount >= pageSize*pageNo){
					pageEnd = pageSize*pageNo;
				}else{
				 	pageEnd = totalCount;
				}
				$("#pageStart").text(pageStart);
				$("#pageEnd").text(pageEnd);
				if(pageNo<=1){
					$("#indexPage").addClass("disabled");
					$("#indexPage").find(">a").attr("href","javascript:void(0)");
					$("#prevPage").addClass("disabled")
					$("#prevPage").find(">a").attr("href","javascript:void(0)");
				}
				if(pageNo >= totalPages){
					$("#endPage").addClass("disabled");
					$("#endPage").find(">a").attr("href","javascript:void(0)");
					$("#nextPage").addClass("disabled")
					$("#nextPage").find(">a").attr("href","javascript:void(0)");
				}
				
				var bigPageSize = $("li[li-type='singlePage']").size();
				var bigTotalPages = parseInt(totalPages / bigPageSize);
				if (totalPages % bigPageSize > 0) {
					bigTotalPages++;
				}
				var bigPageNo = parseInt((pageNo - 1)/bigPageSize)+ 1;
				var bigPageStart = (bigPageNo - 1)*bigPageSize + 1;//开始条数
				var bigPageEnd = 1;
				if(totalPages >= bigPageSize*bigPageNo){
					bigPageEnd = bigPageSize*bigPageNo;
				}else{
					bigPageEnd = totalPages;
				}
				
				
				$("li[li-type='singlePage']").each(function(i){
					if(i >= (bigPageEnd - (bigPageNo - 1)*bigPageSize)){
						$(this).hide();
					}
					if(((bigPageNo-1)*bigPageSize+i)==(pageNo-1)){
						$(this).attr("class","disabled");
						$(this).find(">a").attr("href","javascript:void(0)");
					}
					$(this).find(">a").text((bigPageNo-1)*bigPageSize+i+1);
				});
				
			});
			function jumpPage(pageNo){
				$("#pageNo").val(pageNo);
				$("#iForm").submit();
			}
		</script>
	</head>
	<body>
		<div class="row">
			<div class="col-lg-12">
				<div class="dataTables_info" id="editable-sample_info">
					从第 <span id = "pageStart">1</span> 条至第 <span id = "pageEnd">1</span> 条 共 ${page.totalPages } 页 ${page.totalCount} 条
				</div>
			</div>
			<div class="col-lg-12">
				<input type = "hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/><!-- 当前页 -->
				<input type = "hidden" name="totalCount" id="totalCount" value="${page.totalCount}"/><!-- 总条数 -->
				<input type = "hidden" name = "pageSize" id = "pageSize" value = "${page.pageSize }" /><!-- 每页大小 -->
				<input type = "hidden" name = "totalPages" id = "totalPages" value = "${page.totalPages }" /><!-- 总页数 -->
				
				<div class="dataTables_paginate paging_bootstrap pagination">
					<ul>
						<li id = "indexPage" class="prev">
							<a href="javascript:jumpPage(1)">
							 	首页
							</a>
						</li>
						<li id = "prevPage" class="prev">
							<a href="javascript:jumpPage(${page.prePage})">
							 	上一页
							</a>
						</li>
						<li li-type = "singlePage" id = "firstPage" class="active">
							<a href="javascript:jumpPage(1)">
								1
							</a>
						</li>
						<li li-type = "singlePage" id = "secondPage" class="active">
							<a href="javascript:jumpPage(2)">
								2
							</a>
						</li>
						<li li-type = "singlePage" id = "thirdPage" class="active">
							<a href="javascript:jumpPage(3)">
								3
							</a>
						</li>
						<li li-type = "singlePage" id = "forthPage" class="active">
							<a href="javascript:jumpPage(4)">
								4
							</a>
						</li>
						<li li-type = "singlePage" id = "fifthPage" class="active">
							<a href="javascript:jumpPage(5)">
								5
							</a>
						</li>
						<li id = "nextPage" class="next">
							<a href="javascript:jumpPage(${page.nextPage})">
								下一页 
							</a>
						</li>
						<li id = "endPage" class="next">
							<a href="javascript:jumpPage(${page.totalPages})">
								尾页
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>								
	</body>
</html>
