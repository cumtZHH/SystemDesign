package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DbUtil;
import entity.Medicines;

public class StoreDaoJDBCImpl implements StoreDao{
	//修改库存数量
	@Override
	public boolean update(Medicines oldStore,Medicines newStore) {
		// TODO Auto-generated method stub
		boolean rs=DbUtil.executeUpdate(
				"update medicine set store=? where medicine_id=?",
				new Object[]{newStore.getStore(),oldStore.getStore()});

		Medicines medicine=null;
		try{
			medicine=new Medicines();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	//删除药物
	@Override
	public boolean delete(String medicine_id) {
		// TODO Auto-generated method stub
		return DbUtil.executeUpdate("delete from medicine where medicine_id = ?", new Object[]{medicine_id});
	}
	//查看所有药物库存
	@Override
	public List<Medicines> getMedicines() {
		// TODO Auto-generated method stub
		return null;
	}
	//查看某种药物库存
	@Override
	public List<Medicines> getMedicines(String medicine_id) {
		// TODO Auto-generated method stub
		return null;
	}
	//查看所有预警的药物信息
	@Override
	public List<Medicines> getYj() {
		// TODO Auto-generated method stub
		ResultSet rs=DbUtil.executeQuery("select * from medicine", new Object[]{});
		List<Medicines> medicinesList=new ArrayList<Medicines>();
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
				medicinesList.add(medicine);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return medicinesList;
	}

}
