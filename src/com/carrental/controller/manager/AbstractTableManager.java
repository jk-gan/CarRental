/**
 * 
 */
package com.carrental.controller.manager;

/**
 * This class is used as the template for table manager classes.
 * @author JKGan
 * @version 1.0
 * @since 1.0
 */
abstract class AbstractTableManager {
	protected Facade facade;
	
	public AbstractTableManager(Facade facade) {
		this.facade = facade;
	}
}
