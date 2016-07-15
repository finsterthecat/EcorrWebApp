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
@Table(name = "NOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findById", query = "SELECT n FROM Note n WHERE n.id = :id"),
    @NamedQuery(name = "Note.findByNoteTypeCode", query = "SELECT n FROM Note n WHERE n.noteTypeCode = :noteTypeCode"),
    @NamedQuery(name = "Note.findByNote", query = "SELECT n FROM Note n WHERE n.note = :note"),
    @NamedQuery(name = "Note.findByCreatedBy", query = "SELECT n FROM Note n WHERE n.createdBy = :createdBy"),
    @NamedQuery(name = "Note.findByCreatedTime", query = "SELECT n FROM Note n WHERE n.createdTime = :createdTime")})
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOTE_TYPE_CODE")
    private String noteTypeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "NOTE")
    private String note;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @JoinColumn(name = "SINGLE_CORRESPONDENCE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SingleCorrespondence singleCorrespondenceId;

    public Note() {
    }

    public Note(Integer id) {
        this.id = id;
    }

    public Note(Integer id, String noteTypeCode, String note, String createdBy, Date createdTime) {
        this.id = id;
        this.noteTypeCode = noteTypeCode;
        this.note = note;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteTypeCode() {
        return noteTypeCode;
    }

    public void setNoteTypeCode(String noteTypeCode) {
        this.noteTypeCode = noteTypeCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "govonca.tbs.ecorrwebapp.Note[ id=" + id + " ]";
    }
    
}
