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
@Table(name = "permission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permission.nextId", query = "SELECT MAX(p.idpermission) FROM Permission p")
    ,@NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p")
    , @NamedQuery(name = "Permission.findByIdpermission", query = "SELECT p FROM Permission p WHERE p.idpermission = :idpermission")
    , @NamedQuery(name = "Permission.findByDatedep", query = "SELECT p FROM Permission p WHERE p.datedep = :datedep")
    , @NamedQuery(name = "Permission.findByDateretour", query = "SELECT p FROM Permission p WHERE p.dateretour = :dateretour")
    , @NamedQuery(name = "Permission.findByMotif", query = "SELECT p FROM Permission p WHERE p.motif = :motif")
    , @NamedQuery(name = "Permission.findByNbrpermission", query = "SELECT p FROM Permission p WHERE p.nbrpermission = :nbrpermission")})
public class Permission implements Serializable {

    @Size(max = 20)
    @Column(name = "Statut", length = 20)
    private String statut;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpermission")
    private Integer idpermission;
    @Column(name = "datedep")
    @Temporal(TemporalType.DATE)
    private Date datedep;
    @Column(name = "dateretour")
    @Temporal(TemporalType.DATE)
    private Date dateretour;
    @Size(max = 254)
    @Column(name = "motif")
    private String motif;
    @Column(name = "nbrpermission")
    private Integer nbrpermission;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers")
    @ManyToOne(optional = false)
    private Personnel idpers;

    public Permission() {
    }

    public Permission(Integer idpermission) {
        this.idpermission = idpermission;
    }

    public Integer getIdpermission() {
        return idpermission;
    }

    public void setIdpermission(Integer idpermission) {
        this.idpermission = idpermission;
    }

    public Date getDatedep() {
        return datedep;
    }

    public void setDatedep(Date datedep) {
        this.datedep = datedep;
    }

    public Date getDateretour() {
        return dateretour;
    }

    public void setDateretour(Date dateretour) {
        this.dateretour = dateretour;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Integer getNbrpermission() {
        return nbrpermission;
    }

    public void setNbrpermission(Integer nbrpermission) {
        this.nbrpermission = nbrpermission;
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
        hash += (idpermission != null ? idpermission.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permission)) {
            return false;
        }
        Permission other = (Permission) object;
        if ((this.idpermission == null && other.idpermission != null) || (this.idpermission != null && !this.idpermission.equals(other.idpermission))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Permission[ idpermission=" + idpermission + " ]";
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
}
