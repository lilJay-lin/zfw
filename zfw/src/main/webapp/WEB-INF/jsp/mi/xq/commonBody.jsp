<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=44843934aa23b524f4456723fea8dbdf"></script>
						<div class="box-cnt js-rc-detail-container" style="display:none">
							<div class="form">
								<fieldset>
									<input type="hidden" id="rcId" name ="id" value="${rcId}" />
									<input type="hidden" id="longitude" name ="longitude" />
									<input type="hidden" id="latitude" name ="latitude" />
									<div class="control-group">
										<label class="control-label">名称</label>
										<div class="control">
											<input type="text" name="name" max="32" maxlength="32" error="小区名长度最多32个字" 
											require="require" require_msg ="小区名不能为空"  placeholder="输入小区名称"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">地址</label>
										<div class="control">
											<textarea name="address" max="200" maxlength="200" error="地址长度最多200个字"></textarea>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">开盘时间</label>
										<div class="control">
											<input type="text"  name="onSaleDate" id="onSaleDate" 
											require="require" require_msg ="开盘时间不能为空"  placeholder="选择开盘时间" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">物业类型</label>
										<div class="control">
											<select name="propertyType">
												<option value="自住型商品房">自住型商品房</option>
												<option value="建筑综合体">建筑综合体</option>
												<option value="住宅">住宅</option>
												<option value="限价房">限价房</option>
												<option value="别墅">别墅</option>
												<option value="商铺">商铺</option>
												<option value="写字楼">写字楼</option>
												<option value="经济适用房">经济适用房</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">建筑类别</label>
										<div class="control control-large">
											<ul class="avg-5">
												<li><label><input name="buildingType" type="checkbox" value="类独栋别墅" />类独栋别墅 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="建筑综合体" />建筑综合体 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="超高层" />超高层 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="高层" />高层 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="小高层" />小高层 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="多层" />多层 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="底层" />底层 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="叠排别墅" />叠排别墅 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="联排别墅" />联排别墅 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="双拼别墅" />双拼别墅 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="独栋别墅" />独栋别墅 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="板塔结合" />板塔结合 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="板楼" />板楼 </label> </li>
												<li><label><input name="buildingType" type="checkbox" value="塔楼" />塔楼 </label> </li>
											</ul>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">住户数</label>
										<div class="control">
											<input type="text"  name="householdNum" id="householdNum" max="6"  error="住户数范围0-999999" 
											patterns = "^\d{1,6}$"  placeholder="输入住户数 0-999999" value="0" />&nbsp;户
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">容积率</label>
										<div class="control">
											<input type="text"  name="floorAreaRatio" id="floorAreaRatio" max="11" maxlength="11" error="容积率范围0.0-999999.9999" 
											patterns = "^\d{1,6}(\.\d{1,4})?$"  placeholder="输入容积率0.0-999999.9999" value="0.0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">绿化率</label>
										<div class="control">
											<input type="text"  name="greenRate" id="greenRate" max="11" maxlength="11"  error="绿化率范围0.0-99999.9999" 
											patterns = "^\d{1,6}(\.\d{1,4})?$"  placeholder="输入绿化率 0.0-99999.9999" value="0.0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">停车位</label>
										<div class="control">
											<input type="text"  name="parkingSpaceNum" id="parkingSpaceNum" max="6" maxlength="6" error="停车位范围0-999999" 
											patterns = "^\d{1,6}$"  placeholder="输入停车位 0-999999" value="0" />&nbsp;个
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">产权年限</label>
										<div class="control">
											<input type="text"  name="propertyYears" id="propertyYears" max="2" maxlength="2" error="产权年限范围0-99" 
											patterns = "^\d{1,2}$"  placeholder="输入产权年限 0-99" value="70" />&nbsp;年
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">物业公司</label>
										<div class="control">
											<input type="text"  name="propertyCompany" id="propertyCompany" max="50" maxlength="50" error="物业公司长度做多50个字" placeholder="输入物业公司"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">物业费</label>
										<div class="control">
											<input type="text"  name="propertyFee" id="propertyFee" max="7" maxlength="7" error="物业费范围0.0-9999.99" 
											patterns = "^\d{1,4}(\.\d{1,2})?$"  placeholder="输入物业费0.0-9999.99" value="0.0" />&nbsp;元/平方米·月
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">小区介绍</label>
										<div class="control">
											<textarea name="introduction" max="2000" error="介绍内容最多2000个字" maxlength="2000"></textarea>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">周边配套</label>
										<div class="control">
											<textarea name="surrounding"  max="2000" error="周边配套内容最多2000个字" maxlength="2000"></textarea>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">交通配套</label>
										<div class="control">
											<textarea name="traffic"max="2000" error="交通配套内容最多2000个字" maxlength="2000"></textarea>
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
										<label class="control-label">完善程度</label>
										<div class="control">
											<select name="active" id="active">
												<option value="true">已完善</option>
												<option value="false">待完善</option>
											</select>
										</div>
									</div>
									<div class="control-group js-not-ru">
										<label class="control-label">优先级</label>
										<div class="control">
											<input type="text"  name="priority" id="priority" max="4" maxlength="4"  error="优先级范围0-9999" 
											patterns = "^\d{1,4}$"  placeholder="输入优先级 0-9999" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">标签</label>
										<div class="control">
											<input type="text"  name="tags" id="tags" max="100" maxlength="100"  error="标签长度最大为100" 
								 placeholder="输入标签"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<form enctype="multipart/form-data" method="post" id="uploadForm">
										<div class="control-group">
											<label class="control-label">上传预览图像</label>
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
										<label class="control-label">描述</label>
										<div class="control">
											<textarea name="description" max="200" maxlength="200" error="描述最多200个字" ></textarea>
										</div>
									</div>
									<div class="box box-inline">
										<label >地图定位</label>
										<div id="l-map" style="height:600px;border: solid 3px #36a9e1;"></div>
										<script type="text/javascript">
											// 百度地图API功能
											var map = new BMap.Map("l-map");
											var point = new BMap.Point(112.475916,23.06011);
											var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
											var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
											map.addControl(top_left_control);        
											map.addControl(top_left_navigation);  
											var marker = new BMap.Marker(point);// 创建标注
											map.addOverlay(marker);             // 将标注添加到地图中
											setTimeout(function(){
												map.centerAndZoom(point, 15);
											},500);
										</script>
									</div>
									
									<div class="form-actions">
									  <button type="button" class="btn btn-primary" id="submit">保存</button>
									  <button type="reset" class="btn cancle js-add-only" style="display: none;">返回</button>
									</div>
								</fieldset>
							</div>
						</div>