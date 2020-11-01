package com.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.bean.UserBean;
import com.util.JpaUtil;

public class TestJpa_Query {
	public static void main(String[] args) {
		//TestJpa_Query.basicQuery();
		//TestJpa_Query.prepareQuery();
		//TestJpa_Query.singleResult();
		//TestJpa_Query.pageSQL();

		TestJpa_Query.dmlSQL();
		System.out.println("three");
	}

	//常见的查询操作
	private static void basicQuery() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		String jpql = "";
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			//1、最简单的写法,类名有区分大小写。关键字不区分大小写,类中的属性也不区分大小写。
			//jpql = "from UserBean";
			//2、标准的写法
			//jpql = "Select u From UserBean u";

			//3、排序的写法
			//jpql = "Select u From UserBean u order by userid desc";

			//4、加入查询条件
			jpql = "Select u From UserBean u where username like '%a%' and truename like '%张%' order by userid asc";
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

	private static void prepareQuery() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		String jpql = "";
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			//1、预编译的写法。
			//参考Spring中的操作：问号的写法，命名参数的写法

			//jpa比Spring少的功能。在Spring中可以直接将一个Bean或者一个Map设置到参数。而jpa中设置参数只能逐个设置。

			//			jpql = "Select u From UserBean u where username like ? and truename like ? order by userid asc";
			//			Query query = manager.createQuery(jpql);
			//			query.setParameter(1, "%a%");
			//			query.setParameter(2, "%张%");

			jpql = "Select u From UserBean u where username like :username and truename like :truename order by userid asc";
			Query query = manager.createQuery(jpql);
			query.setParameter("username", "%a%");
			query.setParameter("truename", "%张%");

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

	private static void singleResult() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		String jpql = "";
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			//1、查找所有的字段
			jpql = "Select u From UserBean u where username = 'ritapei'";
			Query query = manager.createQuery(jpql);
			UserBean userBean = (UserBean) query.getSingleResult();
			System.out.println(userBean);

			//2、返回部分的字段
			jpql = "Select userid,username,password From UserBean where username = 'ritapei'";
			query = manager.createQuery(jpql);
			Object[] objArray = (Object[]) query.getSingleResult();
			System.out.println(objArray[0] + "\t" + objArray[1] + "\t" + objArray[2]);

			//3、返回聚合函数
			jpql = "Select count(userid),max(salary),avg(salary) From UserBean";
			query = manager.createQuery(jpql);
			objArray = (Object[]) query.getSingleResult();
			System.out.println(objArray[0] + "\t" + objArray[1] + "\t" + objArray[2]);

			//4、只返回一列的具体类型
			jpql = "Select truename From UserBean where username = 'ritapei'";
			query = manager.createQuery(jpql);
			String truename = (String) query.getSingleResult();
			System.out.println("truename = " + truename);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			JpaUtil.closeManager();
			System.exit(0);
		}
	}

	private static void pageSQL() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		String jpql = "";
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			jpql = "Select u From UserBean u";
			Query query = manager.createQuery(jpql);
			//设置分页（第一页）,就是limit后面的2个参数
			query.setFirstResult(0);//表示开始的位置
			query.setMaxResults(5);//表示每页显示多少条
			List<UserBean> userList = query.getResultList();
			for (UserBean userBean : userList) {
				System.out.println(userBean);
			}
			//第二页的数据
			System.out.println("======================");
			query = manager.createQuery(jpql);
			//设置分页（第一页）,就是limit后面的2个参数
			query.setFirstResult(5);//表示开始的位置
			query.setMaxResults(5);//表示每页显示多少条
			userList = query.getResultList();
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

	private static void dmlSQL() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		String jpql = "";
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			//update语句
			jpql = "Update UserBean Set userage = ? where userid in (4,7,9,10)";
			Query query = manager.createQuery(jpql);
			query.setParameter(1, "30");
			int rowCount = query.executeUpdate();
			System.out.println("更新所影响的行数 = " + rowCount);

			//delete语句
			jpql = "Delete From UserBean where userid in (28,29,30)";
			query = manager.createQuery(jpql);
			rowCount = query.executeUpdate();
			System.out.println("删除所影响的行数 = " + rowCount);
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
