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
	//输出指定字段
	public PrintWriter out()throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();  
        response.setContentType("text/html");  
        response.setContentType("text/plain; charset=utf-8");
        PrintWriter out= response.getWriter();
        return out;
	}
	//根据选择日期查询该日期所有入库药品
	//查询入库记录
	public String show() throws IOException{
		out();
		//调用Service层代码
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
//		//根据所选择的日期查询当天入库的药品信息
//		List<Medicines> medicines = buyService.getMedicines(orderDate);
		//根据所选择的日期查询当天入库的子订单信息
		List<OrderItem> orderItem = buyService.getOrderItem(storageDate,supplierId);
		//将查出的数据返回前台
		JSONArray array=new JSONArray();
		for(OrderItem o:orderItem){
			 JSONObject jo=new JSONObject();
			 //字符型
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
	//查出所有供应商
	public String supplier() throws IOException{
		out();
		//调用Service层代码
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		List<Supplier> suppliers = buyService.getSuppliers();
		JSONArray array=new JSONArray();
		for(Supplier s:suppliers){
			 JSONObject jo=new JSONObject();
			 //字符型
			 jo.put("supplierId", s.getSupplierId());
			 jo.put("supplierName", s.getSupplierName());
			 array.add(jo);
		}
		out().print(array);
		out().flush(); 
		out().close();
		return SUCCESS;
	}
	//查出所有药品
	public String medicine() throws IOException{
		out();
		//调用Service层代码
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		List<Medicines> medicines = buyService.getAllMedicines();
		JSONArray array=new JSONArray();
		for(Medicines m:medicines){
			 JSONObject jo=new JSONObject();
			 //字符型
			 jo.put("medicineId", m.getMedicine_id());
			 jo.put("medicineName", m.getMedicineName());
			 array.add(jo);
		}
		out().print(array);
		out().flush(); 
		out().close();
		return SUCCESS;
	}
	//查出所有人员
	public String person() throws IOException{
		out();
		//调用Service层代码
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		List<Person> persons = buyService.getAllPersons();
		JSONArray array=new JSONArray();
		for(Person p:persons){
			 JSONObject jo=new JSONObject();
			 //字符型
			 jo.put("personId", p.getPerson_id());
			 jo.put("personName", p.getPerson_name());
			 array.add(jo);
		}
		out().print(array);
		out().flush(); 
		out().close();
		return SUCCESS;
	}
	//增加入库信息
	public String add() throws IOException{
		out();
		String message="";
		try{
			//调用Service层代码
			BuyService buyService=new BuyServiceImpl();
			BuyDao buyDao=new BuyDaoJDBCImpl();
			buyService.setBuyDao(buyDao);
			//添加订单记录
			Order order=new Order();
			order.setOrder_id(orderId);
			order.setSupplier_id(supplierId);
			order.setPerson_id(personId);
			Date date=new Date();
			order.setOrder_date(date);
			buyService.addOrder(order);
			//添加子订单记录
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder_item_id(orderItemId);
			orderItem.setNumber(number);
			orderItem.setOrder_id(orderId);
			orderItem.setMedicine_id(medicineId);
			orderItem.setOrder_item_price(orderItemPrice);
			buyService.addOrderItem(orderItem);
			//更新库存信息
			Medicines medicine=buyService.getMedicineByMedicineId(medicineId);
			int store=medicine.getStore()+number;
			medicine.setStore(store);	
			medicine.setStorageDate(storageDate);
			buyService.updateMedicine(medicine);
			message="登记药品信息成功！";
		}
		catch(Exception e){
			message="登记药品信息失败，请检查操作！";
		}
		
		out().print(message);
	    out().flush(); 
	    out().close();      
		return SUCCESS;
	}
	//修改入库信息
	public String update() throws IOException{
		out();
		String message="";
		try{
			//调用Service层代码
			BuyService buyService=new BuyServiceImpl();
			BuyDao buyDao=new BuyDaoJDBCImpl();
			buyService.setBuyDao(buyDao);
			//添加订单记录
			Order order=new Order();
			order.setOrder_id(orderId);
			order.setSupplier_id(supplierId);
			order.setPerson_id(personId);
			Date date=new Date();
			order.setOrder_date(date);
			buyService.addOrder(order);
			//添加子订单记录
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder_item_id(orderItemId);
			orderItem.setNumber(number);
			orderItem.setOrder_id(orderId);
			orderItem.setMedicine_id(medicineId);
			orderItem.setOrder_item_price(orderItemPrice);
			buyService.addOrderItem(orderItem);
			//更新库存信息
			Medicines medicine=buyService.getMedicineByMedicineId(medicineId);
			int store=medicine.getStore()+number;
			medicine.setStore(store);
			buyService.updateMedicine(medicine);
			message="登记药品信息成功！";
		}
		catch(Exception e){
			message="登记药品信息失败，请检查操作！";
		}
		
		out().print(message);
	    out().flush(); 
	    out().close();      
		return SUCCESS;
	}
	//删除药物信息
	public String delete() throws IOException{
		out();
		String message="";
		try{
			//调用Service层代码
			BuyService buyService=new BuyServiceImpl();
			BuyDao buyDao=new BuyDaoJDBCImpl();
			buyService.setBuyDao(buyDao);
			//添加订单记录
			Order order=new Order();
			order.setOrder_id(orderId);
			order.setSupplier_id(supplierId);
			order.setPerson_id(personId);
			Date date=new Date();
			order.setOrder_date(date);
			buyService.addOrder(order);
			//添加子订单记录
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder_item_id(orderItemId);
			orderItem.setNumber(number);
			orderItem.setOrder_id(orderId);
			orderItem.setMedicine_id(medicineId);
			orderItem.setOrder_item_price(orderItemPrice);
			buyService.addOrderItem(orderItem);
			//更新库存信息
			Medicines medicine=buyService.getMedicineByMedicineId(medicineId);
			int store=medicine.getStore()+number;
			medicine.setStore(store);
			buyService.updateMedicine(medicine);
			message="登记药品信息成功！";
		}
		catch(Exception e){
			message="登记药品信息失败，请检查操作！";
		}
		
		out().print(message);
	    out().flush(); 
	    out().close();      
		return SUCCESS;
	}
	
}
