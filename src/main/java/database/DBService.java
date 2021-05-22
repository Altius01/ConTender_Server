package database;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import dao.DealDaoImpl;
import dao.ProductDaoImpl;
import dao.SupplierDaoImpl;
import dao.TenderDaoImpl;
import dao.UserAuthDaoImpl;
import dao.UserDaoImpl;
import entities.Deal;
import entities.Product;
import entities.Supplier;
import entities.Tender;
import entities.TenderSupplierJoin;
import entities.User;
import entities.UserAuth;

public class DBService {
	private static DBService instance;	
	static String dataBaseUrl = "jdbc:sqlite:sample.db";
	JdbcConnectionSource connectionPool;

	Class<?>[] classes = {User.class, UserAuth.class, Supplier.class, Tender.class, TenderSupplierJoin.class, 
			Deal.class, Product.class};
	
	UserDaoImpl userDao;
	SupplierDaoImpl supplierDao;
	TenderDaoImpl tenderDao;
	DealDaoImpl dealDao;
	ProductDaoImpl productDao;
	UserAuthDaoImpl userAuthDao;
	
	private DBService() {
		try {
			connectionPool = new JdbcConnectionSource(dataBaseUrl);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		try {
			createTables();
			//clearTables();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static DBService getInstance() {
		if (instance == null) {
			instance = new DBService();
		}
		return instance;
	}
	
	public JdbcConnectionSource getConnectionSourse() {
		return connectionPool;
	}
	
	private void createTables() throws SQLException {
		for(Class<?> c : classes)
			TableUtils.createTableIfNotExists(connectionPool, c);
    }
	
	public void clearTables() throws SQLException {
		for(Class<?> c : classes)
			TableUtils.clearTable(connectionPool, c);
	}
	
	
	public UserAuthDaoImpl getUserAuthDao() throws SQLException {
		if (userAuthDao == null) {
			userAuthDao = new UserAuthDaoImpl(connectionPool);
		}
		return userAuthDao;
	}
}
