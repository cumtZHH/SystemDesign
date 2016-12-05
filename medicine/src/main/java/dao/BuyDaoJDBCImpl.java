package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dbUtil.DbUtil;
import entity.Medicines;
import entity.Order;
import entity.OrderItem;
import entity.Person;
import entity.Supplier;

public class BuyDaoJDBCImpl implements BuyDao{
	//根据日期查看当天所有入库药物
	@Override
	public List<Medicines> getMedicines(String date) {
		ResultSet rs=DbUtil.executeQuery("select * from medicines where storage_date=?", new Object[]{date});
		List<Medicines> medicines=new ArrayList<Medicines>();
		try{
			while(rs.next()){
				Medicines medicine = new Medicines();
				medicine.setMedicine_id(rs.getString(1));
				medicine.setMedicineName(rs.getString(2));
				medicine.setPrice(rs.getDouble(3));
				medicine.setStore(rs.getInt(4));
				medicine.setCategory_id(rs.getInt(5));
				medicine.setFinance_report_id(rs.getString(6));
				medicine.setMin_store(rs.getInt(7));
				medicines.add(medicine);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return medicines;
	}

	@Override
	public List<Supplier> getSuppliers() {
		ResultSet rs=DbUtil.executeQuery("select * from supplier", new Object[]{});
		List<Supplier> suppliers=new ArrayList<Supplier>();
		try{
			while(rs.next()){
				Supplier supplier = new Supplier();
				supplier.setSupplierId(rs.getString(1));
				supplier.setSupplierName(rs.getString(2));
				suppliers.add(supplier);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return suppliers;
	}

	@Override
	public List<Medicines> getAllMedicines() {
		ResultSet rs=DbUtil.executeQuery("select * from medicines", new Object[]{});
		List<Medicines> medicines=new ArrayList<Medicines>();
		try{
			while(rs.next()){
				Medicines medicine = new Medicines();
				medicine.setMedicine_id(rs.getString(1));
				medicine.setMedicineName(rs.getString(2));
				medicines.add(medicine);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return medicines;
	}

	@Override
	public List<Person> getAllPersons() {
		ResultSet rs=DbUtil.executeQuery("select * from person where role_id='buyer'", new Object[]{});
		List<Person> persons=new ArrayList<Person>();
		try{
			while(rs.next()){
				Person person = new Person();
				person.setPerson_id(rs.getString(1));
				person.setPerson_name(rs.getString(2));
				persons.add(person);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return persons;
	}

	
	@Override
	public void addOrder(Order order) {
		DbUtil.executeUpdate("insert `order` values (?,?,?,?)", new Object[]{order.getOrder_id(),order.getOrder_date(),order.getPerson_id(),order.getSupplier_id()});
	}

	@Override
	public void updateOrder(Order order) {
		
	}

	@Override
	public void deleteOrder(String orderId) {
		
	}

	@Override
	public Medicines getMedicineByMedicineId(String medicineId) {
		ResultSet rs=DbUtil.executeQuery("select * from medicines where medicine_id=?", new Object[]{medicineId});
		Medicines medicine = new Medicines();
		try{
			while(rs.next()){
				medicine.setMedicine_id(rs.getString(1));
				medicine.setMedicineName(rs.getString(2));
				medicine.setPrice(rs.getDouble(3));
				medicine.setStore(rs.getInt(4));
				medicine.setCategory_id(rs.getInt(5));
				medicine.setFinance_report_id(rs.getString(6));
				medicine.setMin_store(rs.getInt(7));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return medicine;
	}

	@Override
	public void updateMedicine(Medicines medicine) {
		DbUtil.executeUpdate("update `medicines` set store=?,storage_date=? where medicine_id=?", new Object[]{medicine.getStore(),medicine.getStorageDate(),medicine.getMedicine_id()});

	}

	@Override
	public void addOrderItem(OrderItem orderItem) {
		DbUtil.executeUpdate("insert `order_item` values(?,?,?,?,?)", new Object[]{orderItem.getOrder_item_id(),orderItem.getNumber(),orderItem.getOrder_item_price(),orderItem.getOrder_id(),orderItem.getMedicine_id()});
		
	}

	@Override
	public List<OrderItem> getOrderItem(String orderDate, String supplierId) {
		ResultSet rs=DbUtil.executeQuery("select * from order_item,`order`,medicines where medicines.medicine_id=order_item.medicine_id and `order`.order_id=order_item.order_id and storage_date=? and supplier_id=?", new Object[]{orderDate,supplierId});
		List<OrderItem> orderItems=new ArrayList<OrderItem>();
		try{
			while(rs.next()){
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder_item_id(rs.getString(1));
				orderItem.setNumber(rs.getInt(2));
				orderItem.setOrder_item_price(rs.getDouble(3));
				orderItem.setOrder_id(rs.getString(4));
				orderItem.setMedicine_id(rs.getString(5));
				orderItems.add(orderItem);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return orderItems;
	}

	@Override
	public Order getOrderByOrderId(String orderId) {
		ResultSet rs=DbUtil.executeQuery("select * from `order` where order_id=?", new Object[]{orderId});
		Order order = new Order();
		try{
			while(rs.next()){
				order.setOrder_id(rs.getString(1));
				order.setOrder_date(rs.getDate(2));
				order.setPerson_id(rs.getString(3));
				order.setSupplier_id(rs.getString(4));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return order;
	}
}
