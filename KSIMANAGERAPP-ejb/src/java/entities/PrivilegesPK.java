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
public class PrivilegesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idpers")
    private int idpers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmenu")
    private int idmenu;

    public PrivilegesPK() {
    }

    public PrivilegesPK(int idpers, int idmenu) {
        this.idpers = idpers;
        this.idmenu = idmenu;
    }

    public int getIdpers() {
        return idpers;
    }

    public void setIdpers(int idpers) {
        this.idpers = idpers;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idpers;
        hash += (int) idmenu;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrivilegesPK)) {
            return false;
        }
        PrivilegesPK other = (PrivilegesPK) object;
        if (this.idpers != other.idpers) {
            return false;
        }
        if (this.idmenu != other.idmenu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PrivilegesPK[ idpers=" + idpers + ", idmenu=" + idmenu + " ]";
    }
    
}
