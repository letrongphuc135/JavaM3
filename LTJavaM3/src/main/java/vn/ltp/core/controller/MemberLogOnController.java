package vn.ltp.core.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.ltp.core.dao.MemberDao;
import vn.ltp.core.dao.impl.MemberDaoImpl;
import vn.ltp.core.domain.Member;

@WebServlet("/login.html")
public class MemberLogOnController extends HttpServlet {
	MemberDao dao = new MemberDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/auth/logon.jsp").forward(req, resp);
	}

	private static void savedCookie(HttpServletRequest request, HttpServletResponse response) {
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("JSESSIONID")) {
				cookie.setMaxAge(120);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
		}
	}

	private static void savedSession(Member obj, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("member", obj);
		if (request.getParameter("remember") != null) {
			savedCookie(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Member ret = dao.logOn(request.getParameter("usr"), request.getParameter("pwd"));
			if (ret != null) {
				savedSession(ret, request, response);
				response.sendRedirect(request.getContextPath() + "/home.html");
			} else {
				request.setAttribute("msg", "Log On Failed");
				doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
