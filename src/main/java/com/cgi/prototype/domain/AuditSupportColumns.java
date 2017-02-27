package com.cgi.prototype.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * JPA embeddable entity to provide audit support to all JPA domain entities at run time.
 *
 *
 */
@Embeddable
public class AuditSupportColumns implements Serializable
{

    @Column(name = "created_on", updatable = false)
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;

    public void setUpdatedOn(@NotNull Date updatedOn) {
        this.updatedOn = updatedOn == null ? null : new Date(updatedOn.getTime());
    }

    public Date getCreatedOn() {
        return createdOn == null ? null : new Date(createdOn.getTime());
    }

    public void setCreatedOn( @NotNull Date createdOn ) {
        this.createdOn = createdOn == null ? null : new Date(createdOn.getTime());
    }

    public Date getUpdatedOn()  {
        return updatedOn == null ? null : new Date(updatedOn.getTime());
    }

}
