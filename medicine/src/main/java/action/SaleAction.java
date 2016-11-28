package action;

import com.opensymphony.xwork2.ActionSupport;

import dao.SaleDaoImpl;
import service.SaleService;

public class SaleAction extends ActionSupport {
	private SaleService saleService=new SaleService();
	private String medicine_id;
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
	//查询药品信息
	@Override
	public String execute() throws Exception{
		saleService.getAll(medicine_id);
			return SUCCESS;
		}
	}


