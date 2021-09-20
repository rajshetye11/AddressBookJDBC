package com.bridgelabz.addressbook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressBook {
	Connection con;
	public void getDatafromDatabase() throws SQLException{		
		try {
			JDBCConn connection = new JDBCConn();
			con = connection.establishDbConection();
			String query = "select * from person";
			Statement st =  con.createStatement();
		      ResultSet rs= st.executeQuery(query);
		        while (rs.next())
		        {
		            System.out.println(
		                    rs.getString(1)+" "+
		                            rs.getString(2)+ " "+
		                            rs.getString(3)+" "+
		                            rs.getString(4)+" "+
		                            rs.getString(5)
		            );
		        }
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
