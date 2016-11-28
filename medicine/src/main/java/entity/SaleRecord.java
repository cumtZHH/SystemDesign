package entity;

import java.util.Date;

public class SaleRecord {
    private String saleRecord_id;
    private Double saleRecordPrice;
    private Date SaleRecordDate;
	public String getSaleRecord_id() {
		return saleRecord_id;
	}
	public void setSaleRecord_id(String saleRecord_id) {
		this.saleRecord_id = saleRecord_id;
	}
	public Double getSaleRecordPrice() {
		return saleRecordPrice;
	}
	public void setSaleRecordPrice(Double saleRecordPrice) {
		this.saleRecordPrice = saleRecordPrice;
	}
	public Date getSaleRecordDate() {
		return SaleRecordDate;
	}
	public void setSaleRecordDate(Date saleRecordDate) {
		SaleRecordDate = saleRecordDate;
	}
}
