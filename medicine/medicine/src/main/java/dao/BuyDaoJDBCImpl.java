package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dbUtil.DbUtil;
import entity.Medicines;
import entity.Order;
import entity.Person;
import entity.Supplier;

public class BuyDaoJDBCImpl implements BuyDao{
	//根据日期查看当天所有入库药物
	@Override
	public List<Medicines> getMedicines(String date) {
		ResultSet rs=DbUtil.executeQuery("select * from medicine where storage_date=?", new Object[]{date});
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
		ResultSet rs=DbUtil.executeQuery("select * from medicine", new Object[]{});
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
		// TODO Auto-generated method stub
		DbUtil.executeUpdate("insert order(order_id) values (?)", new Object[]{order.getOrder_id()});
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		
	}
}
