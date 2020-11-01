package com.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.bean.UserBean;
import com.util.JpaUtil;

public class TestJPA {
	public static void main(String[] args) {
		//TestJPA.insert();
		//TestJPA.query_one();
		//TestJPA.delete();
		//TestJPA.update();

		TestJPA.query_all();
		System.out.println("two");
		System.out.println("three");
	}

	private static void insert() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			//
			UserBean userBean = new UserBean();
			userBean.setUserid(30);
			userBean.setUsername("jpa");
			userBean.setPassword("123");
			userBean.setTruename("JPA的增加");
			userBean.setAddress("地址");
			userBean.setRemark("备注");
			userBean.setUserage("20");

			manager.persist(userBean);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			JpaUtil.closeManager();
			System.exit(0);
		}
	}

	//使用find,getReference

	//1、只能使用主键来加载相应的对象，所以参数必须是主键的值。

	//2、主键参数的类型，必须与Bean中属性的类型一致。(Bean是int类型，那必须传入Interger类型)
	private static void query_one() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			//
			UserBean userBean = manager.find(UserBean.class, new Integer(5));

			System.out.println(userBean);

			userBean = manager.getReference(UserBean.class, new Integer(7));
			System.out.println("userBean = " + userBean);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			JpaUtil.closeManager();
			System.exit(0);
		}
	}

	private static void delete() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			//
			UserBean userBean = manager.find(UserBean.class, new Integer(5));

			if (userBean != null) {
				manager.remove(userBean);
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

	private static void update() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			//
			UserBean userBean = manager.find(UserBean.class, new Integer(30));

			if (userBean != null) {
				userBean.setUsersex("1");
				userBean.setSalary(new BigDecimal("4567"));

				manager.merge(userBean);
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

	private static void query_all() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		String jpql = "";
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			//1、最简单的写法,类名有区分大小写。关键字不区分大小写。
			jpql = "from UserBean";
			Query query = manager.createQuery(jpql);
			List<UserBean> userList = query.getResultList();
			for (UserBean userBean : userList) {
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
