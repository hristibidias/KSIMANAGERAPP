/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Journalisation;
import entities.Mission;
import entities.Personnel;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletRequest;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.JournalisationFacadeLocal;
import sessions.MissionFacadeLocal;

/**
 *
 * @author Hristi
 */
public class MissionController implements Serializable{

    @EJB
    private MissionFacadeLocal missionFacade;
    private List<Mission> listMission = new ArrayList<>();
    private Mission mission = new Mission();
    private String operation;
    private String msg;
    @EJB
    private JournalisationFacadeLocal journalisationFacade;
    /**
     * Creates a new instance of MissionController
     */
    public MissionController() {
    }

    @PostConstruct
    public void initMission(){
        listMission.clear();
        listMission.addAll(missionFacade.findAll());
    }
    
    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        mission = new Mission();
        msg = "";
        action(e);
    }
    
    public void saveMission() {
        try {
            mission.setIdmission(missionFacade.nextId());
            missionFacade.create(mission);
            logFile("Enregistrement d'une mission",mission.getDescriptionmission() + mission.getStatut() + mission.getLieumission());
            msg = "Enregistrement effectué avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_mission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            initMission();
        }
    }

    public void modifyMission() {
        try {
            missionFacade.edit(mission);
            logFile("Modification d'une mission",mission.getDescriptionmission() + mission.getStatut() + mission.getLieumission());
            msg = "Modification effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_mission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            initMission();
        }
    }

    public void deleteMission() {
        try {
            missionFacade.remove(mission);
            logFile("Suppression d'une mission",mission.getDescriptionmission() + mission.getStatut() + mission.getLieumission());
            msg = "Suppression effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_mission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de la suppression!";
        } finally {
            initMission();
        }
    }

    public void persist() {
        switch (operation) {
            case "add":
                saveMission();
                break;
            case "modify":
                modifyMission();
                break;
            case "delete":
                deleteMission();
                break;
            case "addMission":
                saveMission();
                break;
            case "modifyMission":
                modifyMission();
                break;
            case "deleteMission":
                deleteMission();
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

    public List<Mission> getListMission() {
        return listMission;
    }

    public void setListMission(List<Mission> listMission) {
        this.listMission = listMission;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
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

    public JournalisationFacadeLocal getJournalisationFacade() {
        return journalisationFacade;
    }

    public void setJournalisationFacade(JournalisationFacadeLocal journalisationFacade) {
        this.journalisationFacade = journalisationFacade;
    }

    public MissionFacadeLocal getMissionFacade() {
        return missionFacade;
    }

    public void setMissionFacade(MissionFacadeLocal missionFacade) {
        this.missionFacade = missionFacade;
    }
    
    
    
}
