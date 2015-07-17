<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="box-cnt js-shop-detail-container" style="display: none">
	<div class="form">
		<fieldset>
			<!--<input type="hidden" id="repId" name ="realEstateProjectId" value="${repId}" />-->
			<input type="hidden" id="shopId" name ="id" value="${shopId}" />
			<form enctype="multipart/form-data" method="post" id="uploadForm">
				<div class="control-group">
					<label class="control-label">上传缩略图</label>
					<div class="control control-img-box">
						<img class="control-user-img" />
					</div>
					<div class="control">
						<div class="uploader">
							<input type="hidden" name="preImageUrl" />
							<input type="file" name="theFile" accept="image/*"/>
							<span class="filename" style="-webkit-user-select: none;">没有选择文件...</span>
							<span class="action" style="-webkit-user-select: none;">选择</span>
						</div>
					</div>
					<div class="control uploader-loading" style="display: none;">
						<div class="loading">
							<img src="${ctx}/assets/img/loading.gif"  />
						</div>
					</div>
				</div>
			</form>
			<div class="control-group">
				<label class="control-label">名称</label>
				<div class="control">
					<input type="text" name="name" max="16" maxlength="16" error="全商铺名长度少于16个字" 
					require="require" require_msg ="全商铺名不能为空"  placeholder="输入全商铺名称"  />
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">销售类型</label>
				<div class="control">
					<select name="rentOrSale">
						<option value="出售">出售</option>
						<option value="出租">出租</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">面积</label>
				<div class="control">
					<input type="text" name="grossFloorArea"  />
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">装修</label>
				<div class="control">
					<select name="decorationStatus">
						<option value="毛坯">毛坯</option>
						<option value="简单装修">简单装修</option>
						<option value="精装修">精装修</option>
						<option value="豪华装修">豪华装修</option>
					</select>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">区域</label>
				<div class="control">
					<select name="region">
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
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">类别</label>
				<div class="control">
					<select name="type">
						<option value="住宅底商">住宅底商</option>
						<option value="商业街商铺">商业街商铺</option>
						<option value="临街门面">临街门面</option>
						<option value="写字楼配套底铺">写字楼配套底铺</option>
						<option value="购物中心/百货">购物中心/百货</option>
					</select>
				</div>
			</div>
			<div class="control-group js-control-group-rental">
				<label class="control-label">出租</label>
				<div class="control">
					<input type="text"  name="rental" id="rental" max="6"  error="租金范围0.0-9999.0" 
											patterns = "^[0-9\.]*$"  placeholder="输入出租费0.0-9999.0" value="0.0" />&nbsp;元/平方米·月
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group js-control-group-totalPrice">
				<label class="control-label">在售</label>
				<div class="control">
					<input type="text"  name="totalPrice" id="totalPrice" max="5"  error="租金范围0.0-999.0" 
											patterns = "^[0-9\.]*$"  placeholder="输入出售费0.0-999.0" value="0.0" />&nbsp;万元
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">物业费</label>
				<div class="control">
					<input type="text"  name="propertyFee" id="propertyFee" max="5"  error="物业费范围0.0-999.0" 
					patterns = "^[0-9\.]*$"  placeholder="输入物业费0.0-999.0" value="0.0" />&nbsp;元/平方米·月
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">地址</label>
				<div class="control">
					<textarea name="address" maxlength="100" error="地址内容长度不能超过100"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">优先级</label>
				<div class="control">
					<input type="text"  name="priority" id="priority" max="4"  error="优先级范围0-9999" 
					patterns = "^[0-9]*$" error="最多只能输入4个字符" placeholder="输入优先级 0-9999" value="0" />
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">标签</label>
				<div class="control">
					<input type="text"  error="最多只能输入24个字符"  name="tags" id="tags" max="24" maxlength="24" 
		 placeholder="输入标签"  />
					<span class="help-inline"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">热线电话</label>
				<div class="control">
					<input type="text"  name="phoneNum" id="phoneNum" max="32" error="热线电话不能超长" placeholder="输入热线电话"/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">描述</label>
				<div class="control">
					<textarea name="introduction" maxlength="200" style="height:100px"></textarea>
				</div>
			</div>
			<div class="form-actions" style="display: none;">
			  <button type="button" class="btn btn-primary" id="submit">保存</button>
			  <button type="reset" class="btn cancle" style="display: none;">返回</button>
			</div>
		</fieldset>
	</div>
</div>
