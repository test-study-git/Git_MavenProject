package com.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CJID implements Serializable {
	@Column(name = "stu_name")
	private String stu_name;
	@Column(name = "stu_subject")
	private String stu_subject;

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getStu_subject() {
		return stu_subject;
	}

	public void setStu_subject(String stu_subject) {
		this.stu_subject = stu_subject;
	}

}
