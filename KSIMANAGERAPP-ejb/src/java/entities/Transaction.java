/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hristi
 */
@Entity
@Table(name = "transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t")
    , @NamedQuery(name = "Transaction.findByIdtransaction", query = "SELECT t FROM Transaction t WHERE t.idtransaction = :idtransaction")
    , @NamedQuery(name = "Transaction.findByTypetrans", query = "SELECT t FROM Transaction t WHERE t.typetrans = :typetrans")
    , @NamedQuery(name = "Transaction.findByPoids", query = "SELECT t FROM Transaction t WHERE t.poids = :poids")
    , @NamedQuery(name = "Transaction.findByNumlta", query = "SELECT t FROM Transaction t WHERE t.numlta = :numlta")
    , @NamedQuery(name = "Transaction.findByNumbl", query = "SELECT t FROM Transaction t WHERE t.numbl = :numbl")})
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtransaction")
    private Integer idtransaction;
    @Size(max = 100)
    @Column(name = "typetrans")
    private String typetrans;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "poids")
    private Double poids;
    @Size(max = 50)
    @Column(name = "numlta")
    private String numlta;
    @Size(max = 50)
    @Column(name = "numbl")
    private String numbl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtransaction")
    private Collection<Piecesjointes> piecesjointesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtransaction")
    private Collection<Facture> factureCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtransaction")
    private Collection<Inventaire> inventaireCollection;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers")
    @ManyToOne(optional = false)
    private Client idpers;
    @JoinColumn(name = "per_idpers", referencedColumnName = "idpers")
    @ManyToOne(optional = false)
    private Personnel perIdpers;

    public Transaction() {
    }

    public Transaction(Integer idtransaction) {
        this.idtransaction = idtransaction;
    }

    public Integer getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(Integer idtransaction) {
        this.idtransaction = idtransaction;
    }

    public String getTypetrans() {
        return typetrans;
    }

    public void setTypetrans(String typetrans) {
        this.typetrans = typetrans;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public String getNumlta() {
        return numlta;
    }

    public void setNumlta(String numlta) {
        this.numlta = numlta;
    }

    public String getNumbl() {
        return numbl;
    }

    public void setNumbl(String numbl) {
        this.numbl = numbl;
    }

    @XmlTransient
    public Collection<Piecesjointes> getPiecesjointesCollection() {
        return piecesjointesCollection;
    }

    public void setPiecesjointesCollection(Collection<Piecesjointes> piecesjointesCollection) {
        this.piecesjointesCollection = piecesjointesCollection;
    }

    @XmlTransient
    public Collection<Facture> getFactureCollection() {
        return factureCollection;
    }

    public void setFactureCollection(Collection<Facture> factureCollection) {
        this.factureCollection = factureCollection;
    }

    @XmlTransient
    public Collection<Inventaire> getInventaireCollection() {
        return inventaireCollection;
    }

    public void setInventaireCollection(Collection<Inventaire> inventaireCollection) {
        this.inventaireCollection = inventaireCollection;
    }

    public Client getIdpers() {
        return idpers;
    }

    public void setIdpers(Client idpers) {
        this.idpers = idpers;
    }

    public Personnel getPerIdpers() {
        return perIdpers;
    }

    public void setPerIdpers(Personnel perIdpers) {
        this.perIdpers = perIdpers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtransaction != null ? idtransaction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.idtransaction == null && other.idtransaction != null) || (this.idtransaction != null && !this.idtransaction.equals(other.idtransaction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transaction[ idtransaction=" + idtransaction + " ]";
    }
    
}
