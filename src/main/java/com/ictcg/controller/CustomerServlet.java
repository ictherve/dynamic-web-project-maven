package com.ictcg.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (Objects.isNull(action) || action.equals("LIST"))
			this.findAll(request, response);
		else if(action.equals("ADD_FORM"))
			this.getServletContext().getRequestDispatcher("/add_customer.jsp").forward(request, response);
		else if(action.equals("UPDATE_FORM")) {
			Long id =  Long.parseLong((request.getParameter("customerId")));
			Customer customer = CustomerRepository.getInstance().findById(id);
			request.setAttribute("customer", customer);
			this.getServletContext().getRequestDispatcher("/update_customer.jsp").forward(request, response);
		}else if (action.equals("DELETE")) {
			delete(request, response);
			findAll(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		switch (action) {
		case "ADD":
			save(request, response);
			break;
			case "UPDATE": 
				update(request, response);
		}
		findAll(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		Long id =  Long.parseLong((request.getParameter("customerId")));
		CustomerRepository.getInstance().delete(id);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		
		Long id =  Long.valueOf(request.getParameter("id"));
		String fisrtName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Customer customer = new Customer(id, fisrtName, lastName);
		CustomerRepository.getInstance().update(customer);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		
		Customer customer = new Customer(null, request.getParameter("firstName"), 
				request.getParameter("lastName"));
		CustomerRepository.getInstance().save(customer);
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Collection<Customer> customers = CustomerRepository.getInstance().findAll();
		System.out.println("All cuqtomers : " + customers);
		request.setAttribute("customers", customers);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
