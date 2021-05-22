package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import entities.Deal;

public class DealDaoImpl extends BaseDaoImpl<Deal, Integer> implements DealDao{

	public DealDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Deal.class);
		// TODO Auto-generated constructor stub
	}

}
