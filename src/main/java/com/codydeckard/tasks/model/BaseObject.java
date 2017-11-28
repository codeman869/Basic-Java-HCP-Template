package com.codydeckard.tasks.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codydeckard.tasks.services.UserManager;

/**
 * Base class for all domain model objects.
 */
@MappedSuperclass
public abstract class BaseObject {
	@Id @Column(name="GUID", length = 36 )// @GeneratedValue(strategy=GenerationType.AUTO)
	private String guid = UUID.randomUUID().toString();
	
	/**
	 * The {@link Date} the object was created at.
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATION_DATE", updatable = false)
    private Date createdAt = null;
 
    /**
	 * The {@link Date} the object was last modified at.
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MODIFICATION_DATE")
    private Date lastModifiedAt = null;

    /**
     * ID of the user who created the object.
     */
    @Column(name="CREATED_BY", updatable = false, length = 20)
    private String createdBy = null;
    
    /**
     * ID of the user who was the last to modify the object.
     */
    @Column(name="MODIFIED_BY", length = 20)
    private String lastModifiedBy = null;
        
    /**
     * Life-cycle event callback, which automatically sets the last modification date.  
     */
    @PreUpdate
    protected void updateAuditInformation() 
    {
        lastModifiedAt = new Date();
        
        lastModifiedBy = UserManager.getCurrentUser();
        
        UserManager.remove();
        
    }

    /**
     * Life-cycle event callback, which automatically creates a unique ID for the object
     * and populates its audit information.  
     */
    @PrePersist
    protected void generateAuditInformation() 
    {   
        final Date now = new Date();
        
        createdAt = now;
        lastModifiedAt = now;
        
        String user = UserManager.getCurrentUser();
        
        lastModifiedBy = user;
        createdBy = user; 
        
        UserManager.remove();
    }

    public String getGUID()
	{
		return this.guid;
	}

	public void setGUID(String guid)
	{
		this.guid = guid;
	}
    
	public Date getCreatedAt()
	{
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt()
	{
		return this.lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt)
	{
		this.lastModifiedAt = lastModifiedAt;
	}

	public String getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy()
	{
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy)
	{
		this.lastModifiedBy = lastModifiedBy;
	}

}
