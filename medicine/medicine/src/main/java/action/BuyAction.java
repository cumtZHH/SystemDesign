package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.BuyDao;
import dao.BuyDaoJDBCImpl;
import entity.Medicines;
import entity.Order;
import entity.Person;
import entity.Supplier;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.BuyService;
import service.impl.BuyServiceImpl;

public class BuyAction extends ActionSupport{
	private String orderId;
	private String orderDate;
	private String personId;
	private String supplierId;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	//���ָ���ֶ�
	public PrintWriter out()throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();  
        response.setContentType("text/html");  
        response.setContentType("text/plain; charset=utf-8");
        PrintWriter out= response.getWriter();
        return out;
	}
	//����ѡ�����ڲ�ѯ�������������ҩƷ
	public String show() throws IOException{
		out();
		//����Service�����
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		//������ѡ������ڲ�ѯ��������ҩƷ��Ϣ
		List<Medicines> medicines = buyService.getMedicines("2016-11-08");
		//����������ݷ���ǰ̨
		JSONArray array=new JSONArray();
		for(Medicines m:medicines){
			 JSONObject jo=new JSONObject();
			 //�ַ���
			 jo.put("id", m.getCategory_id());
			 array.add(jo);
		}
		String str="{\"rows\":"+array+"}";
		out().print(str);
		out().flush(); 
		out().close();
		return SUCCESS;
	}
	//������й�Ӧ��
	public String supplier() throws IOException{
		out();
		//����Service�����
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		List<Supplier> suppliers = buyService.getSuppliers();
		JSONArray array=new JSONArray();
		for(Supplier s:suppliers){
			 JSONObject jo=new JSONObject();
			 //�ַ���
			 jo.put("supplierId", s.getSupplierId());
			 jo.put("supplierName", s.getSupplierName());
			 array.add(jo);
		}
		out().print(array);
		out().flush(); 
		out().close();
		return SUCCESS;
	}
	//�������ҩƷ
	public String medicine() throws IOException{
		out();
		//����Service�����
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		List<Medicines> medicines = buyService.getAllMedicines();
		JSONArray array=new JSONArray();
		for(Medicines m:medicines){
			 JSONObject jo=new JSONObject();
			 //�ַ���
			 jo.put("medicineId", m.getMedicine_id());
			 jo.put("medicineName", m.getMedicineName());
			 array.add(jo);
		}
		out().print(array);
		out().flush(); 
		out().close();
		return SUCCESS;
	}
	//���������Ա
	public String person() throws IOException{
		out();
		//����Service�����
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		List<Person> persons = buyService.getAllPersons();
		JSONArray array=new JSONArray();
		for(Person p:persons){
			 JSONObject jo=new JSONObject();
			 //�ַ���
			 jo.put("personId", p.getPerson_id());
			 jo.put("personName", p.getPerson_name());
			 array.add(jo);
		}
		out().print(array);
		out().flush(); 
		out().close();
		return SUCCESS;
	}
	//���������Ϣ

	public String add() throws IOException{
		out();
		String message="";
		try{
			//����Service�����
			BuyService buyService=new BuyServiceImpl();
			BuyDao buyDao=new BuyDaoJDBCImpl();
			buyService.setBuyDao(buyDao);
			Order order=new Order();
			order.setOrder_id(orderId);
			buyService.addOrder(order);
			message="�Ǽ�ҩƷ��Ϣ�ɹ���";
		}
		catch(Exception e){
			message="�Ǽ�ҩƷ��Ϣʧ�ܣ����������";
		}
		
		out().print(message);
	    out().flush(); 
	    out().close();      
		return SUCCESS;
	}
	//�޸������Ϣ
	//ɾ�������Ϣ
	
}
