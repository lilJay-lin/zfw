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
		},
		ajax : function(t,u,s,e){
			$.ajax({
				type:t,
				url:u,
				dataType :"json",
				success:s||{},
				error:e||{}
			})
		}
	}
	
	util = a;
	
})(window.util || (window.util ={}))
