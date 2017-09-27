/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hristi
 */
@Entity
@Table(name = "facture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f")
    , @NamedQuery(name = "Facture.findByIdfacture", query = "SELECT f FROM Facture f WHERE f.idfacture = :idfacture")
    , @NamedQuery(name = "Facture.findByConcerne", query = "SELECT f FROM Facture f WHERE f.concerne = :concerne")
    , @NamedQuery(name = "Facture.findByMontant", query = "SELECT f FROM Facture f WHERE f.montant = :montant")
    , @NamedQuery(name = "Facture.findByDate", query = "SELECT f FROM Facture f WHERE f.date = :date")})
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfacture")
    private Integer idfacture;
    @Size(max = 254)
    @Column(name = "concerne")
    private String concerne;
    @Size(max = 11)
    @Column(name = "montant")
    private String montant;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers")
    @ManyToOne(optional = false)
    private Personnel idpers;
    @JoinColumn(name = "idtransaction", referencedColumnName = "idtransaction")
    @ManyToOne(optional = false)
    private Transaction idtransaction;

    public Facture() {
    }

    public Facture(Integer idfacture) {
        this.idfacture = idfacture;
    }

    public Integer getIdfacture() {
        return idfacture;
    }

    public void setIdfacture(Integer idfacture) {
        this.idfacture = idfacture;
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

    public Transaction getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(Transaction idtransaction) {
        this.idtransaction = idtransaction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfacture != null ? idfacture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.idfacture == null && other.idfacture != null) || (this.idfacture != null && !this.idfacture.equals(other.idfacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Facture[ idfacture=" + idfacture + " ]";
    }
    
}
