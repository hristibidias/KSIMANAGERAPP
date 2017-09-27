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
@Table(name = "partenairecote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partenairecote.findAll", query = "SELECT p FROM Partenairecote p")
    , @NamedQuery(name = "Partenairecote.findByIdcotation", query = "SELECT p FROM Partenairecote p WHERE p.partenairecotePK.idcotation = :idcotation")
    , @NamedQuery(name = "Partenairecote.findByIdpartenaire", query = "SELECT p FROM Partenairecote p WHERE p.partenairecotePK.idpartenaire = :idpartenaire")
    , @NamedQuery(name = "Partenairecote.findByDate", query = "SELECT p FROM Partenairecote p WHERE p.date = :date")})
public class Partenairecote implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PartenairecotePK partenairecotePK;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "idcotation", referencedColumnName = "idcotation", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cotations cotations;
    @JoinColumn(name = "idpartenaire", referencedColumnName = "idpartenaire", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Partenaires partenaires;

    public Partenairecote() {
    }

    public Partenairecote(PartenairecotePK partenairecotePK) {
        this.partenairecotePK = partenairecotePK;
    }

    public Partenairecote(int idcotation, int idpartenaire) {
        this.partenairecotePK = new PartenairecotePK(idcotation, idpartenaire);
    }

    public PartenairecotePK getPartenairecotePK() {
        return partenairecotePK;
    }

    public void setPartenairecotePK(PartenairecotePK partenairecotePK) {
        this.partenairecotePK = partenairecotePK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Cotations getCotations() {
        return cotations;
    }

    public void setCotations(Cotations cotations) {
        this.cotations = cotations;
    }

    public Partenaires getPartenaires() {
        return partenaires;
    }

    public void setPartenaires(Partenaires partenaires) {
        this.partenaires = partenaires;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partenairecotePK != null ? partenairecotePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partenairecote)) {
            return false;
        }
        Partenairecote other = (Partenairecote) object;
        if ((this.partenairecotePK == null && other.partenairecotePK != null) || (this.partenairecotePK != null && !this.partenairecotePK.equals(other.partenairecotePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Partenairecote[ partenairecotePK=" + partenairecotePK + " ]";
    }
    
}
