package com.cgi.prototype.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "permissions")
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "resource", nullable = false)
    private String resource;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "additional_permission", nullable = true)
    private String additionalPermission;

    @Column(name = "display", nullable = false)
    private int display = 1;

    /**
     * @return the additionalPermission
     */
    public String getAdditionalPermission()
    {
        return additionalPermission;
    }

    /**
     * @return the resource
     */
    public String getResource()
    {
        return resource;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return the display
     */
    public int getDisplay()
    {
        return display;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @return the actions
     */
    public String getAction()
    {
        return action;
    }

    /**
     * @param additionalPermission the additionalPermission to set
     */
    public void setAdditionalPermission( String additionalPermission )
    {
        this.additionalPermission = additionalPermission;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource( String resource )
    {
        this.resource = resource;
    }

    /**
     * @param description the description to set
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * @param display the display to set
     */
    public void setDisplay( int display )
    {
        this.display = display;
    }

    /**
     * @param id the id to set
     */
    public void setId( Long id )
    {
        this.id = id;
    }

    /**
     * @param action the method actions to set
     */
    public void setAction( String action )
    {
        this.action = action;
    }

}
