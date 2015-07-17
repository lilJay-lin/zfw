<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
						<div class="box-cnt">
							<div class="form">
								<fieldset>
									<input type="hidden" id="infoId" name ="id" value="${infoId}" />
									<div class="control-group">
										<label class="control-label">标题</label>
										<div class="control">
											<input type="text" name="name" max="16" maxlength="16" error="资讯名长度少于16个字" 
											require="require" require_msg ="资讯名不能为空"  placeholder="输入资讯标题"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">作者</label>
										<div class="control">
											<input type="text"  name="author" id="author" max="32" error="作者不能为空" 
									require="require" require_msg ="作者不能为空" placeholder="输入作者"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">类型</label>
										<div class="control">
											<select name="type">
												<option value="房产">房产</option>
												<option value="综合">综合</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">优先级</label>
										<div class="control">
											<input type="text"  name="priority" id="priority" max="4"  error="优先级范围0-9999" 
							patterns = "^[0-9]*$"  placeholder="输入优先级 0-9999" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">标签</label>
										<div class="control">
											<input type="text"  name="tags" id="tags" max="24" maxlength="24" 
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
										<label class="control-label">提要</label>
										<div class="control">
											<textarea name="summary" style="height:100px"></textarea>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">内容</label>
										<div class="control" style="width:780px;height:600px">
										<script id="UEContainer" name="content" type="text/plain">
										</script>
									    <!-- 配置文件 -->
									    <script type="text/javascript" src="${ctx }/assets/tools/ueditor/ueditor.config.js"></script>
									    <!-- 编辑器源码文件 -->
									    <script type="text/javascript" src="${ctx }/assets/tools/ueditor/ueditor.all.js"></script>
									    <script src="${ctx }/assets/tools/ueditor/ueditor.parse.js"></script>
									    <!-- 实例化编辑器 -->
									    <script type="text/javascript">
										        var ue = UE.getEditor('UEContainer', {
// 										            toolbars: [
// 																['fullscreen', 'source', 'undo', 'redo'],
// 																['bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
// 																                   ],
													toolbars: [
													    [
													        'source', //源代码
// 													        'anchor', //锚点
													        'undo', //撤销
													        'redo', //重做
													        'bold', //加粗
													        'indent', //首行缩进
// 													        'snapscreen', //截图
													        'italic', //斜体
													        'underline', //下划线
													        'strikethrough', //删除线
													        'subscript', //下标
													        'fontborder', //字符边框
													        'superscript', //上标
													        'formatmatch', //格式刷
													        'blockquote', //引用
													        'pasteplain', //纯文本粘贴模式
													        'selectall', //全选
													        'print', //打印
													        'preview', //预览
													        'horizontal', //分隔线
													        'removeformat', //清除格式
													        'time', //时间
													        'date', //日期
													        'unlink', //取消链接
													        'insertrow', //前插入行
													        'insertcol', //前插入列
													        'mergeright', //右合并单元格
													        'mergedown', //下合并单元格
													        'deleterow', //删除行
													        'deletecol', //删除列
													        'splittorows', //拆分成行
													        'splittocols', //拆分成列
													        'splittocells', //完全拆分单元格
													        'deletecaption', //删除表格标题
													        'inserttitle', //插入标题
													        'mergecells', //合并多个单元格
													        'deletetable', //删除表格
													        'cleardoc', //清空文档
													        'insertparagraphbeforetable', //"表格前插入行"
													        'insertcode', //代码语言
													        'fontfamily', //字体
													        'fontsize', //字号
													        'paragraph', //段落格式
													        'simpleupload', //单图上传
// 													        'insertimage', //多图上传
													        'edittable', //表格属性
													        'edittd', //单元格属性
													        'link', //超链接
													        'emotion', //表情
													        'spechars', //特殊字符
													        'searchreplace', //查询替换
													        'map', //Baidu地图
// 													        'gmap', //Google地图
// 													        'insertvideo', //视频
													        'help', //帮助
													        'justifyleft', //居左对齐
													        'justifyright', //居右对齐
													        'justifycenter', //居中对齐
													        'justifyjustify', //两端对齐
													        'forecolor', //字体颜色
													        'backcolor', //背景色
													        'insertorderedlist', //有序列表
													        'insertunorderedlist', //无序列表
													        'fullscreen', //全屏
													        'directionalityltr', //从左向右输入
													        'directionalityrtl', //从右向左输入
													        'rowspacingtop', //段前距
													        'rowspacingbottom', //段后距
													        'pagebreak', //分页
													        'insertframe', //插入Iframe
													        'imagenone', //默认
													        'imageleft', //左浮动
													        'imageright', //右浮动
// 													        'attachment', //附件
													        'imagecenter', //居中
// 													        'wordimage', //图片转存
													        'lineheight', //行间距
													        'edittip ', //编辑提示
													        'customstyle', //自定义标题
													        'autotypeset', //自动排版
// 													        'webapp', //百度应用
													        'touppercase', //字母大写
													        'tolowercase', //字母小写
													        'background', //背景
// 													        'template', //模板
// 													        'scrawl', //涂鸦
// 													        'music', //音乐
													        'inserttable', //插入表格
// 													        'drafts', // 从草稿箱加载
													        'charts', // 图表
													    ]
													],
										                   autoHeightEnabled: false,
										                   autoFloatEnabled: false,
										                   enableAutoSave :false,
										                   initialFrameHeight :420,
										                   maximumWords :5000
										               });
// 										        ue.ready(function() {
// 										            //设置编辑器的内容
// 										            ue.setContent('请输入资讯内容');
// 										            //获取html内容，返回: <p>hello</p>
// // 										            var html = ue.getContent();
// 										            //获取纯文本内容，返回: hello
// // 										            var txt = ue.getContentTxt();
// 										        });
									    </script>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">描述</label>
										<div class="control">
											<textarea name="description"></textarea>
										</div>
									</div>
									
									<div class="box box-inline">
										<div class="box-hd">
											<h2>添加关联关系</h2>
										</div>
										<div class="box-cnt">
											<div class="datatable" id="repInfo">
												<div class="datatabls-filter">
													<!--搜索：-->
													<input type="text" id="searchbyname"/>
													<input type="button" class="btn btn-primary" value="搜索" id="search-rep"/>
												</div>
												<table class="datatable-table">
													<thead>
														<tr>
															<th>楼盘</th>
															<th>描述</th>
															<th class="operation">操作</th>
														</tr>
													</thead>
													<tbody class="page-data-list">
														
													</tbody>
												</table>
												<div class="datatable-footer">
													<div class="datatable-info">
														<div>共32条 当前展示第1条到第10条</div>
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
									
									<div class="box box-inline">
										<div class="box-hd">
											<h2>已选关联关系</h2>
										</div>
										<div class="box-cnt">
											<ul class="relation">
												
											</ul>
											<div class="clearfix"></div>
										</div>
									</div>
									
									<div class="form-actions">
									  <button type="button" class="btn btn-primary" id="submit">保存</button>
									  <button type="reset" class="btn cancle">返回</button>
									</div>
								</fieldset>
							</div>
						</div>