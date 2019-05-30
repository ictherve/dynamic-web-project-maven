package com.ictcg.dataaccess;

import java.util.Collection;

import com.ictcg.model.Customer;

public interface Repository {

	Collection<Customer> findAll();
	
	Customer findById(Long id);
	
	boolean save(Customer customer);
	
	boolean update(Customer customer);
	
	void delete(Long id);
}
