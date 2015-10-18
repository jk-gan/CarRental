/**
 * 
 */
package com.carrental.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Vector;

import com.carrental.controller.manager.Facade;
import com.carrental.model.Car;
import com.carrental.model.Customer;

/**
 * This class is the entry point of the system.
 * @author JKGan
 * @version 1.0
 * @version 1.0
 *
 */
public class Console {

	private BufferedReader reader;
	
	public Console() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void close() throws IOException {
		reader.close();
	}
	
	public void showHeader() {
		System.out.println("Welcoem to Car Rental Management System");
		System.out.println("---------------------------------------");
	}
	
	public int showMainMenu() throws IOException {
		showHeader();
		System.out.println("Main Menu: ");
		System.out.println("1. Car Management");
		System.out.println("2. Customer Management");
		System.out.println("3. Rental Management");
		System.out.println("4. Exit");
		System.out.println();
		System.out.print("Please select an option: ");
		
		return Integer.parseInt(reader.readLine());
	}
	
	public int showCarManagementMenu() throws IOException {
		showHeader();
		System.out.println("Main Menu: ");
		System.out.println("1. Add Car");
		System.out.println("2. Edit Car");
		System.out.println("3. Search Car");
		System.out.println("4. Back to main menu");
		System.out.println();
		System.out.print("Please select an option: ");

		return Integer.parseInt(reader.readLine());
	}
	
	public Car showAddCarMenu() throws IOException {
		Car car = new Car();
		
		showHeader();
		System.out.println("Add Car Menu");
		System.out.print("Plate number: ");
		car.setPlateNo(reader.readLine());
		System.out.print("Model: ");
		car.setModel(reader.readLine());
		System.out.print("Price: ");
		car.setPrice(Double.parseDouble(reader.readLine()));
		System.out.print("Status: ");
		car.setStatus(Integer.parseInt(reader.readLine()));
		
		return car;
	}
	
	public Car showEditCarMenu() throws IOException {
		Car car = new Car();
		
		showHeader();
		System.out.println("Edit Car Menu");
		System.out.print("Car Id: ");
		car.setCarID(Integer.parseInt(reader.readLine()));
		System.out.print("Plate number: ");
		car.setPlateNo(reader.readLine());
		System.out.print("Model: ");
		car.setModel(reader.readLine());
		System.out.print("Price: ");
		car.setPrice(Double.parseDouble(reader.readLine()));
		System.out.print("Status: ");
		car.setStatus(Integer.parseInt(reader.readLine()));
		
		return car;
	}
	
	public String showSearchCarsMenu() throws IOException {
		showHeader();
		System.out.println("Search Car Menu");
		System.out.print("Model / Plate no: ");
		
		return reader.readLine();
		
	}
	
	public int showCustomerManagementMenu() throws IOException {
		showHeader();
		System.out.println("Main Menu: ");
		System.out.println("1. Add Customer");
		System.out.println("2. Edit Customer");
		System.out.println("3. Search Customer");
		System.out.println("4. Back to main menu");
		System.out.println();
		System.out.print("Please select an option: ");

		return Integer.parseInt(reader.readLine());
	}
	
	public Customer showAddCustomerMenu() throws IOException {
		Customer customer = new Customer();
		
		showHeader();
		System.out.println("Add Customer Menu");
		System.out.print("Name: ");
		customer.setName(reader.readLine());
		System.out.print("Identity Card No: ");
		customer.setIdentityCardNo(reader.readLine());
		System.out.print("Phone No: ");
		customer.setPhoneNo(reader.readLine());
		
		return customer;
	}
	
	public String showSearchCustomerMenu() throws IOException {
		showHeader();
		System.out.println("Search Customer Menu");
		System.out.print("Name / Identity card no: ");
		
		return reader.readLine();
		
	}
	
