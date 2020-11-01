package com.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.bean.UserBean;
import com.util.JpaUtil;

public class TestJpa_SQL {
	public static void main(String[] args) {
		//正常情况下，一般都是使用jpql语句，只有jpql查询不了的情况，才会使用SQL语句。
		TestJpa_SQL.one();
		//TestJpa_SQL.two();
		System.out.println("one");
		System.out.println("two");
		System.out.println("three");
		System.out.println("four");
		System.out.println("Five");
	}

	private static void one() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		String sql = "";
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			//update语句
			sql = "Select * From T_Userinfo order by userid asc";

			Query query = manager.createNativeQuery(sql);
			List dataList = query.getResultList();
			for (int i = 0; i < dataList.size(); i++) {
				Object[] objArray = (Object[]) dataList.get(i);

				int userid = Integer.parseInt(String.valueOf(objArray[0]));
				String username = String.valueOf(objArray[1]);

				System.out.println(userid + "\t" + username);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			JpaUtil.closeManager();
			System.exit(0);
		}
	}

	private static void two() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		String sql = "";
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();

			sql = "Select * From T_Userinfo order by userid asc";
			//将查询的结果进行Class的转换。Bean中的属性要与字段的名称一致。
			//仅限单表。如果是多表关联，那么Class中也必须要有两表的所有字段。
			Query query = manager.createNativeQuery(sql, UserBean.class);
			List dataList = query.getResultList();
			for (int i = 0; i < dataList.size(); i++) {
				UserBean userBean = (UserBean) dataList.get(i);

				System.out.println(userBean);
			}
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
