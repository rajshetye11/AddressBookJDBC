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
		                            rs.getString(5)+" "+
		                            rs.getString(6)
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
	
	public void getDatabyDate() throws SQLException {
		try {
			JDBCConn jdbccon = new JDBCConn();
			con = jdbccon.establishDbConection();
			
			String query = "SELECT * FROM person WHERE dateofperson BETWEEN CAST('2017-01-01' AS DATE) AND DATE(NOW())";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {			
				System.out.println(
						rs.getString(1)+" "+
						rs.getString(2)+" "+
						rs.getString(6));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void getCountofPersonsByCity() throws SQLException {
		JDBCConn connection = new JDBCConn();
		con = connection.establishDbConection();
		try {
			String query = String.format("select address.city ,count(address.city),count(address.state) from person inner join address on address.person_id = person.person_id group by city");
			PreparedStatement stmt=con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) 
				System.out.println(rs.getString(1)+" "+
									rs.getString(2)+" "+
									rs.getString(3)
									);
		}catch(SQLException e){
			e.printStackTrace();
		}
	
	}
	
	
	public void addNewContact(int person_id,String firstname,String lastname,String phonenumber,String email,String city,String state,String zip,int address_id) throws SQLException{
		JDBCConn connection = new JDBCConn();
		con = connection.establishDbConection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
			
		try {
			String query = String.format("insert into person(person_id,firstname,lastname,phonenumber,email,dateofperson) values (%d,'%s','%s','%s','%s',DATE(NOW()));", person_id,firstname, lastname, phonenumber, email);
			int result = stm.executeUpdate(query);			
		}
		catch(SQLException e){
			e.printStackTrace();
			con.rollback();
		}
		try {
			String query = String.format("insert into address(address_id,city,state,zip,person_id) values (%d,'%s','%s','%s',%d);", address_id,city, state, zip,person_id);
            int result = stm.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
			con.rollback();
		}
		try {
			con.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
			
	}
}
