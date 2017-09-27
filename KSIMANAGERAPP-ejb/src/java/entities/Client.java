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
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findByIdpers", query = "SELECT c FROM Client c WHERE c.idpers = :idpers")
    , @NamedQuery(name = "Client.findByType", query = "SELECT c FROM Client c WHERE c.type = :type")
    , @NamedQuery(name = "Client.findByNomstructure", query = "SELECT c FROM Client c WHERE c.nomstructure = :nomstructure")
    , @NamedQuery(name = "Client.findByDomaine", query = "SELECT c FROM Client c WHERE c.domaine = :domaine")
    , @NamedQuery(name = "Client.findByNompers", query = "SELECT c FROM Client c WHERE c.nompers = :nompers")
    , @NamedQuery(name = "Client.findByPrenompers", query = "SELECT c FROM Client c WHERE c.prenompers = :prenompers")
    , @NamedQuery(name = "Client.findByDatenaisspers", query = "SELECT c FROM Client c WHERE c.datenaisspers = :datenaisspers")
    , @NamedQuery(name = "Client.findByCnipers", query = "SELECT c FROM Client c WHERE c.cnipers = :cnipers")
    , @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email")
    , @NamedQuery(name = "Client.findBySexe", query = "SELECT c FROM Client c WHERE c.sexe = :sexe")
    , @NamedQuery(name = "Client.findByTel", query = "SELECT c FROM Client c WHERE c.tel = :tel")
    , @NamedQuery(name = "Client.findByPhotos", query = "SELECT c FROM Client c WHERE c.photos = :photos")
    , @NamedQuery(name = "Client.findByVille", query = "SELECT c FROM Client c WHERE c.ville = :ville")
    , @NamedQuery(name = "Client.findByPays", query = "SELECT c FROM Client c WHERE c.pays = :pays")
    , @NamedQuery(name = "Client.findByQuartier", query = "SELECT c FROM Client c WHERE c.quartier = :quartier")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpers")
    private Integer idpers;
    @Size(max = 25)
    @Column(name = "type")
    private String type;
    @Size(max = 100)
    @Column(name = "nomstructure")
    private String nomstructure;
    @Size(max = 70)
    @Column(name = "domaine")
    private String domaine;
    @Size(max = 35)
    @Column(name = "nompers")
    private String nompers;
    @Size(max = 35)
    @Column(name = "prenompers")
    private String prenompers;
    @Column(name = "datenaisspers")
    @Temporal(TemporalType.DATE)
    private Date datenaisspers;
    @Size(max = 20)
    @Column(name = "cnipers")
    private String cnipers;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "email")
    private String email;
    @Size(max = 10)
    @Column(name = "sexe")
    private String sexe;
    @Size(max = 30)
    @Column(name = "tel")
    private String tel;
    @Size(max = 254)
    @Column(name = "photos")
    private String photos;
    @Size(max = 70)
    @Column(name = "ville")
    private String ville;
    @Size(max = 70)
    @Column(name = "pays")
    private String pays;
    @Size(max = 70)
    @Column(name = "quartier")
    private String quartier;
    @JoinColumn(name = "per_idpers", referencedColumnName = "idpers")
    @ManyToOne(optional = false)
    private Personnel perIdpers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpers")
    private Collection<Transaction> transactionCollection;

    public Client() {
    }

    public Client(Integer idpers) {
        this.idpers = idpers;
    }

    public Integer getIdpers() {
        return idpers;
    }

    public void setIdpers(Integer idpers) {
        this.idpers = idpers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getNompers() {
        return nompers;
    }

    public void setNompers(String nompers) {
        this.nompers = nompers;
    }

    public String getPrenompers() {
        return prenompers;
    }

    public void setPrenompers(String prenompers) {
        this.prenompers = prenompers;
    }

    public Date getDatenaisspers() {
        return datenaisspers;
    }

    public void setDatenaisspers(Date datenaisspers) {
        this.datenaisspers = datenaisspers;
    }

    public String getCnipers() {
        return cnipers;
    }

    public void setCnipers(String cnipers) {
        this.cnipers = cnipers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public Personnel getPerIdpers() {
        return perIdpers;
    }

    public void setPerIdpers(Personnel perIdpers) {
        this.perIdpers = perIdpers;
    }

    @XmlTransient
    public Collection<Transaction> getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(Collection<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpers != null ? idpers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idpers == null && other.idpers != null) || (this.idpers != null && !this.idpers.equals(other.idpers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ idpers=" + idpers + " ]";
    }
    
}
