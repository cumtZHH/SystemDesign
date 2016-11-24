package action;

import java.util.Date;

import dao.BuyDao;
import dao.BuyDaoJDBCImpl;
import service.BuyService;
import service.impl.BuyServiceImpl;

public class BuyAction {
	//根据选择日期查询该日期所有入库药品
	public String show(){
		//调用Service层的代码
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		Date date=new Date();
		buyService.getMedicines(date.toString());
		return null;
	}
	public static void main(String[] args) {
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		Date date=new Date();
		buyService.getMedicines(date.toString());
	}
}
