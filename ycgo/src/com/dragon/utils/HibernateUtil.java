package com.dragon.utils;

import javax.jms.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** 
 * @Title HibernateUtil.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-14
 */

public class HibernateUtil {
	private static final ThreadLocal< Session> threadLocal = new ThreadLocal<Session>();
	private static SessionFactory sessionFactory = null;
	
	static {
		try {
			//加载配置文件
			Configuration cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("创建sessionfactory 失败");
		}
	}
	
	//获取session
/*	public static Session getSession(){
		Session session = (Session)threadLocal.get();
		if (null == session || !session.isOpen()) {
			if (null == sessionFactory) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null)?sessionFactory.openSession():null;
			threadLocal.set(session);
		}
		return session;
	}*/
}
