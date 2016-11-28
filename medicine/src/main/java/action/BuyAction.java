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
	//根据选择日期查询该日期所有入库药品
	//输出指定字段
	public PrintWriter out()throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();  
        response.setContentType("text/html");  
        response.setContentType("text/plain; charset=utf-8");
        PrintWriter out= response.getWriter();
        return out;
	}
	public String show() throws IOException{
		out();
		//调用Service层代码
		BuyService buyService=new BuyServiceImpl();
		BuyDao buyDao=new BuyDaoJDBCImpl();
		buyService.setBuyDao(buyDao);
		//根据所选择的日期查询当天入库的药品信息
		List<Medicines> medicines = buyService.getMedicines("2016-11-08");
		//将查出的数据返回前台
		JSONArray array=new JSONArray();
		for(Medicines m:medicines){
			 JSONObject jo=new JSONObject();
			 //字符型
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
