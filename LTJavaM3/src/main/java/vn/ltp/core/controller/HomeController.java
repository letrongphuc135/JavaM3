package vn.ltp.core.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ltp.core.dao.ProductDao;
import vn.ltp.core.dao.impl.ProductDaoImpl;

@WebServlet("/home.html")
public class HomeController extends HttpServlet {
	private int size = 8;
	private ProductDao dao = new ProductDaoImpl();

	private int getPage(int total) {
		return (int) Math.ceil(total / (float) size);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int p = 1;
		if (req.getParameter("p") != null) {
			p = Integer.parseInt(req.getParameter("p"));
		}
		try {
			req.setAttribute("n", getPage(dao.count()));
			req.setAttribute("list", dao.getProduct(p, size));
			req.getRequestDispatcher("/views/home/index.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
