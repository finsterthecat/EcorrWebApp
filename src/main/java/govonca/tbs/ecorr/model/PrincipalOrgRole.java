/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorr.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brouwerto
 */
@Entity
@Table(name = "PRINCIPAL_ORG_ROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrincipalOrgRole.findAll", query = "SELECT p FROM PrincipalOrgRole p"),
    @NamedQuery(name = "PrincipalOrgRole.findById", query = "SELECT p FROM PrincipalOrgRole p WHERE p.id = :id")})
public class PrincipalOrgRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "SEC_ROLE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SecRole secRoleId;
    @JoinColumn(name = "PRINCIPAL_ID", referencedColumnName = "PRINCIPAL_ID")
    @ManyToOne(optional = false)
    private User principalId;

    public PrincipalOrgRole() {
    }

    public PrincipalOrgRole(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SecRole getSecRoleId() {
        return secRoleId;
    }

    public void setSecRoleId(SecRole secRoleId) {
        this.secRoleId = secRoleId;
    }

    public User getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(User principalId) {
        this.principalId = principalId;
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
        if (!(object instanceof PrincipalOrgRole)) {
            return false;
        }
        PrincipalOrgRole other = (PrincipalOrgRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "govonca.tbs.ecorrwebapp.PrincipalOrgRole[ id=" + id + " ]";
    }
    
}
