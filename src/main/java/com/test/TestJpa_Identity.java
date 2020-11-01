package com.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.bean.NoticeBean;
import com.util.JpaUtil;

public class TestJpa_Identity {
	public static void main(String[] args) {
		TestJpa_Identity.save();
	}

	private static void save() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		String jpql = "";
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();

			NoticeBean noticeBean = new NoticeBean();
			//ID设置为自增的策略之后，这边保存时就不能再进行赋值。
			//noticeBean.setNotice_id(1);
			noticeBean.setNotice_title("公告标题");
			noticeBean.setNotice_adduser("添加人");
			noticeBean.setNotice_addtime("2020-10-08");

			manager.persist(noticeBean);

			System.out.println("保存后自增的ID号= " + noticeBean.getNotice_id());

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
