package vn.ltp.core.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ltp.core.dao.PublisherDao;
import vn.ltp.core.dao.impl.PublisherDaoImpl;
import vn.ltp.core.domain.Publisher;

@WebServlet("/publisher/del.html")
public class PublisherDelController extends HttpServlet{
	PublisherDao dao = new PublisherDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
//			int id = Integer.parseInt(req.getParameter("id"));
//			req.setAttribute("o", dao.findById(id));
//			req.getRequestDispatcher("/views/publisher/edit.jsp").forward(req, resp);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		if(!req.getParameter("id").equals("")) {
			try {
				List<Integer> list = new LinkedList<Integer>();
				for(String id : req.getParameterValues("id")) {//tra ve mang
					list.add(Integer.parseInt(id));
					System.out.println(id);
				}
				dao.delete(list);
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
