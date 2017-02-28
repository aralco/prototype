package com.cgi.prototype.domain;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class EntityStatusListener {
    @PrePersist
    @PreUpdate
    public void onCreateOrUpdate( Object entity )
    {
        if ( entity instanceof HasResourceStatus )
        {
            StatusColumns statusCols = ((HasResourceStatus) entity).getStatusColumns();
            if ( statusCols == null )
                ((HasResourceStatus) entity).setStatusColumns(statusCols = new StatusColumns());

            if ( !statusCols.isDeleted() && statusCols.getDeletedOn() != null)
            {
                throw new UnsupportedOperationException("Undelete is not supported");
            }

            if ( statusCols.isDeleted() && statusCols.getDeletedOn() == null )
            {
                throw new UnsupportedOperationException("Delete is only allowed through DELETE operation");
            }

            if ( statusCols.isActive() )
            {
                if ( statusCols.getActivatedOn() == null )
                    statusCols.setActivatedOn(LocalDateTime.now(DateTimeZone.UTC).toDateTime(DateTimeZone.UTC).toDate());
            }
        }
    }

    @PreRemove
    public void onDelete( Object entity )
    {
        if ( entity instanceof HasResourceStatus )
        {
            StatusColumns statusCols = ((HasResourceStatus) entity).getStatusColumns();
            if ( statusCols == null )
                ((HasResourceStatus) entity).setStatusColumns(statusCols = new StatusColumns());
            if ( !statusCols.isDeleted() )
                statusCols.setStatus(ResourceStatus.Deleted);
            if ( statusCols.getDeletedOn() == null )
                statusCols.setDeletedOn(LocalDateTime.now(DateTimeZone.UTC).toDateTime(DateTimeZone.UTC).toDate());
        }
    }

}

