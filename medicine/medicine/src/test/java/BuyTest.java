import java.util.Date;
import java.util.List;

import org.junit.Test;

import dao.BuyDao;
import dao.BuyDaoJDBCImpl;
import entity.Medicines;
import service.BuyService;
import service.impl.BuyServiceImpl;

public class BuyTest {
    @Test 
    public void testSomeLibraryMethod() {
    	BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		Date date=new Date();
		List<Medicines> list = buyService.getMedicines("2016-11-08");
		System.out.println(date);
		System.out.println(list);
    }
}
