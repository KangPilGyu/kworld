package org.web.kworld.notice.vo;

import java.util.Date;

public class ReplyVO {

	private int r_no;
	private String r_content;
    private String r_writer;
    private int m_id;
    private int r_fav;
    private int n_no;
    private Date n_regdate;
    
    
	public Date getN_regdate() {
		return n_regdate;
	}
	public void setN_regdate(Date n_regdate) {
		this.n_regdate = n_regdate;
	}
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getR_writer() {
		return r_writer;
	}
	public void setR_writer(String r_writer) {
		this.r_writer = r_writer;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public int getR_fav() {
		return r_fav;
	}
	public void setR_fav(int r_fav) {
		this.r_fav = r_fav;
	}
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
    
    
}
