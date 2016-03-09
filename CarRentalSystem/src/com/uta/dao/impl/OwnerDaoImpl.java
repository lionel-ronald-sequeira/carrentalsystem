package com.uta.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uta.crs.bo.Car;
import com.uta.crs.bo.Owner;
import com.uta.crs.constants.QueryConstants;
import com.uta.crs.dao.OwnerDao;

public class OwnerDaoImpl implements OwnerDao {

	@Override
	public List<Owner> getOwnersForCars() {
		List<Owner>ownerList=new ArrayList<Owner>();
		Connection conn=DaoUtility.getConnection();
		PreparedStatement statement=DaoUtility.getPreparedStatement(conn,QueryConstants.GET_OWNER_LIST);
		try {
				ResultSet resultSet= statement.executeQuery();
				while(resultSet.next()){
					Owner owner=new Owner();
					owner.setOwnerId(resultSet.getInt("owner_id"));
					owner.setOwnerName(resultSet.getString("bank_name"));
					ownerList.add(owner);
				}
				DaoUtility.closePreparedStatement(statement);
				DaoUtility.closeConnection(conn);
		}catch(SQLException e){
				e.printStackTrace();
		}
		return ownerList;
	}

}
