package com.cgi.prototype.rest.model;

import com.cgi.prototype.domain.AuditSupportColumns;
import com.cgi.prototype.domain.EntityWithAuditSupportColumns;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class TimestampEntityListener {
    @PrePersist
    public void onCreate(Object entity) {
        if(entity instanceof EntityWithAuditSupportColumns) {
            EntityWithAuditSupportColumns entityWithAuditSupportColumns = (EntityWithAuditSupportColumns) entity;
            if(entityWithAuditSupportColumns.getAuditSupportColumns() == null)
                entityWithAuditSupportColumns.setAuditSupportColumns(new AuditSupportColumns());
            entityWithAuditSupportColumns.getAuditSupportColumns().setCreatedOn(LocalDateTime.now(DateTimeZone.UTC).toDateTime(DateTimeZone.UTC).toDate());
            entityWithAuditSupportColumns.getAuditSupportColumns().setUpdatedOn(entityWithAuditSupportColumns.getAuditSupportColumns().getCreatedOn());
        }
    }

    @PreUpdate
    public void onUpdate(Object entity) {
        if(entity instanceof EntityWithAuditSupportColumns) {
            EntityWithAuditSupportColumns entityWithAuditSupportColumns = (EntityWithAuditSupportColumns) entity;
            if(entityWithAuditSupportColumns.getAuditSupportColumns() == null) {
                entityWithAuditSupportColumns.setAuditSupportColumns(new AuditSupportColumns());
                entityWithAuditSupportColumns.getAuditSupportColumns().setCreatedOn(LocalDateTime.now(DateTimeZone.UTC).toDateTime(DateTimeZone.UTC).toDate());
            }
            entityWithAuditSupportColumns.getAuditSupportColumns().setUpdatedOn(LocalDateTime.now(DateTimeZone.UTC).toDateTime(DateTimeZone.UTC).toDate());
        }
    }
}
