package com.bridgelabz.addressbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	public void updateData(String firstname, int person_id) throws SQLException {
		try{
			JDBCConn connection = new JDBCConn();
			con = connection.establishDbConection();
			
			String query = "update person set firstname=? where person_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, firstname);
			ps.setInt(2, person_id);
			ps.executeUpdate();
			System.out.println("Updated");			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
}
