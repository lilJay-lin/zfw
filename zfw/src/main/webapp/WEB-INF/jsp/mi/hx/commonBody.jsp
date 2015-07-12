<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=44843934aa23b524f4456723fea8dbdf"></script>
						<div class="box-cnt js-ht-detail-container" style="display:none">
							<div class="form">
								<fieldset>
									<input type="hidden" id="repId" name ="realEstateProjectId" value="${repId}" />
									<input type="hidden" id="htId" name ="id" value="${htId}" />
									<div class="control-group">
										<label class="control-label">名称</label>
										<div class="control error">
											<input type="text" name="name" max="16" maxlength="16" error="户型名长度少于16个字" 
											require="require" require_msg ="户型名不能为空"  placeholder="输入户型名称"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">户型均价</label>
										<div class="control error">
											<input type="text"  name="averagePrice" id="averagePrice" max="6"  error="户型均价范围0-999999" 
											patterns = "^[0-9]*$"  placeholder="输入户型均价 0-999999" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">开售时间</label>
										<div class="control error">
											<input type="text"  name="onSaleDate" id="onSaleDate"
											require="require" require_msg ="开售时间不能为空"  placeholder="选择开售时间" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">销售</label>
										<div class="control error">
											<select name="saleStatus">
												<option value="在售">在售</option>
												<option value="售完">售完</option>
												<option value="待售">待售</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">建筑面积</label>
										<div class="control error">
											<input type="text"  name="grossFloorArea" id="grossFloorArea" max="6"  error="建筑面积范围0.0-999.0" 
											patterns = "^[0-9\.]*$"  placeholder="输入建筑面积0.0-999.0" value="0.0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">居室数量</label>
										<div class="control error">
											<input type="text"  name="roomNum" id="roomNum" max="2"  error="居室数量范围0-99" 
											patterns = "^[0-9]*$"  placeholder="输入居室数 量0-99" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">厅数量</label>
										<div class="control error">
											<input type="text"  name="hallNum" id="hallNum" max="2"  error="厅数量范围0-99" 
											patterns = "^[0-9]*$"  placeholder="输入厅数 量0-99" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">厨房数量</label>
										<div class="control error">
											<input type="text"  name="kitchenNum" id="kitchenNum" max="2"  error="厨房数量范围0-99" 
											patterns = "^[0-9]*$"  placeholder="输入厨房数量 0-99" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">卫生间数量</label>
										<div class="control error">
											<input type="text"  name="toiletNum" id="toiletNum" max="2"  error="卫生间数量范围0-99" 
											patterns = "^[0-9]*$"  placeholder="输入 卫生间数量0-99" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">优先级</label>
										<div class="control error">
											<input type="text"  name="priority" id="priority" max="4"  error="优先级范围0-9999" 
											patterns = "^[0-9]*$"  placeholder="输入优先级 0-9999" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">标签</label>
										<div class="control error">
											<input type="text"  name="tags" id="tags" max="24" maxlength="24" 
								 placeholder="输入标签"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<form enctype="multipart/form-data" method="post" id="uploadForm">
										<div class="control-group">
											<label class="control-label">上传预览图像</label>
											<div class="control error">
												<div class="uploader">
													<input type="hidden" name="preImageUrl" />
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
										<div class="control error">
											<textarea name="description" maxlength="200"></textarea>
										</div>
									</div>
									<div class="form-actions">
									  <button type="button" class="btn btn-primary" id="submit">保存</button>
									  <button type="reset" class="btn" id="cancle">返回</button>
									</div>
								</fieldset>
							</div>
						</div>