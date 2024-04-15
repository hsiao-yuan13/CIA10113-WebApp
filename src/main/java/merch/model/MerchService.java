package merch.model;

import java.util.List;

import merch.model.MerchVO;

public class MerchService {
	private MerchDAO_interface dao;

	public MerchService() {
		dao = new MerchJDBCDAO();
	}

	//新增
	public MerchVO addMerch(String merchName, byte[] merchImg, String merchInfo,
			Integer merchPrice, String merchStatus) {

		MerchVO merchVO = new MerchVO();

		merchVO.setMerchName(merchName);
		merchVO.setMerchImg(merchImg);
		merchVO.setMerchInfo(merchInfo);
		merchVO.setMerchPrice(merchPrice);
		merchVO.setMerchStatus(merchStatus);
		
		dao.insert(merchVO);
		return merchVO;
	}
	//修改
	public MerchVO updateMerch(Integer merchID, String merchName, byte[] merchImg, String merchInfo,
			Integer merchPrice, String merchStatus) {

		MerchVO merchVO = new MerchVO();

		merchVO.setMerchID(merchID);
		merchVO.setMerchName(merchName);
		merchVO.setMerchImg(merchImg);
		merchVO.setMerchInfo(merchInfo);
		merchVO.setMerchPrice(merchPrice);
		merchVO.setMerchStatus(merchStatus);
		dao.update(merchVO);

		return merchVO;
	}


	//查詢商品編號
	public MerchVO findByPrimaryKey(Integer merchID) {
		return dao.findByPrimaryKey(merchID);
	}
	//查詢商品名稱
	public MerchVO findByName(String merchName) {
		return dao.findByName(merchName);
	}
	//修改單一商品
	public MerchVO getOneMerch(Integer merchID) {
		return dao.findByPrimaryKey(merchID);
	}
	//查詢全部
	public List<MerchVO> getAll() {
		return dao.getAll();
	}
}
