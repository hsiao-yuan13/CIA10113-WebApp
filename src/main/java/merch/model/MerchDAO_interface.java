package merch.model;

import java.util.*;

public interface MerchDAO_interface {
	//新增
	public void insert(MerchVO merchVO);
	//修改
    public void update(MerchVO merchVO);
    //查詢周邊商品編號
    public MerchVO findByPrimaryKey(Integer merchID);
    //查詢周邊商品名稱
    public MerchVO findByName(String merchName);
    //查詢全部
    public List<MerchVO> getAll();
}
