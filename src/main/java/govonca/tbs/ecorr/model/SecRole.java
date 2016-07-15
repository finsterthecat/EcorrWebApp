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
@Table(name = "SEC_ROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SecRole.findAll", query = "SELECT s FROM SecRole s"),
    @NamedQuery(name = "SecRole.findById", query = "SELECT s FROM SecRole s WHERE s.id = :id"),
    @NamedQuery(name = "SecRole.findBySecRoleCode", query = "SELECT s FROM SecRole s WHERE s.secRoleCode = :secRoleCode"),
    @NamedQuery(name = "SecRole.findBySecRoleType", query = "SELECT s FROM SecRole s WHERE s.secRoleType = :secRoleType")})
public class SecRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SEC_ROLE_CODE")
    private String secRoleCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SEC_ROLE_TYPE")
    private String secRoleType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secRoleId")
    private Collection<PrincipalOrgRole> principalOrgRoleCollection;

    public SecRole() {
    }

    public SecRole(Integer id) {
        this.id = id;
    }

    public SecRole(Integer id, String secRoleCode, String secRoleType) {
        this.id = id;
        this.secRoleCode = secRoleCode;
        this.secRoleType = secRoleType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecRoleCode() {
        return secRoleCode;
    }

    public void setSecRoleCode(String secRoleCode) {
        this.secRoleCode = secRoleCode;
    }

    public String getSecRoleType() {
        return secRoleType;
    }

    public void setSecRoleType(String secRoleType) {
        this.secRoleType = secRoleType;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecRole)) {
            return false;
        }
        SecRole other = (SecRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "govonca.tbs.ecorrwebapp.SecRole[ id=" + id + " ]";
    }
    
}
