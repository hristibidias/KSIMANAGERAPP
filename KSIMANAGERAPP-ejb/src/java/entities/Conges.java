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
@Table(name = "conges")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conges.FindByMesconges", query = "SELECT c FROM Conges c WHERE c.idpers = :idpers")
    ,@NamedQuery(name = "Conges.nextId", query = "SELECT MAX(c.idconges) FROM Conges c")
    , @NamedQuery(name = "Conges.findAll", query = "SELECT c FROM Conges c")
    , @NamedQuery(name = "Conges.findByIdconges", query = "SELECT c FROM Conges c WHERE c.idconges = :idconges")
    , @NamedQuery(name = "Conges.findByDatedep", query = "SELECT c FROM Conges c WHERE c.datedep = :datedep")
    , @NamedQuery(name = "Conges.findByDateretour", query = "SELECT c FROM Conges c WHERE c.dateretour = :dateretour")
    , @NamedQuery(name = "Conges.findByTypeconge", query = "SELECT c FROM Conges c WHERE c.typeconge = :typeconge")
    , @NamedQuery(name = "Conges.findByMotif", query = "SELECT c FROM Conges c WHERE c.motif = :motif")})
public class Conges implements Serializable {

    @Size(max = 20)
    @Column(name = "Statut", length = 20)
    private String statut;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconges")
    private Integer idconges;
    @Column(name = "datedep")
    @Temporal(TemporalType.DATE)
    private Date datedep;
    @Column(name = "dateretour")
    @Temporal(TemporalType.DATE)
    private Date dateretour;
    @Size(max = 60)
    @Column(name = "typeconge")
    private String typeconge;
    @Size(max = 254)
    @Column(name = "motif")
    private String motif;
    @JoinColumn(name = "idpers", referencedColumnName = "idpers")
    @ManyToOne(optional = false)
    private Personnel idpers;

    public Conges() {
    }

    public Conges(Integer idconges) {
        this.idconges = idconges;
    }

    public Integer getIdconges() {
        return idconges;
    }

    public void setIdconges(Integer idconges) {
        this.idconges = idconges;
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

    public String getTypeconge() {
        return typeconge;
    }

    public void setTypeconge(String typeconge) {
        this.typeconge = typeconge;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
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
        hash += (idconges != null ? idconges.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conges)) {
            return false;
        }
        Conges other = (Conges) object;
        if ((this.idconges == null && other.idconges != null) || (this.idconges != null && !this.idconges.equals(other.idconges))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Conges[ idconges=" + idconges + " ]";
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}
