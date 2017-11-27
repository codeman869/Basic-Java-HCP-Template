package com.codydeckard.tasks.db;

import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;

public class Database {

	@SuppressWarnings({"rawtypes","unchecked"})
	public static EntityManager getEntityManager() {
		Map properties = new HashMap();
		EntityManager em = null;

		
		try {
			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
			
			properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
		
			em = Persistence.createEntityManagerFactory("tasks", properties).createEntityManager();
				
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return em;
	}

}
