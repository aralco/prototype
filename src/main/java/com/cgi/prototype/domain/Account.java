package com.cgi.prototype.domain;

import com.cgi.prototype.rest.model.TimestampEntityListener;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;


/**
 * @author aralco
 */

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "accounts")
@EntityListeners({TimestampEntityListener.class, EntityStatusListener.class})
public class Account implements Serializable, EntityWithAuditSupportColumns, HasResourceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Version
    @NotNull
    @Column(name = "version")
    private Long version;

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty
    @Size(max = 64)
    private String name;

    @Embedded
    private AuditSupportColumns auditSupport = new AuditSupportColumns();

    @Embedded
    private StatusColumns statusColumns = new StatusColumns();

    @Column(name = "marked_archived", nullable = false)
    private Boolean markedArchived = false;

    @Column(name = "locale")
    @Size(max = 32)
    private String locale;

    @Column(name = "enterprise")
    private Boolean enterprise = false;

    @Column(name = "time_zone")
    @Size(max = 64)
    private String timeZone;

    public Long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    /**
     * @return the version
     */
    public Long getVersion()
    {
        return version;
    }

    public void setVersion( long ver )
    {
        version = ver;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * @return the createdOn
     */
    public Date getCreatedOn()
    {
        return auditSupport.getCreatedOn();
    }

    /**
     * @param createdOn - the createdOn to set
     */
    public void setCreatedOn(Date createdOn )
    {
        auditSupport.setCreatedOn(createdOn);
    }

    /**
     * @return the updatedOn
     */
    public Date getUpdatedOn()
    {
        return auditSupport.getUpdatedOn();
    }

    /**
     * @param setUpdatedOn the setUpdatedOn to set
     */
    public void setUpdatedOn(Date setUpdatedOn )
    {
        auditSupport.setUpdatedOn(setUpdatedOn);
    }

    /**
     * @return the activationDate
     */
    public Date getActivationDate()
    {
        return getStatusColumns().getActivatedOn();
    }

    /**
     * @param activationDate the activationDate to set
     */
    public void setActivationDate( Date activationDate )
    {
        getStatusColumns().setActivatedOn(activationDate);
    }

    public Date getDeletionDate()
    {
        return getStatusColumns().getDeletedOn();
    }

    public void setDeletionDate( Date deletionDate )
    {
        getStatusColumns().setDeletedOn(deletionDate);
    }

    /**
     * @return the markedArchived
     */
    public Boolean isMarkedArchived()
    {
        return markedArchived == Boolean.TRUE;
    }

    /**
     * @param markedArchived the markedArchived to set
     */
    public void setMarkedArchived( Boolean markedArchived )
    {
        this.markedArchived = markedArchived;
    }

    /**
     * @return the locale
     */
    public String getLocale()
    {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale( String locale )
    {
        this.locale = locale;
    }

    /**
     * @return the enterprise
     */
    public Boolean isEnterprise()
    {
        return enterprise;
    }

    /**
     * @param enterprise the enterprise to set
     */
    public void setEnterprise( Boolean enterprise )
    {
        this.enterprise = enterprise;
    }

    /**
     * @return the accountStatus
     */
    public ResourceStatus getAccountStatus()
    {
        return getStatusColumns().getStatus();
    }

    /**
     * @param accountStatus the accountStatus to set
     */
    public void setAccountStatus( ResourceStatus accountStatus )
    {
        getStatusColumns().setStatus(accountStatus);
    }

    public Boolean isActive()
    {
        return getStatusColumns().isActive();
    }

    public Boolean isSuspended()
    {
        return getStatusColumns().getStatus() == ResourceStatus.Suspended;
    }

    public Boolean isInactive()
    {
        return getStatusColumns().isInactive();
    }

    public Boolean isDeleted()
    {
        return getStatusColumns().isDeleted();
    }

    @Override
    public AuditSupportColumns getAuditSupportColumns()
    {
        return auditSupport;
    }

    @Override
    public void setAuditSupportColumns( @NotNull AuditSupportColumns auditSupportColumns )
    {
        auditSupport = auditSupportColumns;
    }

    public String getTimeZone()
    {
        return timeZone;
    }

    public void setTimeZone( String timeZone )
    {
        this.timeZone = timeZone;
    }

    @Override
    public StatusColumns getStatusColumns()
    {
        return statusColumns;
    }

    @Override
    public void setStatusColumns( @NotNull StatusColumns statusColumns )
    {
        this.statusColumns = new StatusColumns(statusColumns);
    }

}


