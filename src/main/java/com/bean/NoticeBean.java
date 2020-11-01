package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_Notice")
public class NoticeBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notice_id;

	private String notice_title;
	private String notice_adduser;
	private String notice_addtime;

	public int getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_adduser() {
		return notice_adduser;
	}

	public void setNotice_adduser(String notice_adduser) {
		this.notice_adduser = notice_adduser;
	}

	public String getNotice_addtime() {
		return notice_addtime;
	}

	public void setNotice_addtime(String notice_addtime) {
		this.notice_addtime = notice_addtime;
	}

}
