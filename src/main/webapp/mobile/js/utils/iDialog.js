(function($){
	var bodyObj = $("body");
	$.iDialog = function(){
		this.backdrops = new Array();
		this.dialogs = new Array(),
		this.backdrop = '<div id = "backdrop" class="modal-backdrop fade" style = "display:none;"></div>',
	    this.itip = '<div div-type = "idialog" dialog-type = "ialert" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">'+
							'<div class="modal-dialog">'+
								'<div class="modal-content">'+
									'<div class="modal-header">'+
											'<h4 class="modal-title" style = "color:white">'+TIPS+'</h4>'+
									'</div>'+
									'<div class="modal-body">'+EMPTY_CONTENT+'</div>'+
								'</div>'+
							'</div>'+
						'</div>',
		this.ialert = '<div div-type = "idialog" dialog-type = "ialert" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">'+
                           '<div class="modal-dialog">'+
                                 '<div class="modal-content">'+
                                       '<div class="modal-header">'+
                                             '<button class="close" idialog-data-dismiss="modal" aria-hidden="true">&times;</button>'+
                                             '<h4 class="modal-title"  style = "color:white">'+TIPS+'</h4>'+
                                        '</div>'+
                                        '<div class="modal-body">'+EMPTY_CONTENT+'</div>'+
                                          '<div class="modal-footer">'+
                                              '<button idialog-data-dismiss="modal" class="btn btn-success">'+CONFIRM+'</button>'+
                                          '</div>'+
                                      '</div>'+
                                  '</div>'+
                              '</div>',
         this.iconfirm =  '<div div-type = "idialog" dialog-type = "iconfirm" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">'+
                           '<div class="modal-dialog">'+
                                 '<div class="modal-content">'+
                                       '<div class="modal-header">'+
                                             '<button class="close" idialog-data-dismiss="modal" aria-hidden="true">&times;</button>'+
                                             '<h4 class="modal-title"  style = "color:white">'+TIPS+'</h4>'+
                                        '</div>'+
                                        '<div class="modal-body">'+EMPTY_CONTENT+'</div>'+
                                          '<div class="modal-footer">'+
                                          	  '<button idialog-data-dismiss="modal" class="btn btn-danger">'+CLOSE+'</button>'+
                                              '<button idialog-data-dismiss="modal-ok" class="btn btn-success">'+CONFIRM+'</button>'+
                                          '</div>'+
                                      '</div>'+
                                  '</div>'+
                              '</div>',
         this.iloading = '<input type="text" class = "loading form-control spinner">',
         
         this.iwindow =  '<div div-type = "idialog" dialog-type = "iwindow" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">'+
                           '<div class="modal-dialog">'+
                                 '<div class="modal-content">'+
                                       '<div class="modal-header">'+
                                             '<button class="close" idialog-data-dismiss="modal" aria-hidden="true">&times;</button>'+
                                             '<h4 class="modal-title"  style = "color:white"></h4>'+
                                        '</div>'+
                                        '<div class="modal-body"></div>'+
                                      '</div>'+
                                  '</div>'+
                              '</div>',
         
         this.iShow = function(){//私有函数，显示提示框
	         $("#backdrop").show(function(){
				 $("#backdrop").addClass("in");
			 });
			 $("div[div-type='idialog']").show(function(){
				 $("div[div-type='idialog']").addClass("in");
			 });
         },
         this.iHide = function(){//私有函数，隐藏提示框
         	$("div[div-type='idialog']").removeClass("in");
			$("#backdrop").removeClass("in");
			setTimeout(function(){
				$(".modal").remove();
				$(".modal-backdrop").remove();
			},150);
         },
         this.iHideRightNow=function(){//立马隐藏提示框
         	$("div[div-type='idialog']").remove();
			//$(".modal-backdrop").remove();
         },
         this.hasBackdrop = function(){//页面上是否已经有背景遮罩层
         	if($(bodyObj).find("#backdrop").length>0){
         		return true;
         	}else{
         		return false;
         	}
         }
	};
	
	$.extend($.iDialog.prototype,{
		iTip:function(content){//自定义alert函数
			var obj = this;
			obj.iHideRightNow();
			$(bodyObj).append(obj.itip);
			if(!obj.hasBackdrop()){
				$(bodyObj).append(obj.backdrop);
			}

			$(".modal-body").html(content);
			obj.iShow();
			setTimeout(function(){
				obj.iHide();
			},2000);
		},
		iAlert:function(content){//自定义alert函数
			var obj = this;
			obj.iHideRightNow();
			$(bodyObj).append(obj.ialert);
			if(!obj.hasBackdrop()){
				$(bodyObj).append(obj.backdrop);
			}
			
			$(".modal-body").html(content);
			obj.iShow();
			$("div[div-type='idialog']").find("[idialog-data-dismiss='modal']").click(function(){
				obj.iHide();
			});
		},
		iConfirm:function(content, fun){//自定义confirm函数
			var obj = this;
			obj.iHideRightNow();
			$(bodyObj).append(obj.iconfirm);
			if(!obj.hasBackdrop()){
				$(bodyObj).append(obj.backdrop);
			}
			
			$(".modal-body").html(content);
			obj.iShow();
			$("div[div-type='idialog']").find("[idialog-data-dismiss='modal']").click(function(){
				obj.iHide();
			});
			$("div[div-type='idialog']").find("[idialog-data-dismiss='modal-ok']").click(function(){
				if(fun.callBackHandler != undefined && typeof fun.callBackHandler == "function"){
					fun.callBackHandler();
				}
				obj.iHide();
			});
		},
		iLoading:function(cmd, btnObj){//自定义loading状态
			if(cmd!=null){
				if(cmd.toUpperCase() == "SHOW" || cmd.toUpperCase() == "YES" || cmd == "1"){
					if(btnObj == null || btnObj == undefined){
						$(bodyObj).append(this.iloading);
					}else{
						$(btnObj).parent().append(this.iloading);
					}
				}else if(cmd.toUpperCase() == "HIDE" || cmd.toUpperCase() == "NO" || cmd == "0"){
					if(btnObj == null || btnObj == undefined){
						$(bodyObj).find(".loading").remove();
					}else{
						$(btnObj).parent().find(".loading").remove();
					}
				}
			}else{
				if(btnObj == null || btnObj == undefined){
					$(bodyObj).append(this.iloading);
					setTimeout(function(){
						$(bodyObj).find(".loading").remove();
					},2000);
				}else{
					$(btnObj).parent().append(this.iloading);
					setTimeout(function(){
						$(btnObj).parent().find(".loading").remove();
					},2000);
				}
			}
		},
		iWindow:function(url, hdtext){//自定义弹窗表单效果
			var obj = this;
			obj.iHideRightNow();
			$(bodyObj).append(obj.iwindow);
			if(!obj.hasBackdrop()){
				$(bodyObj).append(obj.backdrop);
			}
			
			if(hdtext == null || hdtext == undefined){
				hdtext = "新打开窗口";
			}
			$(".modal-title").text(hdtext);
			$(".modal-body").load(url);
			obj.iShow();
			$("div[div-type='idialog']").find("[idialog-data-dismiss='modal']").click(function(){
				obj.iHide();
			});
		}
	});
})(jQuery);

var iDialog = new $.iDialog();