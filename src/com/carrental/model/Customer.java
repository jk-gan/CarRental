/**
 * 
 */
package com.carrental.model;

import java.io.Serializable;

/**
 * This class represents the data stored in Customer table.
 * @author JKGan
 * @version 1.0
 * @since 1.0
 */
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int CustomerID;
	private String name;
	private String identityCardNo;
	private String phoneNo;
	
	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return CustomerID;
	}
	
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the identityCardNo
	 */
	public String getIdentityCardNo() {
		return identityCardNo;
	}
	
	/**
	 * @param identityCardNo the identityCardNo to set
	 */
	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
	}
	
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Override
	public String toString() {
		return getName() + " ( " + getIdentityCardNo() + " )";
	}
}
