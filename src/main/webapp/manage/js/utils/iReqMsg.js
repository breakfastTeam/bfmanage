/*
 *生成请求报文
 *参数：Map
 *返回：字符串
 */
(function($){
	$.iReqMsg = function(){
		 var bd = "";
         this.analyzeBody = function(map){//私有函数，显示提示框
         	var body = "";//array内部拼接串儿
         	var flag = map.isEmpty();
	         if(flag){
				bd = '{}';				
			}else{
				var keys = map.keys();
				var startTag = "{";
				var tag = "";
				var endTag = "}";
				for(var i = 0; i<keys.length; i++){
					if(Object.prototype.toString.call(map.get(keys[i])) === '[object Array]'){
						body = body + '"'+keys[i]+'":'+"[";
						for(var j = 0; j < map.get(keys[i]).length; j++){
							body = body + this.analyzeBody(map.get(keys[i])[j])+",";
						}
						body = body.substring(0, (body.length-1));
						body = body + "],";
					}else{
						tag = tag + '"'+keys[i]+'":"'+map.get(keys[i])+'",';
						if((keys.length-1)==i){
							tag = tag.substring(0, (tag.length-1));
						}	
					}
					if((keys.length-1)==i){
						body = body.substring(0, (body.length-1));
					}
				}
				bd = startTag + tag + body + endTag;
			}
			return bd
         }
	};
	
	$.extend($.iReqMsg.prototype,{
		getReqMsg:function(map){//自定义alert函数
			var head = '"head":{"appCode":"0","accessCode":"001","lastCallTag":"1","interfaceName":"BEL001"}';
			var body = '"body":'+this.analyzeBody(map);
			return '{'+head+','+body+'}'
		}
	});
})(jQuery);

var iReqMsg = new $.iReqMsg();