package dao;

import java.util.Date;
import java.util.List;

import entity.Medicines;

public interface BuyDao {
	//�������ڲ鿴�����������ҩ��
	public List<Medicines> getMedicines(String date);
}
