package com.ictcg.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ictcg.dataaccess.CustomerRepository;
import com.ictcg.model.Customer;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CustomerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.findAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
	}
	
private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Collection<Customer> customers = CustomerRepository.getInstance().findAll();
		System.out.println("All cuqtomers : " + customers);
		request.setAttribute("customers", customers);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
