<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>${initParam.appName}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/css1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pub.css">

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
				<TABLE cellSpacing=0 cellPadding=0 border=0>
					<TBODY>
						<TR>
							<TD vAlign=top><IMG src="${pageContext.request.contextPath}/images/login.gif" border=0></TD>
						</TR>
						<TR>
							<td>
								<h1>恭喜你注册成功</h1>
							</td>
						</TR>
					</TBODY>
				</TABLE>
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
