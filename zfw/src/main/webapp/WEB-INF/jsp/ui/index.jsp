<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="inc/header.jsp" %>
<script type="text/javascript"
	src="${ctx }/assets/js/jquery.event.drag-1.5.min.js"></script>
<script src="${ctx }/assets/js/jquery.touchSlider.js"></script>
</head>
<body>
	<div class="main">
		<c:if test="${adts != null and fn:length(adts) != 0 }">
			<div class="imgTouchSlider">
				<div class="main_image">
					<ul>
			       		<c:forEach items="${adts}" var="t" varStatus="status">
							<li><a href="${t.contentUrl}">
								<img src='${t.preImageUrl }'>
								</a></li>
						</c:forEach>
					</ul>
					<a href="javascript:void(0);" id="btn_prev" class="btn_prev"></a> <a
						href="javascript:void(0);" id="btn_next" class="btn_next"></a>
				</div>
				<div class="flicking_con">
					<div class="flicking_inner">
						<c:if test="${adts != null and fn:length(adts) != 0 }">
				       		<c:forEach items="${adts}" var="t" varStatus="status">
				       			<a href=""></a>
				       		</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</c:if>
		<section class="whitebg">
			<header class="homeHeader">
				<div class="cent mt10">
					<img width="120px" alt="" src="${ctx}/assets/img/ui/logo.jpg"
						style="display: inline;">
				</div>
				<div id="wapdsy_D01_03" class="head-icon mt10">
					<a href="user/index" class="ico-my"> <span><i></i></span>
					</a>
				</div>
			</header>
		<form class="search0620_new flexbox" name="wapSearchForm" action=""
			onsubmit="return false;" method="get" autocomplete="off">
			<div class="searbox_new">
				<div class="ipt" >
					<input id="keyWord" type="search" name="keyWord" value=""
						placeholder="楼盘名/户型名/地名等" autocomplete="off"><a
						href="javascript:void(0);" class="off" style="display: none;"></a>
				</div>
				<a href="javascript:search();" class="btn" rel="nofollow"><i></i></a>
			</div>
		</form>
			<div class="bigNav" style="display: block;">
				<div class="icons chooseNav">
					<div class="flexbox chooseNav1">
						<a href="${ctx }/xf" class="xf"><i></i><p>新房</p></a>
						<a href="${ctx }/esf" class="esf"><i></i><p>二手房</p></a>
						<a href="${ctx }/zf" class="zf"><i></i><p>租房</p></a>
						<a href="javascript:spreadNav();" class="more gather"><i></i><p>更多</p></a>
						<a href="javascript:gatherNav();" class="more spread none"><i></i><p>收起</p></a>
					</div>
					<div class="flexbox chooseNav2 spread none">
						<a href="${ctx }/sp" class="sp"><i></i> <p>商铺</p></a> 
						<a href="${ctx }/xzl" class="xzl"><i></i><p>写字楼</p></a>
						<a href="${ctx }/cfck" class="cfck"><i></i><p>厂房仓库</p></a> 
						<a href="${ctx }/pg" class="esfpg"><i></i><p>二手房评估</p></a>
					</div>
				</div>
				<br style="display: inline;">
			</div>
		</section>
		
		<c:if test="${adm4s != null and fn:length(adm4s) != 0 }">
		<section class="homeBan mt10">
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tbody>
	       			<c:forEach items="${adm4s}" var="t" varStatus="status">
						<c:if test="${status.index%2==0}">
							<tr>
						</c:if>
						<td><a href="${t.contentUrl }" ><dl>
									<dt>
										<h3>${t.name }
										</h3>
										<p>${t.summary }</p>
									</dt>
									<dd>
										<img alt="" src="${t.preImageUrl }" style="display: inline;">
									</dd>
								</dl> </a></td>
						<c:if test="${status.index%2==1}">
							</tr>
						</c:if>
	       			</c:forEach>
				</tbody>
			</table>
		</section>
		</c:if>
		
		<c:if test="${admo != null }">
		<div class="mt10">
			<a href="${admo.contentUrl }">
				<img width="100%" src="${admo.preImageUrl }" style="display: inline;">
			</a>
		</div>
		</c:if>
		
		<c:if test="${fczx != null and fn:length(fczx) != 0 }">
			<section class="NewsList mt10" id="fczx" >
				<div class="zd-tip" style="display: none;">
					<span></span>
				</div>
				<div class="homeTitle clearfix">
					<h1>房产资讯</h1>
				</div>
				<ul>
		       		<c:forEach items="${fczx}" var="t" varStatus="status">
						<li <c:if test="${status.index>4}">
							 class="none"
						</c:if>><a href="${ctx }/info/${t.id}/detail" >
								<div class="bord">
									<div class="lt">
										<img alt="" src="${t.preImageUrl }" style="display: inline;">
									</div>
									<div class="rt">
										<h3>${t.name }</h3>
										<p>${t.summary }</p>
									</div>
								</div>
						</a></li>
					</c:forEach>
				</ul>
				<div class="homeOption flexbox">
					<a  href="javascript:nextPage('fczx');" class="huanhuan">换一换</a>
					<a href="${ctx }/info/房产/type">进入资讯频道</a>
				</div>
			</section>
		</c:if>
		
		<c:if test="${zhzx != null and fn:length(zhzx) != 0 }">
			<section class="NewsList mt10" id="zhzx" >
				<div class="zd-tip" style="display: none;">
					<span></span>
				</div>
				<div class="homeTitle clearfix">
					<h1>综合资讯</h1>
				</div>
				<ul>
		       		<c:forEach items="${zhzx}" var="t" varStatus="status">
						<li <c:if test="${status.index>4}">
							 class="none"
						</c:if>><a href="${ctx }/info/${t.id}/detail" >
								<div class="bord">
									<div class="lt">
										<img alt="" src="${t.preImageUrl }" style="display: inline;">
									</div>
									<div class="rt">
										<h3>${t.name }</h3>
										<p>${t.summary }</p>
									</div>
								</div>
						</a></li>
					</c:forEach>
				</ul>
				<div class="homeOption flexbox">
					<a  href="javascript:nextPage('zhzx');" class="huanhuan">换一换</a>
					<a href="${ctx }/info/综合/type">进入资讯频道</a>
				</div>
			</section>
		</c:if>

		<c:if test="${showSignUp}">
			<section class="mt10">
				<div class="dsbmForm center pd10 bdt bdb">
				<form onsubmit="return submitDSBM()">
					<span class="f18">${signUpFormTitle }</span>
					<div class="searbox_new mt10">
						<div class="ipt mr0" >
							<input id="dsbmName" type="text" name="dsbmName" value="" placeholder="请输入姓名" required >
						</div>
					</div>
					<div class="searbox_new mt10">
						<div class="ipt mr0" >
							<input id="dsbmPhoneNum" type="text" name="dsbmPhoneNum" value="" placeholder="请留下电话号码" maxlength="11" pattern="^1[0-9]{10}$" required>
						</div>
					</div>
					<input type="submit" id="dsbmSubmitBtn" class="formbtn02 mt10"  value="马上报名">
				</form>
				</div>
			</section>
		</c:if>
		<%@include file="inc/footer.jsp" %>
		<%@include file="inc/goHead.jsp" %>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$(".imgTouchSlider").each(function(){
			initSlider(this);
		})

		$(window).resize(function() {
			fitSize();
		});
		fitSize();
	});
	function fitSize() {
		var bw = $("body").width();
		if (bw > 640) {
			bw = 640;
		}
		$(".main_image").height(bw / 3);

		var fw = $(".flicking_inner").children("a").length * 21;
		$(".flicking_inner").css("left", (bw - fw) * 0.5 + "px");
	}
	function initSlider(element){

		var sliderObj = $(element).children(".main_image");
		var flickObj = $(element).find(".flicking_con a");
		
		var dragBln = false;
		sliderObj.touchSlider(
				{
					flexible : true,
					speed : 200,
					paging : flickObj,
					counter : function(e) {
						flickObj.removeClass("on")
								.eq(e.current - 1).addClass("on");
					}
				});
		sliderObj.bind("mousedown", function() {
			dragBln = false;
		})
		sliderObj.bind("dragstart", function() {
			dragBln = true;
		})
		sliderObj.click(function() {
			if (dragBln) {
				return false;
			}
		})
		var timer = setInterval(function() {
			sliderObj[0].animate(-1,true);
		}, 5000);
		$(element).hover(function() {
			clearInterval(timer);
		}, function() {
			timer = setInterval(function() {
				sliderObj[0].animate(-1,true);
			}, 5000);
		})
		sliderObj.bind("touchstart", function() {
			clearInterval(timer);
		}).bind("touchend", function() {
			timer = setInterval(function() {
				sliderObj[0].animate(-1,true);
			}, 5000);
		})
	}
	function spreadNav() {
		$(".spread").removeClass("none");
		$(".gather").addClass("none");
	}
	function gatherNav() {
		$(".spread").addClass("none");
		$(".gather").removeClass("none");
	}
	
	function submitDSBM(){
		var btn = $("#dsbmSubmitBtn");
		if(btn.hasClass("disabled")){
			return false;
		}
		btn.addClass("disabled");
		btn.val("提交中...");
// 		setTimeout(function(){
			
// 				btn.removeClass("disabled");
// 				btn.val("马上报名");
// 		}, 2000);
		$.ajax({
			type: "POST",
			async: true,
			url: "${ctx}/nameList/json/signUp",
			data: {name:$("#dsbmName").val(),phoneNum:$("#dsbmPhoneNum").val()},
			dataType: "json",
			success: function (data) {
				if(data.success){
					alert("报名成功!\n姓名："+data.name+"\n电话："+data.phoneNum);
				}else{
					alert(data.msg);
				}
			},
			error: function (data) {
				alert("报名失败，请稍后尝试！");
			},
			complete:function(data){
				btn.removeClass("disabled");
				btn.val("马上报名");
			}
		});
		return false;
	}
	
	function nextPage(elementId){
		var element = $("#"+elementId);
		var list = element.children("ul").children("li");
		var pageSize = 5;
		var curPage = parseInt(element.attr("curPage"));
		var maxPage = Math.ceil(list.length/pageSize);
		var minPage = 1;
		if(!curPage){
			curPage = minPage;
		}
		var targetPage = curPage+1;
		if(targetPage>maxPage){
			targetPage = minPage;
		}
		list.each(function(index,ele){
			if(index<targetPage*5 && index>=(targetPage-1)*5){
				$(ele).removeClass("none");
			}else{
				$(ele).addClass("none");
			}
		})
		element.attr("curPage",targetPage);
	}
	
	function search(){
		var keyWord = $("#keyWord").val();
		keyWord = keyWord.replace(/([^A-Za-z0-9\u4e00-\u9fa5\(\)_])+/g,"");
		top.location = "${ctx}/"+keyWord+"-/search";
	}
</script>
</html>