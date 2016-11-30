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
	private Double saleRecordPrice; 
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
	public Double getSaleRecordPrice() {
		return saleRecordPrice;
	}
	public void setSaleRecordPrice(Double saleRecordPrice) {
		this.saleRecordPrice = saleRecordPrice;
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
	//��ѯҩƷ��Ϣ
	@Override
	public String execute() throws Exception{
		SaleDaoImpl saleDaoImpl=new SaleDaoImpl();
		saleService.setSaleDao(saleDaoImpl);
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		request.setAttribute("mdinformation",saleService.getAll(medicine_id));
		//System.out.println(saleService.getAll(medicine_id));
			return SUCCESS;
		}
	//�������ۼ�¼
	public String addSaleRecord() throws Exception{
		SaleDaoImpl saleDaoImpl=new SaleDaoImpl();
		saleService.setSaleDao(saleDaoImpl);
		int r1=(int)(Math.random()*(10));//����2��0-9�������
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//һ��13λ��ʱ���
		SaleRecord saleRecord=new SaleRecord();
		saleRecord.setSaleRecord_id(String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now));//�������id
		saleRecord.setSaleRecordPrice(saleRecordPrice);
	    saleService.addSales(saleRecord);
	  //������ϸ���ۼ�¼
	    HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		List saleList= (List) session.getAttribute("sale");
		for (int t = 0; t < saleList.size(); t++) {
	          SaleList sale = (SaleList)saleList.get(t);
	          SaleRecordItem saleRecordItem=new SaleRecordItem();
	          saleRecordItem.setSaleRecordItem_id(String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now)+t);
	          saleRecordItem.setSaleRecordItemNumber(sale.getSaleRecordNumber());
	          saleRecordItem.setSaleRecord_id(String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now));
	          saleRecordItem.setMedicine_id(sale.getMedicine_id());
		}
	    return SUCCESS;
		}
	//������ϸ��¼����session
		public String addSession() throws Exception{
			SaleDaoImpl saleDaoImpl=new SaleDaoImpl();
			saleService.setSaleDao(saleDaoImpl);
			List<SaleList> salelist=new ArrayList();
			SaleList slist=new SaleList();
			slist.setMedicine_id(medicine_id);
			slist.setMedicineName(medicineName);
			slist.setPrice(price);
			slist.setSaleRecordNumber(saleRecordNumber);
			HttpServletRequest request= ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			salelist.add(slist);
			session.setAttribute("sale",salelist);
			return SUCCESS;
	}
}

