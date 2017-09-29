/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import static entities.Partenaires_.idpers;
import static entities.PrivilegesPK_.idmenu;
import java.io.Serializable;
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
@Table(name = "privileges")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privileges.findAll", query = "SELECT p FROM Privileges p")
    , @NamedQuery(name = "Privileges.findByPersMenu", query = "SELECT p FROM Privileges p WHERE p.privilegesPK.idpers = :idpers AND p.privilegesPK.idmenu = :idmenu")
    , @NamedQuery(name = "Privileges.findByIdpers", query = "SELECT p FROM Privileges p WHERE p.privilegesPK.idpers = :idpers")
    , @NamedQuery(name = "Privileges.findByIdmenu", query = "SELECT p FROM Privileges p WHERE p.privilegesPK.idmenu = :idmenu")
    , @NamedQuery(name = "Privileges.findByRole", query = "SELECT p FROM Privileges p WHERE p.role = :role")})
public class Privileges implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrivilegesPK privilegesPK;
    @Column(name = "role")
    private Integer role;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Menu menu;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personnel personnel;
    private int idmenu;
    private int idpers;

    public Privileges() {
    }

    public Privileges(PrivilegesPK privilegesPK) {
        this.privilegesPK = privilegesPK;
    }

    public Privileges(int idpers, int idmenu) {
        this.privilegesPK = new PrivilegesPK(idpers, idmenu);
    }

    public PrivilegesPK getPrivilegesPK() {
        return privilegesPK;
    }

    public void setPrivilegesPK(PrivilegesPK privilegesPK) {
        this.privilegesPK = privilegesPK;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
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
        hash += (privilegesPK != null ? privilegesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privileges)) {
            return false;
        }
        Privileges other = (Privileges) object;
        if ((this.privilegesPK == null && other.privilegesPK != null) || (this.privilegesPK != null && !this.privilegesPK.equals(other.privilegesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Privileges[ privilegesPK=" + privilegesPK + " ]";
    }

}
