package vn.ltp.core.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ltp.core.dao.ProductDao;
import vn.ltp.core.dao.impl.ProductDaoImpl;

@WebServlet("/home/detail.html")
public class HomeDetailController extends HttpServlet{

	private ProductDao dao = new ProductDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("o", dao.findById(id));
			req.getRequestDispatcher("/views/home/detail.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
