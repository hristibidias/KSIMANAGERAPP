/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Personnel;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sessions.PersonnelFacadeLocal;

/**
 *
 * @author Hristi
 */
public class SessionController implements Serializable{

    @EJB
    private PersonnelFacadeLocal personnelFacade;
    private Personnel currentUser = new Personnel();   
    private String langue = "fr";
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
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", currentUser);
                return "index.xhtml?faces-redirect=true";
            } else {
                currentUser = new Personnel();
                return "authenticate.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            currentUser = new Personnel();
            return "authenticate.xhtml?faces-redirect=true";
        }
    }

    public String logOut() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("currentUser");
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "authenticate.xhtml?faces-redirect=true";
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
      
    
}
