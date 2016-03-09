package com.uta.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class DaoUtility {
	
	private static Connection connection=null;
	private static PreparedStatement preparedStatement=null;
	private static Statement statement=null;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost/car_rental_system","root","root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static PreparedStatement getPreparedStatement(Connection conn,String query){
		try {
			preparedStatement=conn.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	public static void closeConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closePreparedStatement(PreparedStatement stmt){
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Statement createStatement(Connection conn){
		try {
			statement=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	public static void commitTranscation(Connection conn){
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void rollbackTransaction(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void settingAutoCommitTranscation(Connection conn){
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
