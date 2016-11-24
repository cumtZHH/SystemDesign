package dao;

import java.util.List;

import entity.Medicines;

public interface StoreDao {
	//修改库存数量
	public boolean update(Medicines oldStore,Medicines newStore);
	//删除药物信息
	public boolean delete(String medicine_id);
	//查看所有库存信息
	public List<Medicines> getMedicines();
	//查看某种库存信息
	public List<Medicines> getMedicines(String medicine_id);
	//预警
	public List<Medicines> getYj();
}
