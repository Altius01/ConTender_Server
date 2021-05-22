package entities;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.DatabaseTable;

import dao.SupplierDaoImpl;
import dao.TenderDaoImpl;
import dao.TenderSupplierJoinDaoImpl;

@DatabaseTable(tableName = "tender", daoClass = TenderDaoImpl.class)
public class Tender {
	public static final String ID_FIELD = "id";
	public static final String VOLUME_FIELD = "volume";
	public static final String USER_FIELD = "user";
	public static final String PRODUCT_FIELD = "product";
	
	@DatabaseField(generatedId = true, columnName = ID_FIELD)
	private Integer pid;

	@DatabaseField(columnName = VOLUME_FIELD)
	private Float volume;

	@DatabaseField(foreign = true, columnName = USER_FIELD)
	private User user;
    
	@DatabaseField(foreign = true, columnName = PRODUCT_FIELD)
	private Product product;
    
    @ForeignCollectionField()
	transient ForeignCollection<TenderSupplierJoin> suppliers;
	
	public Tender() {}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Float getVolume() {
		return volume;
	}

	public void setVolume(Float volume) {
		this.volume = volume;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

    public ForeignCollection<TenderSupplierJoin> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(ForeignCollection<TenderSupplierJoin> suppliers) {
        this.suppliers = suppliers;
    }

    public void setSuppliers(JdbcConnectionSource connectionSource, PreparedQuery query) throws SQLException, IOException {
        SupplierDaoImpl supplierDao = new SupplierDaoImpl(connectionSource);
        TenderSupplierJoinDaoImpl tsjoinDao = new TenderSupplierJoinDaoImpl(connectionSource);

        if (query == null) {
            QueryBuilder<Supplier, Integer> supplier_qb = supplierDao.queryBuilder();
            supplier_qb.where().eq("product_id", this.product.getPid());
            query = supplier_qb.prepare();
        }
        

        List<Supplier> suppliers = supplierDao.query(query);
        for (Supplier supplier: suppliers) {
            TenderSupplierJoin tsjoin = new TenderSupplierJoin();
            tsjoin.setTender(this);
            tsjoin.setSupplier(supplier);
            tsjoinDao.createIfNotExists(tsjoin);
        }
    }
}
