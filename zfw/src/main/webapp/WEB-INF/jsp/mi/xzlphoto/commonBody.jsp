<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="box-cnt js-rep-detail-container">
	<div class="form">
		<fieldset>
			<input type="hidden" id="officeBuildingId" name ="officeBuildingId" value="${officeBuildingId}" />
			<input type="hidden" id="imageId" name ="id" value="${imageId}" />
			<div class="control-group">
				<label class="control-label">名称</label>
				<div class="control">
					<input type="text" name="name" max="32" maxlength="32" error="图片名长度最多32个字" 
					require="require" require_msg ="图片名不能为空"  placeholder="输入图片名称"  />
					<span class="help-inline"></span>
				</div>
			</div>
			<form enctype="multipart/form-data" method="post" id="uploadForm">
				<div class="control-group">
					<label class="control-label">图像</label>
					<!--<div class="control control-img-box">
						<img  class="control-user-img" />
					</div>-->
					<div class="control js-not-detail">
						<div class="uploader">
							<input type="hidden" name="contentUrl" />
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
					<div class="control">
						<span class="help-inline uploade-img-error"></span>
					</div>
					<div class="control-img">
						<img src=""/>
					</div>
				</div>
			</form>
			<div class="control-group">
				<label class="control-label">描述</label>
				<div class="control">
					<textarea name="description" max="200" maxlength="200" error="描述最多200个字"></textarea>
				</div>
			</div>
			<div class="form-actions">
			  <button type="button" class="btn btn-primary js-not-detail" id="submit">保存</button>
			  <button type="reset" class="btn cancle">返回</button>
			</div>
		</fieldset>
	</div>
</div>