/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorr.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brouwerto
 */
@Entity
@Table(name = "INCOMING_CORRESPONDENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncomingCorrespondence.findAll", query = "SELECT i FROM IncomingCorrespondence i"),
    @NamedQuery(name = "IncomingCorrespondence.findById", query = "SELECT i FROM IncomingCorrespondence i WHERE i.id = :id"),
    @NamedQuery(name = "IncomingCorrespondence.findByDocumentDate", query = "SELECT i FROM IncomingCorrespondence i WHERE i.documentDate = :documentDate"),
    @NamedQuery(name = "IncomingCorrespondence.findByReceivedTime", query = "SELECT i FROM IncomingCorrespondence i WHERE i.receivedTime = :receivedTime"),
    @NamedQuery(name = "IncomingCorrespondence.findByEnteredTime", query = "SELECT i FROM IncomingCorrespondence i WHERE i.enteredTime = :enteredTime"),
    @NamedQuery(name = "IncomingCorrespondence.findByIncomingChannel", query = "SELECT i FROM IncomingCorrespondence i WHERE i.incomingChannel = :incomingChannel"),
    @NamedQuery(name = "IncomingCorrespondence.findByRecipientTypeCode", query = "SELECT i FROM IncomingCorrespondence i WHERE i.recipientTypeCode = :recipientTypeCode"),
    @NamedQuery(name = "IncomingCorrespondence.findBySubject", query = "SELECT i FROM IncomingCorrespondence i WHERE i.subject = :subject"),
    @NamedQuery(name = "IncomingCorrespondence.findByIncomingStatusCode", query = "SELECT i FROM IncomingCorrespondence i WHERE i.incomingStatusCode = :incomingStatusCode")})
public class IncomingCorrespondence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOCUMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date documentDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RECEIVED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTERED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "INCOMING_CHANNEL")
    private String incomingChannel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RECIPIENT_TYPE_CODE")
    private String recipientTypeCode;
    @Size(max = 1000)
    @Column(name = "SUBJECT")
    private String subject;
    @Size(max = 20)
    @Column(name = "INCOMING_STATUS_CODE")
    private String incomingStatusCode;
    @JoinColumn(name = "CLIENT_SNAPSHOT_ID", referencedColumnName = "ID")
    @ManyToOne
    private ClientSnapshot clientSnapshotId;
    @JoinColumn(name = "SINGLE_CORRESPONDENCE_ID", referencedColumnName = "ID")
    @ManyToOne
    private SingleCorrespondence singleCorrespondenceId;

    public IncomingCorrespondence() {
    }

    public IncomingCorrespondence(Integer id) {
        this.id = id;
    }

    public IncomingCorrespondence(Integer id, Date documentDate, Date receivedTime, Date enteredTime, String incomingChannel, String recipientTypeCode) {
        this.id = id;
        this.documentDate = documentDate;
        this.receivedTime = receivedTime;
        this.enteredTime = enteredTime;
        this.incomingChannel = incomingChannel;
        this.recipientTypeCode = recipientTypeCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Date getEnteredTime() {
        return enteredTime;
    }

    public void setEnteredTime(Date enteredTime) {
        this.enteredTime = enteredTime;
    }

    public String getIncomingChannel() {
        return incomingChannel;
    }

    public void setIncomingChannel(String incomingChannel) {
        this.incomingChannel = incomingChannel;
    }

    public String getRecipientTypeCode() {
        return recipientTypeCode;
    }

    public void setRecipientTypeCode(String recipientTypeCode) {
        this.recipientTypeCode = recipientTypeCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIncomingStatusCode() {
        return incomingStatusCode;
    }

    public void setIncomingStatusCode(String incomingStatusCode) {
        this.incomingStatusCode = incomingStatusCode;
    }

    public ClientSnapshot getClientSnapshotId() {
        return clientSnapshotId;
    }

    public void setClientSnapshotId(ClientSnapshot clientSnapshotId) {
        this.clientSnapshotId = clientSnapshotId;
    }

    public SingleCorrespondence getSingleCorrespondenceId() {
        return singleCorrespondenceId;
    }

    public void setSingleCorrespondenceId(SingleCorrespondence singleCorrespondenceId) {
        this.singleCorrespondenceId = singleCorrespondenceId;
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
        if (!(object instanceof IncomingCorrespondence)) {
            return false;
        }
        IncomingCorrespondence other = (IncomingCorrespondence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "govonca.tbs.ecorrwebapp.IncomingCorrespondence[ id=" + id + " ]";
    }
    
}
