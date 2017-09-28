/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Personnel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.PersonnelFacadeLocal;

/**
 *
 * @author Hristi
 */
public class PersonnelController implements Serializable {

    @EJB
    private PersonnelFacadeLocal personnelFacade;
    private List<Personnel> listPersonnel = new ArrayList<>();
    private Personnel personnel = new Personnel();
    private String operation;
    private String msg;

    /**
     * Creates a new instance of PersonnelController
     */
    public PersonnelController() {
    }

    @PostConstruct
    public void init() {
        listPersonnel.clear();
        listPersonnel.addAll(personnelFacade.findAll());
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        personnel = new Personnel();
        msg = "";
        action(e);
    }

    public void saveAccoun() {
        try {
                personnel.setIdpers(personnelFacade.nextId());
                personnel.setPassword(((Integer) personnel.getPassword().hashCode()).toString());
                personnelFacade.create(personnel);
                msg = "Enregistrement effectué avec succès";
                RequestContext.getCurrentInstance().execute("PF('wv_utilisateur').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'enregistrement";
        } finally {
            init();
        }
    }
    public void saveAccount() {
        try {
            if (personnelFacade.findByLogin(personnel.getLogin()).isEmpty()) {
                personnel.setIdpers(personnelFacade.nextId());
                personnel.setPassword(((Integer) personnel.getPassword().hashCode()).toString());
                personnelFacade.create(personnel);
                msg = "Enrégistrement effectué avec succès!";
                RequestContext.getCurrentInstance().execute("PF('wv_utilisateur').hide()");
            } else {
                msg = "Echec de l'enrégistrement!";
            }

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
        }
    }

    public void modifyAccount() {
        try {
            if (personnelFacade.findByLogin(personnel.getLogin()).isEmpty()) {
                personnel.setPassword(((Integer) personnel.getPassword().hashCode()).toString());
                personnelFacade.edit(personnel);
                msg = "Modification effectuée avec succès!";
                RequestContext.getCurrentInstance().execute("PF('wv_utilisateur').hide()");
            } else {
                msg = "Echec de la modification!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
        }
    }

    public void deleteAccount() {
        try {
            personnelFacade.remove(personnel);
            msg = "Suppression effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_utilisateur').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de la suppression!";
        } finally {
            init();
        }
    }

    public void persist() {
        switch (operation) {
            case "add":
                saveAccount();
                break;
            case "modify":
                modifyAccount();
                break;
            case "delete":
                deleteAccount();
                break;
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
