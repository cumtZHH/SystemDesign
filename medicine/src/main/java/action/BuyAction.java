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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.BuyService;
import service.impl.BuyServiceImpl;

public class BuyAction extends ActionSupport{
	private String data;
	//����ѡ�����ڲ�ѯ�������������ҩƷ
	//���ָ���ֶ�
	public PrintWriter out()throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();  
        response.setContentType("text/html");  
        response.setContentType("text/plain; charset=utf-8");
        PrintWriter out= response.getWriter();
        return out;
	}
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
