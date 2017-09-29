/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Menu;
import entities.Personnel;
import entities.Privileges;
import entities.PrivilegesPK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import static jdk.nashorn.internal.runtime.ListAdapter.create;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.MenuFacadeLocal;
import sessions.PersonnelFacadeLocal;
import sessions.PrivilegesFacadeLocal;

/**
 *
 * @author Hristi
 */
public class PrivilegesController implements Serializable {

    @EJB
    private PrivilegesFacadeLocal privilegesFacade;
    private List<Privileges> listPrivileges = new ArrayList<>();
    private Privileges privileges = new Privileges();

    private PrivilegesPK privilegespk = new PrivilegesPK();
    private String operation;
    private String msg;
    private Integer idPersonnel;
    private Integer idMenu;
    private Integer roles;
    @EJB
    private PersonnelFacadeLocal personnelFacade;
    private List<Personnel> listPersonnel = new ArrayList<>();
    private Personnel personnel = new Personnel();
    @EJB
    private MenuFacadeLocal menuFacade;
    private List<Menu> listMenu = new ArrayList<>();
    private Menu menu = new Menu();

    /**
     * Creates a new instance of PrivilegesController
     */
    public PrivilegesController() {
    }

    @PostConstruct
    public void init() {
        listPrivileges.clear();
        listPrivileges.addAll(privilegesFacade.findAll());
        chargeListMenu();
        chargeListPersonnel();
    }

    public void chargeListMenu() {
        listMenu.clear();
        listMenu.addAll(menuFacade.findAll());
    }

    public void chargeListPersonnel() {
        listPersonnel.clear();
        listPersonnel.addAll(personnelFacade.findAll());
    }

    protected void setEmbeddableKeys() {
        privileges.getPrivilegesPK().setIdpers(privileges.getPersonnel().getIdpers());
        privileges.getPrivilegesPK().setIdmenu(privileges.getMenu().getIdmenu());
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        privileges = new Privileges();
        msg = "";
        action(e);
    }

    public void savePrivileges() {
        try {
//            privileges.getPrivilegesPK().setIdpers(idPersonnel);
//            privileges.getPrivilegesPK().setIdmenu(idMenu);
//            privileges.setRole(roles);
//            privileges.setMenu(menu);
//            privilegespk.setIdmenu(idMenu);
//            privilegespk.setIdpers(idPersonnel);
//            privileges.setIdmenu(idMenu);
//            privileges.setIdpers(idPersonnel); 
//            //privilegesFacade.create(privileges);
            privilegesFacade.create(privileges(idPersonnel, idMenu));
            msg = "Opération effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_privileges').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
        }
    }

    public void modifyPrivileges() {
        try {
            privilegesFacade.edit(privileges);
            msg = "Successful operation!";
            RequestContext.getCurrentInstance().execute("PF('wv_privileges').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Failure!";
        } finally {
            init();
        }
    }

    public void deletePrivileges() {
        try {
            privilegesFacade.remove(privileges);
            msg = "Successful operation!";
            RequestContext.getCurrentInstance().execute("PF('wv_privileges').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Failure!";
        } finally {
            init();
        }
    }

    public void persist() {
        switch (operation) {
            case "addPrivileges":
                savePrivileges();
                break;
            case "modifyPrivileges":
                modifyPrivileges();
                break;
            case "deletePrivileges":
                deletePrivileges();
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
    public PrivilegesFacadeLocal getPrivilegesFacade() {
        return privilegesFacade;
    }

    public void setPrivilegesFacade(PrivilegesFacadeLocal privilegesFacade) {
        this.privilegesFacade = privilegesFacade;
    }

    public List<Privileges> getListPrivileges() {
        return listPrivileges;
    }

    public void setListPrivileges(List<Privileges> listPrivileges) {
        this.listPrivileges = listPrivileges;
    }

    public Privileges getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Privileges privileges) {
        this.privileges = privileges;
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

    public Integer getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Integer idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public MenuFacadeLocal getMenuFacade() {
        return menuFacade;
    }

    public void setMenuFacade(MenuFacadeLocal menuFacade) {
        this.menuFacade = menuFacade;
    }

    public List<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<Menu> listMenu) {
        this.listMenu = listMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public PrivilegesPK getPrivilegespk() {
        return privilegespk;
    }

    public void setPrivilegespk(PrivilegesPK privilegespk) {
        this.privilegespk = privilegespk;
    }

    public Integer getRoles() {
        return roles;
    }

    public void setRoles(Integer roles) {
        this.roles = roles;
    }

    private Privileges privileges(Integer idPersonnel, Integer idMenu) {
        privileges.getPrivilegesPK().setIdpers(idPersonnel);
            privileges.getPrivilegesPK().setIdmenu(idMenu);
            privileges.setRole(roles);
            return null;
    }

    
}
