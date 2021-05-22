package entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dao.DealDaoImpl;

@DatabaseTable(tableName = "deal", daoClass = DealDaoImpl.class)
public class Deal {
	public static final String ID_FIELD = "id";
	public static final String COUNT_FIELD = "count";
	public static final String STATE_FIELD = "state";
	
	@DatabaseField(generatedId = true, columnName = ID_FIELD)
	private Integer pid;
	@DatabaseField(columnName = COUNT_FIELD)
	private Integer count;
	@DatabaseField(columnName = STATE_FIELD)
	private DealState state;
	@DatabaseField(foreign = true)
	private Tender tender;
	@DatabaseField(foreign = true)
	private Supplier supplier;
	
	public Deal(){}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public DealState getState() {
		return state;
	}

	public void setState(DealState state) {
		this.state = state;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
		this.tender = tender;
	}

}
