package service.impl;

import java.util.Date;
import java.util.List;

import dao.BuyDao;
import entity.Medicines;
import entity.Order;
import entity.Person;
import entity.Supplier;
import service.BuyService;

public class BuyServiceImpl implements BuyService{
	private BuyDao buyDao;
	public void setBuyDao(BuyDao buyDao) {
		this.buyDao =buyDao;
	}
	public List<Medicines> getMedicines(String date) {
		return buyDao.getMedicines(date);
	}
	@Override
	public List<Supplier> getSuppliers() {
		return buyDao.getSuppliers();
	}
	@Override
	public List<Medicines> getAllMedicines() {
		return buyDao.getAllMedicines();
	}
	@Override
	public List<Person> getAllPersons() {
		return buyDao.getAllPersons();
	}
	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		buyDao.addOrder(order);
	}
	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		buyDao.updateOrder(order);
	}
	@Override
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		buyDao.deleteOrder(orderId);
	}

}
