package com.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();
	private static EntityManagerFactory managerFactory;

	static {
		try {
			managerFactory = Persistence.createEntityManagerFactory("Jpa_Project_1");
		} catch (Exception e) {
			System.err.println("%%%% Error Creating MyBatis SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	private JpaUtil() {
	}

	public static EntityManager getManager() throws Exception {
		EntityManager manager = (EntityManager) threadLocal.get();

		if (manager == null) {
			if (managerFactory == null) {
				rebuildManagerFactory();
			}
			manager = (managerFactory != null) ? managerFactory.createEntityManager() : null;
			threadLocal.set(manager);
		}
		return manager;
	}

	public static void rebuildManagerFactory() {
		try {
			managerFactory = Persistence.createEntityManagerFactory("Jpa_Project_1");
		} catch (Exception e) {
			System.err.println("%%%% Error Creating MyBatis SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	public static void closeManager() {
		EntityManager manager = (EntityManager) threadLocal.get();
		threadLocal.set(null);

		if (manager != null) {
			manager.close();
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return managerFactory;
	}
}
