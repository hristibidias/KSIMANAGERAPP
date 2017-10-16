/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Journalisation;
import entities.Mission;
import entities.Personnel;
import entities.Personnelmission;
import entities.PersonnelmissionPK;
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
import sessions.PersonnelFacadeLocal;
import sessions.PersonnelmissionFacadeLocal;

/**
 *
 * @author Hristi
 */
public class PersonnelMissionController {

    @EJB
    private PersonnelmissionFacadeLocal personnelmissionFacade;
    private List<Personnelmission> listPersonnelmission = new ArrayList<>();
    private Personnelmission personnelmission = new Personnelmission(); 

    private PersonnelmissionPK personnelmissionpk = new PersonnelmissionPK();
    private String operation;
    private String msg;
    private Integer idPersonnel;
    private Integer idMission;
    private BigInteger Prime;
    private int testPrivilege = 14 ;
    @EJB
    private PersonnelFacadeLocal personnelFacade;
    private List<Personnel> listPersonnel = new ArrayList<>();
    private List<Personnel> listPersonnelTarget = new ArrayList<>();
    private Personnel personnel = new Personnel();
    @EJB
    private MissionFacadeLocal missionFacade;
    private List<Mission> listMission = new ArrayList<>();
    private Mission menu = new Mission();
    @EJB
    private JournalisationFacadeLocal journalisationFacade;

    /**
     * Creates a new instance of PersonnelMissionController
     */
    public PersonnelMissionController() {
    }
    @PostConstruct
    public void init() {
        listPersonnelmission.clear();
        listPersonnelmission.addAll(personnelmissionFacade.findAll());
        
        chargeListMission();
        chargeListPersonnel();
    }

    public void chargeListMission() {
        listMission.clear();
        listMission.addAll(missionFacade.findAll());
    }

    public void chargeListPersonnel() {
        listPersonnel.clear();
        listPersonnel.addAll(personnelFacade.findAll());
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        personnelmission = new Personnelmission();
        msg = "";
        action(e);
    }

    public void savePersonnelMission() {
        try {
            personnelmissionpk.setIdmission(idMission);
            personnelmissionpk.setIdpers(idPersonnel);
            personnelmission.setPersonnelmissionPK(personnelmissionpk);
            personnelmission.setPrime(Prime);
            System.out.println(missionFacade.find(idMission).getDescriptionmission());
            personnelmissionFacade.create(personnelmission);
            logFile("Ajouter un personnle", personnel.getMatricule() + personnel.getNompers() + personnel.getPrenompers());
            msg = "Opération effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_personnelmission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
        }
    }

    public void modifyPersonnelMission() {
        try {
            personnelmissionFacade.edit(personnelmission);
            msg = "Opération effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_personnelmission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!!";
        } finally {
            init();
        }
    }

    public void deletePersonnelMission() {
        try {
            personnelmissionFacade.remove(personnelmission);
            msg = "Opération effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_personnelmission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!!";
        } finally {
            init();
        }
    }

    public void persist() {
        switch (operation) {
            case "addPersonnelmission":
                savePersonnelMission();
                break;
            case "modifyPersonnelmission":
                modifyPersonnelMission();
                break;
            case "deletePersonnelmission":
                deletePersonnelMission();
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
    
    
    
    public PersonnelmissionFacadeLocal getPersonnelmissionFacade() {
        return personnelmissionFacade;
    }

    public void setPersonnelmissionFacade(PersonnelmissionFacadeLocal personnelmissionFacade) {
        this.personnelmissionFacade = personnelmissionFacade;
    }

    public List<Personnelmission> getListPersonnelmission() {
        return listPersonnelmission;
    }

    public void setListPersonnelmission(List<Personnelmission> listPersonnelmission) {
        this.listPersonnelmission = listPersonnelmission;
    }

    public Personnelmission getPersonnelmission() {
        return personnelmission;
    }

    public void setPersonnelmission(Personnelmission personnelmission) {
        this.personnelmission = personnelmission;
    }

    public PersonnelmissionPK getPersonnelmissionpk() {
        return personnelmissionpk;
    }

    public void setPersonnelmissionpk(PersonnelmissionPK personnelmissionpk) {
        this.personnelmissionpk = personnelmissionpk;
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

    public Integer getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Integer idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public Integer getIdMission() {
        return idMission;
    }

    public void setIdMission(Integer idMission) {
        this.idMission = idMission;
    }

    public BigInteger getPrime() {
        return Prime;
    }

    public void setPrime(BigInteger Prime) {
        this.Prime = Prime;
    }

    

    public int getTestPrivilege() {
        return testPrivilege;
    }

    public void setTestPrivilege(int testPrivilege) {
        this.testPrivilege = testPrivilege;
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

//    public DualListModel<Personnelmission> getPersonnelmissionDuallist() {
//        return personnelmissionDuallist;
//    }
//
//    public void setPersonnelmissionDuallist(DualListModel<Personnelmission> personnelmissionDuallist) {
//        this.personnelmissionDuallist = personnelmissionDuallist;
//    }

    public List<Personnel> getListPersonnelTarget() {
        return listPersonnelTarget;
    }

    public void setListPersonnelTarget(List<Personnel> listPersonnelTarget) {
        this.listPersonnelTarget = listPersonnelTarget;
    }
//
//    public DualListModel<Personnel> getPersonnelDuallist() {
//        return personnelDuallist;
//    }
//
//    public void setPersonnelDuallist(DualListModel<Personnel> personnelDuallist) {
//        this.personnelDuallist = personnelDuallist;
//    }

    
    
    
}
