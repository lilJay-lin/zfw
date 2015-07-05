<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/top.jsp"%>
<form onsubmit="return submitForm()">
	<div class="wapForm pd10">
		<input type="hidden" id="newcode" name="newcode">
		<dl>
			<dt>
				<span class="fc00">*</span>面<span style="margin-right: 2em;"></span>积：
			</dt>
			<dd>
				<input id="grossFloorArea" type="text" class="ipt-text referprice" autocomplete="off"
					style="width: 85%;" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" required>
				平米
			</dd>
		</dl>
		<dl>
			<dt>
				<span style="margin-right: 0.4em;"></span>区<span
					style="margin-right: 2em;"></span>域：
			</dt>
			<dd>
				<select name="region" id="region" class="referprice">
					<option value="城东"> 城东 </option>
					<option value="城西"> 城西 </option>
					<option value="城中"> 城中 </option>
					<option value="四会"> 四会 </option>
					<option value="高要"> 高要 </option>
					<option value="广宁"> 广宁 </option>
					<option value="怀集"> 怀集 </option>
					<option value="封开"> 封开 </option>
					<option value="德庆"> 德庆 </option>
					<option value="鼎湖"> 鼎湖 </option>
				</select>
			</dd>
		</dl>
		<dl>
			<dt>
				<span style="margin-right: 0.4em;"></span>类<span
					style="margin-right: 2em;"></span>别：
			</dt>
			<dd>
				<select name="type" id="type" class="referprice">
					<option value="住宅底商">住宅底商</option>
					<option value="商业街商铺">商业街商铺</option>
					<option value="临街门面">临街门面</option>
					<option value="写字楼配套底铺">写字楼配套底铺</option>
					<option value="购物中心/百货">购物中心/百货</option>
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
				<span class="fc00"></span>物<span style="margin-right: 0.5em;"></span>业<span
					style="margin-right: 0.5em;"></span>费：
			</dt>
			<dd>
				<input id="propertyFee" name="propertyFee" type="number" class="ipt-text"
					style="width: 60%;"> 元/平米·月
			</dd>
		</dl>
		<dl>
			<dt>
				<span class="fc00"></span>出<span style="margin-right: 2em;"></span>租：
			</dt>
			<dd>
				<input id="rental" name="rental" type="number" class="ipt-text"
					style="width: 80%;"> 元/月
			</dd>
		</dl>
		<dl>
			<dt>
				<span class="fc00"></span>出<span style="margin-right: 2em;"></span>售：
			</dt>
			<dd>
				<input id="totalPrice" name="totalPrice" type="number" class="ipt-text"
					style="width: 80%;"> 万元
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