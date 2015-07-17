<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<ul class="slider-nav skin">
	<li class="submenu">
		<a href="javascript:void(0)">
			<i class="icon-cogs"></i>
			<span class="hidden-tablet"> 系统管理</span>
			<span class="label">0</span>
		</a>
		<ul class="subNav" >
			<shiro:hasPermission name="user:query">
				<li>
					<a  href="${ctx}/mi/users">
						<i class="icon-"></i>
						<span > 用户管理</span>
					</a>
				</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:query">
			<li>
				<a  href="${ctx}/mi/roles">
					<i class="icon-"></i>
					<span > 角色管理</span>
				</a>
			</li>
			</shiro:hasPermission>
		</ul>
	</li>
	<li class="submenu">
		<a href="javascript:void(0)">
			<i class="icon-signal"></i>
			<span class="hidden-tablet"> 评估管理</span>
			<span class="label">0</span>
		</a>
		<ul class="subNav" >
			<shiro:hasPermission name="am:view">
			<li>
				<a  href="${ctx }/mi/pg">
					<i class="icon-"></i>
					<span > 评估维护</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="ai:query">
			<li>
				<a  href="${ctx }/mi/pgitem">
					<i class="icon-"></i>
					<span > 评估项管理</span>
				</a>
			</li>
			</shiro:hasPermission>
		</ul>
	</li>
	<li class="submenu">
		<a href="javascript:void(0)">
			<i class="icon-group"></i>
			<span class="hidden-tablet"> 报名管理</span>
			<span class="label">0</span>
		</a>
		<ul class="subNav" >
			<shiro:hasPermission name="su:view">
			<li>
				<a  href="${ctx }/mi/su">
					<i class="icon-"></i>
					<span > 报名维护</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nl:query">
			<li>
				<a  href="${ctx }/mi/nl">
					<i class="icon-"></i>
					<span > 名单管理</span>
				</a>
			</li>
			</shiro:hasPermission>
		</ul>
	</li>
	<li class="submenu">
		<a href="javascript:void(0)">
			<i class="icon-home"></i>
			<span class="hidden-tablet"> 物业管理</span>
			<span class="label">0</span>
		</a>
		<ul class="subNav" >
			<shiro:hasPermission name="rep:query">
			<li>
				<a  href="${ctx }/mi/xf">
					<i class="icon-"></i>
					<span > 新房管理</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="rc:query">
			<li>
				<a  href="${ctx }/mi/xq">
					<i class="icon-"></i>
					<span > 小区管理</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="shop:query">
			<li>
				<a  href="${ctx }/mi/shop">
					<i class="icon-"></i>
					<span > 商铺管理</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="ob:query">
			<li>
				<a  href="${ctx }/mi/xzl">
					<i class="icon-"></i>
					<span > 写字楼管理</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="warehouse:query">
			<li>
				<a  href="${ctx }/mi/cfck">
					<i class="icon-"></i>
					<span > 厂房仓库管理</span>
				</a>
			</li>
			<shiro:hasPermission name="rep:queryru">
			<li>
				<a  href="${ctx }/mi/xfru">
					<i class="icon-"></i>
					<span > 关联楼盘管理</span>
				</a>
			</li>
			</shiro:hasPermission>
			</shiro:hasPermission>
		</ul>
	</li>
	<li class="submenu">
		<a href="javascript:void(0)">
			<i class="icon-cog"></i>
			<span class="hidden-tablet"> 其他管理</span>
			<span class="label">0</span>
		</a>
		<ul class="subNav" >
			<shiro:hasPermission name="info:query">
				<li>
					<a  href="${ctx }/mi/info">
						<i class="icon-"></i>
						<span > 资讯管理</span>
					</a>
				</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="ad:query">
				<li>
					<a  href="${ctx }/mi/ad">
						<i class="icon-"></i>
						<span > 广告管理</span>
					</a>
				</li>
			</shiro:hasPermission>
		</ul>
	</li>
</ul>

