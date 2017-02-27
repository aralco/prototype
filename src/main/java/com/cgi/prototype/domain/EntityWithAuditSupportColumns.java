package com.cgi.prototype.domain;


import javax.validation.constraints.NotNull;

/**
 * Interface to hook audit support for all JPA domain entities at run time.
 *
 *
 */
public interface EntityWithAuditSupportColumns
{
    AuditSupportColumns getAuditSupportColumns();

    void setAuditSupportColumns( @NotNull AuditSupportColumns auditSupportColumns );
}
