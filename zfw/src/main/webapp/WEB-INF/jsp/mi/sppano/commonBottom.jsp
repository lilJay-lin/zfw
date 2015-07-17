<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	var inDetail = false;
	var inAdd = false;
	var inEdit = false;
	$(".cancle").on("click",function(){
// 		window.location.href = "${ctx}/mi/shop/${shopId}/edit";
		window.history.back(-1);
	});
</script>