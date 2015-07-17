<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="box-cnt js-shop-detail-container">
	<div class="form">
		<fieldset>
			<input type="hidden" id="nlId" name ="id" value="${id}" />
			<div class="control-group">
				<label class="control-label">姓名</label>
				<div class="control">
					<input type="text" name="name" max="16" maxlength="16" error="姓名长度少于16个字" 
					require="require" require_msg ="姓名不能为空"  placeholder="输入姓名"  />
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">电话</label>
				<div class="control">
					<input type="text" name="phoneNum" require="require" require_msg ="电话不能为空"  patterns="^1[0-9]{10}$"  error="手机号码格式不正确"/>
					<span class="help-inline"></span>
				</div>
			</div>
		</fieldset>
		<div class="form-actions">
		  <button type="button" class="btn btn-primary" id="submit">保存</button>
		  <button type="reset" class="btn" id="cancle">返回</button>
		</div>
	</div>
</div>
