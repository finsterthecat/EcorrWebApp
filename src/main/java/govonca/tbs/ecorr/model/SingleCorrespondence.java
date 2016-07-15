/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorr.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author brouwerto
 */
@Entity
@Table(name = "SINGLE_CORRESPONDENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SingleCorrespondence.findAll", query = "SELECT s FROM SingleCorrespondence s"),
    @NamedQuery(name = "SingleCorrespondence.findById", query = "SELECT s FROM SingleCorrespondence s WHERE s.id = :id"),
    @NamedQuery(name = "SingleCorrespondence.findByCaseNumber", query = "SELECT s FROM SingleCorrespondence s WHERE s.caseNumber = :caseNumber"),
    @NamedQuery(name = "SingleCorrespondence.findByCaseStatusCode", query = "SELECT s FROM SingleCorrespondence s WHERE s.caseStatusCode = :caseStatusCode"),
    @NamedQuery(name = "SingleCorrespondence.findByClosedDate", query = "SELECT s FROM SingleCorrespondence s WHERE s.closedDate = :closedDate"),
    @NamedQuery(name = "SingleCorrespondence.findByDueDate", query = "SELECT s FROM SingleCorrespondence s WHERE s.dueDate = :dueDate"),
    @NamedQuery(name = "SingleCorrespondence.findBySensitiveFlag", query = "SELECT s FROM SingleCorrespondence s WHERE s.sensitiveFlag = :sensitiveFlag"),
    @NamedQuery(name = "SingleCorrespondence.findByContentiousFlag", query = "SELECT s FROM SingleCorrespondence s WHERE s.contentiousFlag = :contentiousFlag"),
    @NamedQuery(name = "SingleCorrespondence.findByCreateTime", query = "SELECT s FROM SingleCorrespondence s WHERE s.createTime = :createTime")})
public class SingleCorrespondence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CASE_NUMBER")
    private String caseNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CASE_STATUS_CODE")
    private String caseStatusCode;
    @Column(name = "CLOSED_DATE")
    @Temporal(TemporalType.DATE)
    private Date closedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SENSITIVE_FLAG")
    private short sensitiveFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTENTIOUS_FLAG")
    private short contentiousFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "singleCorrespondenceId")
    private Collection<Note> noteCollection;
    @JoinColumn(name = "CLIENT_SNAPSHOT_ID", referencedColumnName = "ID")
    @ManyToOne
    private ClientSnapshot clientSnapshotId;
    @OneToMany(mappedBy = "singleCorrespondenceId")
    private Collection<IncomingCorrespondence> incomingCorrespondenceCollection;

    public SingleCorrespondence() {
    }

    public SingleCorrespondence(Integer id) {
        this.id = id;
    }

    public SingleCorrespondence(Integer id, String caseNumber, String caseStatusCode, Date dueDate, short sensitiveFlag, short contentiousFlag, Date createTime) {
        this.id = id;
        this.caseNumber = caseNumber;
        this.caseStatusCode = caseStatusCode;
        this.dueDate = dueDate;
        this.sensitiveFlag = sensitiveFlag;
        this.contentiousFlag = contentiousFlag;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCaseStatusCode() {
        return caseStatusCode;
    }

    public void setCaseStatusCode(String caseStatusCode) {
        this.caseStatusCode = caseStatusCode;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public short getSensitiveFlag() {
        return sensitiveFlag;
    }

    public void setSensitiveFlag(short sensitiveFlag) {
        this.sensitiveFlag = sensitiveFlag;
    }

    public short getContentiousFlag() {
        return contentiousFlag;
    }

    public void setContentiousFlag(short contentiousFlag) {
        this.contentiousFlag = contentiousFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @XmlTransient
    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    public ClientSnapshot getClientSnapshotId() {
        return clientSnapshotId;
    }

    public void setClientSnapshotId(ClientSnapshot clientSnapshotId) {
        this.clientSnapshotId = clientSnapshotId;
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
        if (!(object instanceof SingleCorrespondence)) {
            return false;
        }
        SingleCorrespondence other = (SingleCorrespondence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "govonca.tbs.ecorrwebapp.SingleCorrespondence[ id=" + id + " ]";
    }
    
}
