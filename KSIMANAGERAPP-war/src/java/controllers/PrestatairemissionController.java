/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Journalisation;
import entities.Mission;
import entities.Personnel;
import entities.Prestataire;
import entities.Prestatairemission;
import entities.PrestatairemissionPK;
import java.math.BigInteger;
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
import sessions.PrestataireFacadeLocal;
import sessions.PrestatairemissionFacadeLocal;

/**
 *
 * @author Hristi
 */
public class PrestatairemissionController {

    @EJB
    private PrestatairemissionFacadeLocal prestatairemissionFacade;
    private List<Prestatairemission> listPrestatairemission = new ArrayList<>();
    private Prestatairemission prestatairemission = new Prestatairemission();
    private PrestatairemissionPK prestatairemissionpk = new PrestatairemissionPK();
    private String operation;
    private String msg;
    private Integer personnelId;
    private Integer missionId;
    private BigInteger Prime;
    @EJB
    private PrestataireFacadeLocal PrestataireFacade;
    private List<Prestataire> listPrestataire = new ArrayList<>();
    private Prestataire prestataire = new Prestataire();
     @EJB
    private MissionFacadeLocal missionFacade;
    private List<Mission> listMission = new ArrayList<>();
    private Mission menu = new Mission();
    @EJB
    private JournalisationFacadeLocal journalisationFacade;
    
    /**
     * Creates a new instance of PrestatairemissionController
     */
    public PrestatairemissionController() {
    }
    
    @PostConstruct
    public void init(){
        listPrestatairemission.clear();
        listPrestatairemission.addAll(prestatairemissionFacade.findAll());
        chargeListMission();
        chargeListPrestataire();
    }
    
    public void chargeListMission() {
        listMission.clear();
        listMission.addAll(missionFacade.findAll());
    }

    public void chargeListPrestataire() {
        listPrestataire.clear();
        listPrestataire.addAll(PrestataireFacade.findAll());
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
        msg = "";
    }
    public void prepareCreate(ActionEvent e) {
        prestatairemission = new Prestatairemission();
        msg = "";
        action(e);
    }

    public void savePrestataireMission() {
        try {
            prestatairemissionpk.setIdmission(missionId);
            prestatairemissionpk.setIdpers(personnelId);
            prestatairemission.setPrestatairemissionPK(prestatairemissionpk);
            prestatairemission.setPrime(Prime);
            System.out.println(missionFacade.find(missionId).getDescriptionmission());
            prestatairemissionFacade.create(prestatairemission);
            logFile("Ajouter un personnle",  prestataire.getNompers() + prestataire.getPrenompers());
            msg = "Opération effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_prestatairemission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
        }
    }

    public void modifyPrestataireMission() {
        try {
            prestatairemissionFacade.edit(prestatairemission);
            msg = "Opération effectué avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_prestatairemission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
        }
    }

    public void deletePrestataireMission() {
        try {
            prestatairemissionFacade.remove(prestatairemission);
            msg = "Opération effectué avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_prestatairemission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
        }
    }

    public void persist() {
        switch (operation) {
            case "addPrestatairemission":
                savePrestataireMission();
                break;
            case "modifyPrestatairemission":
                modifyPrestataireMission();
                break;
            case "deletePrestatairemission":
                deletePrestataireMission();
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

    //    public String imprimer() {
//        try {
//            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listUtilisateur);
//            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/etats/listUtilisateur.jasper");
//            Map parameters = new HashMap();
//            parameters.put("USER", connectedUser);
//            parameters.put("REPORT_LOCALE", FacesContext.getCurrentInstance().getViewRoot().getLocale());
//            JasperPrint jasperPrint = fillReport(reportPath, parameters, beanCollectionDataSource);
//            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            httpServletResponse.addHeader("Content-disposition", "attachment; filename=listUtilisateur.pdf");
//            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//            exportReportToPdfStream(jasperPrint, servletOutputStream);
//            FacesContext.getCurrentInstance().responseComplete();
//            //----------------------------------------------
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
//    }
    
    public PrestatairemissionFacadeLocal getPrestatairemissionFacade() {
        return prestatairemissionFacade;
    }

    public void setPrestatairemissionFacade(PrestatairemissionFacadeLocal prestatairemissionFacade) {
        this.prestatairemissionFacade = prestatairemissionFacade;
    }

    public List<Prestatairemission> getListPrestatairemission() {
        return listPrestatairemission;
    }

    public void setListPrestatairemission(List<Prestatairemission> listPrestatairemission) {
        this.listPrestatairemission = listPrestatairemission;
    }

    public Prestatairemission getPrestatairemission() {
        return prestatairemission;
    }

    public void setPrestatairemission(Prestatairemission prestatairemission) {
        this.prestatairemission = prestatairemission;
    }

    public PrestatairemissionPK getPrestatairemissionpk() {
        return prestatairemissionpk;
    }

    public void setPrestatairemissionpk(PrestatairemissionPK prestatairemissionpk) {
        this.prestatairemissionpk = prestatairemissionpk;
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

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

   
   

    public BigInteger getPrime() {
        return Prime;
    }

    public void setPrime(BigInteger Prime) {
        this.Prime = Prime;
    }

    public PrestataireFacadeLocal getPrestataireFacade() {
        return PrestataireFacade;
    }

    public void setPrestataireFacade(PrestataireFacadeLocal PrestataireFacade) {
        this.PrestataireFacade = PrestataireFacade;
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

    public MissionFacadeLocal getMissionFacade() {
        return missionFacade;
    }

    public void setMissionFacade(MissionFacadeLocal missionFacade) {
        this.missionFacade = missionFacade;
    }

    public List<Mission> getListMission() {
        return listMission;
    }

    public void setListMission(List<Mission> listMission) {
        this.listMission = listMission;
    }

    public Mission getMenu() {
        return menu;
    }

    public void setMenu(Mission menu) {
        this.menu = menu;
    }

    public JournalisationFacadeLocal getJournalisationFacade() {
        return journalisationFacade;
    }

    public void setJournalisationFacade(JournalisationFacadeLocal journalisationFacade) {
        this.journalisationFacade = journalisationFacade;
    }
    
    
    
}
