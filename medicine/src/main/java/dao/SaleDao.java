package dao;

import java.util.List;

import entity.Medicines;
import entity.SaleRecord;
import entity.SaleRecordItem;

public interface SaleDao {
	//��ѯҩƷ
   public List<Medicines> getAll(String medicine_id);
   //����¼��
   public boolean addSales(SaleRecord saleRecord);
   public boolean addSalesItem(SaleRecordItem saleRecordItem);
}
