package service;

import java.util.Date;
import java.util.List;

import dao.BuyDao;
import entity.Medicines;

public interface BuyService {
	public void setBuyDao(BuyDao buyDao);
	public List<Medicines> getMedicines(String date);
}
