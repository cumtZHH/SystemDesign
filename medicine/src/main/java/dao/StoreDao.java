package dao;

import java.util.List;

import entity.Medicines;

public interface StoreDao {
	//�޸Ŀ������
	public boolean update(Medicines oldStore,Medicines newStore);
	//ɾ��ҩ����Ϣ
	public boolean delete(String medicine_id);
	//�鿴���п����Ϣ
	public List<Medicines> getMedicines();
	//�鿴ĳ�ֿ����Ϣ
	public List<Medicines> getMedicines(String medicine_id);
	//Ԥ��
	public List<Medicines> getYj();
}
