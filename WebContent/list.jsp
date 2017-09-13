<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${initParam.appName}</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/css1.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/pub.css">
<script type="text/javascript">
	function openWindow(id){
		window.open('${pageContext.request.contextPath}/car.do?action=add&id='+id, 'win', 'height=200px,width=200px');
	}
	
	function carShow(){
		location.href='${pageContext.request.contextPath}/car.do';
	}
</script>	
	
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
			商品类别:
			<c:if test="${not empty bookclass}">${bookclass.className }</c:if>
			<c:if test="${empty bookclass}">所有图书</c:if>
				<TABLE cellSpacing=1 cellPadding=1 width="100%" border=1
					bordercolor="black">
					<TBODY>
						<TR bgColor=#e1e1e1>
							<TD class=txt align=center width="8%" height=25><FONT
								color=#000000>商品ID</FONT></TD>
							<TD class=txt align=center width="20%" height=25><FONT
								color=#000000>商品名称</FONT></TD>
							<TD class=txt align=center width="14%" height=25><FONT
								color=#000000>商品类别</FONT></TD>
							<TD class=txt align=center width="13%" height=25><FONT
								color=#000000>单位数量</FONT></TD>
							<TD class=txt align=center width="15%" height=25><FONT
								color=#000000>单价</FONT></TD>
							<TD class=txt align=center width="10%" height=25><FONT
								color=#000000>购买</FONT></TD>
						</TR>

						<!-- 通过获取bookList集合来遍历Book对象进行输出 -->
						<c:if test="${not empty bookList}">
							<c:forEach var="book" items="${bookList }" varStatus="ss">
								<TR bgColor=#ffffff>
									<TD class=txt align=center width="8%">${ss.count }</TD>
									<TD class=txt align=center width="20%">${book.bookName }</TD>
									<TD class=txt align=center width="20%">${book.bookcl.className }</TD>
									<TD class=txt align=center width="14%">${book.bookNum }</TD>
									<TD class=txt align=center>${book.bookPrice }</TD>
									<TD align=center  >
									<a  onclick="openWindow(${book.id})"><img src="${pageContext.request.contextPath}/images/cart.gif"
										style="border: 0px" /></a>
									</TD>
								</TR>
							</c:forEach>
							
							<c:if test="${empty bookList}">
								<tr><td>没有数据</td></tr>
							</c:if>

							<c:if test="${(not empty pagebean) and (pagebean.maxPage gt 1) }">
								<tr>
									<td colspan=3 align=center>
									<c:forEach var="i" begin="${pagebean.beginPage }" end="${pagebean.endPage }" >
										<c:if test="${pagebean.currentPageNum eq i }">
											[<font color=red>${i}</font>]&nbsp;&nbsp;
										</c:if>
										<c:if test="${pagebean.currentPageNum ne i }">
											<c:url var="uu" value="book.do">
												<c:param name="currentPageNum" value="${i }"></c:param>
												<c:param name="bookclassid" value="${param.bookclassid}"></c:param>
												<c:param name="keyword" value="${requestScope.keyword }"></c:param>
											</c:url>
											[<a href="${uu}">${i}</a>]&nbsp;&nbsp;
										</c:if>
									</c:forEach>
									</td>
									<td colspan=3 align=center>每页:${pagebean.perPageRow }个 页码:${pagebean.currentPageNum }/${pagebean.maxPage }页 共有:${pagebean.totalRows }个</td>
								</tr>
							</c:if>
						</c:if>
				</table>
			</div>
		</div>
		<!-- 页脚 -->
		<div id="footer">
			<%@ include file="/commons/foot.jsp"%>
		</div>
	</div>
</body>
</html>