package action;

import java.util.Date;

import dao.BuyDao;
import dao.BuyDaoJDBCImpl;
import service.BuyService;
import service.impl.BuyServiceImpl;

public class BuyAction {
	//����ѡ�����ڲ�ѯ�������������ҩƷ
	public String show(){
		//����Service��Ĵ���
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