<script>
localData = {
    hname:location.hostname?location.hostname:'localStatus',
    isLocalStorage:window.localStorage?true:false,
    dataDom:null,
	
    initDom:function(){ //初始化userData
        if(!this.dataDom){
            try{
                this.dataDom = document.createElement('input');//这里使用hidden的input元素
                this.dataDom.type = 'hidden';
                this.dataDom.style.display = "none";
                this.dataDom.addBehavior('#default#userData');//这是userData的语法
                document.body.appendChild(this.dataDom);
                var exDate = new Date();
                exDate = exDate.getDate()+30;
                this.dataDom.expires = exDate.toUTCString();//设定过期时间
            }catch(ex){
                return false;
            }
        }
        return true;
    },
    set:function(key,value){
        if(this.isLocalStorage){
            window.localStorage.setItem(key,value);
        }else{
            if(this.initDom()){
                this.dataDom.load(this.hname);
                this.dataDom.setAttribute(key,value);
                this.dataDom.save(this.hname)
            }
        }
    },
    get:function(key){
        if(this.isLocalStorage){
            return window.localStorage.getItem(key);
        }else{
            if(this.initDom()){
                this.dataDom.load(this.hname);
                return this.dataDom.getAttribute(key);
            }
        }
    },
    remove:function(key){
        if(this.isLocalStorage){
            localStorage.removeItem(key);
        }else{
            if(this.initDom()){
                this.dataDom.load(this.hname);
                this.dataDom.removeAttribute(key);
                this.dataDom.save(this.hname)
            }
        }
    }
}
//	$(".submenu").each(function(){
//		var ele = $(this);
//		var len = ele.children(".subNav").children().length;
//		if(len>0){
//			ele.find(".label").html(len);
//		}else{
//			ele.hide();
//		}
//		
//		var curUrl = window.location.href;
//		ele.find("a").each(function(){
//			if(curUrl.indexOf($(this).attr("href")+"/")>-1){
//// 				ele.addClass("open");
//				ele.addClass("active");
//				ele.click();
//			}
//			if(curUrl.substring(curUrl.indexOf($(this).attr("href")))==$(this).attr("href")){
//				ele.click();
//				ele.addClass("active");
//			}
//		});
	$(function(){
		var key = "__mi__silder__nav__temp__";
		var curUrl = window.location.href;
		var check = !1;
		
		$(".slider-nav .subNav").on("click",function(e){
			e.stopPropagation();
		})
		
		function checkNav(url){
			$(".slider-nav").find("a").each(function(i,e){
				var $e = $(e);
				var href = $e.attr("href");
				var id =0;
				if(!!href && (id=url.indexOf(href))>-1){
					var end = url.substr(id);
					if(end !== href ){
						return;
					}
					var tempString = href.substr(0,id);
					$e.parent().addClass("active");
					var $emp = $e.parentsUntil(".submenu");
					$emp.css("display","block");
					$emp.parent().addClass("open");
					localData.set(key,url);
					check = !0;
				}
			})
		}
		checkNav(curUrl);
//		//使用存储的上级目录
		if(!check){
			var lastUrl = localData.get(key);
			checkNav(lastUrl);
		}
		
		//使用路径截取，没有上级目录，如下场景：直接打开页面http://localhost:8080/zfw/mi/d256ca42-a8f1-4471-a4ab-5877aea3b0ef/xfpano/2d61dd24-0825-40f6-9d39-21cb0dcae517/edit
		//如未登陆，会调到登陆页面再登陆，此时无路径判断，直接截取部分路径进行判断
		if(!check){
			var host =window.location.hostname+":"+window.location.port+"/"+window.location.host;
			var host = window.location.pathname.replace("/zfw/mi","");
			var m = host.match(/\/[a-zA-Z]*\//g);
			for(var i =0,l=m.length;i<l;i++){
				var k = m[i].substr(0,m[i].length-1);
				$(".slider-nav").find("a").each(function(i,e){
					var $e = $(e);
					var href = $e.attr("href");
					if(!!href){
						href = href.substr(href.lastIndexOf("/"));
						console.log(href);
						if(k.indexOf(href)>-1){
							$e.parent().addClass("active");
							var $emp = $e.parentsUntil(".submenu");
							$emp.css("display","block");
							$emp.parent().addClass("open");
							localData.set(key,href);
							check = !0;
						};
					}
				
				})
			}
		}
	})
</script>
