<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jslib/jquery-2.0.2.min.js"></script>
<script>
	$(function() {
		$("#image").attr(
				'src',
				'${pageContext.request.contextPath}/picture.do?action='
						+ Math.random());
	});
</script>

<body>

	<div id="sidebar">
		<TABLE cellSpacing=0 cellPadding=0 width=152 border=0>
			<TBODY>
				<TR>
					<TD>
						<TABLE cellSpacing=0 cellPadding=0 width=152 border=0>
							<TBODY>
								<TR>
									<TD colSpan=3><IMG
										src="${pageContext.request.contextPath}/images/user_info_top.gif"
										border=0></TD>
								</TR>
								<TR>
									<TD bgColor=#cccccc><IMG height=1 alt=""
										src="${pageContext.request.contextPath}/images/dot.gif"
										width=1 border=0></TD>
									<TD class=ko align="center" width=150><SCRIPT
											language=JavaScript>
									<!--
										function winGetPWD(url) {
											window
													.open(
															url,
															"getpwd",
															"top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no,width=240,height=150");
										}
									//-->
									</SCRIPT>

										<table>
											<tr>
												<td align=center><font color="#000000">
														先生/女士，shopuser<br>欢迎访问电子商城 ！
												</font></td>
											</tr>
											<tr>
												<td align=center class="ko">
													<!--<a href="userinfo.do?act=selectoneuser&num=1&uname=shopuser" style="text-decoration:underline; color:#0000FF">&gt;&gt;查看/修改个人信息</a>-->
												</td>

											</tr>
											<tr>
												<td align=center class="ko"><a
													href="javascript:logout('userinfo.do?act=logout')"
													style="text-decoration: underline; color: #0000FF">&gt;&gt;注销</a></td>
											</tr>
										</table></TD>
									<TD bgColor=#cccccc><IMG height=1
										src="${pageContext.request.contextPath}/images/dot.gif"
										width=1 border=0></TD>
								</TR>
								<TR>
									<TD colSpan=3><IMG
										src="${pageContext.request.contextPath}/images/user_info_bottom.gif"
										border=0></TD>
								</TR>
							</TBODY>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=0 width=152 border=0>
							<TBODY>
								<TR>
									<TD height=5><IMG height=5
										src="${pageContext.request.contextPath}/images/dot.gif"
										width=1 border=0></TD>
								</TR>
							</TBODY>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=0 width=152 border=0>
							<TBODY>
								<TR>
									<TD colSpan=3><IMG
										src="${pageContext.request.contextPath}/images/left_search_top.gif"
										border=0></TD>
								</TR>
								<TR>
									<TD bgColor=#cccccc><IMG height=1
										src="${pageContext.request.contextPath}/images/dot.gif"
										width=1 border=0></TD>
									<TD class=ko align=center width=150>
										<FORM class=v9
											action="${pageContext.request.contextPath }/book.do"
											method=post>
											<TABLE cellSpacing=0 cellPadding=0 border=0>
												<TBODY>
													<TR>
														<TD colSpan=2><INPUT style="WIDTH: 130px"
															name="keyword" value="${requestScope.keyword}"></TD>
													</TR>
													<TR>
														<TD><SELECT class=v9 style="WIDTH: 110px"
															name=bookclassid value="${bookclass.className }">
																<OPTION value="">所有图书</OPTION>
																<c:if test="${not empty bookclasslist}">
																	<c:forEach var="bc" items="${bookclasslist}">
																		<c:if test="${not empty bc }">
																			<OPTION value="${bc.id }">${bc.className }</OPTION>
																		</c:if>
																	</c:forEach>
																</c:if>
														</SELECT></TD>
														<TD><INPUT type=image
															src="${pageContext.request.contextPath}/images/search_go.gif"
															border=0 name=imageField></TD>
													</TR>
												</TBODY>
											</TABLE>
										</FORM>
									</TD>
									<TD bgColor=#cccccc><IMG height=1
										src="${pageContext.request.contextPath}/images/dot.gif"
										width=1 border=0></TD>
								</TR>
								<TR>
									<TD colSpan=3><IMG
										src="${pageContext.request.contextPath}/images/user_info_bottom.gif"
										border=0></TD>
								</TR>
							</TBODY>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=0 width=152 border=0>
							<TBODY>
								<TR>
									<TD height=5><IMG height=5
										src="${pageContext.request.contextPath}/images/dot.gif"
										width=1 border=0></TD>
								</TR>
							</TBODY>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=0 width=152 border=0>
							<TBODY>
								<TR>
									<TD colSpan=3><IMG
										src="${pageContext.request.contextPath}/images/left_product_top.gif"
										border=0></TD>
								</TR>
								<TR>
									<TD bgColor=#cccccc><IMG height=1
										src="${pageContext.request.contextPath}/images/dot.gif"
										width=1 border=0></TD>
									<TD class=ko align=center width=150>
										<TABLE width=140>
											<TBODY>
												<TR>
													<TD class=txt><A
														href="${pageContext.request.contextPath}/book.do"><IMG
															alt=""
															src="${pageContext.request.contextPath}/images/add.gif"
															align=left vspace=4 border=0>所有图书</A><BR> <c:if
															test="${not empty bookclasslist}">
															<c:forEach var="bc" items="${bookclasslist}">
																<A
																	href="${pageContext.request.contextPath }/book.do?bookclassid=${bc.id}"><IMG
																	alt=""
																	src="${pageContext.request.contextPath}/images/add.gif"
																	align=left vspace=4 border=0>${bc.className }</A>
																<BR>
															</c:forEach>
														</c:if>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
									<TD bgColor=#cccccc><IMG height=1
										src="${pageContext.request.contextPath}/images/dot.gif"
										width=1 border=0></TD>
								</TR>
								<TR>
									<TD colSpan=3><IMG
										src="${pageContext.request.contextPath}/images/user_info_bottom.gif"
										border=0></TD>
								</TR>
							</TBODY>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=0 width=152 border=0>
							<TBODY>
								<TR>
									<TD height=5><IMG height=5
										src="${pageContext.request.contextPath}/images/dot.gif"
										width=1 border=0></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		&nbsp;&nbsp; 当前在线人数：${counter}<br />
		<c:if test="${not empty cookie.lastTime.value }">
			上次登录时间：<br /> 
			${cookie.lastTime.value}
			</c:if>
	</div>
</body>
</html>