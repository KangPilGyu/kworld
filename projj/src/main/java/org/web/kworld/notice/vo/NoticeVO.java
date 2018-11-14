package org.web.kworld.notice.vo;

import java.util.Date;

public class NoticeVO {
	private int n_no;
    private String n_title;
    private String n_writer;
    private Date n_regdate;
    private int n_cnt;
    private String n_file;
    private String n_content;
    private int n_fav;
    private int m_id;
    
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_writer() {
		return n_writer;
	}
	public void setN_writer(String n_writer) {
		this.n_writer = n_writer;
	}
	public Date getN_regdate() {
		return n_regdate;
	}
	public void setN_regdate(Date n_regdate) {
		this.n_regdate = n_regdate;
	}
	public int getN_cnt() {
		return n_cnt;
	}
	public void setN_cnt(int n_cnt) {
		this.n_cnt = n_cnt;
	}
	public String getN_file() {
		return n_file;
	}
	public void setN_file(String n_file) {
		this.n_file = n_file;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public int getN_fav() {
		return n_fav;
	}
	public void setN_fav(int n_fav) {
		this.n_fav = n_fav;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
    
    
}
