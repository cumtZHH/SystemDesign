package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.SaleDaoImpl;
import entity.SaleList;
import entity.SaleRecord;
import entity.SaleRecordItem;
import service.SaleService;

public class SaleAction extends ActionSupport {
	private SaleService saleService=new SaleService();
	private String medicine_id;
	private Double saleRecordTotalPrice;//总价 
	private String medicineName;
	private double price;
	private int saleRecordNumber;
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSaleRecordNumber() {
		return saleRecordNumber;
	}
	public void setSaleRecordNumber(int saleRecordNumber) {
		this.saleRecordNumber = saleRecordNumber;
	}
	
	public Double getSaleRecordTotalPrice() {
		return saleRecordTotalPrice;
	}
	public void setSaleRecordTotalPrice(Double saleRecordTotalPrice) {
		this.saleRecordTotalPrice = saleRecordTotalPrice;
	}
	public SaleService getSaleService() {
		return saleService;
	}
	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	public String getMedicine_id() {
		return medicine_id;
	}
	public void setMedicine_id(String medicine_id) {
		this.medicine_id = medicine_id;
	}
	HttpServletRequest request= ServletActionContext.getRequest();
	HttpSession session=request.getSession();
	//查询药品信息
	@Override
	public String execute() throws Exception{
		SaleDaoImpl saleDaoImpl=new SaleDaoImpl();
		saleService.setSaleDao(saleDaoImpl);
		request.setAttribute("mdinformation",saleService.getAll(medicine_id));
		//System.out.println(saleService.getAll(medicine_id));
			return SUCCESS;
		}
	//插入销售记录
	public String addSaleRecord() throws Exception{
		SaleDaoImpl saleDaoImpl=new SaleDaoImpl();
		saleService.setSaleDao(saleDaoImpl);
		int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//一个13位的时间戳
		SaleRecord saleRecord=new SaleRecord();
		saleRecord.setSaleRecord_id(String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now));//生成随机id
		saleRecord.setSaleRecordTotalPrice(saleRecordTotalPrice);
	    saleService.addSales(saleRecord);
	  //插入详细销售记录
	    List salelist= (List)session.getAttribute("sale");
	    SaleRecordItem saleRecordItem=new SaleRecordItem();
		for (int t = 0; t < salelist.size(); t++) {
	          SaleList sale = (SaleList)salelist.get(t);
	          saleRecordItem.setSaleRecordItem_id(saleRecord.getSaleRecord_id()+t);
	          saleRecordItem.setSaleRecordItemNumber(sale.getSaleRecordNumber());
	          saleRecordItem.setSaleRecord_id(saleRecord.getSaleRecord_id());
	          saleRecordItem.setMedicine_id(sale.getMedicine_id());
		}
		saleService.addSalesItem(saleRecordItem);
	    return SUCCESS;
		}
	//销售详细记录加入session
		public String addSession() throws Exception{
			SaleDaoImpl saleDaoImpl=new SaleDaoImpl();
			saleService.setSaleDao(saleDaoImpl);
			List<SaleList> salelist=new ArrayList();
			SaleList slist=new SaleList();
			slist.setMedicine_id(medicine_id);
			slist.setMedicineName(medicineName);
			slist.setPrice(price);
			slist.setSaleRecordNumber(saleRecordNumber);
			slist.setSubTotal(price*saleRecordNumber);
			if(session.getAttribute("sale")==null){
				salelist=new ArrayList();
			}else{
			  salelist=(List)session.getAttribute("sale");
			}
			salelist.add(slist);
			double priceTotal=0;
			for(int i=0;i<salelist.size();i++){
				priceTotal=priceTotal+salelist.get(i).getSubTotal();
			}
			session.setAttribute("ptotal", priceTotal);
			session.setAttribute("sale",salelist);
			return SUCCESS;
	}
}

