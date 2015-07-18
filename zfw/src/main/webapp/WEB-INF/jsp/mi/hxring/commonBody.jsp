<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="box-cnt js-ht-detail-container">
	<div class="form">
		<fieldset>
			<input type="hidden" id="htId" name ="houseTypeId" value="${htId}" />
			<input type="hidden" id="ringId" name ="id" value="${ringId}" />
			<div class="control-group">
				<label class="control-label">名称</label>
				<div class="control">
					<input type="text" name="name"  max="32" maxlength="32" error="三维名长度少于32个字" 
					require="require" require_msg ="三维名不能为空"  placeholder="输入三维名称"  />
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">内容路径</label>
				<div class="control">
					<textarea name="contentUrl" max="200" maxlength="200" error="内容路径最多200个字" require="require" require_msg ="内容路径不能为空"  style="height:60px"></textarea>
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
						<div class="control">
							<span class="help-inline uploade-img-error"></span>
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