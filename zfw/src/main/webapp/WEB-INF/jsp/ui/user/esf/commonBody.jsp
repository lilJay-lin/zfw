<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/top.jsp"%>
<form onsubmit="return submitForm()">
	<div class="wapForm pd10">
		<input type="hidden" id="newcode" name="newcode">
		<dl style="overflow: visible;">
			<dt>
				<span class="fc00">*</span>小区名称：
			</dt>
			<dd>
				<div class="dropbox">
					<input type="text" id="xqName" name="xqName" class="ipt-text"
						dataId="" searching="false" searchingName="" autocomplete="off"
						resultContainer="xq_search_result" placeholder="请选择下拉提示"
						onkeyup="return inputOnKeyup(event,this)"
						onFocus="return inputOnFocus(event,this)"
						onblur="return inputOnBlus(event,this)" required>
					<div class="dropcont" id="js_sea" style="display: block;">
						<ul id="xq_search_result" class="none">
						</ul>
					</div>
				</div>
			</dd>
		</dl>
		<dl>
			<dt>
				<span style="margin-right: 0.4em;"></span>户<span style="margin-right: 2em;"></span>型：
			</dt>
			<dd>
				<select name="roomNum" id="roomNum" style="width: 23%">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
				</select> 室 <select name="hallNum" id="hallNum" style="width: 23%">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
				</select> 厅 <select name="toiletNum" id="toiletNum" style="width: 23%">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
				</select> 卫
			</dd>
		</dl>
		<dl>
			<dt>
				<span class="fc00">*</span>建筑面积：
			</dt>
			<dd>
				<input id="grossFloorArea" type="text" class="ipt-text referprice" onkeyup="assessment()"
					style="width: 85%;" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" required autocomplete="off">
				平米
			</dd>
		</dl>
		<dl>
			<dt>
				<span class="fc00">*</span>套内面积：
			</dt>
			<dd>
				<input id="insideArea" type="text" class="ipt-text referprice" 
					style="width: 85%;" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" required autocomplete="off">
				平米
			</dd>
		</dl>
		<dl>
			<dt>
				<span style="margin-right: 0.4em;"></span>朝<span
					style="margin-right: 2em;"></span>向：
			</dt>
			<dd>
				<select name="forward" id="forward" class="referprice" onchange="assessment()">
					<option value="南北">南北</option>
					<option value="南">南</option>
					<option value="东南">东南</option>
					<option value="东">东</option>
					<option value="西南">西南</option>
					<option value="北">北</option>
					<option value="西">西</option>
					<option value="东西">东西</option>
					<option value="东北">东北</option>
					<option value="西北">西北</option>
				</select>
			</dd>
		</dl>
		<dl>
			<dt>
				<span style="margin-right: 0.4em;"></span>装<span
					style="margin-right: 2em;"></span>修：
			</dt>
			<dd>
				<select name="decorationStatus" id="decorationStatus"
					class="referprice">
					<option value="毛坯">毛坯</option>
					<option value="简单装修">简单装修</option>
					<option value="精装修">精装修</option>
					<option value="豪华装修">豪华装修</option>
				</select>
			</dd>
		</dl>
		<dl>
			<dt>
				<span class="fc00">*</span>楼<span
					style="margin-right: 2em;"></span>层：
			</dt>
			<dd>
				第 <input id="curFloor" type="text" pattern="^[1-9][0-9]*$" required autocomplete="off"
					class="ipt-text referprice" style="width: 27%;" onkeyup="assessment()"> 层<span
					style="margin-right: 2em;"></span>共 <input id="totalFloor"
					type="text" pattern="^[1-9][0-9]*$" required autocomplete="off"
					class="ipt-text referprice" style="width: 27%;"> 层
			</dd>
		</dl>
		<div class="formIntro">
			<p class="f16" id="pgShow"></p>
			<p class="f16">
				参考价格：<span class="fc00" id="refPrice">暂无</span><span id="priceUnit"></span>
<!-- 				参考价格：<span class="fc00" id="refPrice">124</span><span id="priceUnit">万/套</span> -->
			</p>
			<p class="f14 f999">评估价格仅供参考</p>
		</div>
		<dl>
			<dt>
				<span class="fc00">*</span>价<span style="margin-right: 2em;"></span>格：
			</dt>
			<dd>
				<input id="totalPrice" name="price" type="text" autocomplete="off"
					pattern="^[1-9][0-9]*$" required class="ipt-text"
					style="width: 80%;"> 万/套
			</dd>
		</dl>
		<dl>
			<dt>
				<span class="fc00">*</span>手<span style="margin-right: 0.5em;"></span>机<span
					style="margin-right: 0.5em;"></span>号：
			</dt>
			<dd>
				<input type="text" class="ipt-text" id="phoneNum" value=""
					placeholder="请留下电话号码" maxlength="11" pattern="^1[0-9]{10}$"
					required>
			</dd>
		</dl>

		<dl>
			<dt>
				<span class="fc00">*</span>标<span style="margin-right: 2em;"></span>题：
			</dt>
			<dd>
				<input type="text" required class="ipt-text" id="name" name="name">
			</dd>
		</dl>
		<dl>
			<dt>
				<span class="fc00">*</span>地<span style="margin-right: 2em;"></span>址：
			</dt>
		</dl>
		<dl>
			<dt>
				<textarea class="ipt-text" id="address" required name="address"
					maxlength="100" placeholder="100个字以内"></textarea>
			</dt>
		</dl>
		<dl>
			<dt>
				<span class="fc00">*</span>介<span style="margin-right: 2em;"></span>绍：
			</dt>
		</dl>
		<dl>
			<dt>
				<textarea class="ipt-text" id="introduction" required
					maxlength="500" placeholder="500个字以内" name="introduction"></textarea>
			</dt>
		</dl>
		<div class="LbtnBox">
			<input type="submit" value="立即发布" class="formbtn02 mt10" id="submit">
		</div>
	</div>
</form>