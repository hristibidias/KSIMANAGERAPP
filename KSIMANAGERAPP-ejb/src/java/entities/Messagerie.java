/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hristi
 */
@Entity
@Table(name = "messagerie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messagerie.findAll", query = "SELECT m FROM Messagerie m")
    , @NamedQuery(name = "Messagerie.findByIdmessage", query = "SELECT m FROM Messagerie m WHERE m.idmessage = :idmessage")
    , @NamedQuery(name = "Messagerie.findByDaterecep", query = "SELECT m FROM Messagerie m WHERE m.daterecep = :daterecep")
    , @NamedQuery(name = "Messagerie.findByMessage", query = "SELECT m FROM Messagerie m WHERE m.message = :message")})
public class Messagerie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmessage")
    private Integer idmessage;
    @Column(name = "daterecep")
    @Temporal(TemporalType.DATE)
    private Date daterecep;
    @Size(max = 2147483647)
    @Column(name = "message")
    private String message;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "messagerie")
    private Collection<Destinatairemessage> destinatairemessageCollection;

    public Messagerie() {
    }

    public Messagerie(Integer idmessage) {
        this.idmessage = idmessage;
    }

    public Integer getIdmessage() {
        return idmessage;
    }

    public void setIdmessage(Integer idmessage) {
        this.idmessage = idmessage;
    }

    public Date getDaterecep() {
        return daterecep;
    }

    public void setDaterecep(Date daterecep) {
        this.daterecep = daterecep;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlTransient
    public Collection<Destinatairemessage> getDestinatairemessageCollection() {
        return destinatairemessageCollection;
    }

    public void setDestinatairemessageCollection(Collection<Destinatairemessage> destinatairemessageCollection) {
        this.destinatairemessageCollection = destinatairemessageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmessage != null ? idmessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messagerie)) {
            return false;
        }
        Messagerie other = (Messagerie) object;
        if ((this.idmessage == null && other.idmessage != null) || (this.idmessage != null && !this.idmessage.equals(other.idmessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Messagerie[ idmessage=" + idmessage + " ]";
    }
    
}
