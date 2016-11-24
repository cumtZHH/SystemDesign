package dao;

import java.util.Date;
import java.util.List;

import entity.Medicines;

public interface BuyDao {
	//根据日期查看当天所有入库药物
	public List<Medicines> getMedicines(String date);
}
