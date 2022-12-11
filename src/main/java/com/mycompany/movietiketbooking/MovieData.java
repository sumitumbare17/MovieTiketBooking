/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movietiketbooking;

import com.google.protobuf.TextFormat.ParseException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author sumit
 */
public class MovieData {
/**
 *
 * @author sumit
 */public int id;
    public String name;
    public String language;
    public String time;
    public String start_date;
    public String end_date;
    public String platinum_seats;
    public String gold_seats;
    public String silver_seats ;
    public String  screen;
    public ImageIcon image;
    
    
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

			sql = "INSERT into movies (Name,language,time,start_date,end_date, platinum_seats ,gold_seats,silver_seats ,screen,image) VALUES(?,?,?,?,?,?,?,?,?,?)";
			stm = con.prepareStatement(sql);

			stm.setString(1, (String) e.get(0));
			stm.setString(2, (String) e.get(1));
			stm.setString(3, (String) e.get(2));
			stm.setString(4, (String) e.get(3));
                        stm.setString(5, (String) e.get(4));
                        stm.setString(6, (String) e.get(5));
                        stm.setString(7, (String) e.get(6));
                        stm.setString(8, (String) e.get(7));
                        stm.setString(9, (String) e.get(8));
                       FileInputStream fis= (FileInputStream) e.get(9);
                        stm.setBinaryStream(10,fis,fis.available());
			a = stm.executeUpdate();

		} catch (Exception e1) {
                    System.out.print(e1);
		}
		return a;
	}  
         
         
         public int rowCount()
         {
             int count = 0;
             String sql;
             Statement stmt = null;
             ResultSet rs = null;
        try {
                 
            Class.forName(driver); 
            Connection con = DriverManager.getConnection(url, user, pass);
            
			stmt = (Statement) con.createStatement();

			sql = "select count(*) from movies";
                        rs = stmt.executeQuery(sql);
                        rs.next();
                        count = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(MovieData.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ClassNotFoundException ex) {
                     Logger.getLogger(MovieData.class.getName()).log(Level.SEVERE, null, ex);
                 }
        return count;
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
                       
			 sql = "select Name ,language,time,start_date,end_date, platinum_seats ,gold_seats,silver_seats ,screen,image from movies where id = ? ";
                      
                   
			stm = con.prepareStatement(sql);
			stm.setInt(1,id);
			rs= stm.executeQuery();
					      
			 while (rs.next()) 
			 {
			        	name  = rs.getString(1);
			        	language= rs.getString(2);
			        	time = rs.getString(3);
			        	start_date = rs.getString(4);
			        	end_date= rs.getString(5);
			        	platinum_seats = rs.getString(6);
			        	gold_seats = rs.getString(7);
			        	silver_seats = rs.getString(8);
                                        screen=rs.getString(9);
                                        byte a[]= rs.getBytes(10);
			        	image=new ImageIcon(a);
                                        this.id  =id;
			        }
			

		} catch(NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "Enter Only Numbers  ");
		}
		catch (Exception e) {
			
		}
		
	}
         
          public int getid(String s)
	{		
		PreparedStatement stm = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
                int id = 0;
		 String sql;
		try {
                        
			Class.forName(driver);

			con = DriverManager.getConnection(url, user, pass);

			stmt = con.createStatement();
                       
			 sql = "select id from movies where name = ? ";
                      
                   
			stm = con.prepareStatement(sql);
			stm.setString(1,s);
			rs= stm.executeQuery();
					      
			 while (rs.next()) 
			 {
			        	id  = rs.getInt(1);
			        }
			

		} catch(NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "Enter Only Numbers  ");
		}
		catch (Exception e) {
			
		}
     return id;
		
	}
          
          public  boolean isExpire(Date dat,String time){
    /*if(dat.isEmpty() || dat.trim().equals("")){
        return false;
    }else{*/
        String[] arrOfStr = time.split("-", 2);
         LocalDateTime now = LocalDateTime.now();
       //  String date = dat+" "+arrOfStr[1];
         //   SimpleDateFormat sdf =  new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy"); // Jan-20-2015 1:30:55 PM
               Date d=dat;
               Date d1=new Date(Long.parseLong(now.toString()));
             
            
               //System.out.println("expdate>> "+date);
               //System.out.println("today>> "+today+"\n\n");
               //  d = (Date) sdf.parse(date);
               //d1 = (Date) sdf.parse(today);
               if(d1.compareTo(d) <0){// not expired
                   return false;
               }else if(d.compareTo(d1)==0){// both date are same
                   if(d.getTime() < d1.getTime()){// not expired
                       return false;
                   }else if(d.getTime() == d1.getTime()){//expired
                       return true;
                   }else{//expired
                       return true;
                   }
               }else{//expired
                   return true;
        
}
          }
            public String getBookedSeats(int id)
         {
             String count = null;
             String sql;
             PreparedStatement stmt = null;
             ResultSet rs = null;
        try {
                 
            Class.forName(driver); 
            Connection con = DriverManager.getConnection(url, user, pass);
                        sql = "select booked_seats from movies where id =? ";
			stmt = con.prepareStatement(sql);
                        stmt.setInt(1, id);
                        rs = stmt.executeQuery();
                        rs.next();
                        count = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(MovieData.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ClassNotFoundException ex) {
                     Logger.getLogger(MovieData.class.getName()).log(Level.SEVERE, null, ex);
                 }
        return count;
         }
            
               public String setBookedSeats(int id,String s)
         {
             String count = this.getBookedSeats(id);
             String sql;
             PreparedStatement stmt = null;
            
        try {
                 
            Class.forName(driver); 
            Connection con = DriverManager.getConnection(url, user, pass);
                        sql = "UPDATE movies set booked_seats=? where  id =? ";
			stmt = con.prepareStatement(sql);
                        stmt.setString(1, count+s);
                        stmt.setInt(2, id);
                         stmt.executeUpdate();
                       
              
        } catch (SQLException ex) {
            Logger.getLogger(MovieData.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ClassNotFoundException ex) {
                     Logger.getLogger(MovieData.class.getName()).log(Level.SEVERE, null, ex);
                 }
        return count;
         }
               public int removeMovie(int id)
	{
		String sql;
		PreparedStatement stm = null;
		Connection con = null;
		Statement stmt = null;
		int b = 0;
		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, user, pass);

			stmt = con.createStatement();

			sql = "delete from  movies where id = ?";
			stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			b = stm.executeUpdate();
		} catch (Exception e1) {

		}
		return b;
	}
}
