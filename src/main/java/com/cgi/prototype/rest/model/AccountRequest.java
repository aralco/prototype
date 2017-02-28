package com.cgi.prototype.rest.model;

import com.cgi.prototype.domain.ResourceStatus;

/**
 * @author aralco
 */
public class AccountRequest {
    private String name;
    private ResourceStatus status = ResourceStatus.Enabled;
    private Boolean markedArchived;
    private String locale;
    private Boolean enterprise;
    private String timeZone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    public Boolean getMarkedArchived() {
        return markedArchived;
    }

    public void setMarkedArchived(Boolean markedArchived) {
        this.markedArchived = markedArchived;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Boolean getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Boolean enterprise) {
        this.enterprise = enterprise;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
