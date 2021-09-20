package com.bridgelabz.addressbook;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;


public class AddressBookTest {
	
	public AddressBook addbook = new AddressBook();
	
	@Test
	public void establishConn() throws SQLException {
		Connection jdbccon = new JDBCConn().establishDbConection();
		addbook.getDatafromDatabase();
		jdbccon.close();
	}
	
	@Test
	public void updateData() throws SQLException {
		Connection jdbccon = new JDBCConn().establishDbConection();
		addbook.updateData("Mahi", 3);
		jdbccon.close();
	}
	
	@Test
	public void retriveDataFromDate() throws SQLException {
    	Connection dbConnection= new JDBCConn().establishDbConection();
    	addbook.getDatabyDate();
    	dbConnection.close();
    }
}
