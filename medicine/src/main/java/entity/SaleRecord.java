package entity;

import java.util.Date;

public class SaleRecord {
    private String saleRecord_id;
    private Double saleRecordTotalPrice;
    private Date saleRecordDate;
    private String person_id;
	public Date getSaleRecordDate() {
		return saleRecordDate;
	}
	public void setSaleRecordDate(Date saleRecordDate) {
		this.saleRecordDate = saleRecordDate;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getSaleRecord_id() {
		return saleRecord_id;
	}
	public void setSaleRecord_id(String saleRecord_id) {
		this.saleRecord_id = saleRecord_id;
	}
	public Double getSaleRecordTotalPrice() {
		return saleRecordTotalPrice;
	}
	public void setSaleRecordTotalPrice(Double saleRecordTotalPrice) {
		this.saleRecordTotalPrice = saleRecordTotalPrice;
	}
	

}
