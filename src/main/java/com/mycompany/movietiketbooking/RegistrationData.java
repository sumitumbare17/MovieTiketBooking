/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movietiketbooking;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author sumit
 */
class RegistrationData {
    public int id=0;
    public String name=null;
    public String mobile=null;
    public String email =null;
       private static String url = "jdbc:mysql://127.0.0.1:3306/moviebook";
	private static String user = "root";
	private static String pass = "";
	private static String driver = "com.mysql.cj.jdbc.Driver";
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

			sql = "INSERT into registration (name,mobile,email,pass) VALUES(?,?,?,?)";
			stm = con.prepareStatement(sql);

			stm.setString(1, (String) e.get(0));
			stm.setString(2, (String) e.get(1));
			stm.setString(3, (String) e.get(2));
			stm.setString(4, (String) e.get(3));
			a = stm.executeUpdate();

		} catch (Exception e1) {
                    System.out.print(e1);
		}
		return a;
	}     
         public int login(String pa , String mobile ) {
		String sql;
		PreparedStatement stm = null;
		Connection con = null;
		Statement stmt = null;
		String pas = null;
		String name = null;
		ResultSet rs = null;
		int b = 0;
		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, user, pass);

			stmt = con.createStatement();

			sql = "select  pass ,name,id  from registration where mobile = ? ";
			stm = con.prepareStatement(sql);
			stm.setString(1,mobile);
			rs= stm.executeQuery();
			if (rs.next() == false) {
			       b=1;
			      } else {
			    	  
			        do {
			        	pas = rs.getString(1);
						name = rs.getString(2);
                                                id=rs.getInt(3);
			        } while (rs.next());
			     }
			if (pas.equals(pa)) {
				JOptionPane.showMessageDialog(null, "Welcome  " + name);
                                DashBoard dt = new DashBoard(id);
                                dt.setVisible(true);
                                
			} else {
				// JOptionPane.showMessageDialog(null, "Enter valid User Id And Pass");
				b = 2;
			}
		} catch(NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "Enter Only Numbers  ");
		}
		catch (Exception e) {		
		}
		return b;
	}
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
                       
			 sql = "select name,mobile,email from registration where id = ? ";
                      
                   
			stm = con.prepareStatement(sql);
			stm.setInt(1,id);
			rs= stm.executeQuery();
					      
			 while (rs.next()) 
			 {
			        	name  = rs.getString(1);
			        	mobile= rs.getString(2);
			        	email = rs.getString(3);
			        	
			        }
			

		} catch(NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "Enter Only Numbers  ");
		}
		catch (Exception e) {
			
		}
		
	}
}
