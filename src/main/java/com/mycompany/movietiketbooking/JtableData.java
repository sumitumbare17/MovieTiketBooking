/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.movietiketbooking;

/**
 *
 * @author sumit
 */

import java.sql.Connection;
import net.proteanit.sql.DbUtils;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class JtableData {
	private static String url = "jdbc:mysql://127.0.0.1:3306/moviebook";
	private static String user = "root";
	private static String pass = "";
	
	public void ShowDataInJtable(String q , JTable x) {
		String sql;
		PreparedStatement stm = null;
		Connection con = null;
		Statement stmt = null;
		String user_id;
		String pas = null;
		String name = null;
		ResultSet rs = null;
		int b = 0;
	try {
	
	con = DriverManager.getConnection(url, user, pass);

	stmt = con.createStatement();
    stmt.executeQuery(q);
    rs = stmt.executeQuery(q);
    x.setModel(DbUtils.resultSetToTableModel(rs));
}catch (SQLException ex) {
	  JOptionPane.showMessageDialog(null, ex);
}
            // TODO Auto-generated catch block
             finally {
  try {
 rs.close();
} catch (SQLException e) { /* ignore */
}

try {
 stmt.close();
} catch (SQLException e) { /* ignore */
}
}
	}
}
