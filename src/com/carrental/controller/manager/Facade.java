/**
 * 
 */
package com.carrental.controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import com.carrental.model.Car;
import com.carrental.model.Customer;
import com.carrental.model.Rental;

/**
 * This class is used to become the bridge of the data manager
 * and the front-end.
 * @author JKGan
 * @version 1.0
 * @since 1.0
 */
public class Facade implements AutoCloseable {
	private Connection connection;
	private CarManager carManager;
	private CustomerManager customerManager;
	private RentalManager rentalManager;
	
	static {
		try {
			// Load the database driver to the program
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e) {
			// Terminate your program in case of failing to load the drive
			System.err.println("Unable to load driver. The system will exit now.");
			System.exit(0);
		}
	}
	
	public Facade() throws SQLException {
		
		
		// Create a database connection
		connection = DriverManager.getConnection("jdbc:derby://localhost:1527/CarRentalDB", "admin", "admin123");
		
		// Set auto-commit to false
		connection.setAutoCommit(false);
	}
	
	Connection getConnection() {
		return connection;
	}
	
	public void close() throws SQLException {
		connection.commit();
		connection.close();
	}
	
	/**
	 * Get CarManager
	 * @return
	 */
	private CarManager getCarManager() {
		return (carManager == null ? carManager = new CarManager(this) : carManager );
	}
	
	/**
	 * Get CustomerManager
	 * @return
	 */
	private CustomerManager getCustomerManager() {
		return (customerManager == null ? customerManager = new CustomerManager(this) : customerManager );
	}
	
	/**
	 * Get RentalManager
	 * @return
	 */
	private RentalManager getRentalManager() {
		return (rentalManager == null ? rentalManager = new RentalManager(this) : rentalManager );
	}
	
	public int addCar(Car car) throws SQLException {

		return getCarManager().addCar(car);
	}
	
	public int updateCar(Car car) throws SQLException {
		return getCarManager().updateCar(car);
	}
	
	public Vector<Car> searchCars(String keyword) throws SQLException {
		return getCarManager().searchCars(keyword);
	}
	
	public Vector<Car> getAvailableCars(Calendar rentalStart) throws SQLException {
		return getCarManager().getAvailableCars(rentalStart);
	}
	
	public int addCustomer(Customer customer) throws SQLException {
		return getCustomerManager().addCustomer(customer);
	}
	
	public int updateCustomer(Customer customer) throws SQLException {
		return getCustomerManager().updateCustomer(customer);
	}
	
	public Vector<Customer> searchCustomers(String keyword) throws SQLException {
		return getCustomerManager().searchCustomers(keyword);
	}
	
	public Vector<Customer> getAvailableCustomers(Calendar rentalStart) throws SQLException {
		return getCustomerManager().getAvailableCustomers(rentalStart);
	}
	
	public int addRental(Rental rental) throws SQLException {
		return getRentalManager().addRental(rental);
	}
	
}
