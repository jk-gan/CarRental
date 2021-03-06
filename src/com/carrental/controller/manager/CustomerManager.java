/**
 * 
 */
package com.carrental.controller.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import com.carrental.model.Customer;

/**
 * This class is used to manage the data in Customer table.
 * @author JKGan
 * @version 1.0
 * @since 1.0
 */
class CustomerManager extends AbstractTableManager {
	CustomerManager(Facade facade) {
		super(facade);
	}	
	
	int addCustomer(Customer customer) throws SQLException {
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = facade.getPreparedStatement("INSERT INTO Customer (Name, IdentityCardNo, PhoneNo) VALUES (?, ?, ?)", 1);
		
		// Set the values for the SQL statement
		ps.setString(1, customer.getName());
		ps.setString(2, customer.getIdentityCardNo());
		ps.setString(3, customer.getPhoneNo());
		
		// Send the statement to database engine
		int status = ps.executeUpdate();
		
		// Check successful insertion
		if(status != 0) {
			// Get the generated keys
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				status = rs.getInt(1);
			}
		}
		
		return status;
	}
	
	int updateCustomer(Customer customer) throws SQLException {
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = facade.getPreparedStatement("UPDATE Customer SET Name = ?, IdentityCardNo = ?, PhoneNo = ? WHERE CustomerID = ?");
		
		// Set the values for the SQL statement
		ps.setString(1, customer.getName());
		ps.setString(2, customer.getIdentityCardNo());
		ps.setString(3, customer.getPhoneNo());
		ps.setInt(4, customer.getCustomerID());
		
		// Send the statement to database engine
		int status = ps.executeUpdate();

		return status;
	}
	
	Vector<Customer> searchCustomers(String keyword) throws SQLException {
		// Create a vector object to be returned
		Vector<Customer> customers = new Vector<>();
		
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = facade.getPreparedStatement("SELECT * FROM Customer WHERE UPPER(Name) LIKE ? OR UPPER(IdentityCardNo) LIKE ?");
		
		// Set the values for the SQL statement
		ps.setString(1, "%" + keyword.toUpperCase() + "%");
		ps.setString(2, "%" + keyword.toUpperCase() + "%");
		
		// Send the statement to database engine and retrieve the result
		ResultSet rs = ps.executeQuery();
		
		// Iterate until end of results
		while(rs.next()) {
			// Add customer to the vector
			customers.add(readCustomer(rs));
		}
		
		return customers;
	}
	
	Vector<Customer> getAvailableCustomers(Calendar rentalStart) throws SQLException {
		// Create a vector object to be returned
		Vector<Customer> customers = new Vector<>();
		
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = facade.getPreparedStatement("SELECT * FROM Customer WHERE CustomerID NOT IN (SELECT CustomerID FROM Rental WHERE ? BETWEEN RentalStart AND RentalEnd)");
		
		// Set the values for the SQL statement
		ps.setTimestamp(1, toTimestamp(rentalStart));
		
		
		// Send the statement to database engine and retrieve the result
		ResultSet rs = ps.executeQuery();
		
		// Iterate until end of results
		while(rs.next()) {
			customers.add(readCustomer(rs));
		}
		
		return customers;
	}
	
	private Customer readCustomer(ResultSet rs) throws SQLException {
		// Read data from table
		Customer customer = new Customer();
		
		// Set the value of car properties
		customer.setCustomerID(rs.getInt(1));
		customer.setName(rs.getString(2));
		customer.setIdentityCardNo(rs.getString(3));
		customer.setPhoneNo(rs.getString(4));
		
		return customer;
	}
}











