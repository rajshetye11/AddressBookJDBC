package com.bridgelabz.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConn {
	private String url = "jdbc:mysql://localhost:3306/addressbookservice?useSSL=false";
	private String username = "root";
    private String password = "root";
    Connection con;

    public Connection establishDbConection() throws SQLException {	       
    	try {
	          Class.forName("com.mysql.jdbc.Driver");
	          con = DriverManager.getConnection(url, username, password);
	          System.out.println("Connection Successful");
    	}
    	catch (ClassNotFoundException e){
    		e.printStackTrace();
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return con;
    }
}
