/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.movietiketbooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author sumit
 */
public class BookingData {
   public int id;
    public String name;
    public String film_name;
    public String booked_seats;
    public int no_seats;
    public String amount;	
 private static String url = "jdbc:mysql://127.0.0.1:3306/moviebook";
	private static String user = "root";
	private static String pass = "";
	private static String driver = "com.mysql.cj.jdbc.Driver";
    /**
     * @param args the command line arguments
     */
   
        // TODO code application logic here
          public void getDetails(int id)
	{		
		PreparedStatement stm = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		 String sql;
		try {
                        ArrayList al;
			Class.forName(driver);

			con = DriverManager.getConnection(url, user, pass);

			stmt = con.createStatement();
                       
			 sql = "select id,name,film_name,booked_seats,no_seats,amount from booking where id = ? ";
                      			stm = con.prepareStatement(sql);
			stm.setInt(1,id);
			rs= stm.executeQuery();
					      
			 while (rs.next()) 
			 {       
                                        name= rs.getString(1);
			        	film_name = rs.getString(2);
			        	booked_seats = rs.getString(3);
			        	no_seats= rs.getInt(4);
			        	amount = rs.getString(5);
                                        
			        }
			this.id=id;

		} catch(NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "Enter Only Numbers  ");
		}
		catch (Exception e) {
			
		}
		
	}
          
             public int insertIntoDB(ArrayList e) {
		String sql;
		PreparedStatement stm = null;
		java.sql.Connection con = null;
		Statement stmt = null;
		int a = 0;
		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, user, pass);

			stmt = (Statement) con.createStatement();

			sql = "INSERT into booking (name,film_name,booked_seats,no_seats,amount,film_id) VALUES(?,?,?,?,?,?)";
			stm = con.prepareStatement(sql);

			stm.setString(1, (String) e.get(0));
			stm.setString(2, (String) e.get(1));
                       Vector v =  (Vector) e.get(2);
                       stm.setString(3, v.toString());
		       stm.setInt(4, (int) e.get(3));
                       stm.setInt(5, (int) e.get(4));
                       stm.setInt(6, (int) e.get(5));
			a = stm.executeUpdate();

		} catch (Exception e1) {
                    System.out.print(e1);
		}
		return a;
	}     
    }

