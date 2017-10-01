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
@Table(name = "prestataire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestataire.nextId", query = "SELECT MAX(p.idpers) FROM Prestataire p")
    , @NamedQuery(name = "Prestataire.findAll", query = "SELECT p FROM Prestataire p")
    ,@NamedQuery(name = "Prestataire.findByIdpers", query = "SELECT p FROM Prestataire p WHERE p.idpers = :idpers")
    , @NamedQuery(name = "Prestataire.findByTypeprestation", query = "SELECT p FROM Prestataire p WHERE p.typeprestation = :typeprestation")
    , @NamedQuery(name = "Prestataire.findByNompers", query = "SELECT p FROM Prestataire p WHERE p.nompers = :nompers")
    , @NamedQuery(name = "Prestataire.findByPrenompers", query = "SELECT p FROM Prestataire p WHERE p.prenompers = :prenompers")
    , @NamedQuery(name = "Prestataire.findByDatenaisspers", query = "SELECT p FROM Prestataire p WHERE p.datenaisspers = :datenaisspers")
    , @NamedQuery(name = "Prestataire.findByCnipers", query = "SELECT p FROM Prestataire p WHERE p.cnipers = :cnipers")
    , @NamedQuery(name = "Prestataire.findByEmail", query = "SELECT p FROM Prestataire p WHERE p.email = :email")
    , @NamedQuery(name = "Prestataire.findBySexe", query = "SELECT p FROM Prestataire p WHERE p.sexe = :sexe")
    , @NamedQuery(name = "Prestataire.findByTel", query = "SELECT p FROM Prestataire p WHERE p.tel = :tel")
    , @NamedQuery(name = "Prestataire.findByPhotos", query = "SELECT p FROM Prestataire p WHERE p.photos = :photos")
    , @NamedQuery(name = "Prestataire.findByVille", query = "SELECT p FROM Prestataire p WHERE p.ville = :ville")
    , @NamedQuery(name = "Prestataire.findByPays", query = "SELECT p FROM Prestataire p WHERE p.pays = :pays")
    , @NamedQuery(name = "Prestataire.findByQuartier", query = "SELECT p FROM Prestataire p WHERE p.quartier = :quartier")})
public class Prestataire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpers")
    private Integer idpers;
    @Size(max = 60)
    @Column(name = "typeprestation")
    private String typeprestation;
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
    @Size(max = 20)
    @Column(name = "tel")
    private String tel;
    @Size(max = 254)
    @Column(name = "photos")
    private String photos;
    @Size(max = 60)
    @Column(name = "ville")
    private String ville;
    @Size(max = 60)
    @Column(name = "pays")
    private String pays;
    @Size(max = 60)
    @Column(name = "quartier")
    private String quartier;
    @JoinColumn(name = "per_idpers", referencedColumnName = "idpers")
    @ManyToOne(optional = false)
    private Personnel perIdpers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prestataire")
    private Collection<Prestatairemission> prestatairemissionCollection;

    public Prestataire() {
    }

    public Prestataire(Integer idpers) {
        this.idpers = idpers;
    }

    public Integer getIdpers() {
        return idpers;
    }

    public void setIdpers(Integer idpers) {
        this.idpers = idpers;
    }

    public String getTypeprestation() {
        return typeprestation;
    }

    public void setTypeprestation(String typeprestation) {
        this.typeprestation = typeprestation;
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
    public Collection<Prestatairemission> getPrestatairemissionCollection() {
        return prestatairemissionCollection;
    }

    public void setPrestatairemissionCollection(Collection<Prestatairemission> prestatairemissionCollection) {
        this.prestatairemissionCollection = prestatairemissionCollection;
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
        if (!(object instanceof Prestataire)) {
            return false;
        }
        Prestataire other = (Prestataire) object;
        if ((this.idpers == null && other.idpers != null) || (this.idpers != null && !this.idpers.equals(other.idpers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prestataire[ idpers=" + idpers + " ]";
    }

    public void setPerIdpers(int idpersonnel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
