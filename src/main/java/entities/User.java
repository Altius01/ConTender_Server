package entities;

import java.sql.SQLException;
import java.util.Objects;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import dao.SupplierDaoImpl;
import dao.TenderDaoImpl;
import dao.UserDaoImpl;

@DatabaseTable(tableName = "user", daoClass = UserDaoImpl.class)
public class User {
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
    
	@ForeignCollectionField()
	transient ForeignCollection<Tender> tenders;
	
	@ForeignCollectionField()
	transient ForeignCollection<Supplier> suppliers;

	public User(){}
	
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

	public ForeignCollection<Tender> getTender() {
		return tenders;
	}

	public void setTenders(ForeignCollection<Tender> tenders) {
		this.tenders = tenders;
	}

	public ForeignCollection<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(ForeignCollection<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

    public void addSupplier(Product product, SupplierDaoImpl supplierDao) throws SQLException {
        Supplier supplier = new Supplier();
		supplier.setUser(this);
		supplier.setProduct(product);
		supplierDao.createIfNotExists(supplier);
    }

    public void addTender(Product product, Float volume, TenderDaoImpl tenderDao) throws SQLException {
        Tender tender = new Tender();
		tender.setUser(this);
		tender.setProduct(product);
        tender.setVolume(volume);
		tenderDao.createIfNotExists(tender);
    }
}
