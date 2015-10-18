/**
 * 
 */
package com.carrental.controller.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.carrental.model.Rental;

/**
 * This class is used to manage the data in Rental table.
 * @author JKGan
 * @version 1.0
 * @since 1.0
 */
class RentalManager extends AbstractTableManager {
	
	private Facade facade;
	
	RentalManager(Facade facade) {
		super(facade);
	}	
	
	int addRental(Rental rental) throws SQLException {
		// Status of execution
		int status = 0;
		
		// Create an SQL statement for checking availability
		PreparedStatement psCheck = facade.getPreparedStatement("SELECT * FROM Rental WHERE (CarID = ? AND CustomerID = ?) AND ? BETWEEN RentalStart AND RentalEnd");
		
		// Set the requirements
		psCheck.setInt(1, rental.getCarID());
		psCheck.setInt(2, rental.getCustomerID());
		psCheck.setTimestamp(3, toTimestamp(rental.getRentalEnd()));
		
		ResultSet rsCheck = psCheck.executeQuery();
		
		if(!rsCheck.next()) {
			// Create an SQL statement to be sent to the database
			PreparedStatement ps = facade.getPreparedStatement("INSERT INTO Rental (CarID, CustomerID, RentalStart, Rentalend, Amount) VALUES (?, ?, ?, ?, ?)", new int[] {1});
			
			// Set the values for the SQL statement
			ps.setInt(1, rental.getCarID());
			ps.setInt(2, rental.getCustomerID());
			ps.setTimestamp(3, toTimestamp(rental.getRentalStart()));
			ps.setTimestamp(4, toTimestamp(rental.getRentalEnd()));
			ps.setDouble(5, rental.getAmount());
			
			// Send the statement to database engine
			status = ps.executeUpdate();
			
			// Check successful insertion
			if(status != 0) {
				// Get the generated keys
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					status = rs.getInt(1);
				}
			}
		}
					
		return status;
	}
	
//	static void main(String[] args) throws ClassNotFoundException, SQLException {
//		Rental rental = new Rental();
//		Calendar rentalStart = Calendar.getInstance();
//		Calendar rentalEnd = Calendar.getInstance();
//		
//		rentalStart.set(2015, 9, 17, 16, 0, 0);
//		rentalEnd.set(2015, 9, 16, 16, 0, 0);
//		
//		rental.setCarID(1);
//		rental.setCustomerID(2);
//		rental.setRentalStart(rentalStart);
//		rental.setRentalEnd(rentalEnd);
//		rental.setAmount(500);
//		
//		RentalManager rentalManager = new RentalManager();
//		int status = rentalManager.addRental(rental);
//		
//		System.out.println("Rental saved with ID " + status);
//		
//	}
}











