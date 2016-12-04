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
	public List<Medicines> getAll(String medicine_id){
		ResultSet rs=DbUtil.executeQuery("select * from medicines where medicine_id=?", new Object[]{medicine_id});
		List<Medicines> list=new ArrayList();
		try{
			while(rs.next()){
			    Medicines medicines=new Medicines();
				medicines.setMedicine_id(rs.getString(1));
				medicines.setMedicineName(rs.getString(2));
				medicines.setPrice(rs.getDouble(3));
				medicines.setStore(rs.getInt(4));
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
					new Object[]{saleRecord.getSaleRecord_id(),saleRecord.getSaleRecordTotalPrice(),df.format(day),"00001"});		
	}
	public boolean addSalesItem(SaleRecordItem saleRecordItem){
		// TODO Auto-generated method stub
		Medicines medicines=new Medicines();
		return DbUtil.executeUpdate("insert into sale_record_item values(?,?,?,?)",
				new Object[]{saleRecordItem.getSaleRecordItem_id(),saleRecordItem.getSaleRecordItemNumber(),saleRecordItem.getSaleRecord_id(),saleRecordItem.getMedicine_id()});		
}
}