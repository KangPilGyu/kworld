package org.web.kworld.login.vo;

import java.util.Date;

public class MemberVO {
	private int m_id; 
	private String m_name; 
	private String m_email; 
	private String m_type; 
	private String m_token; 
	private Date m_regdate; 
	private String m_mainaddr; 
	private String m_wuaddr; 
	private String m_detailaddr; 
	private String m_emailagree;
	private String m_pwd;
	private String m_auth;
	private String m_authnum;
	
	
	
	
	
	public String getM_authnum() {
		return m_authnum;
	}
	public void setM_authnum(String m_authnum) {
		this.m_authnum = m_authnum;
	}
	public String getM_auth() {
		return m_auth;
	}
	public void setM_auth(String m_auth) {
		this.m_auth = m_auth;
	}
	public String getM_pwd() {
		return m_pwd;
	}
	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_type() {
		return m_type;
	}
	public void setM_type(String m_type) {
		this.m_type = m_type;
	}
	public String getM_token() {
		return m_token;
	}
	public void setM_token(String m_token) {
		this.m_token = m_token;
	}
	public Date getM_regdate() {
		return m_regdate;
	}
	public void setM_regdate(Date m_regdate) {
		this.m_regdate = m_regdate;
	}
	public String getM_mainaddr() {
		return m_mainaddr;
	}
	public void setM_mainaddr(String m_mainaddr) {
		this.m_mainaddr = m_mainaddr;
	}
	public String getM_wuaddr() {
		return m_wuaddr;
	}
	public void setM_wuaddr(String m_wuaddr) {
		this.m_wuaddr = m_wuaddr;
	}
	public String getM_detailaddr() {
		return m_detailaddr;
	}
	public void setM_detailaddr(String m_detailaddr) {
		this.m_detailaddr = m_detailaddr;
	}
	public String getM_emailagree() {
		return m_emailagree;
	}
	public void setM_emailagree(String m_emailagree) {
		this.m_emailagree = m_emailagree;
	}
	
	
	
	
}
