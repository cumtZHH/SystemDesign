package dao;

import java.util.Date;
import java.util.List;

import entity.Medicines;
import entity.Order;
import entity.Person;
import entity.Supplier;

public interface BuyDao {
	//根据日期查看当天所有入库药物
	public List<Medicines> getMedicines(String date);
	//查询所有供应商
	public List<Supplier> getSuppliers();
	public List<Medicines> getAllMedicines();
	public List<Person> getAllPersons();
	public void addOrder(Order order);
	public void updateOrder(Order order);
	public void deleteOrder(String orderId);
}
