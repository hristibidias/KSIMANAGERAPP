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
@Table(name = "mission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mission.findAll", query = "SELECT m FROM Mission m")
    , @NamedQuery(name = "Mission.findByIdmission", query = "SELECT m FROM Mission m WHERE m.idmission = :idmission")
    , @NamedQuery(name = "Mission.findByDatedebmission", query = "SELECT m FROM Mission m WHERE m.datedebmission = :datedebmission")
    , @NamedQuery(name = "Mission.findByDatefinmission", query = "SELECT m FROM Mission m WHERE m.datefinmission = :datefinmission")
    , @NamedQuery(name = "Mission.findByLieumission", query = "SELECT m FROM Mission m WHERE m.lieumission = :lieumission")
    , @NamedQuery(name = "Mission.findByDescriptionmission", query = "SELECT m FROM Mission m WHERE m.descriptionmission = :descriptionmission")})
public class Mission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmission")
    private Integer idmission;
    @Column(name = "datedebmission")
    @Temporal(TemporalType.DATE)
    private Date datedebmission;
    @Column(name = "datefinmission")
    @Temporal(TemporalType.DATE)
    private Date datefinmission;
    @Size(max = 60)
    @Column(name = "lieumission")
    private String lieumission;
    @Size(max = 254)
    @Column(name = "descriptionmission")
    private String descriptionmission;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mission")
    private Collection<Prestatairemission> prestatairemissionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mission")
    private Collection<Personnelmission> personnelmissionCollection;

    public Mission() {
    }

    public Mission(Integer idmission) {
        this.idmission = idmission;
    }

    public Integer getIdmission() {
        return idmission;
    }

    public void setIdmission(Integer idmission) {
        this.idmission = idmission;
    }

    public Date getDatedebmission() {
        return datedebmission;
    }

    public void setDatedebmission(Date datedebmission) {
        this.datedebmission = datedebmission;
    }

    public Date getDatefinmission() {
        return datefinmission;
    }

    public void setDatefinmission(Date datefinmission) {
        this.datefinmission = datefinmission;
    }

    public String getLieumission() {
        return lieumission;
    }

    public void setLieumission(String lieumission) {
        this.lieumission = lieumission;
    }

    public String getDescriptionmission() {
        return descriptionmission;
    }

    public void setDescriptionmission(String descriptionmission) {
        this.descriptionmission = descriptionmission;
    }

    @XmlTransient
    public Collection<Prestatairemission> getPrestatairemissionCollection() {
        return prestatairemissionCollection;
    }

    public void setPrestatairemissionCollection(Collection<Prestatairemission> prestatairemissionCollection) {
        this.prestatairemissionCollection = prestatairemissionCollection;
    }

    @XmlTransient
    public Collection<Personnelmission> getPersonnelmissionCollection() {
        return personnelmissionCollection;
    }

    public void setPersonnelmissionCollection(Collection<Personnelmission> personnelmissionCollection) {
        this.personnelmissionCollection = personnelmissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmission != null ? idmission.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mission)) {
            return false;
        }
        Mission other = (Mission) object;
        if ((this.idmission == null && other.idmission != null) || (this.idmission != null && !this.idmission.equals(other.idmission))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mission[ idmission=" + idmission + " ]";
    }
    
}
