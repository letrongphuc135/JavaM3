package vn.ltp.core.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ltp.core.dao.MemberDao;
import vn.ltp.core.dao.impl.MemberDaoImpl;
import vn.ltp.core.domain.Member;
@WebServlet("/register.html")
public class MemberController extends HttpServlet{
	private MemberDao memberDAO = new MemberDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/views/auth/register.jsp").forward(req, resp);
		super.doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String email = request.getParameter("email");
		String username = request.getParameter("usr");
		String password = request.getParameter("pwd");
		byte gender = Byte.parseByte(request.getParameter("gender") );
		
		Member member = new Member();
		member.setId(memberDAO.random());
		member.setUsername(username);
		member.setPassword(password);
		member.setEmail(email);
		member.setGender(gender);
		member.setModifiedDate(new Date(System.currentTimeMillis()));
		member.setAddedDate(new Date(System.currentTimeMillis()));
		
		if (memberDAO.save(member) > 0) {
			resp.sendRedirect(request.getContextPath() + "/login.html");
		} else {
			request.setAttribute("msg", "Register Failed");
			doGet(request, resp);
		}
	}
}
