package com.igo.board.model.cho.dto.suggestion;

public class Sg_goodbadDTO {

	private int sg_gb_idx;
	private int sg_gb_refidx;
	private String sg_gb_ip;
	private String sg_gb_feel;
	
	public int getSg_gb_idx() {
		return sg_gb_idx;
	}
	public void setSg_gb_idx(int sg_gb_idx) {
		this.sg_gb_idx = sg_gb_idx;
	}
	public int getSg_gb_refidx() {
		return sg_gb_refidx;
	}
	public void setSg_gb_refidx(int sg_gb_refidx) {
		this.sg_gb_refidx = sg_gb_refidx;
	}
	public String getSg_gb_ip() {
		return sg_gb_ip;
	}
	public void setSg_gb_ip(String sg_gb_ip) {
		this.sg_gb_ip = sg_gb_ip;
	}
	public String getSg_gb_feel() {
		return sg_gb_feel;
	}
	public void setSg_gb_feel(String sg_gb_feel) {
		this.sg_gb_feel = sg_gb_feel;
	}
	@Override
	public String toString() {
		return "Sg_goodbadDTO [sg_gb_idx=" + sg_gb_idx + ", sg_gb_refidx=" + sg_gb_refidx + ", sg_gb_ip=" + sg_gb_ip
				+ ", sg_gb_feel=" + sg_gb_feel + "]";
	}
	
}
