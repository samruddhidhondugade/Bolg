package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.connection.FactoryProvider;
import com.model.Blog;


@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String desc = request.getParameter("description");
		
		Blog b1 = new Blog();
		
		Session session = FactoryProvider.getFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		b1.setTitle(title);
		b1.setAuthor(author);
		b1.setDescription(desc);
		
		session.save(b1);
		tx.commit();
		session.close();
		
		
		//out.println("<h1 style='text-align:center;'>Blog Added Successfully</h1>");
		response.sendRedirect("showblog.jsp");
	}

}
