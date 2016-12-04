package entity;

public class SaleList {
	private String medicine_id; 
	private String medicineName;
	private double price;
	private int saleRecordNumber;
	private Double subTotal;
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public String getMedicine_id() {
		return medicine_id;
	}
	public void setMedicine_id(String medicine_id) {
		this.medicine_id = medicine_id;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSaleRecordNumber() {
		return saleRecordNumber;
	}
	public void setSaleRecordNumber(int saleRecordNumber) {
		this.saleRecordNumber = saleRecordNumber;
	}
}
