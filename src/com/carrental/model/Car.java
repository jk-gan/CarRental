/**
 * 
 */
package com.carrental.model;

import java.io.Serializable;

/**
 * This class represents the data stored in Car table.
 * @author JKGan
 * @version 1.0
 * @since 1.0
 */
public class Car implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int carID;
	private String plateNo;
	private String model;
	private double price;
	private int status;

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
	 * @return the plateNo
	 */
	public String getPlateNo() {
		return plateNo;
	}

	/**
	 * @param plateNo the plateNo to set
	 */
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return getModel() + " ( " + getPlateNo() + " )";
	}
}
