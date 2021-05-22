package entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dao.TenderSupplierJoinDaoImpl;

@DatabaseTable(tableName = "tendersupplierjoin", daoClass = TenderSupplierJoinDaoImpl.class)
public class TenderSupplierJoin {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(uniqueCombo = true, foreign = true)
    private Tender tender;
    @DatabaseField(uniqueCombo = true, foreign = true)
    private Supplier supplier;

    public void setTender(Tender tender) {
        this.tender = tender;
    }

    public Tender getTender() {
        return tender;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }
}
