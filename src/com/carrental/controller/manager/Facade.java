/**
 * 
 */
package com.carrental.controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to become the bridge of the data manager
 * and the front-end.
 * @author JKGan
 * @version 1.0
 * @since 1.0
 */
public class Facade implements AutoCloseable {
	private Connection connection;
	
	public Facade() throws ClassNotFoundException, SQLException {
		// Load the database driver to the program
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		
		// Create a database connection
		connection = DriverManager.getConnection("jdbc:derby://localhost:1527/CarRentalDB", "admin", "admin123");
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void close() throws SQLException {
		connection.close();
	}
}
