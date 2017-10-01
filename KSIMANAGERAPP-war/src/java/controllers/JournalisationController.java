/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Journalisation;
import entities.Personnel;
import static entities.Personnelmission_.personnel;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import org.primefaces.context.RequestContext;
import sessions.JournalisationFacadeLocal;

/**
 *
 * @author Hristi
 */
public class JournalisationController implements Serializable{

     @EJB
    private JournalisationFacadeLocal journalisationFacade;
    private List<Journalisation> listJournalisation = new ArrayList<>();
    private Journalisation journalisation = new Journalisation();
    private String operation;
    private String msg;
    /**
     * Creates a new instance of JournalisationController
     */
    public JournalisationController() {
    }
    
    @PostConstruct
    public void init() {
        listJournalisation.clear();
        listJournalisation.addAll(journalisationFacade.findAll());
    }
    
    public void deleteAll() {
        try {
            for(Journalisation j : listJournalisation){
                journalisationFacade.remove(j);
            }
                journalisationFacade.edit(journalisation);
            logFile("Supprimer le journal","");
                msg = "Suppression effectuée avec succès!";
                RequestContext.getCurrentInstance().execute("PF('wv_utilisateur').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
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

    public JournalisationFacadeLocal getJournalisationFacade() {
        return journalisationFacade;
    }

    public void setJournalisationFacade(JournalisationFacadeLocal journalisationFacade) {
        this.journalisationFacade = journalisationFacade;
    }

    public List<Journalisation> getListJournalisation() {
        return listJournalisation;
    }

    public void setListJournalisation(List<Journalisation> listJournalisation) {
        this.listJournalisation = listJournalisation;
    }

    public Journalisation getJournalisation() {
        return journalisation;
    }

    public void setJournalisation(Journalisation journalisation) {
        this.journalisation = journalisation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
}
