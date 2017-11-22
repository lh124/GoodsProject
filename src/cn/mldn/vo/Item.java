package cn.mldn.vo;

public class Item {
//	iid			BIGINT AUTO_INCREMENT ,
//	title		VARCHAR(50) ,
	
	private Long iid;
	private String title;
	public Long getIid() {
		return iid;
	}
	public void setIid(Long iid) {
		this.iid = iid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
