/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Hristi
 */
@Embeddable
public class PersonnelmissionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idpers")
    private int idpers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmission")
    private int idmission;

    public PersonnelmissionPK() {
    }

    public PersonnelmissionPK(int idpers, int idmission) {
        this.idpers = idpers;
        this.idmission = idmission;
    }

    public int getIdpers() {
        return idpers;
    }

    public void setIdpers(int idpers) {
        this.idpers = idpers;
    }

    public int getIdmission() {
        return idmission;
    }

    public void setIdmission(int idmission) {
        this.idmission = idmission;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idpers;
        hash += (int) idmission;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonnelmissionPK)) {
            return false;
        }
        PersonnelmissionPK other = (PersonnelmissionPK) object;
        if (this.idpers != other.idpers) {
            return false;
        }
        if (this.idmission != other.idmission) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PersonnelmissionPK[ idpers=" + idpers + ", idmission=" + idmission + " ]";
    }
    
}
