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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cotations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotations.findAll", query = "SELECT c FROM Cotations c")
    , @NamedQuery(name = "Cotations.findByIdcotation", query = "SELECT c FROM Cotations c WHERE c.idcotation = :idcotation")
    , @NamedQuery(name = "Cotations.findByConcerne", query = "SELECT c FROM Cotations c WHERE c.concerne = :concerne")
    , @NamedQuery(name = "Cotations.findByMontant", query = "SELECT c FROM Cotations c WHERE c.montant = :montant")
    , @NamedQuery(name = "Cotations.findByDate", query = "SELECT c FROM Cotations c WHERE c.date = :date")})
public class Cotations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcotation")
    private Integer idcotation;
    @Size(max = 254)
    @Column(name = "concerne")
    private String concerne;
    @Size(max = 8)
    @Column(name = "montant")
    private String montant;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers")
    @ManyToOne(optional = false)
    private Personnel idpers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cotations")
    private Collection<Partenairecote> partenairecoteCollection;

    public Cotations() {
    }

    public Cotations(Integer idcotation) {
        this.idcotation = idcotation;
    }

    public Integer getIdcotation() {
        return idcotation;
    }

    public void setIdcotation(Integer idcotation) {
        this.idcotation = idcotation;
    }

    public String getConcerne() {
        return concerne;
    }

    public void setConcerne(String concerne) {
        this.concerne = concerne;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Personnel getIdpers() {
        return idpers;
    }

    public void setIdpers(Personnel idpers) {
        this.idpers = idpers;
    }

    @XmlTransient
    public Collection<Partenairecote> getPartenairecoteCollection() {
        return partenairecoteCollection;
    }

    public void setPartenairecoteCollection(Collection<Partenairecote> partenairecoteCollection) {
        this.partenairecoteCollection = partenairecoteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcotation != null ? idcotation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotations)) {
            return false;
        }
        Cotations other = (Cotations) object;
        if ((this.idcotation == null && other.idcotation != null) || (this.idcotation != null && !this.idcotation.equals(other.idcotation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cotations[ idcotation=" + idcotation + " ]";
    }
    
}
