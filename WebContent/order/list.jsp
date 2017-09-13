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
				<BR>1、如果需要修改请到订购车处进行。
				<HR SIZE=1>

				<FORM name=frmservice onsubmit="return checkaddress()"
					action=order.do?act=insert method=post>
					<TABLE cellSpacing=2 cellPadding=0 width="100%" border=0>
						<TBODY>
							<TR bgColor=#558bff>
								<TD class=txt align=center width="8%" height=25><FONT
									color=#ffffff>序号</FONT></TD>
								<TD class=txt align=center width="25%" height=25><FONT
									color=#ffffff>物品名称</FONT></TD>
								<TD class=txt align=center width="23%" height=25><FONT
									color=#ffffff>订购数量</FONT></TD>
								<TD class=txt align=center width="15%" height=25><FONT
									color=#ffffff>价格</FONT></TD>
								<TD class=txt align=center width="14%" height=25><FONT
									color=#ffffff>总价</FONT></TD>
								<TD class=txt align=center width="15%" height=25><FONT
									color=#ffffff>删除</FONT></TD>
							</TR>
							
						            
							<c:if test="${(not empty sessionScope.car )&&(not empty sessionScope.car.allItems)}">
							<c:forEach var="kk" items="${car.allItems }" varStatus="ss">
								<TR bgColor=#e1e1e1>
									<TD class=txt align=center width="8%" height=25>${ss.count }</TD>
									<TD class=txt align=center width="25%" height=25>${kk.book.bookName }</TD>
									<TD class=txt align=center width="23%" height=25>
										<input name="ids" value="${kk.book.id }" type="hidden" />
										<INPUT size=12 value="${kk.num }" name="nums"></TD>
									<TD class=txt align=center width="15%" height=25>${kk.book.bookPrice }</TD>
									<TD class=txt align=center width="14%" height=25>${kk.allPrice }</TD>
									<TD class=txt align=center width="15%" height=25>
									<A href="${pageContext.request.contextPath }/car.do?action=delete&id=${kk.book.id }">删除</A></TD>
								</TR>
							</c:forEach>
							</c:if>
							
							
							<TR bgColor=#e1e1e1>
								<TD class=txt align=center width="8%" height=25></TD>
								<TD class=txt align=center width="25%" height=25></TD>
								<TD class=txt align=center width="23%" height=25></TD>
								<TD class=txt align=center width="15%" height=25>总计</TD>
								<TD class=txt align=center width="14%" height=25>${car.totalPrice }</TD>
								<TD class=txt align=center width="15%" height=25></TD>
							</TR>
							<TR bgColor=#e1e1e1>
								<TD class=txt align=center colSpan=6 height=25>
									<a href="javascript:void(0);" onclick="sub()">更新购物车</a>&nbsp;&nbsp;&nbsp;
									<a href="${pageContext.request.contextPath }/car.do?action=clear">清空购物车</a>&nbsp;&nbsp;&nbsp;
									<a href="${pageContext.request.contextPath }/car.do?action=sum">服务台结算</a>
									</TD>
							</TR>
						</TBODY>
					</TABLE>
					<HR SIZE=1>

					<TABLE width="100%" border=0>
						<TBODY>
							<TR>
								<TD class=txt align=right width="22%">下单人<FONT
									color=#666666>真实姓名：</FONT></TD>
								<TD width="78%" height=25><INPUT size=28 value=${sessionScope.loginUser.realname }
									name=txtorderman></TD>
							</TR>
							<TR>
								<TD class=txt align=right width="22%" height=25><FONT
									color=#666666>接收人地址：</FONT></TD>
								<TD width="78%" height=25><INPUT size=58 value=${sessionScope.loginUser.addr }
									name=txtrecadd></TD>
							</TR>
							<TR>
								<TD class=txt align=right width="22%" height=25><FONT
									color=#666666>接收人邮编：</FONT></TD>
								<TD width="78%" height=25><INPUT size=15 value=${sessionScope.loginUser.mailcode }
									name=txtreczip></TD>
							</TR>
							<TR>
								<TD class=txt align=right width="22%" height=25><FONT
									color=#666666>接收人电话：</FONT></TD>
								<TD width="78%" height=25><INPUT size=58 value=${sessionScope.loginUser.tel }
									name=txtrecPhone></TD>
							</TR>
							<TR>
								<TD class=txt align=right width="22%" height=25><FONT
									color=#666666>接收人Email：</FONT></TD>
								<TD width="78%" height=25><INPUT size=58
									value=${sessionScope.loginUser.email } name=txtrecEmail></TD>
							</TR>
							<TR align=middle>
								<TD class=txt colSpan=2 height=25><INPUT type=image
									height=15 width=84 src="images/send.gif" border=0
									name=imageField2></TD>
							</TR>
						</TBODY>
					</TABLE>
				</FORM>
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