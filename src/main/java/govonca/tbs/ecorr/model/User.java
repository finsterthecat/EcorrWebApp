/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorr.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByPrincipalId", query = "SELECT u FROM User u WHERE u.principalId = :principalId"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByMiddleName", query = "SELECT u FROM User u WHERE u.middleName = :middleName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRINCIPAL_ID")
    private Integer principalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USER_NAME")
    private String userName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "principalId")
    private Collection<PrincipalOrgRole> principalOrgRoleCollection;

    public User() {
    }

    public User(Integer principalId) {
        this.principalId = principalId;
    }

    public User(Integer principalId, String userName, String firstName, String lastName) {
        this.principalId = principalId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Integer principalId) {
        this.principalId = principalId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @XmlTransient
    public Collection<PrincipalOrgRole> getPrincipalOrgRoleCollection() {
        return principalOrgRoleCollection;
    }

    public void setPrincipalOrgRoleCollection(Collection<PrincipalOrgRole> principalOrgRoleCollection) {
        this.principalOrgRoleCollection = principalOrgRoleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (principalId != null ? principalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.principalId == null && other.principalId != null) || (this.principalId != null && !this.principalId.equals(other.principalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "govonca.tbs.ecorrwebapp.User[ principalId=" + principalId + " ]";
    }
    
}
