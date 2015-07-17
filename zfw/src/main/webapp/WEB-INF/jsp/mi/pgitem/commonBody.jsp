<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
						<div class="box-cnt js-ai-detail-container" style="display:none">
							<div class="form">
								<fieldset>
									<input type="hidden" id="aiId" name ="id" value="${aiId}" />
									<div class="control-group">
										<label class="control-label">名称</label>
										<div class="control">
											<input type="text" name="name" max="16" maxlength="16" error="评估项名长度少于16个字" 
											require="require" require_msg ="评估项名不能为空"  placeholder="输入评估项名称"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">类型</label>
										<div class="control">
											<select name="type">
												<option value="单选">单选</option>
												<option value="多选">多选</option>
											</select>
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
									  <button type="reset" class="btn cancle js-add-only" style="display: none;">返回</button>
									</div>
								</fieldset>
							</div>
						</div>