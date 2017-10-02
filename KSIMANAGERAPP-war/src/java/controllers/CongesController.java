/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Conges;
import entities.Journalisation;
import entities.Personnel;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletRequest;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.CongesFacadeLocal;
import sessions.JournalisationFacadeLocal;
import sessions.PersonnelFacadeLocal;

/**
 *
 * @author Hristi
 */
public class CongesController implements Serializable{

    @EJB
    private CongesFacadeLocal congesFacade;
    private List<Conges> listeConges = new ArrayList<>();
    private List<Conges> listeMesConges = new ArrayList<>();
    private Conges conges = new Conges();
    private Integer idperson = 14;
    private int idpersonnel = 13;
    private int idconge;
    private String operation;
    private String msg;
    @EJB
    private PersonnelFacadeLocal personnelFacade;
    private List<Personnel> listPersonnel = new ArrayList<>();
    private Personnel personnel = new Personnel();
    private Personnel personnelId = new Personnel();
    @EJB
    private JournalisationFacadeLocal journalisationFacade;
    /**
     * Creates a new instance of CongesController
     */
    public CongesController() {
    }
    
    @PostConstruct
    public void initconges() { 
        listeConges.clear();
        listeConges.addAll(congesFacade.findAll());
        listPersonnel.clear();
        listPersonnel.addAll(personnelFacade.findAll());
        personnel = ((Personnel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
        //personnel = personnelFacade.find(conges.getIdpers());
        //chargeListMesConge();
        listeMesConges.clear();
        listeMesConges.addAll(personnel.getCongesCollection());
    }
    
//    public void chargeListMesConge() {
//        listeMesConges.clear();
//        listeMesConges.addAll(personnel.getCongesCollection());
//    }

    public void selectUtilisateur() {
        personnel = ((Personnel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
        //personnel = personnelFacade.find(conges.getIdpers());
        //chargeListMesConge();
        listeMesConges.clear();
        listeMesConges.addAll(personnel.getCongesCollection());
    }

//    public void initmesconges() {
//        //idpersonnel = 13;
//        //msg = "voici l'id : " + idperson;
//        selectUtilisateur();
//        listeMesConges.clear();
//        listeMesConges.addAll(personnel.getCongesCollection());
//    }
    

    public void findMesConges(int idconge, int idpersonnel){
        
    }
    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        conges = new Conges();
        msg = "";
        action(e);
    }

    public void saveConge() {
        try {
            conges.setIdconges(congesFacade.nextId());
            conges.setIdpers((Personnel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
            congesFacade.create(conges);
            logFile("Initier une demande de congé", conges.getMotif() + conges.getTypeconge());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Enregistrement effectué."));
            //msg = "Enregistrement effectué" + idpersonnel;
            RequestContext.getCurrentInstance().execute("PF('wv_conges').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", "Echec de l'enregistrement."));
            //msg = "Echec de l'enregistrement";
        } finally {
            initconges();
        }
    }

    public void modifyConge() {
        try {
                congesFacade.edit(conges);
            logFile("Modifier une demande de congés", conges.getMotif() + conges.getTypeconge());
                msg = "Modification effectuée avec succès!";
                RequestContext.getCurrentInstance().execute("PF('wv_conges').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            initconges();
        }
    }

    public void deleteConge() {
        try {
            congesFacade.remove(conges);
            logFile("Supprimer un congé", conges.getMotif() + conges.getTypeconge());
            msg = "Suppression effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_conges').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de la suppression!";
        } finally {
            initconges();
        }
    }
    
    public void persist() {
        switch (operation) {
            case "addConge":
                saveConge();
                break;
            case "modifyConge":
                modifyConge();
                break;
            case "deleteConge":
                deleteConge();
                break;
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

    public CongesFacadeLocal getCongesFacade() {
        return congesFacade;
    }

    public void setCongesFacade(CongesFacadeLocal congesFacade) {
        this.congesFacade = congesFacade;
    }


    public Conges getConges() {
        return conges;
    }

    public void setConges(Conges conges) {
        this.conges = conges;
    }

    public int getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(int idpersonnel) {
        this.idpersonnel = idpersonnel;
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

    public JournalisationFacadeLocal getJournalisationFacade() {
        return journalisationFacade;
    }

    public void setJournalisationFacade(JournalisationFacadeLocal journalisationFacade) {
        this.journalisationFacade = journalisationFacade;
    }

    public List<Conges> getListeConges() {
        return listeConges;
    }

    public void setListeConges(List<Conges> listeConges) {
        this.listeConges = listeConges;
    }

    public List<Conges> getListeMesConges() {
        return listeMesConges;
    }

    public void setListeMesConges(List<Conges> listeMesConges) {
        this.listeMesConges = listeMesConges;
    }

    public int getIdconge() {
        return idconge;
    }

    public void setIdconge(int idconge) {
        this.idconge = idconge;
    }

    public Integer getIdperson() {
        return idperson;
    }

    public void setIdperson(Integer idperson) {
        this.idperson = idperson;
    }

    public Personnel getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Personnel personnelId) {
        this.personnelId = personnelId;
    }
    
    
    
}
