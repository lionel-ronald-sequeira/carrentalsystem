package com.uta.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uta.crs.constants.QueryConstants;
import com.uta.crs.dao.UserDao;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean checkUser(String userName, String password) {
		Connection conn=DaoUtility.getConnection();
		PreparedStatement preparedStatement=DaoUtility.getPreparedStatement(conn,QueryConstants.CHECK_USER_EXISTS);
		int count=0;
		try {
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				count=resultSet.getInt(1);
			}
			if(count > 0){
				return true;
			}
			DaoUtility.closePreparedStatement(preparedStatement);
			DaoUtility.closeConnection(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return false;
	}

}
