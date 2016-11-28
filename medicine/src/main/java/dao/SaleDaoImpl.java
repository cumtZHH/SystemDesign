package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dbUtil.DbUtil;
import entity.Medicines;
import entity.SaleRecord;
import entity.SaleRecordItem;

public class SaleDaoImpl implements SaleDao{
	@Override
	//查询药品
	public List getAll(String medicine_id){
		ResultSet rs=DbUtil.executeQuery("select * from medicine where medicine_id=?", new Object[]{medicine_id});
		List list=new ArrayList();
		try{
			while(rs.next()){
			    Medicines medicines=new Medicines();
				medicines.setMedicine_id(rs.getString(0));
				medicines.setMedicineName(rs.getString(1));
				medicines.setPrice(rs.getDouble(2));
				medicines.setStore(rs.getInt(3));
				list.add(medicines);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	//销售录入
	public boolean addSales(SaleRecord saleRecord){
			// TODO Auto-generated method stub
			Date day=new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return DbUtil.executeUpdate("insert into sale_record values(?,?,?,?)",
					new Object[]{saleRecord.getSaleRecord_id(),saleRecord.getSaleRecordPrice(),df.format(day),/*录入人员id*/});		
	}
	public boolean addSalesItem(SaleRecordItem saleRecordItem){
		// TODO Auto-generated method stub
		SaleRecord saleRecord=new SaleRecord();
		Medicines medicines=new Medicines();
		return DbUtil.executeUpdate("insert into sale_record values(?,?,?,?)",
				new Object[]{saleRecordItem.getSaleRecordItem_id(),saleRecordItem.getSaleRecordItemNumber(),saleRecord.getSaleRecord_id(),medicines.medicine_id});		
}
}