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
@Table(name = "partenaires")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partenaires.findAll", query = "SELECT p FROM Partenaires p")
    , @NamedQuery(name = "Partenaires.findByIdpartenaire", query = "SELECT p FROM Partenaires p WHERE p.idpartenaire = :idpartenaire")
    , @NamedQuery(name = "Partenaires.findByTel", query = "SELECT p FROM Partenaires p WHERE p.tel = :tel")
    , @NamedQuery(name = "Partenaires.findByEmail", query = "SELECT p FROM Partenaires p WHERE p.email = :email")
    , @NamedQuery(name = "Partenaires.findByNomstructure", query = "SELECT p FROM Partenaires p WHERE p.nomstructure = :nomstructure")
    , @NamedQuery(name = "Partenaires.findByDomaine", query = "SELECT p FROM Partenaires p WHERE p.domaine = :domaine")
    , @NamedQuery(name = "Partenaires.findByPays", query = "SELECT p FROM Partenaires p WHERE p.pays = :pays")
    , @NamedQuery(name = "Partenaires.findByVille", query = "SELECT p FROM Partenaires p WHERE p.ville = :ville")})
public class Partenaires implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpartenaire")
    private Integer idpartenaire;
    @Size(max = 30)
    @Column(name = "tel")
    private String tel;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "email")
    private String email;
    @Size(max = 100)
    @Column(name = "nomstructure")
    private String nomstructure;
    @Size(max = 254)
    @Column(name = "domaine")
    private String domaine;
    @Size(max = 70)
    @Column(name = "pays")
    private String pays;
    @Size(max = 70)
    @Column(name = "ville")
    private String ville;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partenaires")
    private Collection<Partenairecote> partenairecoteCollection;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers")
    @ManyToOne(optional = false)
    private Personnel idpers;

    public Partenaires() {
    }

    public Partenaires(Integer idpartenaire) {
        this.idpartenaire = idpartenaire;
    }

    public Integer getIdpartenaire() {
        return idpartenaire;
    }

    public void setIdpartenaire(Integer idpartenaire) {
        this.idpartenaire = idpartenaire;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomstructure() {
        return nomstructure;
    }

    public void setNomstructure(String nomstructure) {
        this.nomstructure = nomstructure;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @XmlTransient
    public Collection<Partenairecote> getPartenairecoteCollection() {
        return partenairecoteCollection;
    }

    public void setPartenairecoteCollection(Collection<Partenairecote> partenairecoteCollection) {
        this.partenairecoteCollection = partenairecoteCollection;
    }

    public Personnel getIdpers() {
        return idpers;
    }

    public void setIdpers(Personnel idpers) {
        this.idpers = idpers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpartenaire != null ? idpartenaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partenaires)) {
            return false;
        }
        Partenaires other = (Partenaires) object;
        if ((this.idpartenaire == null && other.idpartenaire != null) || (this.idpartenaire != null && !this.idpartenaire.equals(other.idpartenaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Partenaires[ idpartenaire=" + idpartenaire + " ]";
    }
    
}
