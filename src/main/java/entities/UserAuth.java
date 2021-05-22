package entities;


import java.util.Objects;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dao.UserAuthDaoImpl;

@DatabaseTable(tableName = "userAuth", daoClass = UserAuthDaoImpl.class)
public class UserAuth {
	public static final String ID_FIELD = "id";
	public static final String NAME_FIELD = "name";
	public static final String MAIL_FIELD = "mail";
	public static final String PASSWORD_FIELD = "password";

	@DatabaseField(generatedId = true, columnName = ID_FIELD)
	private Integer pid;
	
	@DatabaseField(unique = true, columnName = NAME_FIELD)
	private String name;

	@DatabaseField(unique = true, columnName = MAIL_FIELD)
	private String mail;
	
	@DatabaseField(columnName = PASSWORD_FIELD)
	private String passwd;
    
	public UserAuth() {
		// TODO Auto-generated constructor stub
	}
	
	public UserAuth(String name, String passwd) {
		this.name = name;
		this.passwd = passwd;
	}
	
	public UserAuth(String name, String mail, String passwd) {
		this.name = name;
		this.mail = mail;
		this.passwd = passwd;
	}
	
	public void setName(String name) {
		this.name = Objects.requireNonNull(name, "Name must not be Null!"); 
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setPassword(String passwd) {
		this.passwd = Objects.requireNonNull(passwd, "Name must not be Null!"); 
	}
	
	public String getPassword() {
		return this.passwd;
	}
}
