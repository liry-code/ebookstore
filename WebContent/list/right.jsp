<%@ page language="java"pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<!-- 右侧 -->
	<div id="mainbody">
		商品类别:所有
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
				<c:if test="${not empty booklist}">
					<c:forEach var="book" items="${booklist }" varStatus="ss">
				
						<TR bgColor=#ffffff>
							<TD class=txt align=center width="8%">${ss.counter }</TD>
							<TD class=txt align=center width="20%">${book.name }</TD>
							<TD class=txt align=center width="20%">${book.bookClass.className }</TD>
							<TD class=txt align=center width="14%">${book.bookNum }</TD>
							<TD class=txt align=center>${book.bookPrice }</TD>
							<TD
								onmouseover="this.style.cursor='hand';this.style.backgroundColor='#eeeeee';"
								onclick="" onmouseout="this.style.backgroundColor='#ffffff';"
								noWrap align=center><img src="${pageContext.request.contextPath}/images/cart.gif" style="border:0px" /></TD>
						</TR>
				
					</c:forEach>
				</c:if>
				
				<tr>
					<td colspan=3 align=center>1&nbsp;&nbsp;2&nbsp;&nbsp;3&nbsp;&nbsp;4&nbsp;&nbsp;5&nbsp;&nbsp;6&nbsp;&nbsp;7&nbsp;&nbsp;8</td>
					<td colspan=3 align=center>每页:15个 页码:1/8页 共有:87个</td>
				</tr>
		</table>

	</div>
</body>
</html>