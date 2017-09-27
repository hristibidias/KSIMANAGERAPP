/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hristi
 */
@Entity
@Table(name = "inventaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventaire.findAll", query = "SELECT i FROM Inventaire i")
    , @NamedQuery(name = "Inventaire.findByIdinventaire", query = "SELECT i FROM Inventaire i WHERE i.idinventaire = :idinventaire")
    , @NamedQuery(name = "Inventaire.findByNom", query = "SELECT i FROM Inventaire i WHERE i.nom = :nom")
    , @NamedQuery(name = "Inventaire.findByUrl", query = "SELECT i FROM Inventaire i WHERE i.url = :url")})
public class Inventaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinventaire")
    private Integer idinventaire;
    @Size(max = 100)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers")
    @ManyToOne(optional = false)
    private Personnel idpers;
    @JoinColumn(name = "idtransaction", referencedColumnName = "idtransaction")
    @ManyToOne(optional = false)
    private Transaction idtransaction;

    public Inventaire() {
    }

    public Inventaire(Integer idinventaire) {
        this.idinventaire = idinventaire;
    }

    public Integer getIdinventaire() {
        return idinventaire;
    }

    public void setIdinventaire(Integer idinventaire) {
        this.idinventaire = idinventaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        hash += (idinventaire != null ? idinventaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventaire)) {
            return false;
        }
        Inventaire other = (Inventaire) object;
        if ((this.idinventaire == null && other.idinventaire != null) || (this.idinventaire != null && !this.idinventaire.equals(other.idinventaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Inventaire[ idinventaire=" + idinventaire + " ]";
    }
    
}
