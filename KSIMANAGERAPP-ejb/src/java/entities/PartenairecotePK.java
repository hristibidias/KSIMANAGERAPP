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
public class PartenairecotePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idcotation")
    private int idcotation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpartenaire")
    private int idpartenaire;

    public PartenairecotePK() {
    }

    public PartenairecotePK(int idcotation, int idpartenaire) {
        this.idcotation = idcotation;
        this.idpartenaire = idpartenaire;
    }

    public int getIdcotation() {
        return idcotation;
    }

    public void setIdcotation(int idcotation) {
        this.idcotation = idcotation;
    }

    public int getIdpartenaire() {
        return idpartenaire;
    }

    public void setIdpartenaire(int idpartenaire) {
        this.idpartenaire = idpartenaire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcotation;
        hash += (int) idpartenaire;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartenairecotePK)) {
            return false;
        }
        PartenairecotePK other = (PartenairecotePK) object;
        if (this.idcotation != other.idcotation) {
            return false;
        }
        if (this.idpartenaire != other.idpartenaire) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PartenairecotePK[ idcotation=" + idcotation + ", idpartenaire=" + idpartenaire + " ]";
    }
    
}
