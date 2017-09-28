/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Privileges;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessions.PrivilegesFacadeLocal;

/**
 *
 * @author Hristi
 */
public class PrivilegesController implements Serializable  {

    @EJB
    private PrivilegesFacadeLocal privilegesFacade;
    private List<Privileges> listPrivileges = new ArrayList<>();
    private Privileges privileges = new Privileges();
    private String operation;
    private String msg;
    /**
     * Creates a new instance of PrivilegesController
     */
    public PrivilegesController() {
    }
    
    @PostConstruct
    public void init(){
        listPrivileges.clear();
        listPrivileges.addAll(privilegesFacade.findAll());
    }

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
    
}
