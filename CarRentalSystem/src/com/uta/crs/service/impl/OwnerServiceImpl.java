package com.uta.crs.service.impl;

import java.util.List;

import com.uta.crs.bo.Owner;
import com.uta.crs.dao.OwnerDao;
import com.uta.crs.service.OwnerService;
import com.uta.dao.impl.OwnerDaoImpl;

public class OwnerServiceImpl implements OwnerService {

	@Override
	public List<Owner> getOwner() {
		List<Owner>ownerList=null;
		OwnerDao ownerDao=new OwnerDaoImpl();
		ownerList=ownerDao.getOwnersForCars();
		return ownerList;
	}

}
