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
			//��¼����
			try {
				doLogin(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("regist".equals(action)){
			//ע�����
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
			//Ϊ��
			map.put("username", "�˻�����Ϊ��");
		}else{
			//�ж��˻��Ƿ����//�û���Ψһ���ж�
			loginUser = usi.isExist(username);
			//System.out.println(loginUser);
			if(loginUser==null){
				map.put("username", "�˻�������");
			}
		}
		
		if(StringUtil.isBlank(password)){
			//Ϊ��
			map.put("password", "�����Ϊ��");
		}else if((loginUser!=null) && (!loginUser.getPassword().equals(password))){
			//�ж������Ƿ���ȷ
			map.put("password", "�������");
		}
		
		//ͨ��session��Χ��ȡ����
		HttpSession session = request.getSession();
//		String word = (String)session.getAttribute("word");
////		System.out.println(word);
//		if(StringUtil.isBlank(checkcode)){
//			map.put("checkcode", "��֤�벻��Ϊ��");
//		}else if(!(word.equals(checkcode))){
//			map.put("checkcode", "��֤���������������");
//		}
		
		
		//��ȡurl��Դ
		String url = request.getParameter("url");
		//��map��Ϊ��ʱ�򷵻�Ϊfalse
		if(!(map.isEmpty())){
			session.setAttribute("map", map);
			//��ת��ԭҳ�棬��ʾ������Ϣ
			response.sendRedirect(url);
			return;
		}
		
		//���û����벻Ϊ�յ�����ʱ���������ݿ�Ա�
		loginUser.setUsername(username);
		loginUser.setPassword(password);
		try{
			//�ж����ݿ����Ƿ���ڸ��û�
			Boolean bb = usi.doLogin(loginUser);
			if(bb){
//				System.out.println("�û���¼�ɹ�");
				session.setAttribute("loginUser", loginUser);
			}else{
				session.setAttribute("msg", "�����������������");
			}
		}catch(Exception e){
			throw new ServletException(e);
		}
		
		response.sendRedirect(this.getServletContext().getContextPath()+"/book.do");
		
		
		//����û�������ȷ�����ض���loginAfterҳ��,
		//����������Ҫ��ת����Ϊ��jspҳ�������жϣ��ж�loginUser�Ƿ�Ϊ�գ���Ϊ���򲻽��и���ҳ�棬��Ϊ������и���ҳ�棩
//		response.sendRedirect(this.getServletContext().getContextPath()+"/SuccessLogin.jsp");
	}
	
	
	public void doRegiste(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, ServletException {
		//����Map��ע�����Ľ���У��
		Map<String ,String > map = new HashMap<String ,String>();
		String username = request.getParameter("username");
		if(StringUtil.isBlank(username)){
			map.put("username", "�û�������Ϊ��");
		}else{
			//�û���Ψһ���ж�
			User loginUser = usi.isExist(username);
			if(loginUser!=null){
				map.put("username", "�û����Ѿ�����");
			}
		}
		String password = request.getParameter("password");
		if(StringUtil.isBlank(password)){
			map.put("password", "�����Ϊ��");
		}
		
		String repassword = request.getParameter("repassword");
		if(StringUtil.isBlank(repassword)){
			map.put("repassword", "ȷ�Ͽ����Ϊ��");
		}else if(repassword==password){
			map.put("repassword", "��������Ŀ��һ��");
		}
		
		String realname = request.getParameter("realname");
		if(StringUtil.isBlank(realname)){
			map.put("realname", "��ʵ��������Ϊ��");
		}
		
		String ssex = request.getParameter("sex");
		boolean sex = "true".equals(ssex);
		
		String addr = request.getParameter("addr");
		if(StringUtil.isBlank(addr)){
			map.put("addr", "��ַ����Ϊ��");
		}
		
		
		String tel = request.getParameter("tel");
		if(StringUtil.isBlank(tel)){
			map.put("tel", "�绰����Ϊ��");
		}
		String email = request.getParameter("email");
		String mailcode = request.getParameter("mailcode");
		
//		System.out.println("map��"+map);
		
		if(!map.isEmpty()){
			request.setAttribute("map", map);
			//�ض���ע��ҳ��
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return ;
		}
		
		//����user����
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
		//��ת����ҳ
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return;
	}
}