	public Customer showEditCustomerMenu() throws IOException {
		Customer customer = new Customer();
		
		showHeader();
		System.out.println("Edit Customer Menu");
		System.out.print("Customer ID: ");
		customer.setCustomerID(Integer.parseInt(reader.readLine()));
		System.out.print("Name: ");
		customer.setName(reader.readLine());
		System.out.print("Identity Card No: ");
		customer.setIdentityCardNo(reader.readLine());
		System.out.print("Phone No: ");
		customer.setPhoneNo(reader.readLine());
		
		return customer;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, SQLException {
		Console console = new Console();
		int option = 0;
		
		try(Facade facade = new Facade()) {
			while(option != 4) {
				option = console.showMainMenu();
				
				if(option == 1) {
					int carOption = 0;
					
					while(carOption != 4) {
						carOption = console.showCarManagementMenu();
						
						if(carOption == 1) {
							Car car = console.showAddCarMenu();
							
							try {
								int status = facade.addCar(car);
								
								if(status != 0) {
									System.out.println("Successfully added a new car with ID " + status);
								} else {
									System.out.println("Failed to add a new car");
								}
							} catch (SQLException e) {
								if(e instanceof SQLIntegrityConstraintViolationException) {
									System.out.println("Car with plate number " + car.getPlateNo() + " already exists");
								} else {
									System.out.println("Exception occured: " + e.getMessage());
								}
							}
						} else if(carOption == 2) {
							Car car = console.showEditCarMenu();
							
							try {
								int status = facade.updateCar(car);
								
								if(status != 0) {
									System.out.println("Successfully updated a new car with ID " + car.getCarID());
								} else {
									System.out.println("Failed to update an existing car with ID " + car.getCarID());
								}
							} catch (SQLException e) {
								if(e instanceof SQLIntegrityConstraintViolationException) {
									System.out.println("Car with plate number " + car.getPlateNo() + " already exists");
								} else {
									System.out.println("Exception occured: " + e.getMessage());
								}
							}
						} else if(carOption == 3) {
							String keyword = console.showSearchCarsMenu();
							Vector<Car> cars = facade.searchCars(keyword);
							
							System.out.println();
							
							if(cars.size() != 0) {
								System.out.println("Car ID\tCar Description");
								
								for(Car car : cars) {
									System.out.println(car.getCarID() + "\t" + car);
								}
							} else {
								System.out.println("No results found");
							}
						} else if(carOption == 4) {
							break;
						} else {
							System.out.println("Invalid option");
						}
					}
					
				} else if(option == 2) {
					int customerOption = 0;
					
					while(customerOption != 4) {
						customerOption = console.showCustomerManagementMenu();
						
						if(customerOption == 1) {
							Customer customer = console.showAddCustomerMenu();
							
							try {
								int status = facade.addCustomer(customer);
								
								if(status != 0) {
									System.out.println("Successfully added a new customer with ID " + status);
								} else {
									System.out.println("Failed to add a new customer");
								}
							} catch (SQLException e) {
								if(e instanceof SQLIntegrityConstraintViolationException) {
									System.out.println("Customer with Identity Card number " + customer.getIdentityCardNo() + " already exists");
								} else {
									System.out.println("Exception occured: " + e.getMessage());
								}
							}
						} else if(customerOption == 2) {
							Customer customer = console.showEditCustomerMenu();
							
							try {
								int status = facade.updateCustomer(customer);
								
								if(status != 0) {
									System.out.println("Successfully updated a new customer with ID " + customer.getCustomerID());
								} else {
									System.out.println("Failed to update an existing customer with ID " + customer.getCustomerID());
								}
							} catch (SQLException e) {
								if(e instanceof SQLIntegrityConstraintViolationException) {
									System.out.println("Car with identity card number " + customer.getIdentityCardNo() + " already exists");
								} else {
									System.out.println("Exception occured: " + e.getMessage());
								}
							}
						} else if(customerOption == 3) {
							String keyword = console.showSearchCustomerMenu();
							Vector<Customer> customers = facade.searchCustomers(keyword);
							
							System.out.println();
							
							if(customers.size() != 0) {
								System.out.println("Customer\tCustomer Description");
								
								for(Customer customer : customers) {
									System.out.println(customer.getCustomerID() + "\t" + customer);
								}
							} else {
								System.out.println("No results found");
							}
						} else if(customerOption == 4) {
							break;
						} else {
							System.out.println("Invalid option");
						}
					}
				} else if(option == 4) {
					break;
				} else
					System.out.println("Invalid option");
			}
		}
		
	}
}

