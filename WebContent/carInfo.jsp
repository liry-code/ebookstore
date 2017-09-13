<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<script type="text/javascript">
	function closeinfo() {
		window.close();
	}
	
	function showCar(){
		window.opener.carShow();
		window.close();
	}
	
</script>



<title>电子商城</title>
<style>
td {
	font-size: 12px;
	color: #000000;
}
</style>
</head>
<body topmargin=0 leftmargin=0 marginwidth=0 marginheight=0
	bgcolor="#FFFFFF">
	<table cellpadding=10>
		<tr>
			<td><table cellspacing=0 cellpadding=0 border=0>
					<tr>
						<td colspan=3><img src="${pageContext.request.contextPath}/images/add_car.gif" border=0></td>
					</tr>
					<tr>
						<td width=1 bgcolor="#0033CC"><img src="${pageContext.request.contextPath}/images/dot.gif"
							border=0></td>
						<td width=158 align=center>
							<table cellpadding=3>
								<tr>
									<td>您订购的物品已经放入到购物车中，您可以点击
										<a href="javascript:void(0);" onclick="showCar()">购物车</a>查看购物状态
									</td>
								</tr>
								<tr>
									<td align=center><a href="javascript:window.close();">
									<img src="${pageContext.request.contextPath}/images/continue_buy.gif" border=0></a></td>
								</tr>
							</table>
						</td>
						<td width=1 bgcolor="#0033CC"><img src="${pageContext.request.contextPath}/images/dot.gif"
							border=0></td>
					</tr>
					<tr>
						<td colspan=3 bgcolor="#0033CC"><img src="${pageContext.request.contextPath}/images/dot.gif"
							border=0></td>
					</tr>
				</table></td>
		</tr>
	</table>

	<SCRIPT type="text/javascript">
	<!--
		setTimeout("closeinfo()", 6000);
	//-->
	</SCRIPT>
</body>
</html>