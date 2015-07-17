<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="box-cnt js-ht-detail-container">
	<div class="form">
		<fieldset>
			<input type="hidden" id="htId" name ="houseTypeId" value="${htId}" />
			<input type="hidden" id="imageId" name ="id" value="${imageId}" />
			<div class="control-group">
				<label class="control-label">名称</label>
				<div class="control">
					<input type="text" name="name" max="16" maxlength="16" error="图片名长度少于16个字" 
					require="require" require_msg ="图片名不能为空"  placeholder="输入图片名称"  />
					<span class="help-inline"></span>
				</div>
			</div>
			<form enctype="multipart/form-data" method="post" id="uploadForm">
				<div class="control-group">
					<label class="control-label">上传图片</label>
					<div class="control">
						<div class="uploader">
							<input type="hidden" name="contentUrl" />
							<input type="file" name="theFile" accept="image/*"/>
							<span class="filename" style="-webkit-user-select: none;">没有选择文件...</span>
							<span class="action" style="-webkit-user-select: none;">选择</span>
						</div>
						<div class="control-img">
							<img src=""/>
						</div>
					</div>
				</div>
			</form>
			<div class="control-group">
				<label class="control-label">描述</label>
				<div class="control">
					<textarea name="description" maxlength="200" style="height:100px"></textarea>
				</div>
			</div>
			<div class="form-actions">
			  <button type="button" class="btn btn-primary" id="submit">保存</button>
			  <button type="reset" class="btn" id="cancle">返回</button>
			</div>
		</fieldset>
	</div>
</div>