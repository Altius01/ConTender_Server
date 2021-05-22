package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import entities.Supplier;

public class SupplierDaoImpl extends BaseDaoImpl<Supplier, Integer> implements SupplierDao{

	public SupplierDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Supplier.class);
		// TODO Auto-generated constructor stub
	}

}
