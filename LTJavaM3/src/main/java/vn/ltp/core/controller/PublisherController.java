package vn.ltp.core.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ltp.core.dao.PublisherDao;
import vn.ltp.core.dao.impl.PublisherDaoImpl;

@WebServlet("/admin/publisher.html")
public class PublisherController extends HttpServlet {
	PublisherDao dao = new PublisherDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		req.setAttribute("list", dao.findAll());
		RequestDispatcher rd = req.getRequestDispatcher("/views/publisher/index.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Integer> list = new LinkedList<Integer>();
		for(String id : req.getParameterValues("ids")) {//tra ve mang
			list.add(Integer.parseInt(id));
			System.out.println(id);
		}
		try {
			dao.delete(list);
			doGet(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
