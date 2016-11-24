package service.impl;

import java.util.Date;
import java.util.List;

import dao.BuyDao;
import entity.Medicines;
import service.BuyService;

public class BuyServiceImpl implements BuyService{
	private BuyDao buyDao;
	public void setBuyDao(BuyDao buyDao) {
		this.buyDao =buyDao;
	}
	public List<Medicines> getMedicines(String date) {
		return buyDao.getMedicines(date);
	}

}
