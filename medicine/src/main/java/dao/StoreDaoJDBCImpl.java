package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DbUtil;
import entity.Medicines;

public class StoreDaoJDBCImpl implements StoreDao{
	//�޸Ŀ������
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
	//ɾ��ҩ��
	@Override
	public boolean delete(String medicine_id) {
		// TODO Auto-generated method stub
		return DbUtil.executeUpdate("delete from medicine where medicine_id = ?", new Object[]{medicine_id});
	}
	//�鿴����ҩ����
	@Override
	public List<Medicines> getMedicines() {
		// TODO Auto-generated method stub
		return null;
	}
	//�鿴ĳ��ҩ����
	@Override
	public List<Medicines> getMedicines(String medicine_id) {
		// TODO Auto-generated method stub
		return null;
	}
	//�鿴����Ԥ����ҩ����Ϣ
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
