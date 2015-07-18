<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="box-cnt js-shop-detail-container js-add-only" style="display: none">
	<div class="form">
		<fieldset>
			<input type="hidden" id="shopId" name ="id" value="${shopId}" />
			<form enctype="multipart/form-data" method="post" id="uploadForm">
				<div class="control-group">
					<label class="control-label">上传缩略图</label>
					<div class="control control-img-box">
						<img class="control-user-img" />
					</div>
					<div class="control js-not-detail">
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
					<input type="text" name="name" max="32" maxlength="32" error="商铺名长度少于32个字" 
					require="require" require_msg ="商铺名不能为空"  placeholder="输入商铺名称"  />
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
				<label class="control-label">热线电话</label>
				<div class="control">
					<input type="text"  name="phoneNum" id="phoneNum" patterns="^((0\d{2,3}-\d{7,8})|(1\d{10}))$" error="热线电话格式为固话(0660-12345678）或者手机号码" placeholder="输入热线电话"/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group js-control-group-rental">
				<label class="control-label">租金</label>
				<div class="control">
					<input type="text"  name="rental" id="rental" max="6" maxlength="6" error="租金范围0-999999" 
								patterns = "^\d{1,6}$"  placeholder="输入租金范围0-999999" value="0" />&nbsp;元/平方米·月
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group js-control-group-totalPrice">
				<label class="control-label">总价</label>
				<div class="control">
					<input type="text"  name="totalPrice" id="totalPrice" max="4" maxlength="4"  error="总价范围0-9999" 
											patterns = "^\d{1,4}$"  placeholder="输入总价0-9999" value="0" />&nbsp;万元
					<span class="help-inline"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">销售类型</label>
				<div class="control">
					<select name="rentOrSale">
						<option value="不限">租售</option>
						<option value="出售">出售</option>
						<option value="出租">出租</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">面积</label>
				<div class="control">
					<input type="text" name="grossFloorArea" max="9" maxlength="9"  error="建筑面积范围0.00-999999.99" 
											patterns = "^\d{1,6}(\.\d{1,2})?$"  placeholder="输入建筑面积0.00-999999.99" value="0.0"   />&nbsp;平方米
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
				<label class="control-label">地址</label>
				<div class="control">
					<textarea name="address" maxlength="200" max="200" style="height:50px" error="地址内容长度不能超过200"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">介绍</label>
				<div class="control">
					<textarea name="introduction" maxlength="2000" max="2000" error="介绍内容长度不能超过2000"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">物业费</label>
				<div class="control">
					<input type="text"  name="propertyFee" id="propertyFee" max="7" maxlength="7"  error="物业费范围0.00-9999.99" 
					patterns = "^\d{1,4}(\.\d{1,2})?$"  placeholder="输入物业费0.00-9999.99" value="0.00" />&nbsp;元/平方米·月
					<span class="help-inline"></span>
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
			<div class="control-group">
				<label class="control-label">信息时效</label>
				<div class="control">
					<select name="outOfDate" id="outOfDate">
						<option value="false">未过期</option>
						<option value="true">已过期</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">描述</label>
				<div class="control">
					<textarea name="description" max="200" maxlength="200" error="描述最多200个字" style="height:100px"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">标签</label>
				<div class="control">
					<input type="text" max="100" maxlength="100"  error="标签长度最大为100"  name="tags" id="tags" placeholder="输入标签"  />
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">优先级</label>
				<div class="control">
					<input type="text"  name="priority" id="priority" max="4"  error="优先级范围0-9999" 
					patterns = "^\d{1,4}$" error="优先级最大长度为4" placeholder="输入优先级 0-9999" value="0" />
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="form-actions">
			  <button type="button" class="btn btn-primary js-not-detail" id="submit">保存</button>
			  <button type="reset" class="btn cancle js-add-only" style="display: none;">返回</button>
			</div>
		</fieldset>
	</div>
</div>
