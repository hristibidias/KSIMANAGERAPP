/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hristi
 */
@Entity
@Table(name = "destinatairemessage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Destinatairemessage.findAll", query = "SELECT d FROM Destinatairemessage d")
    , @NamedQuery(name = "Destinatairemessage.findByIdpers", query = "SELECT d FROM Destinatairemessage d WHERE d.destinatairemessagePK.idpers = :idpers")
    , @NamedQuery(name = "Destinatairemessage.findByIdmessage", query = "SELECT d FROM Destinatairemessage d WHERE d.destinatairemessagePK.idmessage = :idmessage")
    , @NamedQuery(name = "Destinatairemessage.findByDateenvoi", query = "SELECT d FROM Destinatairemessage d WHERE d.dateenvoi = :dateenvoi")})
public class Destinatairemessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DestinatairemessagePK destinatairemessagePK;
    @Column(name = "dateenvoi")
    @Temporal(TemporalType.DATE)
    private Date dateenvoi;
    @JoinColumn(name = "idmessage", referencedColumnName = "idmessage", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Messagerie messagerie;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personnel personnel;

    public Destinatairemessage() {
    }

    public Destinatairemessage(DestinatairemessagePK destinatairemessagePK) {
        this.destinatairemessagePK = destinatairemessagePK;
    }

    public Destinatairemessage(int idpers, int idmessage) {
        this.destinatairemessagePK = new DestinatairemessagePK(idpers, idmessage);
    }

    public DestinatairemessagePK getDestinatairemessagePK() {
        return destinatairemessagePK;
    }

    public void setDestinatairemessagePK(DestinatairemessagePK destinatairemessagePK) {
        this.destinatairemessagePK = destinatairemessagePK;
    }

    public Date getDateenvoi() {
        return dateenvoi;
    }

    public void setDateenvoi(Date dateenvoi) {
        this.dateenvoi = dateenvoi;
    }

    public Messagerie getMessagerie() {
        return messagerie;
    }

    public void setMessagerie(Messagerie messagerie) {
        this.messagerie = messagerie;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (destinatairemessagePK != null ? destinatairemessagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destinatairemessage)) {
            return false;
        }
        Destinatairemessage other = (Destinatairemessage) object;
        if ((this.destinatairemessagePK == null && other.destinatairemessagePK != null) || (this.destinatairemessagePK != null && !this.destinatairemessagePK.equals(other.destinatairemessagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Destinatairemessage[ destinatairemessagePK=" + destinatairemessagePK + " ]";
    }
    
}
