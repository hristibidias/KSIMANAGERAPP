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
@Table(name = "piecesjointes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Piecesjointes.findAll", query = "SELECT p FROM Piecesjointes p")
    , @NamedQuery(name = "Piecesjointes.findByIdpj", query = "SELECT p FROM Piecesjointes p WHERE p.idpj = :idpj")
    , @NamedQuery(name = "Piecesjointes.findByNom", query = "SELECT p FROM Piecesjointes p WHERE p.nom = :nom")
    , @NamedQuery(name = "Piecesjointes.findByUrl", query = "SELECT p FROM Piecesjointes p WHERE p.url = :url")
    , @NamedQuery(name = "Piecesjointes.findByType", query = "SELECT p FROM Piecesjointes p WHERE p.type = :type")})
public class Piecesjointes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpj")
    private Integer idpj;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "url")
    private String url;
    @Size(max = 254)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "idtransaction", referencedColumnName = "idtransaction")
    @ManyToOne(optional = false)
    private Transaction idtransaction;

    public Piecesjointes() {
    }

    public Piecesjointes(Integer idpj) {
        this.idpj = idpj;
    }

    public Integer getIdpj() {
        return idpj;
    }

    public void setIdpj(Integer idpj) {
        this.idpj = idpj;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash += (idpj != null ? idpj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piecesjointes)) {
            return false;
        }
        Piecesjointes other = (Piecesjointes) object;
        if ((this.idpj == null && other.idpj != null) || (this.idpj != null && !this.idpj.equals(other.idpj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Piecesjointes[ idpj=" + idpj + " ]";
    }
    
}
