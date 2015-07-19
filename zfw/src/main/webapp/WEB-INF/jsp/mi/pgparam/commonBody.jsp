<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
						<div class="box-cnt js-ap-detail-container" style="display:none">
							<div class="form">
								<fieldset>
									<input type="hidden" id="aiId" name ="assessmentItemId" value="${aiId}" />
									<input type="hidden" id="apId" name ="id" value="${apId}" />
									<div class="control-group">
										<label class="control-label">名称</label>
										<div class="control">
											<input type="text" name="name" max="32" maxlength="32" error="评估参数名长度少于32个字" 
											require="require" require_msg ="评估参数名不能为空"  placeholder="输入评估参数名称"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">值</label>
										<div class="control">
											<input type="text" name="value"  error="评估参数值为整数，最大为8位数" 
											require="require" patterns = "^-?\d{1,8}$" require_msg ="评估参数值不能为空"  placeholder="输入评估参数值"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">描述</label>
										<div class="control">
											<textarea name="description" maxlength="200"></textarea>
										</div>
									</div>
									<div class="form-actions">
									  <button type="button" class="btn btn-primary" id="submit">保存</button>
									  <button type="reset" class="btn cancle">返回</button>
									</div>
								</fieldset>
							</div>
						</div>