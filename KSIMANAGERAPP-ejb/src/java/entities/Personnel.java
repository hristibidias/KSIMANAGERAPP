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
@Table(name = "personnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnel.nextId", query = "SELECT MAX(p.idpers) FROM Personnel p")
    ,@NamedQuery(name = "Personnel.findAll", query = "SELECT p FROM Personnel p")
    , @NamedQuery(name = "Personnel.findByIdpers", query = "SELECT p FROM Personnel p WHERE p.idpers = :idpers")
    , @NamedQuery(name = "Personnel.findByLoginMdp", query = "SELECT p FROM Personnel p WHERE p.login = :login AND p.password = :password")
    , @NamedQuery(name = "Personnel.findByLogin", query = "SELECT p FROM Personnel p WHERE p.login = :login")
    , @NamedQuery(name = "Personnel.findByPassword", query = "SELECT p FROM Personnel p WHERE p.password = :password")
    , @NamedQuery(name = "Personnel.findByDatedeb", query = "SELECT p FROM Personnel p WHERE p.datedeb = :datedeb")
    , @NamedQuery(name = "Personnel.findByNbrconnect", query = "SELECT p FROM Personnel p WHERE p.nbrconnect = :nbrconnect")
    , @NamedQuery(name = "Personnel.findByLastconnect", query = "SELECT p FROM Personnel p WHERE p.lastconnect = :lastconnect")
    , @NamedQuery(name = "Personnel.findByLastlogout", query = "SELECT p FROM Personnel p WHERE p.lastlogout = :lastlogout")
    , @NamedQuery(name = "Personnel.findByToken", query = "SELECT p FROM Personnel p WHERE p.token = :token")
    , @NamedQuery(name = "Personnel.findByResettoken", query = "SELECT p FROM Personnel p WHERE p.resettoken = :resettoken")
    , @NamedQuery(name = "Personnel.findByMatricule", query = "SELECT p FROM Personnel p WHERE p.matricule = :matricule")
    , @NamedQuery(name = "Personnel.findByReferent", query = "SELECT p FROM Personnel p WHERE p.referent = :referent")
    , @NamedQuery(name = "Personnel.findByPoste", query = "SELECT p FROM Personnel p WHERE p.poste = :poste")
    , @NamedQuery(name = "Personnel.findByNompers", query = "SELECT p FROM Personnel p WHERE p.nompers = :nompers")
    , @NamedQuery(name = "Personnel.findByPrenompers", query = "SELECT p FROM Personnel p WHERE p.prenompers = :prenompers")
    , @NamedQuery(name = "Personnel.findByDatenaisspers", query = "SELECT p FROM Personnel p WHERE p.datenaisspers = :datenaisspers")
    , @NamedQuery(name = "Personnel.findByCnipers", query = "SELECT p FROM Personnel p WHERE p.cnipers = :cnipers")
    , @NamedQuery(name = "Personnel.findByEmail", query = "SELECT p FROM Personnel p WHERE p.email = :email")
    , @NamedQuery(name = "Personnel.findBySexe", query = "SELECT p FROM Personnel p WHERE p.sexe = :sexe")
    , @NamedQuery(name = "Personnel.findByTel", query = "SELECT p FROM Personnel p WHERE p.tel = :tel")
    , @NamedQuery(name = "Personnel.findByPhotos", query = "SELECT p FROM Personnel p WHERE p.photos = :photos")
    , @NamedQuery(name = "Personnel.findByVille", query = "SELECT p FROM Personnel p WHERE p.ville = :ville")
    , @NamedQuery(name = "Personnel.findByPays", query = "SELECT p FROM Personnel p WHERE p.pays = :pays")
    , @NamedQuery(name = "Personnel.findByQuartier", query = "SELECT p FROM Personnel p WHERE p.quartier = :quartier")})
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpers")
    private Integer idpers;
    @Size(max = 30)
    @Column(name = "login")
    private String login;
    @Size(max = 30)
    @Column(name = "password")
    private String password;
    @Column(name = "datedeb")
    @Temporal(TemporalType.DATE)
    private Date datedeb;
    @Column(name = "nbrconnect")
    private Integer nbrconnect;
    @Column(name = "lastconnect")
    @Temporal(TemporalType.DATE)
    private Date lastconnect;
    @Column(name = "lastlogout")
    @Temporal(TemporalType.DATE)
    private Date lastlogout;
    @Size(max = 254)
    @Column(name = "token")
    private String token;
    @Size(max = 254)
    @Column(name = "resettoken")
    private String resettoken;
    @Size(max = 20)
    @Column(name = "matricule")
    private String matricule;
    @Size(max = 254)
    @Column(name = "referent")
    private String referent;
    @Size(max = 100)
    @Column(name = "poste")
    private String poste;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpers")
    private Collection<Cotations> cotationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perIdpers")
    private Collection<Prestataire> prestataireCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personnel")
    private Collection<Privileges> privilegesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personnel")
    private Collection<Destinatairemessage> destinatairemessageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpers")
    private Collection<Partenaires> partenairesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpers")
    private Collection<Permission> permissionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpers")
    private Collection<Conges> congesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpers")
    private Collection<Journalisation> journalisationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personnel")
    private Collection<Personnelmission> personnelmissionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpers")
    private Collection<Facture> factureCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perIdpers")
    private Collection<Client> clientCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpers")
    private Collection<Inventaire> inventaireCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perIdpers")
    private Collection<Transaction> transactionCollection;

    public Personnel() {
    }

    public Personnel(Integer idpers) {
        this.idpers = idpers;
    }

    public Integer getIdpers() {
        return idpers;
    }

    public void setIdpers(Integer idpers) {
        this.idpers = idpers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(Date datedeb) {
        this.datedeb = datedeb;
    }

    public Integer getNbrconnect() {
        return nbrconnect;
    }

    public void setNbrconnect(Integer nbrconnect) {
        this.nbrconnect = nbrconnect;
    }

    public Date getLastconnect() {
        return lastconnect;
    }

    public void setLastconnect(Date lastconnect) {
        this.lastconnect = lastconnect;
    }

    public Date getLastlogout() {
        return lastlogout;
    }

    public void setLastlogout(Date lastlogout) {
        this.lastlogout = lastlogout;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResettoken() {
        return resettoken;
    }

    public void setResettoken(String resettoken) {
        this.resettoken = resettoken;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getReferent() {
        return referent;
    }

    public void setReferent(String referent) {
        this.referent = referent;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
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

    @XmlTransient
    public Collection<Cotations> getCotationsCollection() {
        return cotationsCollection;
    }

    public void setCotationsCollection(Collection<Cotations> cotationsCollection) {
        this.cotationsCollection = cotationsCollection;
    }

    @XmlTransient
    public Collection<Prestataire> getPrestataireCollection() {
        return prestataireCollection;
    }

    public void setPrestataireCollection(Collection<Prestataire> prestataireCollection) {
        this.prestataireCollection = prestataireCollection;
    }

    @XmlTransient
    public Collection<Privileges> getPrivilegesCollection() {
        return privilegesCollection;
    }

    public void setPrivilegesCollection(Collection<Privileges> privilegesCollection) {
        this.privilegesCollection = privilegesCollection;
    }

    @XmlTransient
    public Collection<Destinatairemessage> getDestinatairemessageCollection() {
        return destinatairemessageCollection;
    }

    public void setDestinatairemessageCollection(Collection<Destinatairemessage> destinatairemessageCollection) {
        this.destinatairemessageCollection = destinatairemessageCollection;
    }

    @XmlTransient
    public Collection<Partenaires> getPartenairesCollection() {
        return partenairesCollection;
    }

    public void setPartenairesCollection(Collection<Partenaires> partenairesCollection) {
        this.partenairesCollection = partenairesCollection;
    }

    @XmlTransient
    public Collection<Permission> getPermissionCollection() {
        return permissionCollection;
    }

    public void setPermissionCollection(Collection<Permission> permissionCollection) {
        this.permissionCollection = permissionCollection;
    }

    @XmlTransient
    public Collection<Conges> getCongesCollection() {
        return congesCollection;
    }

    public void setCongesCollection(Collection<Conges> congesCollection) {
        this.congesCollection = congesCollection;
    }

    @XmlTransient
    public Collection<Journalisation> getJournalisationCollection() {
        return journalisationCollection;
    }

    public void setJournalisationCollection(Collection<Journalisation> journalisationCollection) {
        this.journalisationCollection = journalisationCollection;
    }

    @XmlTransient
    public Collection<Personnelmission> getPersonnelmissionCollection() {
        return personnelmissionCollection;
    }

    public void setPersonnelmissionCollection(Collection<Personnelmission> personnelmissionCollection) {
        this.personnelmissionCollection = personnelmissionCollection;
    }

    @XmlTransient
    public Collection<Facture> getFactureCollection() {
        return factureCollection;
    }

    public void setFactureCollection(Collection<Facture> factureCollection) {
        this.factureCollection = factureCollection;
    }

    @XmlTransient
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @XmlTransient
    public Collection<Inventaire> getInventaireCollection() {
        return inventaireCollection;
    }

    public void setInventaireCollection(Collection<Inventaire> inventaireCollection) {
        this.inventaireCollection = inventaireCollection;
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
        if (!(object instanceof Personnel)) {
            return false;
        }
        Personnel other = (Personnel) object;
        if ((this.idpers == null && other.idpers != null) || (this.idpers != null && !this.idpers.equals(other.idpers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personnel[ idpers=" + idpers + " ]";
    }

}
