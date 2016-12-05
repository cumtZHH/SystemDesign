package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.BuyDao;
import dao.BuyDaoJDBCImpl;
import entity.Medicines;
import entity.Order;
import entity.OrderItem;
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
	private String orderItemId;
	private int number;
	private Double orderItemPrice;
	private String medicineId;
	private String storageDate;
	public String getStorageDate() {
		return storageDate;
	}
	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}
	public String getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Double getOrderItemPrice() {
		return orderItemPrice;
	}
	public void setOrderItemPrice(Double orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}
	public String getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}
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
	//��ѯ����¼
	public String show() throws IOException{
		out();
		//����Service�����
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
//		//������ѡ������ڲ�ѯ��������ҩƷ��Ϣ
//		List<Medicines> medicines = buyService.getMedicines(orderDate);
		//������ѡ������ڲ�ѯ���������Ӷ�����Ϣ
		List<OrderItem> orderItem = buyService.getOrderItem(storageDate,supplierId);
		//����������ݷ���ǰ̨
		JSONArray array=new JSONArray();
		for(OrderItem o:orderItem){
			 JSONObject jo=new JSONObject();
			 //�ַ���
			 jo.put("id", o.getOrder_item_id());
			 jo.put("number", o.getNumber());
			 jo.put("orderItemPrice", o.getOrder_item_price());
			 jo.put("orderId", o.getOrder_id());
			 Order order= buyService.getOrderByOrderId(orderId);
			 jo.put("personId",order.getPerson_id());
			 Medicines medicine=buyService.getMedicineByMedicineId(o.getMedicine_id());
			 jo.put("medicineName",medicine.getMedicineName());
			 jo.put("medicinePrice",medicine.getPrice());
			 jo.put("categoryId",medicine.getCategory_id());
			 jo.put("store",medicine.getStore());
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
			//��Ӷ�����¼
			Order order=new Order();
			order.setOrder_id(orderId);
			order.setSupplier_id(supplierId);
			order.setPerson_id(personId);
			Date date=new Date();
			order.setOrder_date(date);
			buyService.addOrder(order);
			//����Ӷ�����¼
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder_item_id(orderItemId);
			orderItem.setNumber(number);
			orderItem.setOrder_id(orderId);
			orderItem.setMedicine_id(medicineId);
			orderItem.setOrder_item_price(orderItemPrice);
			buyService.addOrderItem(orderItem);
			//���¿����Ϣ
			Medicines medicine=buyService.getMedicineByMedicineId(medicineId);
			int store=medicine.getStore()+number;
			medicine.setStore(store);	
			medicine.setStorageDate(storageDate);
			buyService.updateMedicine(medicine);
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
	public String update() throws IOException{
		out();
		String message="";
		try{
			//����Service�����
			BuyService buyService=new BuyServiceImpl();
			BuyDao buyDao=new BuyDaoJDBCImpl();
			buyService.setBuyDao(buyDao);
			//��Ӷ�����¼
			Order order=new Order();
			order.setOrder_id(orderId);
			order.setSupplier_id(supplierId);
			order.setPerson_id(personId);
			Date date=new Date();
			order.setOrder_date(date);
			buyService.addOrder(order);
			//����Ӷ�����¼
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder_item_id(orderItemId);
			orderItem.setNumber(number);
			orderItem.setOrder_id(orderId);
			orderItem.setMedicine_id(medicineId);
			orderItem.setOrder_item_price(orderItemPrice);
			buyService.addOrderItem(orderItem);
			//���¿����Ϣ
			Medicines medicine=buyService.getMedicineByMedicineId(medicineId);
			int store=medicine.getStore()+number;
			medicine.setStore(store);
			buyService.updateMedicine(medicine);
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
	//ɾ��ҩ����Ϣ
	public String delete() throws IOException{
		out();
		String message="";
		try{
			//����Service�����
			BuyService buyService=new BuyServiceImpl();
			BuyDao buyDao=new BuyDaoJDBCImpl();
			buyService.setBuyDao(buyDao);
			//��Ӷ�����¼
			Order order=new Order();
			order.setOrder_id(orderId);
			order.setSupplier_id(supplierId);
			order.setPerson_id(personId);
			Date date=new Date();
			order.setOrder_date(date);
			buyService.addOrder(order);
			//����Ӷ�����¼
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder_item_id(orderItemId);
			orderItem.setNumber(number);
			orderItem.setOrder_id(orderId);
			orderItem.setMedicine_id(medicineId);
			orderItem.setOrder_item_price(orderItemPrice);
			buyService.addOrderItem(orderItem);
			//���¿����Ϣ
			Medicines medicine=buyService.getMedicineByMedicineId(medicineId);
			int store=medicine.getStore()+number;
			medicine.setStore(store);
			buyService.updateMedicine(medicine);
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
	
}
