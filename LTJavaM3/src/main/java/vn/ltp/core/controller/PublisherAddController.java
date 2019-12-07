package vn.ltp.core.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ltp.core.dao.PublisherDao;
import vn.ltp.core.dao.impl.PublisherDaoImpl;
import vn.ltp.core.domain.Publisher;

@WebServlet("/publisher/add.html")
public class PublisherAddController extends HttpServlet{
	PublisherDao dao = new PublisherDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/publisher/add.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!req.getParameter("name").equals("")) {
			try {
				Publisher item = new Publisher();
				item.setName(req.getParameter("name"));
				dao.save(item);
				resp.sendRedirect(req.getContextPath() + "/admin/publisher.html");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else {
			req.setAttribute("msg", "Inserted Failed");
			doGet(req, resp);
		}
	}
}
