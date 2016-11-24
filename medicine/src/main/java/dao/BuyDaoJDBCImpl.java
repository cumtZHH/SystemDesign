package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dbUtil.DbUtil;
import entity.Medicines;

public class BuyDaoJDBCImpl implements BuyDao{
	//根据日期查看当天所有入库药物
	@Override
	public List<Medicines> getMedicines(String date) {
		ResultSet rs=DbUtil.executeQuery("select * from medicine where storage_date=?", new Object[]{date});
		List<Medicines> medicines=new ArrayList<Medicines>();
		try{
			while(rs.next()){
				Medicines medicine = new Medicines();
				medicine.setMedicine_id(rs.getString(1));
				medicine.setMedicineName(rs.getString(2));
				medicine.setPrice(rs.getDouble(3));
				medicine.setStore(rs.getInt(4));
				medicine.setCategory_id(rs.getInt(5));
				medicine.setFinance_report_id(rs.getString(6));
				medicine.setMin_store(rs.getInt(7));
				medicines.add(medicine);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return medicines;
	}
}
