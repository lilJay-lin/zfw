<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=44843934aa23b524f4456723fea8dbdf"></script>
						<div class="box-cnt js-rh-detail-container" style="display:none">
							<div class="form">
								<fieldset>
									<input type="hidden" id="rcId" name ="residenceCommunityId" value="${rcId}" />
									<input type="hidden" id="rhId" name ="id" value="${rhId}" />
									<div class="control-group">
										<label class="control-label">名称</label>
										<div class="control error">
											<input type="text" name="name" max="16" maxlength="16" error="租房名长度少于16个字" 
											require="require" require_msg ="租房名不能为空"  placeholder="输入租房名称"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">联系电话</label>
										<div class="control error">
											<input type="text"  name="phoneNum" id="phoneNum" max="11"  error="联系电话格式有误" 
											patterns = "^[0-9]*$" placeholder="输入联系电话"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">租房租金</label>
										<div class="control error">
											<input type="text"  name="rental" id="rental" max="6"  error="租房租金范围0-9999" 
											patterns = "^[0-9]*$"  placeholder="输入租房租金 0-9999" value="0" />&nbsp;元/月
											<span class="help-inline"></span>
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
										<label class="control-label">卫生间数量</label>
										<div class="control error">
											<input type="text"  name="toiletNum" id="toiletNum" max="2"  error="卫生间数量范围0-99" 
											patterns = "^[0-9]*$"  placeholder="输入 卫生间数量0-99" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">朝向</label>
										<div class="control error">
											<select name="forward">
												<option value="南北">南北</option>
												<option value="南">南</option>
												<option value="东南">东南</option>
												<option value="东">东</option>
												<option value="西南">西南</option>
												<option value="北">北</option>
												<option value="西">西</option>
												<option value="东西">东西</option>
												<option value="东北">东北</option>
												<option value="西北">西北</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">所在楼层</label>
										<div class="control error">
											<input type="text"  name="curFloor" id="curFloor" max="3"  error="所在楼层范围0-999" 
											patterns = "^[0-9]*$"  placeholder="输入 所在楼层0-999" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">总楼层</label>
										<div class="control error">
											<input type="text"  name="totalFloor" id="totalFloor" max="3"  error="总楼层范围0-999" 
											patterns = "^[0-9]*$"  placeholder="输入 总楼层0-999" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">装修状况</label>
										<div class="control error">
											<select name="decorationStatus">
												<option value="毛坯">毛坯</option>
												<option value="简单装修">简单装修</option>
												<option value="精装修">精装修</option>
												<option value="豪华装修">豪华装修</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">租凭方式</label>
										<div class="control error">
											<select name="leaseWay">
												<option value="整租">整租</option>
												<option value="合租">合租</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">配套设施</label>
										<div class="control error">
											<label><input name="facilityBed" type="checkbox" />床 </label> 
											<label><input name="facilityBroadband" type="checkbox" />宽带</label> 
											<label><input name="facilityTv" type="checkbox" />电视 </label> 
											<label><input name="facilityWasher" type="checkbox" />洗衣机 </label> 
											<label><input name="facilityRefrigerator" type="checkbox" />冰箱 </label> 
											<label><input name="facilityAirConditioner" type="checkbox" />空调 </label> 
											<label><input name="facilityHeating" type="checkbox" />暖气 </label> 
											<label><input name="facilityHeater" type="checkbox" />热水器 </label> 
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">地址</label>
										<div class="control error">
											<textarea name="address" maxlength="200"></textarea>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">租房介绍</label>
										<div class="control error">
											<textarea name="introduction" maxlength="2000"></textarea>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">信息时效</label>
										<div class="control error">
											<select name="outOfDate" id="outOfDate">
												<option value="false">未过期</option>
												<option value="true">已过期</option>
											</select>
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
									<div class="control-group">
										<label class="control-label">所属小区</label>
										<div class="control error">
											<input type="text" name="residenceCommunityName"  readonly value="${rcName }"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="box box-inline js-edit-only" style="display:none">
										<div class="box-hd">
											<h2>选择小区</h2>
										</div>
										<div class="box-cnt">
											<div class="datatable" id="rcInfo">
												<div class="datatabls-filter">
													<!--搜索：-->
													<input type="text" id="search-rc-text"/>
													<input type="button" class="btn" value="搜索" id="search-rc-btn"/>
												</div>
												<table class="datatable-table">
													<thead>
														<tr>
															<th>名称</th>
															<th>描述</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody class="page-data-list">
														
													</tbody>
												</table>
												<div class="datatable-footer">
													<div class="datatable-info">
														<div>共0条</div>
													</div>
													<div class="center">
														<div class="datatable-pagination">
															<ul class="pagination">
																
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="form-actions">
									  <button type="button" class="btn btn-primary" id="submit">保存</button>
									  <button type="reset" class="btn" id="cancle">返回</button>
									</div>
								</fieldset>
							</div>
						</div>