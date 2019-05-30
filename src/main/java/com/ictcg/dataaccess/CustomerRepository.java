package com.ictcg.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import com.ictcg.model.Customer;

public class CustomerRepository implements Repository{
	
	private static CustomerRepository customerRepository;
	private CustomerRepository() {}
	
	public static CustomerRepository getInstance() {
		if(Objects.isNull(customerRepository))
			customerRepository = new CustomerRepository();
		return customerRepository;
	}
	
	@Override
	public Collection<Customer> findAll() {
		
		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Collection<Customer> Customers = null;

		String sql = "SELECT * FROM customer";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			Customers = new ArrayList<>();
			while(resultSet.next()) {
				Customer Customer = new Customer();
				Customer.setId(resultSet.getLong("id"));
				Customer.setFirstName(resultSet.getString("firstName"));
				Customer.setLastName(resultSet.getString("lastName"));
				Customers.add(Customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeAll(resultSet, preparedStatement, connection);
		}
		
		return Customers;
	}

	@Override
	public boolean save(Customer Customer) {
		
		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int row = 0;
		
		String sql = "INSERT INTO customer (firstName, lastName) VALUES (?,?)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, Customer.getFirstName());
			preparedStatement.setString(2, Customer.getLastName());
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeAll(resultSet, preparedStatement, connection);
		}
		
		return row == 1;
	}

	@Override
	public boolean update(Customer Customer) {
		
		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int row = 0;
		String sql = "UPDATE customer SET firstName=?, lastName=? WHERE id=?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, Customer.getFirstName());
			preparedStatement.setString(2, Customer.getLastName());
			preparedStatement.setLong(3, Customer.getId());
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeAll(resultSet, preparedStatement, connection);
		}
		
		return row == 1;
	}

	@Override
	public void delete(Long id) {
		
		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "DELETE FROM customer WHERE id=?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeAll(resultSet, preparedStatement, connection);
		}
		
	}

	@Override
	public Customer findById(Long id) {

		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer Customer = null;

		String sql = "SELECT * FROM customer WHERE id=?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Customer = new Customer();
				Customer.setId(resultSet.getLong("id"));
				Customer.setFirstName(resultSet.getString("firstName"));
				Customer.setLastName(resultSet.getString("lastName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeAll(resultSet, preparedStatement, connection);
		}
		return Customer;
	}
}
