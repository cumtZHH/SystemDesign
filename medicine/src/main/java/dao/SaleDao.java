package dao;

import java.util.List;

import entity.Medicines;
import entity.SaleRecord;
import entity.SaleRecordItem;

public interface SaleDao {
	//查询药品
   public List getAll(String medicine_id);
   //销售录入
   public boolean addSales(SaleRecord saleRecord);
   public boolean addSalesItem(SaleRecordItem saleRecordItem);
}
