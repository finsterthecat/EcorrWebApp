/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorr.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author brouwerto
 */
@Entity
@Table(name = "CLIENT_SNAPSHOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientSnapshot.findAll", query = "SELECT c FROM ClientSnapshot c"),
    @NamedQuery(name = "ClientSnapshot.findById", query = "SELECT c FROM ClientSnapshot c WHERE c.id = :id"),
    @NamedQuery(name = "ClientSnapshot.findByHonorificTitle", query = "SELECT c FROM ClientSnapshot c WHERE c.honorificTitle = :honorificTitle"),
    @NamedQuery(name = "ClientSnapshot.findByFirstName", query = "SELECT c FROM ClientSnapshot c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "ClientSnapshot.findByMiddleName", query = "SELECT c FROM ClientSnapshot c WHERE c.middleName = :middleName"),
    @NamedQuery(name = "ClientSnapshot.findByLastName", query = "SELECT c FROM ClientSnapshot c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "ClientSnapshot.findByEmailTypeCode", query = "SELECT c FROM ClientSnapshot c WHERE c.emailTypeCode = :emailTypeCode"),
    @NamedQuery(name = "ClientSnapshot.findByEmailAddress", query = "SELECT c FROM ClientSnapshot c WHERE c.emailAddress = :emailAddress"),
    @NamedQuery(name = "ClientSnapshot.findByPhoneTypeCode", query = "SELECT c FROM ClientSnapshot c WHERE c.phoneTypeCode = :phoneTypeCode"),
    @NamedQuery(name = "ClientSnapshot.findByAreaCode", query = "SELECT c FROM ClientSnapshot c WHERE c.areaCode = :areaCode"),
    @NamedQuery(name = "ClientSnapshot.findByLocalNumber", query = "SELECT c FROM ClientSnapshot c WHERE c.localNumber = :localNumber"),
    @NamedQuery(name = "ClientSnapshot.findByExtension", query = "SELECT c FROM ClientSnapshot c WHERE c.extension = :extension"),
    @NamedQuery(name = "ClientSnapshot.findByPostalAddressTypeCode", query = "SELECT c FROM ClientSnapshot c WHERE c.postalAddressTypeCode = :postalAddressTypeCode"),
    @NamedQuery(name = "ClientSnapshot.findByStreetAddressLine1", query = "SELECT c FROM ClientSnapshot c WHERE c.streetAddressLine1 = :streetAddressLine1"),
    @NamedQuery(name = "ClientSnapshot.findByStreetAddressLine2", query = "SELECT c FROM ClientSnapshot c WHERE c.streetAddressLine2 = :streetAddressLine2"),
    @NamedQuery(name = "ClientSnapshot.findByProvinceCode", query = "SELECT c FROM ClientSnapshot c WHERE c.provinceCode = :provinceCode"),
    @NamedQuery(name = "ClientSnapshot.findByPostalCode", query = "SELECT c FROM ClientSnapshot c WHERE c.postalCode = :postalCode"),
    @NamedQuery(name = "ClientSnapshot.findByCountryCode", query = "SELECT c FROM ClientSnapshot c WHERE c.countryCode = :countryCode")})
public class ClientSnapshot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 200)
    @Column(name = "HONORIFIC_TITLE")
    private String honorificTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 200)
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 20)
    @Column(name = "EMAIL_TYPE_CODE")
    private String emailTypeCode;
    @Size(max = 100)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Size(max = 20)
    @Column(name = "PHONE_TYPE_CODE")
    private String phoneTypeCode;
    @Size(max = 3)
    @Column(name = "AREA_CODE")
    private String areaCode;
    @Size(max = 10)
    @Column(name = "LOCAL_NUMBER")
    private String localNumber;
    @Size(max = 5)
    @Column(name = "EXTENSION")
    private String extension;
    @Size(max = 20)
    @Column(name = "POSTAL_ADDRESS_TYPE_CODE")
    private String postalAddressTypeCode;
    @Size(max = 100)
    @Column(name = "STREET_ADDRESS_LINE1")
    private String streetAddressLine1;
    @Size(max = 100)
    @Column(name = "STREET_ADDRESS_LINE2")
    private String streetAddressLine2;
    @Size(max = 2)
    @Column(name = "PROVINCE_CODE")
    private String provinceCode;
    @Size(max = 20)
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Size(max = 3)
    @Column(name = "COUNTRY_CODE")
    private String countryCode;
    @OneToMany(mappedBy = "clientSnapshotId")
    private Collection<SingleCorrespondence> singleCorrespondenceCollection;
    @OneToMany(mappedBy = "clientSnapshotId")
    private Collection<IncomingCorrespondence> incomingCorrespondenceCollection;

    public ClientSnapshot() {
    }

    public ClientSnapshot(Integer id) {
        this.id = id;
    }

    public ClientSnapshot(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHonorificTitle() {
        return honorificTitle;
    }

    public void setHonorificTitle(String honorificTitle) {
        this.honorificTitle = honorificTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailTypeCode() {
        return emailTypeCode;
    }

    public void setEmailTypeCode(String emailTypeCode) {
        this.emailTypeCode = emailTypeCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneTypeCode() {
        return phoneTypeCode;
    }

    public void setPhoneTypeCode(String phoneTypeCode) {
        this.phoneTypeCode = phoneTypeCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPostalAddressTypeCode() {
        return postalAddressTypeCode;
    }

    public void setPostalAddressTypeCode(String postalAddressTypeCode) {
        this.postalAddressTypeCode = postalAddressTypeCode;
    }

    public String getStreetAddressLine1() {
        return streetAddressLine1;
    }

    public void setStreetAddressLine1(String streetAddressLine1) {
        this.streetAddressLine1 = streetAddressLine1;
    }

    public String getStreetAddressLine2() {
        return streetAddressLine2;
    }

    public void setStreetAddressLine2(String streetAddressLine2) {
        this.streetAddressLine2 = streetAddressLine2;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @XmlTransient
    public Collection<SingleCorrespondence> getSingleCorrespondenceCollection() {
        return singleCorrespondenceCollection;
    }

    public void setSingleCorrespondenceCollection(Collection<SingleCorrespondence> singleCorrespondenceCollection) {
        this.singleCorrespondenceCollection = singleCorrespondenceCollection;
    }

    @XmlTransient
    public Collection<IncomingCorrespondence> getIncomingCorrespondenceCollection() {
        return incomingCorrespondenceCollection;
    }

    public void setIncomingCorrespondenceCollection(Collection<IncomingCorrespondence> incomingCorrespondenceCollection) {
        this.incomingCorrespondenceCollection = incomingCorrespondenceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientSnapshot)) {
            return false;
        }
        ClientSnapshot other = (ClientSnapshot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "govonca.tbs.ecorrwebapp.ClientSnapshot[ id=" + id + " ]";
    }
    
}
