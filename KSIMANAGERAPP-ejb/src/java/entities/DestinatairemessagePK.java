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
public class DestinatairemessagePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idpers")
    private int idpers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmessage")
    private int idmessage;

    public DestinatairemessagePK() {
    }

    public DestinatairemessagePK(int idpers, int idmessage) {
        this.idpers = idpers;
        this.idmessage = idmessage;
    }

    public int getIdpers() {
        return idpers;
    }

    public void setIdpers(int idpers) {
        this.idpers = idpers;
    }

    public int getIdmessage() {
        return idmessage;
    }

    public void setIdmessage(int idmessage) {
        this.idmessage = idmessage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idpers;
        hash += (int) idmessage;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DestinatairemessagePK)) {
            return false;
        }
        DestinatairemessagePK other = (DestinatairemessagePK) object;
        if (this.idpers != other.idpers) {
            return false;
        }
        if (this.idmessage != other.idmessage) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DestinatairemessagePK[ idpers=" + idpers + ", idmessage=" + idmessage + " ]";
    }
    
}
