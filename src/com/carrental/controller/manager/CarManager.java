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

import com.carrental.model.Car;

/**
 * This class is used to manage the data in Car table.
 * @author JKGan
 * @version 1.0
 * @since 1.0
 */
public class CarManager {
	
	private Facade facade;
	
	public CarManager(Facade facade) {
		this.facade = facade;
	}	
	
	
	public int addCar(Car car) throws SQLException {
		// Create a database connection
		Connection connection = facade.getConnection();
		
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Car (PlateNo, Model, Price, Status) VALUES (?, ?, ?, ?)", new int[] {1});
		
		// Set the values for the SQL statement
		ps.setString(1, car.getPlateNo());
		ps.setString(2, car.getModel());
		ps.setDouble(3, car.getPrice());
		ps.setInt(4, car.getStatus());
		
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
	
	public int updateCar(Car car) throws SQLException {
		// Create a database connection
		Connection connection = facade.getConnection();
		
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = connection.prepareStatement("UPDATE Car SET PlateNo = ?, Model = ?, Price = ? , Status = ? WHERE CarID = ?");
		
		// Set the values for the SQL statement
		ps.setString(1, car.getPlateNo());
		ps.setString(2, car.getModel());
		ps.setDouble(3, car.getPrice());
		ps.setInt(4, car.getStatus());
		ps.setInt(5, car.getCarID());
		
		// Send the statement to database engine
		int status = ps.executeUpdate();
		
		return status;
	}
	
	public Vector<Car> searchCars(String keyword) throws SQLException {
		// Create a vector object to be returned
		Vector<Car> cars = new Vector<>();
		
		// Create a database connection
		Connection connection = facade.getConnection();
		
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Car WHERE UPPER(PlateNo) LIKE ? OR UPPER(Model) LIKE ?");
		
		// Set the values for the SQL statement
		ps.setString(1, "%" + keyword.toUpperCase() + "%");
		ps.setString(2, "%" + keyword.toUpperCase() + "%");
		
		// Send the statement to database engine and retrieve the result
		ResultSet rs = ps.executeQuery();
		
		// Iterate until end of results
		while(rs.next()) {
			
			// Read data from table
			Car car = new Car();
			
			// Set the value of car properties
			car.setCarID(rs.getInt(1));
			car.setPlateNo(rs.getString(2));
			car.setModel(rs.getString(3));
			car.setPrice(rs.getDouble(4));
			car.setStatus(rs.getInt(5));
			
			// Add car to the vector
			cars.add(car);
		}
		
		return cars;
	}
	
	public Vector<Car> getAvailableCars(Calendar rentalStart) throws SQLException {
		// Create a vector object to be returned
		Vector<Car> cars = new Vector<>();
		
		// Create a database connection
		Connection connection = facade.getConnection();
		
		// Create an SQL statement to be sent to the database
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Car WHERE CarID NOT IN (SELECT CarID FROM Rental WHERE ? BETWEEN RentalStart AND RentalEnd) AND Status = 0");
		
		// Set the values for the SQL statement
		ps.setTimestamp(1, new Timestamp(rentalStart.getTimeInMillis()));
		
		
		// Send the statement to database engine and retrieve the result
		ResultSet rs = ps.executeQuery();
		
		// Iterate until end of results
		while(rs.next()) {
			
			// Read data from table
			Car car = new Car();
			
			// Set the value of car properties
			car.setCarID(rs.getInt(1));
			car.setPlateNo(rs.getString(2));
			car.setModel(rs.getString(3));
			car.setPrice(rs.getDouble(4));
			car.setStatus(rs.getInt(5));
			
			// Add car to the vector
			cars.add(car);
		}
		
		return cars;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		Car car = new Car();
//		
//		car.setPlateNo("XYZ 1020");
//		car.setModel("Proton Kancil");
//		car.setPrice(200);
//		car.setStatus(0);
//		
//		CarManager carManager = new CarManager();
//		int status = carManager.addCar(car);
//		
//		System.out.println("Car saved with ID " + status);
		
		CarManager carManager = new CarManager();
		Vector<Car> cars = carManager.searchCars("xyz");
		
		for(Car car : cars) {
			System.out.println(car);
		}	
	}
}











