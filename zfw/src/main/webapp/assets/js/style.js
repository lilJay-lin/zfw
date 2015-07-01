/*
 * 
 * slider下拉子项
 * 
 */
(function(){
	/*
	 * 下拉菜单
	 */
	function t(){
		var a = this,
		b = $(this);
		b.hasClass("open")?(b.find("ul").css("display","none")):b.find("ul").css("display","block")
		b.toggleClass("open")
	}
	
	/*
	 * 文件上传
	 */
	function u(){
		$(this).parent().find(".filename").html($(this).val())
	}
	
	/*
	 * 关联关系
	 */
	function r(){
		$(this).parentsUntil("li").remove();
	}
	
	$(document).delegate("li.submenu","click",t)
	$(".uploader input").on("change",u)
	$(".relation").delegate("a","click",r)
	var h = function(){
		var height = window.innerHeight;
		if(typeof height != 'number'){
			if(document.compatMode == 'CSS1Compat'){
				height = document.documentElement.clientHeight;
			}else{
				height = document.body.clientHeight;
			}
		}
		return height-41-36;
	}();
	$("div.container").css("min-height",h +"px");
})();


/*
 * util
 * 
 */
(function(r){
	var a = {
		/*
		 * n :1 过滤已存在不复制
		 * 
		 */
		mix:function(e,t,n){
			for(var i in t){
				if(e[t]&&!!n)continue;
				e[t] = n[t]
			}
			return e;
		},
		toArray: function(e,t){
			return [].slice.call(e,t||0)
		},
		cookie: function(e,t,n){
			var r, i ,s, o;
			if(arguments.length >1){
				n = n || 30;
				if (t === null || t === undefined) n = -1;
				return typeof n =="number" && (r = n * 24 * 60 * 60 * 1e3,i = n = new Date,i.setTime(i.getTime()+r)),
				t = String(t),
				document.cookie = [encodeURIComponent(e),"=",encodeURIComponent(t),"; expires=",n.toGMTString(),"; path='/'"].join("");
			}
			return (s = (new RegExp("(?:^|; )" + encodeURIComponent(e) + "=([^;]*)")).exec(document.cookie)) ? decodeURIComponent(s[1]) : null
		}
	}
	
	r.util = a;
})(window.util || (window.util ={}));
/*
*jquery fn
*/
(function($){
	$.fn.extend({
		validate:function(){
			var form = $(this);
			var inputs = form.find("input");
			var showerror = function(el,err){
				var h =null;
				(h=el.next(".help-inline")).length==0&& (h=$("<div class='help-inline'></div>"),el.after(h));
				if(arguments.length==1){
					h.hide()
				}else {
					h.html(err);
					h.show();
				}
			};
			for(var i=0,l=inputs.length;i<l;i++){
				var el = inputs.eq(i);
				var type = el.attr("type");
				if("text/email/url/password/phonenumber/".indexOf(type) ==-1){
					continue;
				}
				var value,max,min,error,require,require_msg,patterns;
				max = el.attr("max");
				min = el.attr("min");
				error = el.attr("error")||"输入内容格式错误";
				require = el.attr("require");
				require_msg = el.attr("require_msg")||"不能为空";
				patterns = el.attr("patterns");
				value = el.val();
				if(value.trim().length==0)value="";
				len = value.length;
				if(require == "require" && !value){
					showerror(el,require_msg);
					return false;
				}
				
				if(min && len<min){
					showerror(el,error);
					return false;
				}
				
				if(max && len>max){
					showerror(el,error);
					return false;
				}
				
				if(patterns){
					var reg = new RegExp(patterns);
					if(!reg.exec(value)){
						showerror(el,error);
						return false;
					}
				}
				showerror(el);
			}
			return true;
		}
	})
})(jQuery);
