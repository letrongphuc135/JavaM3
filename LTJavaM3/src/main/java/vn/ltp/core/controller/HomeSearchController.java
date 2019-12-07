package vn.ltp.core.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ltp.core.dao.ProductDao;
import vn.ltp.core.dao.impl.ProductDaoImpl;

@WebServlet("/home/search.html")
public class HomeSearchController extends HttpServlet {
	ProductDao dao = new ProductDaoImpl();
	private int size = 8;
	
	private int getPage(int total) {
		return (int) Math.ceil(total / (float) size);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int p = 1;
		if (request.getParameter("p") != null) {
			p = Integer.parseInt(request.getParameter("p"));
		}
		
		try {
			request.setAttribute("n", getPage(dao.count()));
			request.setAttribute("list", dao.search("title",(request.getParameter("q")), p, size));
			request.getRequestDispatcher("/views/home/search.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
