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
		$(this).parent().find(".filename").html($(this).val());
	}
	
	/*
	 * 关联关系
	 */
//	function r(){
//		$(this).parentsUntil("li").remove();
//	}
	
	$(document).delegate("li.submenu","click",t);
	$(document).delegate(".uploader input","change",u)
//	$(".relation").delegate("a","click",r)
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
	
	
	/*
  	 * 分页
  	 * 
  	 */
  	function Page(opt){
  		this.options={
  			container:"",
  			template:"",
  			url:"",
  			data:{}
  		}
  		$.extend(this.options,opt);
  		this.$container = $(this.options.container);
  	}
  	Page.prototype={
  		init:function(idx){
			this.getPage(idx);
  		},
  		setData:function(data){
	  		$.extend(this.options.data,data)
	  	},
  		template:function(id,data){
			var tpl = Handlebars.compile($(id).html());
			return tpl(data);
		},
  		getPage:function(index){
  			var self = this;
  			var p = index || 1;
			var url = self.options.url+p;
	  		$.ajax({
				type:"GET",
				url:url,
				dataType:"json",
				async:true,
				data:self.options.data,
				success:function(data){
					var items = data.items;
					var pageinfo = data.pageinfo;
					self.$container.find(".page-data-list").html(self.template(self.options.template,items));
					self.pagination(pageinfo);
				},
				error:function(){
					
				}
			});
  		},
  		pagination:function(pageinfo){
  			var self = this;
  			self.removePaginationHandle();
  			var p = self.$container.find(".pagination");
  			var info = self.$container.find(".datatable-info");
  			var pagesize = pageinfo.pagesize||10;
			var start = (pageinfo.curpage-1)*pagesize + 1;
			var end = pageinfo.curpage*pagesize >=pageinfo.totalrows?pageinfo.totalrows:pageinfo.curpage*pagesize;
			info.html("共"+pageinfo.totalrows+"条 当前展示第"+start+"条到第"+end+"条");
			var curpage = parseInt(pageinfo.curpage,10);
			var html ='<li  class="active"><a href="javascript:;"  data-page="'+curpage+'">'+curpage+' </a></li>';
			var s=curpage-1,e=curpage+1,st  = '',a = parseInt(pageinfo.totalpage,10);;
			for(;s>curpage-4;s--){
				if(s>1)
				html='<li><a href="javascript:;" data-page="'+s+'">'+s+'</a></li>'+html;
			}
			if(curpage!=1){
				if(s==2){
					st='<li><a href="javascript:;"  data-page="2">2</a>'+html;
				}else if(s>2){
					st='<li><a  href="javascript:;" class="blank">...</a></li>'+html;
				}
				html = '<li><a href="javascript:;"  data-page="1">1</a>'+st;
			}
			for(;e<curpage+4;e++){
				if(e<a)
				html+='<li><a href="javascript:;" data-page="'+e+'">'+e+'</a></li>';
			}
			
			if(curpage!=a){
				if(e == a-1){
					html+='<li><a href="javascript:;"  data-page="'+(a-1)+'">'+(a-1)+'</a>'
				}else if(e<a-1){
					html+='<li><a href="javascript:;" class="blank">...</a></li>'
				}
				html +='<li><a href="javascript:;"  data-page="'+pageinfo.totalpage+'">'+pageinfo.totalpage+' </a></li>';
			}
			p.html(html);
			p.data("curpage",pageinfo.curpage);
			p.data("totalpage",pageinfo.totalpage);
			self.addPaginationHandle(p);
  		},
  		addPaginationHandle:function(){
  			var self = this;
  			var p = self.$container.find(".pagination");
  			self.paginationHandle =function(e){
  				e.preventDefault();
  				var $e = $(this);
  				var page = $e.data("page");
  				if(page == 'pre'){
  					self.prex();
  				}else if(page=='next'){
  					self.next();
  				}else{
  					self.getPage(page)
  				}
  			};
  			p.delegate("a","click",self.paginationHandle)
  		},
  		removePaginationHandle:function(){
  			var self = this;
  			self.$container.find(".pagination").undelegate("a","click",self.paginationHandle)
  		},
		prex:function (){
			var self = this;
  			var p = self.$container.find(".pagination");
			var page = p.data("curpage")||0;
			console.log(page);
			page = (page-1>0?page-1:1);
			self.getPage(page)
		},
		next:function (){
			var self = this;
  			var p = self.$container.find(".pagination");
			var page = p.data("curpage")||0;
			var totalpage = p.data("totalpage")||0;
			page = (page+1>totalpage?totalpage:(page+1));
			self.getPage(page)
		},
		reloadPage:function(){
			var self = this;
  			var p = self.$container.find(".pagination");
			var page = p.data("curpage")||0;
			self.getPage(page)
		}
  	}
  	r.Page = Page;
})(window);
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
				
				if(require !="require" && !value){
					continue;
				}
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
