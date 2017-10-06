/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Journalisation;
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
import sessions.MailSender;
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
    private String message;
    private String loginNewuser;
    private String passwordNewuser;
    @EJB
    private JournalisationFacadeLocal journalisationFacade;
    Journalisation recupIdpers = new Journalisation();
    @EJB
    private MailSender sendmail = new MailSender();

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
                logFile("Ajouter un compte utilisateur", personnel.getMatricule() + personnel.getNompers() + personnel.getPrenompers());
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
                logFile("Modifier un compte utilisateur", personnel.getMatricule() + personnel.getNompers() + personnel.getPrenompers());
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
            logFile("Supprimer un compte utilisateur", personnel.getMatricule() + personnel.getNompers() + personnel.getPrenompers());
            msg = "Suppression effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_utilisateur').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de la suppression!";
        } finally {
            init();
        }
    }

    public void savePersonnel() {
        try {
            personnel.setIdpers(personnelFacade.nextId());
            loginNewuser = "KSI_Login_"+personnel.getIdpers();
            passwordNewuser = "KSI_Password_"+personnel.getIdpers();
            personnel.setLogin(loginNewuser);
            personnel.setPassword(passwordNewuser);
            personnel.setPassword(((Integer) personnel.getPassword().hashCode()).toString());
            personnelFacade.create(personnel);
            message = "Bonjour Monsieur/Madame " + personnel.getNompers() + " " + personnel.getPrenompers() + " l'ensemble du personnel de Kevin's "
                    + "Services International vous souhaite la bienvenu.  Voici vos identifiants. Login : " + loginNewuser + ", Mot de passe : " + passwordNewuser + ""
                    + ".  Au plaisir de vous voir dans les prochain jours.";
            sendmail.sendEmail("hrististoikov@gmail.com", "hristi stoikov", "borisarroga", "tonysimonadji@gmail.com", "Recrutement", message);
            logFile("Enregistrer un personnel", personnel.getMatricule() + personnel.getNompers() + personnel.getPrenompers());
            msg = "Enrégistrement effectué avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_personnel').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
        }
    }

    public void modifyPersonnel() {
        try {
            personnelFacade.edit(personnel);
            logFile("Modifier un personnel", personnel.getMatricule() + personnel.getNompers() + personnel.getPrenompers());
            msg = "Modification effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_personnel').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
        }
    }

    public void modifyProfil() {
        try {
            personnel.setIdpers(1);
            personnelFacade.edit(personnel);
            logFile("Modifier mon profil", personnel.getMatricule() + personnel.getNompers() + personnel.getPrenompers());
            msg = "Modification effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_personnel').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        } finally {
            init();
        }
    }

    public void deletePersonnel() {
        try {
            personnelFacade.remove(personnel);
            logFile("Supprimer un personnel", personnel.getMatricule() + personnel.getNompers() + personnel.getPrenompers());
            msg = "Suppression effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_personnel').hide()");
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
            case "addPersonnel":
                savePersonnel();
                break;
            case "modifyPersonnel":
                modifyPersonnel();
                break;
            case "deletePersonnel":
                deletePersonnel();
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

    public JournalisationFacadeLocal getJournalisationFacade() {
        return journalisationFacade;
    }

    public void setJournalisationFacade(JournalisationFacadeLocal journalisationFacade) {
        this.journalisationFacade = journalisationFacade;
    }

    public Journalisation getRecupIdpers() {
        return recupIdpers;
    }

    public void setRecupIdpers(Journalisation recupIdpers) {
        this.recupIdpers = recupIdpers;
    }

    public MailSender getSendmail() {
        return sendmail;
    }

    public void setSendmail(MailSender sendmail) {
        this.sendmail = sendmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLoginNewuser() {
        return loginNewuser;
    }

    public void setLoginNewuser(String loginNewuser) {
        this.loginNewuser = loginNewuser;
    }

    public String getPasswordNewuser() {
        return passwordNewuser;
    }

    public void setPasswordNewuser(String passwordNewuser) {
        this.passwordNewuser = passwordNewuser;
    }

}
