package com.bean;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cj")
public class CJBean {
	@Id
	@EmbeddedId
	private CJID cjid;
	private BigDecimal stu_score;

	public CJID getCjid() {
		return cjid;
	}

	public void setCjid(CJID cjid) {
		this.cjid = cjid;
	}

	public BigDecimal getStu_score() {
		return stu_score;
	}

	public void setStu_score(BigDecimal stu_score) {
		this.stu_score = stu_score;
	}

}
