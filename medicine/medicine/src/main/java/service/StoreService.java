package service;

import java.util.List;

import dao.StoreDao;
import dao.StoreDaoJDBCImpl;
import entity.Medicines;

public class StoreService {
	public StoreService(){
		super();
	}
	
	public StoreDao storeDao= new StoreDaoJDBCImpl();

	public StoreDao getStoreDao() {
		return storeDao;
	}

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}
	
	public boolean update(Medicines oldStore,Medicines newStore){
		return storeDao.update(oldStore,newStore);
	}
	
	public boolean delete(String medicine_id){
		return storeDao.delete(medicine_id);
	}
	
	public List<Medicines> getMedicines(){
		return storeDao.getMedicines();
	}
	
	public List<Medicines> getMedicines(String medicine_id){
		return storeDao.getMedicines(medicine_id);
	}
	
	public List<Medicines> getYj(){
		return storeDao.getYj();
	}
	
}
