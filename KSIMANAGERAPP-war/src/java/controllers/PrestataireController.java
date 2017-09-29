/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Personnel;
import entities.Prestataire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.PersonnelFacadeLocal;
import sessions.PrestataireFacadeLocal;

/**
 *
 * @author Hristi
 */
public class PrestataireController implements Serializable{

    @EJB
    private PrestataireFacadeLocal prestataireFacade;
    private List<Prestataire> listPrestataire = new ArrayList<>();
    private Prestataire prestataire = new Prestataire();
    private String operation;
    private String msg;
    @EJB
    private PersonnelFacadeLocal personnelFacade;
    private List<Personnel> listPersonnel = new ArrayList<>();
    private Personnel personnel = new Personnel();
    /**
     * Creates a new instance of PrestataireController
     */
    public PrestataireController() {
    }
    
    @PostConstruct
    public void initPrestataire() {
        listPrestataire.clear();
        listPrestataire.addAll(prestataireFacade.findAll());
        listPersonnel.clear();
        listPersonnel.addAll(personnelFacade.findAll());
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        prestataire = new Prestataire();
        msg = "";
        action(e);
    }

    public void savePrestataire() {
        try {
            prestataire.setIdpers(prestataireFacade.nextId());
            prestataireFacade.create(prestataire);
            msg = "Enregistrement effectué avec succès";
            RequestContext.getCurrentInstance().execute("PF('wv_prestataire').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'enregistrement";
        } finally {
            initPrestataire();
        }
    }

    public void modifyPrestataire() {
        try {
                prestataireFacade.edit(prestataire);
                msg = "Modification effectuée avec succès!";
                RequestContext.getCurrentInstance().execute("PF('wv_prestataire').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            initPrestataire();
        }
    }

    public void deletePrestataire() {
        try {
            prestataireFacade.remove(prestataire);
            msg = "Suppression effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_prestataire').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de la suppression!";
        } finally {
            initPrestataire();
        }
    }
    
    public void persist() {
        switch (operation) {
            case "addPrestataire":
                savePrestataire();
                break;
            case "modifyPrestataire":
                modifyPrestataire();
                break;
            case "deletePrestataire":
                deletePrestataire();
                break;
        }
    }

    public PrestataireFacadeLocal getPrestataireFacade() {
        return prestataireFacade;
    }

    public void setPrestataireFacade(PrestataireFacadeLocal prestataireFacade) {
        this.prestataireFacade = prestataireFacade;
    }

    public List<Prestataire> getListPrestataire() {
        return listPrestataire;
    }

    public void setListPrestataire(List<Prestataire> listPrestataire) {
        this.listPrestataire = listPrestataire;
    }

    public Prestataire getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Prestataire prestataire) {
        this.prestataire = prestataire;
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

    public PersonnelFacadeLocal getPersonnelFacade() {
        return personnelFacade;
    }

    public void setPersonnelFacade(PersonnelFacadeLocal personnelFacade) {
        this.personnelFacade = personnelFacade;
    }

    public List<Personnel> getListPersonnel() {
        return listPersonnel;
    }

    public void setListPersonnel(List<Personnel> listPersonnel) {
        this.listPersonnel = listPersonnel;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
    
    
}
