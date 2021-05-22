package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import entities.UserAuth;

public class UserAuthDaoImpl extends BaseDaoImpl<UserAuth, Integer> implements UserAuthDao{
	public UserAuthDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, UserAuth.class);
		// TODO Auto-generated constructor stub
	}

}
