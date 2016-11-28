package service;

import java.util.List;

import dao.SaleDao;

public class SaleService {
	public SaleService() {
		super();
	}
	private SaleDao saleDao;

	public void setSaleDao(SaleDao saleDao) {
		this.saleDao = saleDao;
	}
	
	public SaleService(SaleDao saleDao){
		this.saleDao = saleDao;
	}
	//“©ŒÔ≤È—Ø
	public List getAll(String medicine_id){
		return saleDao.getAll(medicine_id);
	}
}
