package com.cgi.prototype.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "users")
@Cacheable(true)
public class User implements Serializable, EntityWithAuditSupportColumns, HasResourceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "username", nullable = false, insertable = true, updatable = true, unique = true)
    @NotNull
    @Size(max = 64)
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull
    @Size(max = 64, min = 1)
    private String password;

    @Column(name = "first_name", nullable = true)
    @Size(max = 64)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    @Size(max = 64)
    private String lastName;

    @Column(name = "email", nullable = true)
    @Size(max = 256)
    private String email;

    @Column(name = "phone", nullable = true)
    @Size(max = 50)
    private String phone;

    @Column(name = "pin", nullable = true)
    @Size(max = 32)
    private String pin;

    @Column(name = "temp_password", nullable = true)
    @Size(max = 64)
    private String tempPassword;

    @Column(name = "password_updated", nullable = false)
    private boolean passwordUpdated = false;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    @NotNull
    private Account account;

    @Embedded
    private AuditSupportColumns auditSupport = new AuditSupportColumns();

    @Embedded
    @AttributeOverride(name = "status", column = @Column(name = "status", nullable = false))
    private StatusColumns userStatus = new StatusColumns();

    @Column(name = "termination_date", nullable = false)
    private Date terminationDate;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Role.class)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, unique = false)})
    @NotEmpty
    private Set<Role> userRoles = new HashSet<>();

    @Transient
    private Set<Permission> permissions = new HashSet<>();

    @Version
    @NotNull
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "timeZone")
    @Size(max = 64)
    private String timeZone;

    @Column(name = "title", nullable = true)
    @Size(max = 64)
    private String title;

    @Column(name = "account_type", nullable = false)
    @Size(max = 12)
    private String accountType = "AUTO";

    public Set<Role> getUserRoles()
    {
        return Collections.unmodifiableSet(userRoles);
    }

    public void addUserRole( Role role )
    {
        if ( role == null )
            return;

        userRoles.add(role);
        permissions.addAll(role.getPermissions());
    }

    public void setUserRoles( Set<Role> userRoles )
    {
        this.userRoles.clear();
        if ( userRoles == null || userRoles.isEmpty() )
            return;

        this.userRoles.addAll(userRoles);
        // Let's set the permissions here as well since roles may have changed
        permissions.clear();
        for ( Role r : userRoles )
            permissions.addAll(r.getPermissions());
    }

    public Set<Permission> getPermissions()
    {
        permissions.clear();
        if ( userRoles != null && !userRoles.isEmpty() )
            for ( Role r : userRoles )
                permissions.addAll(r.getPermissions());

        return permissions;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = (password == null || password.trim().length() == 0) ? null : password;
    }

    public Boolean isEnabled()
    {
        return userStatus.isActive() || userStatus.getStatus() == ResourceStatus.Suspended;
    }

    public void setEnabled( Boolean enabled )
    {
        if ( enabled )
            userStatus.setStatus(ResourceStatus.Enabled);
        else
            userStatus.setStatus(ResourceStatus.Disabled);
    }

    /**
     * @return the account
     */
    public Account getAccount()
    {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount( Account account )
    {
        this.account = account;
    }

    public Date getCreatedOn()
    {
        return auditSupport.getCreatedOn();
    }

    public void setCreatedOn( Date createdOn )
    {
        auditSupport.setCreatedOn(createdOn);
    }

    public Date getLastModifiedOn()
    {
        return auditSupport.getUpdatedOn();
    }

    public void setLastModifiedOn( Date lastModifiedOn )
    {
        auditSupport.setUpdatedOn(lastModifiedOn);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPin()
    {
        return pin;
    }

    public void setPin( String pin )
    {
        this.pin = pin;
    }

    public String getTempPassword()
    {
        return tempPassword;
    }

    public void setTempPassword( String tempPassword )
    {
        this.tempPassword = tempPassword;
    }

    public boolean isPasswordUpdated()
    {
        return passwordUpdated;
    }

    public void setPasswordUpdated( boolean passwordUpdated )
    {
        this.passwordUpdated = passwordUpdated;
    }

    public Date getTerminationDate()
    {
        return terminationDate;
    }

    public void setTerminationDate( Date terminationDate )
    {
        this.terminationDate = terminationDate;
    }

    public Boolean isActive()
    {
        return getStatus() == ResourceStatus.Enabled || getStatus() == ResourceStatus.Suspended; // No logic for suspended users so treat as enabled
    }

    public ResourceStatus getStatus()
    {
        return userStatus.getStatus();
    }

    @Override
    public StatusColumns getStatusColumns()
    {
        return userStatus;
    }

    @Override
    public void setStatusColumns( @NotNull StatusColumns statusColumns )
    {
        userStatus = new StatusColumns(statusColumns);
    }

    public void setStatus( ResourceStatus status )
    {
        if ( status == null )
            return;

        userStatus = new StatusColumns().setStatus(status);
    }

    public Date getActivationDate()
    {
        return userStatus.getActivatedOn();
    }

    public void setActivationDate( Date activationDate )
    {
        userStatus.setActivatedOn(activationDate);
    }

    public Date getDeletionDate()
    {
        return userStatus.getDeletedOn();
    }


    public void setDeletionDate( Date deletionDate )
    {
        getStatusColumns().setDeletedOn(deletionDate);
    }

    public void setPermissions( Set<Permission> permissions )
    {
        this.permissions.clear();
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType( String accountType )
    {
        this.accountType = accountType;
    }

}
