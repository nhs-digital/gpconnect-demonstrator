package uk.gov.hscic.appointment.slot;

import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import uk.gov.hscic.organization.OrganizationEntity;

@Entity
@Table(name = "appointment_slots")
@Cacheable(false)
public class SlotEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "typeCode")
    private Long typeCode;
    
    @Column(name = "typeDisplay")
    private String typeDisply;
    
    @Column(name = "scheduleReference")
    private Long scheduleReference;
    
    @Column(name = "freeBusyType")
    private String freeBusyType;
    
    @Column(name = "startDateTime")
    private Date startDateTime;
    
    @Column(name = "endDateTime")
    private Date endDateTime;
    
    @Column(name = "lastUpdated")
    private Date lastUpdated;
    
    @Column(name = "gpConnectBookable")
    private boolean gpConnectBookable;
    
    @Column(name = "deliveryChannelCode")
    private String deliveryChannelCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "appointment_slots_organizations", joinColumns = {
            @JoinColumn(name = "slotId", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "organizationId", referencedColumnName = "id") })
    private List<OrganizationEntity> bookableOrganizations;
    
    // This is a workaround for the fact that you can't have > 1 fetch = FetchType.EAGER
    // This is a way of achieving the same thing that the persistence manager does not object to
    // see https://stackoverflow.com/questions/36263384/why-i-cant-use-2-lists-on-the-same-entity-class-using-fetchtype-eager
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @JoinTable(name = "appointment_slots_orgType", joinColumns = {
            @JoinColumn(name = "slotId", referencedColumnName = "id") } )
    private List<String> bookableOrgTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Long typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDisply() {
        return typeDisply;
    }

    public void setTypeDisply(String typeDisply) {
        this.typeDisply = typeDisply;
    }

    public Long getScheduleReference() {
        return scheduleReference;
    }

    public void setScheduleReference(Long scheduleReference) {
        this.scheduleReference = scheduleReference;
    }

    public String getFreeBusyType() {
        return freeBusyType;
    }

    public void setFreeBusyType(String freeBusyType) {
        this.freeBusyType = freeBusyType;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

	public boolean isGpConnectBookable() {
		return gpConnectBookable;
	}

	public void setGpConnectBookable(boolean gpConnectBookable) {
		this.gpConnectBookable = gpConnectBookable;
	}

	public List<OrganizationEntity> getBookableOrganizations() {
		return bookableOrganizations;
	}

	public void setBookableOrganizations(List<OrganizationEntity> bookableOrganizations) {
		this.bookableOrganizations = bookableOrganizations;
	}

	public List<String> getBookableOrgTypes() {
		return bookableOrgTypes;
	}

	public void setBookableOrgTypes(List<String> bookableOrgTypes) {
		this.bookableOrgTypes = bookableOrgTypes;
	}

    public void setDeliveryChannelCode(String deliveryChannelCode) {
        this.deliveryChannelCode = deliveryChannelCode;
    }

    public String getDeliveryChannelCode() {
        return deliveryChannelCode;
    }
}
