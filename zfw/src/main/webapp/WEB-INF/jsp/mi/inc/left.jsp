<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<ul class="slider-nav skin">
	<li class="submenu">
		<a href="javascript:void(0)">
			<i class="icon-key"></i>
			<span class="hidden-tablet"> 系统管理</span>
			<span class="label">0</span>
		</a>
		<ul class="subNav" >
			<shiro:hasPermission name="user:query">
				<li>
					<a  href="${ctx}/mi/users">
						<i class="icon-user"></i>
						<span > 用户管理</span>
					</a>
				</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:query">
			<li>
				<a  href="${ctx}/mi/roles">
					<i class="icon-hdd"></i>
					<span > 角色管理</span>
				</a>
			</li>
			</shiro:hasPermission>
		</ul>
	</li>
	<li class="submenu">
		<a href="javascript:void(0)">
			<i class="icon-key"></i>
			<span class="hidden-tablet"> 评估管理</span>
			<span class="label">0</span>
		</a>
		<ul class="subNav" >
			<shiro:hasPermission name="am:view">
			<li>
				<a  href="${ctx }/mi/pg">
					<i class="icon-hdd"></i>
					<span > 评估维护</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="ai:query">
			<li>
				<a  href="${ctx }/mi/pgitem">
					<i class="icon-hdd"></i>
					<span > 评估项管理</span>
				</a>
			</li>
			</shiro:hasPermission>
		</ul>
	</li>
	<li class="submenu">
		<a href="javascript:void(0)">
			<i class="icon-key"></i>
			<span class="hidden-tablet"> 报名管理</span>
			<span class="label">0</span>
		</a>
		<ul class="subNav" >
			<shiro:hasPermission name="su:view">
			<li>
				<a  href="${ctx }/mi/su">
					<i class="icon-hdd"></i>
					<span > 报名维护</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nl:query">
			<li>
				<a  href="${ctx }/mi/nl">
					<i class="icon-hdd"></i>
					<span > 名单管理</span>
				</a>
			</li>
			</shiro:hasPermission>
		</ul>
	</li>
	<li class="submenu">
		<a href="javascript:void(0)">
			<i class="icon-key"></i>
			<span class="hidden-tablet"> 物业管理</span>
			<span class="label">0</span>
		</a>
		<ul class="subNav" >
			<shiro:hasPermission name="rep:query">
			<li>
				<a  href="${ctx }/mi/xf">
					<i class="icon-hdd"></i>
					<span > 新房管理</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="rc:query">
			<li>
				<a  href="${ctx }/mi/xq">
					<i class="icon-hdd"></i>
					<span > 小区管理</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="shop:query">
			<li>
				<a  href="${ctx }/mi/shop">
					<i class="icon-hdd"></i>
					<span > 商铺管理</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="ob:query">
			<li>
				<a  href="${ctx }/mi/xzl">
					<i class="icon-hdd"></i>
					<span > 写字楼管理</span>
				</a>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="warehouse:query">
			<li>
				<a  href="${ctx }/mi/cfck">
					<i class="icon-hdd"></i>
					<span > 厂房仓库管理</span>
				</a>
			</li>
			<shiro:hasPermission name="rep:queryru">
			<li>
				<a  href="${ctx }/mi/xfru">
					<i class="icon-hdd"></i>
					<span > 关联楼盘管理</span>
				</a>
			</li>
			</shiro:hasPermission>
			</shiro:hasPermission>
		</ul>
	</li>
	<li class="submenu">
		<a href="javascript:void(0)">
			<i class="icon-key"></i>
			<span class="hidden-tablet"> 其他管理</span>
			<span class="label">0</span>
		</a>
		<ul class="subNav" >
			<shiro:hasPermission name="info:query">
				<li>
					<a  href="${ctx }/mi/info">
						<i class="icon-hdd"></i>
						<span > 资讯管理</span>
					</a>
				</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="ad:query">
				<li>
					<a  href="${ctx }/mi/ad">
						<i class="icon-hdd"></i>
						<span > 广告管理</span>
					</a>
				</li>
			</shiro:hasPermission>
		</ul>
	</li>
</ul>

<script>
	$(".submenu").each(function(){
		var ele = $(this);
		var len = ele.children(".subNav").children().length;
		if(len>0){
			ele.find(".label").html(len);
		}else{
			ele.hide();
		}
		
		var curUrl = window.location.href;
		ele.find("a").each(function(){
			if(curUrl.indexOf($(this).attr("href")+"/")>-1){
// 				ele.addClass("open");
				ele.addClass("active");
				ele.click();
			}
			if(curUrl.substring(curUrl.indexOf($(this).attr("href")))==$(this).attr("href")){
				ele.click();
				ele.addClass("active");
			}
		});
	});
</script>
