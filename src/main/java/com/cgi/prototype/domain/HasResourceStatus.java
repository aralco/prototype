package com.cgi.prototype.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public interface HasResourceStatus
{
    @Enumerated(EnumType.STRING)
    StatusColumns getStatusColumns();

    void setStatusColumns( @NotNull StatusColumns statusColumns );
}
