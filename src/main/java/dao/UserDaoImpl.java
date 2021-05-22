package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import entities.User;

public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao{

	public UserDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, User.class);
		// TODO Auto-generated constructor stub
	}

}
