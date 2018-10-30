package com.app.interceptor;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "auditlog", catalog = "hibernatetest")
public class AuditLog implements java.io.Serializable {

	
	public AuditLog(String action,  String detail, Date createdDate, long entityId, String entityName) {
		super();
		this.action = action;
		this.detail = detail;
		this.createdDate = createdDate;
		this.entityId = entityId;
		this.entityName = entityName;
	}
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AUDIT_LOG_ID", unique = true, nullable = false)
	private Long auditLogId;
	
	@Column(name = "ACTION", nullable = false, length = 50)
	private String action;
	
	@Column(name = "DETAIL", nullable = false, length = 500)
	private String detail;
	

	@Temporal(TemporalType.DATE)
	@Column(name = "createdDate",nullable = false, length = 10)
	private Date createdDate;
	
	@Column(name = "ENTITY_ID",nullable = false, length = 10)
	private long entityId;
	
	@Column(name = "ENTITY_NAME", nullable = false, length = 500)
	private String entityName;
	
	public Long getAuditLogId() {
		return auditLogId;
	}
	public void setAuditLogId(Long auditLogId) {
		this.auditLogId = auditLogId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public long getEntityId() {
		return entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
}