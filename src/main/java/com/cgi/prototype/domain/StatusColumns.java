package com.cgi.prototype.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Embeddable that defines the properties for tracking resource status, activation and deactivation. By default, the status is set to
 * {@link ResourceStatus#Enabled}
 *
 * Any domain entity that implements {@link HasResourceStatus} will have
 *
 *
 */
@Embeddable
public class StatusColumns implements Serializable
{
    private static final long serialVersionUID = 1010056614820839744L;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ResourceStatus status = ResourceStatus.Enabled;

    @Column(name = "activation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activatedOn;

    @Column(name = "deletion_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedOn;

    public StatusColumns()
    {

    }

    /**
     * Copies the values from the supplied <code>StatusColumns</code> and creates a new instance.
     *
     * @param copyFrom
     */
    public StatusColumns( StatusColumns copyFrom )
    {
        if ( copyFrom == null )
            return;

        status = copyFrom.status;
        activatedOn = copyFrom.activatedOn == null ? null : new Date(copyFrom.activatedOn.getTime());
        deletedOn = copyFrom.deletedOn == null ? null : new Date(copyFrom.deletedOn.getTime());
    }

    public ResourceStatus getStatus()
    {
        return status;
    }

    /**
     * Set the status. This also has a side effect of setting the activatedOn or the deactivatedOn dates to null based on the old status and the new
     * status values.<br>
     * <br>
     *
     * If {@link #!isActive()} returns <code>true</code> and new <code>status == {@link ResourceStatus#Enabled}</code>, <code>activatedOn</code> is
     * set to null. <br>
     * <br>
     *
     * The dates are set to null so as to allow the {@link EntityStatusListener} to automatically handle setting the dates correctly.
     *
     * @param status
     * @throws UnsupportedOperationException If the object is already deleted and the <code>status == {@link ResourceStatus}</code> is different than
     *             <code>status == {@link ResourceStatus#Deleted}</code>.
     */
    public StatusColumns setStatus( ResourceStatus status )
    {
        if ( status == null )
            return this;

        if ( isDeleted() && status != ResourceStatus.Deleted )
            throw new UnsupportedOperationException("Undelete is not currently supported");
        else if ( !isActive() && status == ResourceStatus.Enabled)
            setActivatedOn(null);

        this.status = status;
        return this;
    }

    public boolean isActive()
    {
        return status == ResourceStatus.Enabled;
    }

    public boolean isInactive()
    {
        return status == ResourceStatus.Disabled;
    }

    public boolean isDeleted()
    {
        return status == ResourceStatus.Deleted;
    }

    public Date getActivatedOn()
    {
        return activatedOn == null ? null : new Date(activatedOn.getTime());
    }

    public StatusColumns setActivatedOn( Date d )
    {
        activatedOn = d == null ? null : new Date(d.getTime());
        return this;
    }

    public Date getDeletedOn()
    {
        return deletedOn == null ? null : new Date(deletedOn.getTime());
    }

    public StatusColumns setDeletedOn( Date deletedOn )
    {
        this.deletedOn = deletedOn == null ? null : new Date(deletedOn.getTime());
        return this;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activatedOn == null) ? 0 : activatedOn.hashCode());
        result = prime * result + ((deletedOn == null) ? 0 : deletedOn.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        StatusColumns other = (StatusColumns) obj;
        if ( activatedOn == null )
        {
            if ( other.activatedOn != null )
                return false;
        }
        else if ( !activatedOn.equals(other.activatedOn) )
            return false;
        if ( deletedOn == null )
        {
            if ( other.deletedOn != null )
                return false;
        }
        else if ( !deletedOn.equals(other.deletedOn) )
            return false;
        if ( status != other.status )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("StatusColumns [status=%s, activatedOn=%s, deletedOn=%s]", status, activatedOn, deletedOn);
    }

}
