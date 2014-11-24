$(function(){
		$(".label_radio").die().live("click", function(e){
			e.preventDefault();
    		if(!$(this).hasClass("r_on")){
    			$(this).parents().find(".label_radio").not(this).removeClass("r_on");
    			$(this).parents().find(".label_radio").not(this).children().find("input").removeAttr("checked");
    			$(this).addClass("r_on");
    			$(this).find("input").attr("checked", true);
    		}
    	});
    	$(".label_check").die().live("click", function(e){
    		e.preventDefault();
    		if($(this).hasClass("c_on")){
    			$(this).removeClass("c_on");
    			$(this).find("input").removeAttr("checked");
    			
    		}else{
    			$(this).addClass("c_on");
    			$(this).find("input").attr("checked", true);
	    	}
	    	if($(this).attr("for")=="selectAll"){
	    		var trs = $(this).parents(".table").find("tbody>tr");
	    		$(trs).each(function(i){
	    			$(trs[i]).find("td:first .label_check").trigger("click")
	    		});
	    	}
    	});
});
