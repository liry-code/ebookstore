<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
		<div id="header">
			<div id="menu">
				<ul>
					<li><a href="#">关于</a></li>
					<li class="menudiv"></li>
					<li><a href="${pageContext.request.contextPath }/order.do">服务台结帐</a></li>
					<li class="menudiv"></li>
					<li><a href="${pageContext.request.contextPath }/car.do">我的购物车</a></li>
					<li class="menudiv"></li>
					<li><a href="#">商品目录</a></li>
					<li class="menudiv"></li>
					<li><a href="${pageContext.request.contextPath }/book.do">首页</a></li>
				</ul>
			</div>
			<div id="banner"></div>
		</div>
</body>
</html>