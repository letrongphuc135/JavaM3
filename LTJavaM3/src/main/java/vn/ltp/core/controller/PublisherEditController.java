package vn.ltp.core.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ltp.core.dao.PublisherDao;
import vn.ltp.core.dao.impl.PublisherDaoImpl;
import vn.ltp.core.domain.Publisher;

@WebServlet("/publisher/edit.html")
public class PublisherEditController extends HttpServlet{
	PublisherDao dao = new PublisherDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("o", dao.findById(id));
			req.getRequestDispatcher("/views/publisher/edit.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!req.getParameter("name").equals("")) {
			try {
				int id = Integer.parseInt(req.getParameter("id"));
				Publisher item = dao.findById(id);
				item.setName(req.getParameter("name"));
				dao.update(item);
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
