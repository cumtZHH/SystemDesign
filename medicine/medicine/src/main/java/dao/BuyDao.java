package dao;

import java.util.Date;
import java.util.List;

import entity.Medicines;
import entity.Order;
import entity.Person;
import entity.Supplier;

public interface BuyDao {
	//�������ڲ鿴�����������ҩ��
	public List<Medicines> getMedicines(String date);
	//��ѯ���й�Ӧ��
	public List<Supplier> getSuppliers();
	public List<Medicines> getAllMedicines();
	public List<Person> getAllPersons();
	public void addOrder(Order order);
	public void updateOrder(Order order);
	public void deleteOrder(String orderId);
}
