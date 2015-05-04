<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>role</title>
  </head>
  
  <body>
  		<a href="${pageContext.request.contextPath}/role/batchSave">批量增加</a><br/>
  		time:${time }<br/>
  		${fn:length(roles)}<br/>
       	<c:forEach items="${roles}" var="t" varStatus="status">
       		<span>${t.id }</span><span>${t.name }</span><span>${t.description }</span><br/>
       	</c:forEach>
  </body>
</html>
