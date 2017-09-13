<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>${initParam.appName}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/css1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pub.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-2.0.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jslib/jquery.validate.min.js"></script>

<script type="text/javascript">
	$(function(){
		$("#registerForm").invalidate({
			rules:{
				username:{reqired:true}
			},
			messages:{
				username:{reqired:"用户名必填"}
			}
		});
	});
</script>

<style>
	.err{color:red;}
</style>
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
						<TD vAlign=top align=middle>
								<FORM id="registerForm" onsubmit="return formchk();"
									action="${pageContext.request.contextPath }/user.do?action=regist" method=post>
									<TABLE cellSpacing=1 cellPadding=5 border=0>
										<TBODY>
											<TR>
												<TD class=txt align=right>用户名:</TD>
												<TD><INPUT size=30 name=username value=""></a></TD>
												<td><span class="err">${requestScope.map.username }</span></td>
											</TR>
											<TR>
												<TD class=txt align=right>用户密码:</TD>
												<TD><INPUT type=password size=30 name=password></TD>
												<td><span class="err">${requestScope.map.password }</span></td>
											</TR>
											<TR>
												<TD class=txt align=right>用户密码确认:</TD>
												<TD><INPUT type=password size=30 name=repassword></TD>
												<td><span class="err">${requestScope.map.repassword }</span></td>
											</TR>
											<TR>
												<TD class=txt align=right>真实姓名:</TD>
												<TD><INPUT size=30 name=realname></TD>
												<td><span class="err">${requestScope.map.realname }</span></td>
											</TR>
											<TR>
												<TD class=txt vAlign=top align=right>性别:</TD>
												<TD><INPUT type=radio value=1 name=sex>男 <INPUT
													type=radio value=2 name=sex>女</TD>
											</TR>
											<TR>
												<TD class=txt vAlign=top align=right>地址:</TD>
												<TD><INPUT size=30 name=addr></TD>
												<td><span class="err">${requestScope.map.addr }</span></td>
											</TR>
											<TR>
												<TD class=txt align=right>邮编:</TD>
												<TD><INPUT size=30 name=mailcode></TD>
											</TR>
											<TR>
												<TD class=txt align=right>电话号码:</TD>
												<TD><INPUT size=30 name=tel></TD>
												<td><span class="err">${requestScope.map.tel }</span></td>
											</TR>
											<TR>
												<TD class=txt align=right>Email:</TD>
												<TD><INPUT size=30 name=email></TD>
											</TR>
											<TR align=middle>
												<TD class=txt colSpan=2><FONT color=#df0029>(注：所有信息为必填项)
														<BR> <INPUT type=submit value="提 交"> <INPUT
														type=reset value="重 写">
												</FONT></TD>
											</TR>
											<c:remove var="map" scope="request"></c:remove>
										</TBODY>
									</TABLE>
								</FORM> <SCRIPT type=text/javascript>
									document.registerForm.usrsex[0].checked = true;
								</SCRIPT>
							</TD>
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
