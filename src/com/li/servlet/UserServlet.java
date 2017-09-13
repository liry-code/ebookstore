package com.li.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.li.entity.User;
import com.li.serviceImp.UserServiceImp;
import com.li.util.StringUtil;

public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1132862486769115770L;
	
	private UserServiceImp usi = new UserServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String action = request.getParameter("action");
		if("login".equals(action)){
			//登录操作
			try {
				doLogin(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("regist".equals(action)){
			//注册操作
			try {
				doRegiste(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("exit".equals(action)){
			doExit(request,response);
		}
	}
	

	public void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkcode");
		Map<String,String> map = new HashMap<String,String>();
		User loginUser = null;
		if(StringUtil.isBlank(username)){
			//为空
			map.put("username", "账户不能为空");
		}else{
			//判断账户是否存在//用户名唯一性判断
			loginUser = usi.isExist(username);
			//System.out.println(loginUser);
			if(loginUser==null){
				map.put("username", "账户不存在");
			}
		}
		
		if(StringUtil.isBlank(password)){
			//为空
			map.put("password", "口令不能为空");
		}else if((loginUser!=null) && (!loginUser.getPassword().equals(password))){
			//判断密码是否正确
			map.put("password", "口令错误");
		}
		
		//通过session范围获取参数
		HttpSession session = request.getSession();
//		String word = (String)session.getAttribute("word");
////		System.out.println(word);
//		if(StringUtil.isBlank(checkcode)){
//			map.put("checkcode", "验证码不能为空");
//		}else if(!(word.equals(checkcode))){
//			map.put("checkcode", "验证码错误，请重新输入");
//		}
		
		
		//获取url资源
		String url = request.getParameter("url");
		//当map中为空时则返回为false
		if(!(map.isEmpty())){
			session.setAttribute("map", map);
			//跳转到原页面，显示错误信息
			response.sendRedirect(url);
			return;
		}
		
		//当用户输入不为空的数据时，进行数据库对比
		loginUser.setUsername(username);
		loginUser.setPassword(password);
		try{
			//判断数据库中是否存在该用户
			Boolean bb = usi.doLogin(loginUser);
			if(bb){
//				System.out.println("用户登录成功");
				session.setAttribute("loginUser", loginUser);
			}else{
				session.setAttribute("msg", "密码错误，请重新输入");
			}
		}catch(Exception e){
			throw new ServletException(e);
		}
		
		response.sendRedirect(this.getServletContext().getContextPath()+"/book.do");
		
		
		//如果用户输入正确，则重定向到loginAfter页面,
		//在这里则不需要跳转，因为在jsp页面做了判断（判断loginUser是否为空，若为空则不进行更换页面，不为空则进行更换页面）
//		response.sendRedirect(this.getServletContext().getContextPath()+"/SuccessLogin.jsp");
	}
	
	
	public void doRegiste(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, ServletException {
		//创建Map对注册信心进行校验
		Map<String ,String > map = new HashMap<String ,String>();
		String username = request.getParameter("username");
		if(StringUtil.isBlank(username)){
			map.put("username", "用户名不能为空");
		}else{
			//用户名唯一性判断
			User loginUser = usi.isExist(username);
			if(loginUser!=null){
				map.put("username", "用户名已经存在");
			}
		}
		String password = request.getParameter("password");
		if(StringUtil.isBlank(password)){
			map.put("password", "口令不能为空");
		}
		
		String repassword = request.getParameter("repassword");
		if(StringUtil.isBlank(repassword)){
			map.put("repassword", "确认口令不能为空");
		}else if(repassword==password){
			map.put("repassword", "两次输入的口令不一致");
		}
		
		String realname = request.getParameter("realname");
		if(StringUtil.isBlank(realname)){
			map.put("realname", "真实姓名不能为空");
		}
		
		String ssex = request.getParameter("sex");
		boolean sex = "true".equals(ssex);
		
		String addr = request.getParameter("addr");
		if(StringUtil.isBlank(addr)){
			map.put("addr", "地址不能为空");
		}
		
		
		String tel = request.getParameter("tel");
		if(StringUtil.isBlank(tel)){
			map.put("tel", "电话不能为空");
		}
		String email = request.getParameter("email");
		String mailcode = request.getParameter("mailcode");
		
//		System.out.println("map："+map);
		
		if(!map.isEmpty()){
			request.setAttribute("map", map);
			//重定向到注册页面
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return ;
		}
		
		//创建user对象
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRepassword(repassword);
		user.setRealname(realname);
		user.setSex(sex);
		user.setAddr(addr);
		user.setMailcode(mailcode);
		user.setTel(tel);
		user.setEmail(email);
		
		usi.add(user);
		response.sendRedirect(this.getServletContext().getContextPath()+"/registInfo.jsp");
	}

	public void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		//跳转到首页
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return;
	}
}
