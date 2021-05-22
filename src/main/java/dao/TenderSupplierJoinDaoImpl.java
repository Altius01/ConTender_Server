package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import entities.TenderSupplierJoin;

public class TenderSupplierJoinDaoImpl extends BaseDaoImpl<TenderSupplierJoin, Integer> implements TenderSupplierJoinDao{

	public TenderSupplierJoinDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, TenderSupplierJoin.class);
		// TODO Auto-generated constructor stub
	}

}
