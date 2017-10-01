/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Journalisation;
import entities.Personnel;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import sessions.JournalisationFacadeLocal;
import sessions.PersonnelFacadeLocal;

/**
 *
 * @author Hristi
 */
public class SessionController implements Serializable {

    @EJB
    private PersonnelFacadeLocal personnelFacade;
    private Personnel currentUser = new Personnel();
    private String langue = "fr";
    private String msg;
    private Boolean pers = false;
    private Boolean miss = false;
    private Boolean conge = false;
    private Boolean perm = false;
    private Boolean regl = false;
    private Boolean listepersonnel = false;
    private Boolean gerercar = false;
    private Boolean listemission = false;
    private Boolean ajoutermiss = false;
    private Boolean listeconge = false;
    private Boolean mesconge = false;
    private Boolean listeperm = false;
    private Boolean mespermission = false;
    private Boolean listecompte = false;
    private Boolean moncompte = false;
    private Boolean gererpriv = false;
    private Boolean journal = false;
    private Boolean permanent = false;
    private Boolean prestataire = false;

    @EJB
    private JournalisationFacadeLocal journalisationFacade;

    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }

    public void watchOut() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("currentUser")) {
                ((FacesContext.getCurrentInstance()).getExternalContext()).redirect("authenticate.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String authenticate() {
        try {
            currentUser = personnelFacade.findByLoginMdp(currentUser.getLogin(), ((Integer) currentUser.getPassword().hashCode()).toString());
            if (currentUser != null) {
                msg = "";
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", currentUser);
                logFile("Connexion", "Système");
                //configuaration des privilèges
                pers = true;
                miss = true;
                conge = true;
                perm = true;
                regl = true;
                listepersonnel = true;
                gerercar = true;
                listemission = true;
                ajoutermiss = true;
                listeconge = true;
                mesconge = true;
                listeperm = true;
                mespermission = true;
                listecompte = true;
                moncompte = true;
                gererpriv = true;
                journal = true;
                permanent = true;
                prestataire = true; 

                return "index.xhtml?faces-redirect=true";
            } else {
                msg = "Login ou mot de passe incorrecte";
                currentUser = new Personnel();
                return "authenticate.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Login ou mot de passe incorrecte";
            currentUser = new Personnel();
            return "authenticate.xhtml?faces-redirect=true";
        }
    }

    public String logOut() {
        try {
            msg = "";
            logFile("Deconnexion", "Système");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("currentUser");
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "authenticate.xhtml?faces-redirect=true";
        }
    }

    public void logFile(String name, String target) {
        try {
            Journalisation op = new Journalisation();
            op.setDate(new Date(System.currentTimeMillis()));
            op.setIp(((ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr());
            op.setNom(name);
            op.setCible(target);
            op.setHeure(new Time(System.currentTimeMillis()));
            op.setIdpers((Personnel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
            journalisationFacade.create(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String english() {
        langue = "en";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }

    public String french() {
        langue = "fr";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public PersonnelFacadeLocal getPersonnelFacade() {
        return personnelFacade;
    }

    public void setPersonnelFacade(PersonnelFacadeLocal personnelFacade) {
        this.personnelFacade = personnelFacade;
    }

    public Personnel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Personnel currentUser) {
        this.currentUser = currentUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getPers() {
        return pers;
    }

    public void setPers(Boolean pers) {
        this.pers = pers;
    }

    public Boolean getMiss() {
        return miss;
    }

    public void setMiss(Boolean miss) {
        this.miss = miss;
    }

    public Boolean getConge() {
        return conge;
    }

    public void setConge(Boolean conge) {
        this.conge = conge;
    }

    public Boolean getPerm() {
        return perm;
    }

    public void setPerm(Boolean perm) {
        this.perm = perm;
    }

    public Boolean getRegl() {
        return regl;
    }

    public void setRegl(Boolean regl) {
        this.regl = regl;
    }

    public Boolean getListepersonnel() {
        return listepersonnel;
    }

    public void setListepersonnel(Boolean listepersonnel) {
        this.listepersonnel = listepersonnel;
    }

    public Boolean getGerercar() {
        return gerercar;
    }

    public void setGerercar(Boolean gerercar) {
        this.gerercar = gerercar;
    }

    public Boolean getListemission() {
        return listemission;
    }

    public void setListemission(Boolean listemission) {
        this.listemission = listemission;
    }

    public Boolean getAjoutermiss() {
        return ajoutermiss;
    }

    public void setAjoutermiss(Boolean ajoutermiss) {
        this.ajoutermiss = ajoutermiss;
    }

    public Boolean getListeconge() {
        return listeconge;
    }

    public void setListeconge(Boolean listeconge) {
        this.listeconge = listeconge;
    }

    public Boolean getMesconge() {
        return mesconge;
    }

    public void setMesconge(Boolean mesconge) {
        this.mesconge = mesconge;
    }

    public Boolean getListeperm() {
        return listeperm;
    }

    public void setListeperm(Boolean listeperm) {
        this.listeperm = listeperm;
    }

    public Boolean getMespermission() {
        return mespermission;
    }

    public void setMespermission(Boolean mespermission) {
        this.mespermission = mespermission;
    }

    public Boolean getListecompte() {
        return listecompte;
    }

    public void setListecompte(Boolean listecompte) {
        this.listecompte = listecompte;
    }

    public Boolean getMoncompte() {
        return moncompte;
    }

    public void setMoncompte(Boolean moncompte) {
        this.moncompte = moncompte;
    }

    public Boolean getGererpriv() {
        return gererpriv;
    }

    public void setGererpriv(Boolean gererpriv) {
        this.gererpriv = gererpriv;
    }

    public Boolean getJournal() {
        return journal;
    }

    public void setJournal(Boolean journal) {
        this.journal = journal;
    }

    public JournalisationFacadeLocal getJournalisationFacade() {
        return journalisationFacade;
    }

    public void setJournalisationFacade(JournalisationFacadeLocal journalisationFacade) {
        this.journalisationFacade = journalisationFacade;
    }

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
    }

    public Boolean getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Boolean prestataire) {
        this.prestataire = prestataire;
    }

}
