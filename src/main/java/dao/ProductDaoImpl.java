package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import entities.Product;

public class ProductDaoImpl extends BaseDaoImpl<Product, Integer> implements ProductDao{

	public ProductDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Product.class);
		// TODO Auto-generated constructor stub
	}

}
