var BelAddCookBook = function () {
    return {
        //主模块初始化
        init: function () {
             var um = UM.getEditor('briefIntroEditor');
             bundleDatepicker();
             $("#save").click(function(){
             	var content = um.getContent();
             	$("#briefIntro").val(content);
             	$("#briefIntroContent").val(um.getContentTxt());
             	if(um.getContentTxt().length>400){
             		iDialog.iAlert("简介内容不能够超过400个字");
             		return ;
             	}
				$("#iForm").validate({
						 rules: {
				                cookBookName: {
				                	required: true
				                },
				                price: {
				                	number: true,
				                    required: true
				                },
				                validTime:{
				                	required:true,
				                	date:true
				                },
				                exchangeMoney:{
				                	number: true
				                },
				                briefIntroContent:{
				                	maxlength:400
				                }
				                
				       },
			           messages: {
				                cookBookName: "请输入菜谱名称",
				                price: "请输入数字",
				                validTime:{
		                    		required: "开始时间不能为空"
		                		},
		                		exchangeMoney:{
				                	number: "请输入数字"
				                },
				                briefIntroContent:{
				                	maxlength:"内容不允许超过400字"
				                }
			            },
			         submitHandler: function(form) {
			         	form.action = "saveCookBook.do";
	         			form.submit();
					 }
			     });
			});
             
             
             $('#cookBookPicUpload').fileupload({
		        url: "uploadCookBookPic.do",
		        dataType: 'json',
		        done: function (e, data) {
		        	var fileName = data.result.body.fileName;
		        	var filePath = data.result.body.filePath;
		        	var saveDiskPath = data.result.body.saveDiskPath;
		        	var cropFileName = data.result.body.cropFileName;
		        	$("#fileName").val(fileName);
        			$("#filePath").val(filePath);
        			$("#saveDiskPath").val(saveDiskPath);
        			$("#cropFileName").val(cropFileName);
        			
		        	$('<p/>').text(fileName).appendTo('#cookBookPicName');
		        	$("#cookBookPicDelButton").removeClass("display-none");
		        	$("#cookBookPicEditButton").removeClass("display-none");
		        	openImageCrop(filePath, cropFileName);
		        },
		        progressall:function (e, data) {
		            var progress = parseInt(data.loaded / data.total * 100, 10);
		            $('#cookBookPicProgress .progress-bar').css(
		                'width',
		                progress + '%'
		            );
		            if(progress==100){
		            	setTimeout(function(){
		            		$('#cookBookPicProgress .progress-bar').fadeOut();
		            	},500);
		            }
		        }
		    });
		    
		    function bundleDatepicker(){
        		 $('.default-date-picker').datepicker({
            		format: 'yyyy-mm-dd'
        		 });
        	}
		    $("#isTimeLimitBuyYes").click(function(){
		    	$("#isTimeLimitBuyYesInput").val("on");
		    	$("#isTimeLimitBuyNoInput").val("on");
		    	$("#exchangeMoney").removeAttr("disabled");
		    });
		    $("#isTimeLimitBuyNo").click(function(){
		    	$("#isTimeLimitBuyNoInput").val("off");
		    	$("#isTimeLimitBuyYesInput").val("off");
		    	$("#exchangeMoney").attr("disabled",true);
		    });
		    
		    $("#cookBookPicEditButton").click(function(){
		    	var filePath = $("#filePath").val();
		    	var cropFileName = $("#cropFileName").val(cropFileName);
		    	openImageCrop(filePath, cropFileName);
		    });
		    
		    $("#cookBookPicDelButton").click(function(){
		    	var btnObj = this;
		    	var saveDiskPath = $("#saveDiskPath").val();
		    	var cropFileName = $("#cropFileName").val();
		    	
		    	var map = new Map();
				map.put("filePath", saveDiskPath);
				map.put("cropFileName", cropFileName);
				var reqData = iReqMsg.getReqMsg(map);
		    	$.ajax({
            		url:"deleteCookBook.do",
					type:"POST",
					data:reqData,
					dataType:"json",
					contentType:"text/plain",
					beforeSend:function(){
						iDialog.iLoading("yes",btnObj);
					},
					success:function(data){	
						iDialog.iLoading("no",btnObj);
						if(data.head.rtnCode=="888888"){
							iDialog.iAlert(SUCCESS);
							$("#fileName").val("");
        					$("#filePath").val("");
        					$("#saveDiskPath").val("");
        					$("#cookBookPicName").remove();
						}else{
							iDialog.iAlert(FAIL);
						}
					}
            	});
		    });
		    
		    function openImageCrop(path, cropFileName){
		    	iDialog.iWindow("../openImageCrop.do?path="+path+"&cropFileName="+cropFileName, IMAGE_CROP);
		    }
        }
    };
}();
