package entity;

import java.util.Date;

public class Medicines {
	public String medicine_id;
	public String medicineName;
	public double price;
	public int store;
	public int category_id;
	public String finance_report_id;
	public int min_store;
	private String  storageDate;
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
	public int getStore() {
		return store;
	}
	public void setStore(int store) {
		this.store = store;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getMin_store() {
		return min_store;
	}
	public void setMin_store(int min_store) {
		this.min_store = min_store;
	}
	public String getFinance_report_id() {
		return finance_report_id;
	}
	public void setFinance_report_id(String finance_report_id) {
		this.finance_report_id = finance_report_id;
	}
	public String getStorageDate() {
		return storageDate;
	}
	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}
	
	
}
