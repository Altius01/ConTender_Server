package entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import dao.SupplierDaoImpl;

@DatabaseTable(tableName = "supplier", daoClass = SupplierDaoImpl.class)
public class Supplier {
	public static final String ID_FIELD = "id";
	public static final String USER_FIELD = "user";
	public static final String PRODUCT_FIELD = "product";
	
	@DatabaseField(generatedId = true, columnName = ID_FIELD)
	private Integer pid;
    
    @DatabaseField(foreign = true, columnName = USER_FIELD)
    private User user;

    @DatabaseField(foreign = true, columnName = PRODUCT_FIELD)
    private Product product;

	@ForeignCollectionField()
	transient ForeignCollection<TenderSupplierJoin> tenders;

	@ForeignCollectionField()
	transient ForeignCollection<Deal> deals;
	
	public Supplier(){}
	
	public void setUser(User user) {
		this.user = user; 
	}
	
	public User getUser() {
		return this.user;
	}

    public void setProduct(Product product) {
		this.product = product; 
	}
	
	public Product getProduct() {
		return this.product;
	}    
    
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public ForeignCollection<TenderSupplierJoin> getTenders() {
		return tenders;
	}

	public void setTenders(ForeignCollection<TenderSupplierJoin> tenders) {
		this.tenders = tenders;
	}

	public ForeignCollection<Deal> getDeals() {
		return deals;
	}

	public void setDeals(ForeignCollection<Deal> deals) {
		this.deals = deals;
	}
}
