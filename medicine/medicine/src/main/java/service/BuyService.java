package service;

import java.util.Date;
import java.util.List;

import dao.BuyDao;
import entity.Medicines;
import entity.Order;
import entity.Person;
import entity.Supplier;

public interface BuyService {
	public void setBuyDao(BuyDao buyDao);
	public List<Medicines> getMedicines(String date);
	//查询所有供应商
	public List<Supplier> getSuppliers();
	//查出所有的药品
	public List<Medicines> getAllMedicines();
	//查出所有人员
	public List<Person> getAllPersons();
	public void addOrder(Order order);
	public void updateOrder(Order order);
	public void deleteOrder(String orderId);
}
