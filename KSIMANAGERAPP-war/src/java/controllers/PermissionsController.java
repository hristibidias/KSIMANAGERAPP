/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Journalisation;
import entities.Permission;
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
import sessions.PermissionFacadeLocal;
import sessions.PersonnelFacadeLocal;

/**
 *
 * @author Hristi
 */
public class PermissionsController implements Serializable {

    @EJB
    private PermissionFacadeLocal permissionFacade;
    private List<Permission> listePermission = new ArrayList<>();
    private List<Permission> listeMesPermission = new ArrayList<>();
    private Permission permission = new Permission();
    private int idpersonnel;
    private int idpermission;
    private String operation;
    private String msg;
    @EJB
    private PersonnelFacadeLocal personnelFacade;
    private List<Personnel> listPersonnel = new ArrayList<>();
    private Personnel personnel = new Personnel();
    @EJB
    private JournalisationFacadeLocal journalisationFacade;
    /**
     * Creates a new instance of PermissionsController
     */
    public PermissionsController() {
    }
    
    @PostConstruct
    public void initPermission() {
        listePermission.clear();
        listePermission.addAll(permissionFacade.findAll());
        listPersonnel.clear();
        listPersonnel.addAll(personnelFacade.findAll());
        personnel = ((Personnel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
        listeMesPermission.clear();
        listeMesPermission.addAll(personnel.getPermissionCollection());
    }
    
    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        permission = new Permission();
        msg = "";
        action(e);
    }

    public void savePermission() {
        try {
            permission.setIdpermission(permissionFacade.nextId());
            permission.setIdpers((Personnel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
            permissionFacade.create(permission);
            logFile("Initier une demande de permission", permission.getMotif());
            msg = "Enregistrement effectué avec succès";
            RequestContext.getCurrentInstance().execute("PF('wv_permission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'enregistrement";
        } finally {
            initPermission();
        }
    }

    public void modifyPermission() {
        try {
                permissionFacade.edit(permission);
            logFile("Modifier une demande de permission", permission.getMotif());
                msg = "Modification effectuée avec succès!";
                RequestContext.getCurrentInstance().execute("PF('wv_permission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            initPermission();
        }
    }

    public void deletePermission() {
        try {
            permissionFacade.remove(permission);
            logFile("Supprimer un permission", permission.getMotif());
            msg = "Suppression effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_permission').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de la suppression!";
        } finally {
            initPermission();
        }
    }
    
    public void persist() {
        switch (operation) {
            case "addpermission":
                savePermission();
                break;
            case "modifypermission":
                modifyPermission();
                break;
            case "deletepermission":
                deletePermission();
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

    public PermissionFacadeLocal getPermissionFacade() {
        return permissionFacade;
    }

    public void setPermissionFacade(PermissionFacadeLocal permissionFacade) {
        this.permissionFacade = permissionFacade;
    }

    public List<Permission> getListePermission() {
        return listePermission;
    }

    public void setListePermission(List<Permission> listePermission) {
        this.listePermission = listePermission;
    }

    public List<Permission> getListeMesPermission() {
        return listeMesPermission;
    }

    public void setListeMesPermission(List<Permission> listeMesPermission) {
        this.listeMesPermission = listeMesPermission;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public int getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(int idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public int getIdpermission() {
        return idpermission;
    }

    public void setIdpermission(int idpermission) {
        this.idpermission = idpermission;
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
    
    
    
}
