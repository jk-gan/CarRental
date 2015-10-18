/**
 * 
 */
package com.carrental.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This class represents the data stored in Rental table.
 * @author JKGan
 * @version 1.0
 * @since 1.0
 */
public class Rental implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int rentalID;
	private int carID;
	private int customerID;
	private Calendar rentalStart;
	private Calendar rentalEnd;
	private double amount;
	
	/**
	 * @return the rentalID
	 */
	public int getRentalID() {
		return rentalID;
	}
	
	/**
	 * @param rentalID the rentalID to set
	 */
	public void setRentalID(int rentalID) {
		this.rentalID = rentalID;
	}
	
	/**
	 * @return the carID
	 */
	public int getCarID() {
		return carID;
	}
	
	/**
	 * @param carID the carID to set
	 */
	public void setCarID(int carID) {
		this.carID = carID;
	}
	
	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}
	
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	/**
	 * @return the rentalStart
	 */
	public Calendar getRentalStart() {
		return rentalStart;
	}
	
	/**
	 * @param rentalStart the rentalStart to set
	 */
	public void setRentalStart(Calendar rentalStart) {
		this.rentalStart = rentalStart;
	}
	
	/**
	 * @return the rentalEnd
	 */
	public Calendar getRentalEnd() {
		return rentalEnd;
	}
	
	/**
	 * @param rentalEnd the rentalEnd to set
	 */
	public void setRentalEnd(Calendar rentalEnd) {
		this.rentalEnd = rentalEnd;
	}
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
