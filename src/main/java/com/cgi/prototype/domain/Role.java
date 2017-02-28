package com.cgi.prototype.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "roles")
@EntityListeners(EntityStatusListener.class)
public class Role implements Serializable, HasResourceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id", nullable = false)})
    private Set<Permission> permissions = new HashSet<Permission>();

    @Embedded
    private StatusColumns roleStatus = new StatusColumns();

    @Version
    @NotNull
    @Column(name = "version", nullable = false)
    private Long version;

    public void addPermission( Permission permission )
    {
        permissions.add(permission);
    }

    public void clearPermissions()
    {
        permissions.clear();
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the permissions
     */
    public Set<Permission> getPermissions()
    {
        return Collections.unmodifiableSet(permissions);
    }

    public void removePermission( Permission permission )
    {
        permissions.remove(permission);
    }

    /**
     * @param description the description to set
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * @param id the id to set
     */
    public void setId( Long id )
    {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName( @NotNull String name )
    {
        this.name = name;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions( Set<Permission> permissions )
    {
        clearPermissions();
        if ( permissions == null || permissions.isEmpty() )
            return;

        this.permissions.addAll(permissions);
    }

    public Long getVersion()
    {
        return version;
    }

    public void setVersion( Long version )
    {
        this.version = version;
    }

    /**
     * @return the active
     */
    public Boolean isActive()
    {
        return getStatus() == ResourceStatus.Enabled || getStatus() == ResourceStatus.Suspended; // No logic for suspended roles so treat as enabled
    }

    public ResourceStatus getStatus()
    {
        return roleStatus.getStatus();
    }

    @Override
    public StatusColumns getStatusColumns()
    {
        return roleStatus;
    }

    @Override
    public void setStatusColumns( @NotNull StatusColumns statusColumns )
    {
        roleStatus = new StatusColumns(statusColumns);
    }

    /**
     * @return the activationDate
     */
    public Date getActivationDate()
    {
        return roleStatus.getActivatedOn();
    }

    /**
     * @param activationDate the activationDate to set
     */
    public void setActivationDate( Date activationDate )
    {
        roleStatus.setActivatedOn(activationDate);
    }

    public Date getDeletionDate()
    {
        return roleStatus.getDeletedOn();
    }

    public void setDeletionDate( Date deletionDate )
    {
        getStatusColumns().setDeletedOn(deletionDate);
    }
}
