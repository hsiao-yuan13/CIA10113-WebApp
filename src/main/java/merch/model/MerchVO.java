package merch.model;
import java.sql.Date;

public class MerchVO implements java.io.Serializable{
	private Integer merchID;
	private String merchName;
	private byte[] merchImg;
	private String merchInfo;
	private Integer merchPrice;
	private String merchStatus;
	
	public Integer getMerchID() {
		return merchID;
	}
	public void setMerchID(Integer merchID) {
		this.merchID = merchID;
	}
	public String getMerchName() {
		return merchName;
	}
	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}
	public byte[] getMerchImg() {
		return merchImg;
	}
	public void setMerchImg(byte[] merchImg) {
		this.merchImg = merchImg;
	}
	public String getMerchInfo() {
		return merchInfo;
	}
	public void setMerchInfo(String merchInfo) {
		this.merchInfo = merchInfo;
	}
	public Integer getMerchPrice() {
		return merchPrice;
	}
	public void setMerchPrice(Integer merchPrice) {
		this.merchPrice = merchPrice;
	}
	public String getMerchStatus() {
		return merchStatus;
	}
	public void setMerchStatus(String merchStatus) {
		this.merchStatus = merchStatus;
	}

	
	
}