package com.jittrack.gts.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Account.
 */
@Entity
@Table(name = "T_ACCOUNT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    private Integer accountType;

    
    private Boolean allowNotify;

    
    private Boolean allowWebService;

    
    private Boolean autoAddDevices;

    
    private String contactEmail;

    
    private String contactName;

    
    private String contactPhone;

    
    private Long expirationTime;

    
    private String emailProperties;

    
    private Integer economyUnits;

    
    private Integer distanceUnits;

    
    private String displayName;

    
    private String description;

    
    private String defaultUser;

    
    private Long creationTime;

    
    private Integer currencyUnits;

    
    private String dcsPropertiesID;

    
    private BigDecimal fuelCostPerLiter;

    
    private Integer geocoderMode;

    
    private Boolean isActive;

    
    private Boolean isBorderCrossing;

    
    private Long lastLoginTime;

    
    private Long lastUpdateTime;

    
    private Integer latLonFormat;

    
    private Integer maximumDevices;

    
    private Integer maxPingCount;

    
    private String notes;

    
    private String notifyEmail;

    
    private Long passwdChangeTime;

    
    private Long passwdQueryTime;

    
    private String password;

    
    private Integer pressureUnits;

    
    private String privateLabelName;

    
    private Integer retainedEventAge;

    
    private Boolean smsEnabled;

    
    private String smsProperties;

    
    private Integer speedUnits;

    
    private Integer temperatureUnits;

    
    private String timeZone;

    
    private Integer totalPingCount;

    
    private Integer volumeUnits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Boolean getAllowNotify() {
        return allowNotify;
    }

    public void setAllowNotify(Boolean allowNotify) {
        this.allowNotify = allowNotify;
    }

    public Boolean getAllowWebService() {
        return allowWebService;
    }

    public void setAllowWebService(Boolean allowWebService) {
        this.allowWebService = allowWebService;
    }

    public Boolean getAutoAddDevices() {
        return autoAddDevices;
    }

    public void setAutoAddDevices(Boolean autoAddDevices) {
        this.autoAddDevices = autoAddDevices;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getEmailProperties() {
        return emailProperties;
    }

    public void setEmailProperties(String emailProperties) {
        this.emailProperties = emailProperties;
    }

    public Integer getEconomyUnits() {
        return economyUnits;
    }

    public void setEconomyUnits(Integer economyUnits) {
        this.economyUnits = economyUnits;
    }

    public Integer getDistanceUnits() {
        return distanceUnits;
    }

    public void setDistanceUnits(Integer distanceUnits) {
        this.distanceUnits = distanceUnits;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(String defaultUser) {
        this.defaultUser = defaultUser;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getCurrencyUnits() {
        return currencyUnits;
    }

    public void setCurrencyUnits(Integer currencyUnits) {
        this.currencyUnits = currencyUnits;
    }

    public String getDcsPropertiesID() {
        return dcsPropertiesID;
    }

    public void setDcsPropertiesID(String dcsPropertiesID) {
        this.dcsPropertiesID = dcsPropertiesID;
    }

    public BigDecimal getFuelCostPerLiter() {
        return fuelCostPerLiter;
    }

    public void setFuelCostPerLiter(BigDecimal fuelCostPerLiter) {
        this.fuelCostPerLiter = fuelCostPerLiter;
    }

    public Integer getGeocoderMode() {
        return geocoderMode;
    }

    public void setGeocoderMode(Integer geocoderMode) {
        this.geocoderMode = geocoderMode;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsBorderCrossing() {
        return isBorderCrossing;
    }

    public void setIsBorderCrossing(Boolean isBorderCrossing) {
        this.isBorderCrossing = isBorderCrossing;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getLatLonFormat() {
        return latLonFormat;
    }

    public void setLatLonFormat(Integer latLonFormat) {
        this.latLonFormat = latLonFormat;
    }

    public Integer getMaximumDevices() {
        return maximumDevices;
    }

    public void setMaximumDevices(Integer maximumDevices) {
        this.maximumDevices = maximumDevices;
    }

    public Integer getMaxPingCount() {
        return maxPingCount;
    }

    public void setMaxPingCount(Integer maxPingCount) {
        this.maxPingCount = maxPingCount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public Long getPasswdChangeTime() {
        return passwdChangeTime;
    }

    public void setPasswdChangeTime(Long passwdChangeTime) {
        this.passwdChangeTime = passwdChangeTime;
    }

    public Long getPasswdQueryTime() {
        return passwdQueryTime;
    }

    public void setPasswdQueryTime(Long passwdQueryTime) {
        this.passwdQueryTime = passwdQueryTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPressureUnits() {
        return pressureUnits;
    }

    public void setPressureUnits(Integer pressureUnits) {
        this.pressureUnits = pressureUnits;
    }

    public String getPrivateLabelName() {
        return privateLabelName;
    }

    public void setPrivateLabelName(String privateLabelName) {
        this.privateLabelName = privateLabelName;
    }

    public Integer getRetainedEventAge() {
        return retainedEventAge;
    }

    public void setRetainedEventAge(Integer retainedEventAge) {
        this.retainedEventAge = retainedEventAge;
    }

    public Boolean getSmsEnabled() {
        return smsEnabled;
    }

    public void setSmsEnabled(Boolean smsEnabled) {
        this.smsEnabled = smsEnabled;
    }

    public String getSmsProperties() {
        return smsProperties;
    }

    public void setSmsProperties(String smsProperties) {
        this.smsProperties = smsProperties;
    }

    public Integer getSpeedUnits() {
        return speedUnits;
    }

    public void setSpeedUnits(Integer speedUnits) {
        this.speedUnits = speedUnits;
    }

    public Integer getTemperatureUnits() {
        return temperatureUnits;
    }

    public void setTemperatureUnits(Integer temperatureUnits) {
        this.temperatureUnits = temperatureUnits;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getTotalPingCount() {
        return totalPingCount;
    }

    public void setTotalPingCount(Integer totalPingCount) {
        this.totalPingCount = totalPingCount;
    }

    public Integer getVolumeUnits() {
        return volumeUnits;
    }

    public void setVolumeUnits(Integer volumeUnits) {
        this.volumeUnits = volumeUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (id != null ? !id.equals(account.id) : account.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountType='" + accountType + "'" +
                ", allowNotify='" + allowNotify + "'" +
                ", allowWebService='" + allowWebService + "'" +
                ", autoAddDevices='" + autoAddDevices + "'" +
                ", contactEmail='" + contactEmail + "'" +
                ", contactName='" + contactName + "'" +
                ", contactPhone='" + contactPhone + "'" +
                ", expirationTime='" + expirationTime + "'" +
                ", emailProperties='" + emailProperties + "'" +
                ", economyUnits='" + economyUnits + "'" +
                ", distanceUnits='" + distanceUnits + "'" +
                ", displayName='" + displayName + "'" +
                ", description='" + description + "'" +
                ", defaultUser='" + defaultUser + "'" +
                ", creationTime='" + creationTime + "'" +
                ", currencyUnits='" + currencyUnits + "'" +
                ", dcsPropertiesID='" + dcsPropertiesID + "'" +
                ", fuelCostPerLiter='" + fuelCostPerLiter + "'" +
                ", geocoderMode='" + geocoderMode + "'" +
                ", isActive='" + isActive + "'" +
                ", isBorderCrossing='" + isBorderCrossing + "'" +
                ", lastLoginTime='" + lastLoginTime + "'" +
                ", lastUpdateTime='" + lastUpdateTime + "'" +
                ", latLonFormat='" + latLonFormat + "'" +
                ", maximumDevices='" + maximumDevices + "'" +
                ", maxPingCount='" + maxPingCount + "'" +
                ", notes='" + notes + "'" +
                ", notifyEmail='" + notifyEmail + "'" +
                ", passwdChangeTime='" + passwdChangeTime + "'" +
                ", passwdQueryTime='" + passwdQueryTime + "'" +
                ", password='" + password + "'" +
                ", pressureUnits='" + pressureUnits + "'" +
                ", privateLabelName='" + privateLabelName + "'" +
                ", retainedEventAge='" + retainedEventAge + "'" +
                ", smsEnabled='" + smsEnabled + "'" +
                ", smsProperties='" + smsProperties + "'" +
                ", speedUnits='" + speedUnits + "'" +
                ", temperatureUnits='" + temperatureUnits + "'" +
                ", timeZone='" + timeZone + "'" +
                ", totalPingCount='" + totalPingCount + "'" +
                ", volumeUnits='" + volumeUnits + "'" +
                '}';
    }
}
