var BelImageCrop = function () {
    return {
        //主模块初始化
        init: function () {
        	var x,y,w,h,ow,oh;
        	if (!jQuery().Jcrop) {;
                return;
            }
            
	        var jcrop_api, boundx, boundy,
	           $preview = $('#preview-pane'),
	           $pcnt = $('#preview-pane .preview-container'),
	           $pimg = $('#preview-pane .preview-container img'),
			   $orginImg = $('#orginImg'),
	           xsize = $pcnt.width(),
	           ysize = $pcnt.height();
		    $('#showImg').Jcrop({
		          onChange: updatePreview,
		          onSelect: updatePreview,
		          aspectRatio: xsize / ysize
		        },function(){
		          var bounds = this.getBounds();
		          ow = boundx = bounds[0];
		          oh = boundy = bounds[1];
		          
		          jcrop_api = this;
		          jcrop_api.setOptions({aspectRatio:8/5,allowResize:false,allowMove:true,allowSelect:false});
		          jcrop_api.animateTo([(boundx-320)/2, (boundy-200)/2, 320, 200])
		          $preview.appendTo(jcrop_api.ui.holder);
		    });
			function updatePreview(c){
				orginx = $orginImg.width();
	           	orginy = $orginImg.height();
	           	console.log(orginx+"     "+orginy);
	           	
		       	if(orginx<320 || orginy<200){
		          	iDialog.iAlert("图片太小了亲");
		          	return;
		       	}
	          	if (parseInt(c.w) > 0){
		            var rx = xsize / c.w;
		            var ry = ysize / c.h;
		            $pimg.css({
		              width: Math.round(rx * boundx) + 'px',
		              height: Math.round(ry * boundy) + 'px',
		              marginLeft: '-' + Math.round(rx * c.x) + 'px',
		              marginTop: '-' + Math.round(ry * c.y) + 'px'
		            });
		            x = c.x;
		            y = c.y;
	        	}
	        }
	        $("#cropImg").click(function(){
	        	alert(x+"   "+y);
	        	$("#x").val(x);
	        	$("#y").val(y);
	        	iDialog.iHide();
	        });
        }
    };
}();
