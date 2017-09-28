/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hristi
 */
public class SessionController implements Serializable{

    private String langue = "fr";
    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
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
      
}
