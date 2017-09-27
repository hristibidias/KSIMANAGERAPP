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
@Table(name = "personnelmission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnelmission.findAll", query = "SELECT p FROM Personnelmission p")
    , @NamedQuery(name = "Personnelmission.findByIdpers", query = "SELECT p FROM Personnelmission p WHERE p.personnelmissionPK.idpers = :idpers")
    , @NamedQuery(name = "Personnelmission.findByIdmission", query = "SELECT p FROM Personnelmission p WHERE p.personnelmissionPK.idmission = :idmission")
    , @NamedQuery(name = "Personnelmission.findByPrime", query = "SELECT p FROM Personnelmission p WHERE p.prime = :prime")})
public class Personnelmission implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonnelmissionPK personnelmissionPK;
    @Column(name = "prime")
    private BigInteger prime;
    @JoinColumn(name = "idmission", referencedColumnName = "idmission", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mission mission;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personnel personnel;

    public Personnelmission() {
    }

    public Personnelmission(PersonnelmissionPK personnelmissionPK) {
        this.personnelmissionPK = personnelmissionPK;
    }

    public Personnelmission(int idpers, int idmission) {
        this.personnelmissionPK = new PersonnelmissionPK(idpers, idmission);
    }

    public PersonnelmissionPK getPersonnelmissionPK() {
        return personnelmissionPK;
    }

    public void setPersonnelmissionPK(PersonnelmissionPK personnelmissionPK) {
        this.personnelmissionPK = personnelmissionPK;
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

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personnelmissionPK != null ? personnelmissionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnelmission)) {
            return false;
        }
        Personnelmission other = (Personnelmission) object;
        if ((this.personnelmissionPK == null && other.personnelmissionPK != null) || (this.personnelmissionPK != null && !this.personnelmissionPK.equals(other.personnelmissionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personnelmission[ personnelmissionPK=" + personnelmissionPK + " ]";
    }
    
}
