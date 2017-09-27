/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hristi
 */
@Entity
@Table(name = "prestatairemission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestatairemission.findAll", query = "SELECT p FROM Prestatairemission p")
    , @NamedQuery(name = "Prestatairemission.findByIdpers", query = "SELECT p FROM Prestatairemission p WHERE p.prestatairemissionPK.idpers = :idpers")
    , @NamedQuery(name = "Prestatairemission.findByIdmission", query = "SELECT p FROM Prestatairemission p WHERE p.prestatairemissionPK.idmission = :idmission")
    , @NamedQuery(name = "Prestatairemission.findByPrime", query = "SELECT p FROM Prestatairemission p WHERE p.prime = :prime")})
public class Prestatairemission implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrestatairemissionPK prestatairemissionPK;
    @Column(name = "prime")
    private BigInteger prime;
    @JoinColumn(name = "idmission", referencedColumnName = "idmission", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mission mission;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Prestataire prestataire;

    public Prestatairemission() {
    }

    public Prestatairemission(PrestatairemissionPK prestatairemissionPK) {
        this.prestatairemissionPK = prestatairemissionPK;
    }

    public Prestatairemission(int idpers, int idmission) {
        this.prestatairemissionPK = new PrestatairemissionPK(idpers, idmission);
    }

    public PrestatairemissionPK getPrestatairemissionPK() {
        return prestatairemissionPK;
    }

    public void setPrestatairemissionPK(PrestatairemissionPK prestatairemissionPK) {
        this.prestatairemissionPK = prestatairemissionPK;
    }

    public BigInteger getPrime() {
        return prime;
    }

    public void setPrime(BigInteger prime) {
        this.prime = prime;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Prestataire getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Prestataire prestataire) {
        this.prestataire = prestataire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prestatairemissionPK != null ? prestatairemissionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestatairemission)) {
            return false;
        }
        Prestatairemission other = (Prestatairemission) object;
        if ((this.prestatairemissionPK == null && other.prestatairemissionPK != null) || (this.prestatairemissionPK != null && !this.prestatairemissionPK.equals(other.prestatairemissionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prestatairemission[ prestatairemissionPK=" + prestatairemissionPK + " ]";
    }
    
}
