<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<script>
/*
 * 返回
 */
$("#cancle").on("click",function(){
	if(window.confirm("确定返回？")){
		window.location.href = "${ctx}/mi/nl";
	}
});

function getSaveData(){
	var nl = {
		id:"",
		name:"",
		phoneNum:""
	};
   for(var i in nl){
   		var value = $("[name="+i+"]").val();
   		nl[i]=value;
   }
   return nl;
}


</script>