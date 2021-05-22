package entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dao.ProductDaoImpl;

@DatabaseTable(tableName = "product", daoClass = ProductDaoImpl.class)
public class Product {
	public static final String ID_FIELD = "id";
	public static final String NAME_FIELD = "name";
	public static final String PRICE_FIELD = "price";
	public static final String COUNT_FIELD = "count";
	
	@DatabaseField(generatedId = true, columnName = ID_FIELD)
	private Integer pid;
	@DatabaseField(columnName = NAME_FIELD)
	private String name;
	@DatabaseField(columnName = PRICE_FIELD)
	private Double price;
	@DatabaseField(columnName = COUNT_FIELD)
	private Integer count;
	
	public Product() {}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
