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
}
