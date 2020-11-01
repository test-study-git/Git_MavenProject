package com.test;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.bean.CJBean;
import com.bean.CJID;
import com.util.JpaUtil;

public class TestJpa_PrimaryID {
	public static void main(String[] args) {
		//TestJpa_PrimaryID.save();
		TestJpa_PrimaryID.query();
	}

	private static void save() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();

			CJID cjid = new CJID();
			cjid.setStu_name("张三");
			cjid.setStu_subject("数学");

			CJBean cj_bean = new CJBean();
			cj_bean.setCjid(cjid);
			cj_bean.setStu_score(new BigDecimal("78"));

			manager.persist(cj_bean);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			JpaUtil.closeManager();
			System.exit(0);
		}
	}

	private static void query() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();

			CJID cjid = new CJID();
			cjid.setStu_name("张三");
			cjid.setStu_subject("语文");

			CJBean cj_bean = manager.find(CJBean.class, cjid);

			System.out.println("stu_name = " + cj_bean.getCjid().getStu_name());
			System.out.println("stu_subject = " + cj_bean.getCjid().getStu_subject());

			System.out.println("stu_score = " + cj_bean.getStu_score());
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			JpaUtil.closeManager();
			System.exit(0);
		}
	}

}
