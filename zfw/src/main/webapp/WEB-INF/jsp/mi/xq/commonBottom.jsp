<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script src="${ctx }/assets/tools/jquery-ui/jquery-ui.min.js"></script>
  	<link rel="stylesheet" href="${ctx }/assets/tools/jquery-ui/jquery-ui.min.css">
  	<script type="text/x-handlebars-template" id="relation-user-template">
			{{#this}}
			<li>
				<div class="relation-info">
					<span>
						{{name}}
					</span>
					<a class="btn btn-rel btn-remove-relation"  data-id="{{id}}">
						<i class="icon-remove"></i>
					</a>
				</div>
			</li>
			{{/this}}
		</script>
		
		<script type="text/x-handlebars-template" id="relation-info-template">
			{{#this}}
			<li>
				<div class="relation-info">
					<span>
						{{name}}
					</span>
					<a class="btn btn-rel btn-remove-relation"  data-id="{{id}}">
						<i class="icon-remove"></i>
					</a>
				</div>
			</li>
			{{/this}}
		</script>
	
		<script>
		var inDetail = false;
		var inAdd = false;
		var inEdit = false;
		  $(function() {
		    $( "#onSaleDate" ).datepicker({
		    	dateFormat: 'yy-mm-dd' 
		    });
		    $( "#onReadyDate" ).datepicker({
		    	dateFormat: "yy-mm-dd"
		    });
		  });
		function template(id,data){
			var tpl = Handlebars.compile($(id).html());
			return tpl(data);
		}

		$(".cancle").on("click",function(){
// 				window.location.href = "${ctx}/mi/xf";
				window.history.back(-1);
		});
		 </script>