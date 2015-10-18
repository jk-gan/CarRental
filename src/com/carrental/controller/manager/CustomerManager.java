/**
 * 
 */
package com.carrental.controller.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Vector;

import com.carrental.model.Customer;

/**
 * This class is used to manage the data in Customer table.
 * @author JKGan
 * @version 1.0
 * @since 1.0
 */
class CustomerManager {
	
	private Facade facade;
	
	CustomerManager(Facade facade) {
		this.facade = facade;
	}	
	
	int addCustomer(Customer customer) throws SQLException {
		// Create a database connection
		Connection connection = facade.getConnection();
		
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Customer (Name, IdentityCardNo, PhoneNo) VALUES (?, ?, ?)", new int[] {1});
		
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
		// Create a database connection
		Connection connection = facade.getConnection();
		
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = connection.prepareStatement("UPDATE Customer SET Name = ?, IdentityCardNo = ?, PhoneNo = ? WHERE CustomerID = ?");
		
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
		
		// Create a database connection
		Connection connection = facade.getConnection();
		
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Customer WHERE UPPER(Name) LIKE ? OR UPPER(IdentityCardNo) LIKE ?");
		
		// Set the values for the SQL statement
		ps.setString(1, "%" + keyword.toUpperCase() + "%");
		ps.setString(2, "%" + keyword.toUpperCase() + "%");
		
		// Send the statement to database engine and retrieve the result
		ResultSet rs = ps.executeQuery();
		
		// Iterate until end of results
		while(rs.next()) {
			
			// Read data from table
			Customer customer = new Customer();
			
			// Set the value of customer properties
			customer.setCustomerID(rs.getInt(1));
			customer.setName(rs.getString(2));
			customer.setIdentityCardNo(rs.getString(3));
			customer.setPhoneNo(rs.getString(4));
			
			// Add customer to the vector
			customers.add(customer);
		}
		
		return customers;
	}
	
	Vector<Customer> getAvailableCustomers(Calendar rentalStart) throws SQLException {
		// Create a vector object to be returned
		Vector<Customer> customers = new Vector<>();
		
		// Create a database connection
		Connection connection = facade.getConnection();
		
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Customer WHERE CustomerID NOT IN (SELECT CustomerID FROM Rental WHERE ? BETWEEN RentalStart AND RentalEnd)");
		
		// Set the values for the SQL statement
		ps.setTimestamp(1, new Timestamp(rentalStart.getTimeInMillis()));
		
		
		// Send the statement to database engine and retrieve the result
		ResultSet rs = ps.executeQuery();
		
		// Iterate until end of results
		while(rs.next()) {
			
			// Read data from table
			Customer customer = new Customer();
			
			// Set the value of car properties
			customer.setCustomerID(rs.getInt(1));
			customer.setName(rs.getString(2));
			customer.setIdentityCardNo(rs.getString(3));
			customer.setPhoneNo(rs.getString(4));
			
			// Add car to the vector
			customers.add(customer);
		}
		
		return customers;
	}
	
	static void main(String[] args) throws ClassNotFoundException, SQLException {
//		Customer customer = new Customer();
//		
//		customer.setName("Gan Jun Xian");
//		customer.setIdentityCardNo("931225034133");
//		customer.setPhoneNo("0123214569");
//		
//		CustomerManager customerManager = new CustomerManager();
//		int status = customerManager.addCustomer(customer);
////		
//		System.out.println("Customer saved with ID " + status);
		
		CustomerManager customerManager = new CustomerManager();
		Vector<Customer> customers = customerManager.searchCustomers("931225");
		
		for(Customer customer : customers) {
			System.out.println(customer);
		}
	}
}











