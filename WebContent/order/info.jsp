<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${initParam.appName}</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/css1.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/pub.css" />

</head>
<body>
	<div id="container">
		<!-- 页头 -->
		<%@ include file="/commons/head.jsp"%>

		<div id="pagebody">
			<!-- 左侧 -->
			<%@ include file="/commons/left.jsp"%>

			<!-- 右侧 -->
			<div id="mainbody">
				<BR>请记录您的订单。
				<HR SIZE=1>
					<h3>${param.id }</h3>
				<HR SIZE=1>
				<BR>
			</div>
		</div>


		<!-- 页脚 -->
		<div id="footer">
			<%@ include file="/commons/foot.jsp"%>
		</div>
	</div>
</body>
</html>