<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="box-cnt js-shh-detail-container">
	<div class="form">
		<fieldset>
			<input type="hidden" id="adId" name ="id" value="${adId}" />
			<div class="control-group">
				<label class="control-label">名称</label>
				<div class="control">
					<input type="text" name="name" max="50" maxlength="50" error="广告名长度最多50个字" 
					require="require" require_msg ="广告名不能为空"  placeholder="输入广告名称"  />
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">内容路径</label>
				<div class="control">
					<textarea name="contentUrl" max="200" maxlength="200" error="内容路径最多200个字" require="require" require_msg ="内容路径不能为空" style="height:60px"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">提要</label>
				<div class="control">
					<textarea name="summary"  require="require" require_msg ="提要不能为空" maxlength="200" max="200" error="提要长度最多200个字" style="height:60px"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">位置</label>
				<div class="control">
					<select name="location"  disabled>
						<option value="首页顶端">首页顶端</option>
						<option value="首页中间小型">首页中间小型</option>
						<option value="首页中间大型">首页中间大型</option>
						<option value="资讯页">资讯页</option>
					</select>
				</div>
			</div>
			<form enctype="multipart/form-data" method="post" id="uploadForm">
				<div class="control-group">
					<label class="control-label">上传缩放图像</label>
					<div class="control">
						<div class="uploader">
							<input type="hidden" name="preImageUrl" />
							<input type="file" name="theFile" accept="image/*"/>
							<span class="filename" style="-webkit-user-select: none;">没有选择文件...</span>
							<span class="action" style="-webkit-user-select: none;">选择</span>
						</div>
						<div class="control uploader-loading" style="display: none;">
							<div class="loading">
								<img src="${ctx}/assets/img/loading.gif"  />
							</div>
						</div>
						<div class="control-img">
							<img src=""/>
						</div>
					</div>
				</div>
			</form>
			<div class="control-group">
				<label class="control-label">优先级</label>
				<div class="control">
					<input type="text"  name="priority" id="priority" max="4" maxlength="4" error="优先级范围0-9999" 
					patterns = "^\d{1,4}$"  placeholder="输入优先级 0-9999" value="0" />
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否显示</label>
				<div class="control">
					<select name="active" id="active">
						<option value="true">true</option>
						<option value="false">false</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">描述</label>
				<div class="control">
					<textarea name="description" max="200" maxlength="200" error="描述最多200个字" style="height:100px"></textarea>
				</div>
			</div>
			<div class="form-actions">
			  <button type="button" class="btn btn-primary" id="submit">保存</button>
			  <button type="reset" class="btn cancle">返回</button>
			</div>
		</fieldset>
	</div>
</div>