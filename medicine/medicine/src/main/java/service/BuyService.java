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
	//��ѯ���й�Ӧ��
	public List<Supplier> getSuppliers();
	//������е�ҩƷ
	public List<Medicines> getAllMedicines();
	//���������Ա
	public List<Person> getAllPersons();
	public void addOrder(Order order);
	public void updateOrder(Order order);
	public void deleteOrder(String orderId);
}
