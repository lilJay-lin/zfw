<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<ul class="slider-nav skin">
	<ct:hasAnyPermissions name="user,user:query,role,role:query">
		<li class="submenu active">
			<a href="javascript:void(0)">
				<i class="icon-key"></i>
				<span class="hidden-tablet"> 系统管理</span>
				<span class="label">2</span>
			</a>
			<ul class="subNav" >
				<ct:hasAnyPermissions name="user,user:query">
					<li>
						<a  href="${ctx}/mi/users">
							<i class="icon-user"></i>
							<span > 用户管理</span>
						</a>
					</li>
				</ct:hasAnyPermissions>
				<ct:hasAnyPermissions name="role,role:query">
				<li>
					<a  href="${ctx}/mi/roles">
						<i class="icon-hdd"></i>
						<span > 角色管理</span>
					</a>
				</li>
				</ct:hasAnyPermissions>
			</ul>
		</li>
	</ct:hasAnyPermissions>
	<ct:hasAnyPermissions name="ai,ai:query,assessment">
		<li class="submenu active">
			<a href="javascript:void(0)">
				<i class="icon-key"></i>
				<span class="hidden-tablet"> 评估管理</span>
				<span class="label">2</span>
			</a>
			<ul class="subNav" >
				<ct:hasAnyPermissions name="assessment">
				<li>
					<a  href="${ctx }/mi/pg">
						<i class="icon-hdd"></i>
						<span > 评估维护</span>
					</a>
				</li>
				</ct:hasAnyPermissions>
				<ct:hasAnyPermissions name="ai,ai:query">
				<li>
					<a  href="${ctx }/mi/pgitem">
						<i class="icon-hdd"></i>
						<span > 评估项管理</span>
					</a>
				</li>
				</ct:hasAnyPermissions>
			</ul>
		</li>
	</ct:hasAnyPermissions>
	<ct:hasAnyPermissions name="nl,nl:query,signup">
		<li class="submenu active">
			<a href="javascript:void(0)">
				<i class="icon-key"></i>
				<span class="hidden-tablet"> 报名管理</span>
				<span class="label">2</span>
			</a>
			<ul class="subNav" >
				<ct:hasAnyPermissions name="assessment">
				<li>
					<a  href="${ctx }/mi/bm">
						<i class="icon-hdd"></i>
						<span > 报名维护</span>
					</a>
				</li>
				</ct:hasAnyPermissions>
				<ct:hasAnyPermissions name="nl,nl:query">
				<li>
					<a  href="${ctx }/mi/md">
						<i class="icon-hdd"></i>
						<span > 名单管理</span>
					</a>
				</li>
				</ct:hasAnyPermissions>
			</ul>
		</li>
	</ct:hasAnyPermissions>
	<li class="submenu active">
		<a href="javascript:void(0)">
			<i class="icon-key"></i>
			<span class="hidden-tablet"> 日常管理</span>
			<span class="label">2</span>
		</a>
		<ul class="subNav" >
			<ct:hasAnyPermissions name="rep,rep:query">
			<li>
				<a  href="${ctx }/mi/xf">
					<i class="icon-hdd"></i>
					<span > 新房管理</span>
				</a>
			</li>
			</ct:hasAnyPermissions>
			<ct:hasAnyPermissions name="info,info:query">
			<li>
				<a  href="${ctx }/mi/info">
					<i class="icon-hdd"></i>
					<span > 资讯管理</span>
				</a>
			</li>
			</ct:hasAnyPermissions>
			<ct:hasAnyPermissions name="rc,rc:query">
			<li>
				<a  href="${ctx }/mi/xq">
					<i class="icon-hdd"></i>
					<span > 小区管理</span>
				</a>
			</li>
			</ct:hasAnyPermissions>
			<ct:hasAnyPermissions name="shop,shop:query">
			<li>
			<li>
				<a  href="${ctx }/mi/shop">
					<i class="icon-hdd"></i>
					<span > 商铺管理</span>
				</a>
			</li>
			</ct:hasAnyPermissions>
			<ct:hasAnyPermissions name="repru,repru:query">
			<li>
				<a  href="${ctx }/mi/xfru">
					<i class="icon-hdd"></i>
					<span > 关联楼盘管理</span>
				</a>
			</li>
			</ct:hasAnyPermissions>
			<ct:hasAnyPermissions name="ob,ob:query">
			<li>
				<a  href="${ctx }/mi/xzl">
					<i class="icon-hdd"></i>
					<span > 写字楼管理</span>
				</a>
			</li>
			</ct:hasAnyPermissions>
			<ct:hasAnyPermissions name="warehouse,warehouse:query">
			<li>
				<a  href="${ctx }/mi/cfck">
					<i class="icon-hdd"></i>
					<span > 厂房仓库管理</span>
				</a>
			</li>
			</ct:hasAnyPermissions>
			<ct:hasAnyPermissions name="ad,ad:query">
			<li>
				<a  href="${ctx }/mi/ad">
					<i class="icon-hdd"></i>
					<span > 广告管理</span>
				</a>
			</li>
			</ct:hasAnyPermissions>
			<li>
				<a  href="${ctx }/mi/nl">
					<i class="icon-hdd"></i>
					<span > 电商团购管理</span>
				</a>
			</li>
			<li>
				<a  href="${ctx }/mi/nl">
					<i class="icon-hdd"></i>
					<span > 电商团购管理</span>
				</a>
			</li>
		</ul>
	</li>
</ul>
