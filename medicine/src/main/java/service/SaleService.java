package service;

import java.util.List;

import dao.SaleDao;
import entity.Medicines;
import entity.SaleRecord;

public class SaleService{
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
	//药物查询
	public List<Medicines> getAll(String medicine_id){
		return saleDao.getAll(medicine_id);
	}
	//销售录入
	public boolean addSales(SaleRecord saleRecord){
		return saleDao.addSales(saleRecord);
	}
}
