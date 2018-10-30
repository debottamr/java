package com.app.interceptor;

import java.util.Date;

import org.hibernate.Session;

public class AuditLogUtil {

	public static void LogIt(String action, IAuditLog entity, Session session) {

		try {
			AuditLog auditRecord = new AuditLog(action, entity.getLogDeatil(), new Date(), entity.getId(),
					entity.getClass().toString());
			session.save(auditRecord);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			//tempSession.close();
		}
	}
}
