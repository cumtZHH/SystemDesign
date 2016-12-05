package entity;

public class OrderItem {
	public String order_item_id;
	public int number;
	public double order_item_price;
	public String order_id;
	public String medicine_id;
	public String getOrder_item_id() {
		return order_item_id;
	}
	public void setOrder_item_id(String order_item_id) {
		this.order_item_id = order_item_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getOrder_item_price() {
		return order_item_price;
	}
	public void setOrder_item_price(double order_item_price) {
		this.order_item_price = order_item_price;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getMedicine_id() {
		return medicine_id;
	}
	public void setMedicine_id(String medicine_id) {
		this.medicine_id = medicine_id;
	}
}
