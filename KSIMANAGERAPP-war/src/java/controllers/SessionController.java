/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Journalisation;
import entities.Personnel;
import entities.Privileges;
import entities.PrivilegesPK;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sessions.JournalisationFacadeLocal;
import sessions.MailSender;
import sessions.PersonnelFacadeLocal;
import sessions.PrivilegesFacadeLocal;

/**
 *
 * @author Hristi
 */
public class SessionController implements Serializable {

    @EJB
    private PersonnelFacadeLocal personnelFacade;
    private Personnel currentUser = new Personnel();
    private Personnel currentPersonnel = new Personnel();
    private String langue = "fr";
    private String msg;
    private String mail;
    private String loginReinilize;
    private String passwordReinilize;
    private Boolean pers = false;
    private Boolean miss = false;
    private Boolean conge = false;
    private Boolean perm = false;
    private Boolean regl = false;
    private Boolean listepersonnel = false;
    private Boolean gerercar = false;
    private Boolean listemission = false;
    private Boolean ajoutermiss = false;
    private Boolean listeconge = false;
    private Boolean mesconge = false;
    private Boolean listeperm = false;
    private Boolean mespermission = false;
    private Boolean listecompte = false;
    private Boolean moncompte = false;
    private Boolean gererpriv = false;
    private Boolean journal = false;
    private Boolean permanent = false;
    private Boolean prestataire = false;

    private int persT = 1;
    private int missT = 2;
    private int congeT = 3;
    private int permT = 4;
    private int reglT = 5;
    private int listepersonnelT = 6;
    private int gerercarT = 7;
    private int listemissionT = 8;
    private int ajoutermissT = 9;
    private int listecongeT = 10;
    private int mescongeT = 11;
    private int listepermT = 12;
    private int mespermissionT = 13;
    private int listecompteT = 14;
    private int moncompteT = 15;
    private int gererprivT = 16;
    private int journalT = 17;
    private int permanentT = 18;
    private int prestataireT = 19;

    private String message;
    private String fileName;

    @EJB
    private JournalisationFacadeLocal journalisationFacade;
    @EJB
    private MailSender sendmail = new MailSender();
    @EJB
    private PrivilegesFacadeLocal privilegesFacade;
    private List<Privileges> listPrivileges = new ArrayList<>();
    private Privileges privileges = new Privileges();
    private List<PrivilegesPK> listPrivilegesPK = new ArrayList<>();
    private PrivilegesPK privilegespk = new PrivilegesPK();

    private UploadedFile file;

    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }

    public void watchOut() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("currentUser")) {
                ((FacesContext.getCurrentInstance()).getExternalContext()).redirect("authenticate.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", msg));
        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }

    public void UserPrivileges() {
        int idpersonn = currentUser.getIdpers();
        //configuaration des privilèges
        listPrivileges.clear();
        listPrivileges.addAll(privilegesFacade.findByIdPers(idpersonn));
        listPrivileges.addAll(privilegesFacade.findAll());
        for (int i = 0; i < listPrivileges.size(); i++) {
            System.out.println("le privilège numéro " + i + " de l'utilisateur courant est " + listPrivileges.get(i));
        }
    }

    public String authenticate() {
        try {
            currentUser = personnelFacade.findByLoginMdp(currentUser.getLogin(), ((Integer) currentUser.getPassword().hashCode()).toString());
            if (currentUser != null) {
                msg = "";
                currentUser.setLastconnect(new Date(System.currentTimeMillis()));
                currentUser.setNbrconnect(personnelFacade.nextNbrConnect());
                personnelFacade.edit(currentUser);
                saveMessage();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", currentUser);
                logFile("Connexion", "Système");
                int idpersonn = currentUser.getIdpers();
                listPrivileges.clear();
                listPrivileges.addAll(privilegesFacade.findByIdpers(idpersonn));
                pers = false;
                miss = false;
                conge = false;
                perm = false;
                regl = false;
                listepersonnel = false;
                gerercar = false;
                listemission = false;
                ajoutermiss = false;
                listeconge = false;
                mesconge = false;
                listeperm = false;
                mespermission = false;
                listecompte = false;
                moncompte = false;
                gererpriv = false;
                journal = false;
                permanent = false;
                prestataire = false;
                for (int i = 0; i < listPrivileges.size(); i++) {
                    int idpriv1 = listPrivileges.get(i).getMenu().getIdmenu();
                    if (idpriv1 == persT) {
                        pers = true;
                    }
                    if (idpriv1 == missT) {
                        miss = true;
                    }
                    if (idpriv1 == congeT) {
                        conge = true;
                    }
                    if (idpriv1 == permT) {
                        perm = true;
                    }
                    if (idpriv1 == reglT) {
                        regl = true;
                    }
                    if (idpriv1 == listepersonnelT) {
                        listepersonnel = true;
                    }
                    if (idpriv1 == gerercarT) {
                        gerercar = true;
                    }
                    if (idpriv1 == listemissionT) {
                        listemission = true;
                    }
                    if (idpriv1 == ajoutermissT) {
                        ajoutermiss = true;
                    }
                    if (idpriv1 == listecongeT) {
                        listeconge = true;
                    }
                    if (idpriv1 == mescongeT) {
                        mesconge = true;
                    }
                    if (idpriv1 == listepermT) {
                        listeperm = true;
                    }
                    if (idpriv1 == mespermissionT) {
                        mespermission = true;
                    }
                    if (idpriv1 == listecompteT) {
                        listecompte = true;
                    }
                    if (idpriv1 == moncompteT) {
                        moncompte = true;
                    }
                    if (idpriv1 == gererprivT) {
                        gererpriv = true;
                    }
                    if (idpriv1 == journalT) {
                        journal = true;
                    }
                    if (idpriv1 == permanentT) {
                        permanent = true;
                    }
                    if (idpriv1 == prestataireT) {
                        prestataire = true;
                    }
                }
                //UserPrivileges();
                return "index.xhtml?faces-redirect=true";

            } else {
                FacesMessage messages;
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalide", "Login ou mot de passe incorrecte!");
                FacesContext.getCurrentInstance().addMessage(null, messages);
                msg = "Login ou mot de passe incorrecte";
                currentUser = new Personnel();
                return "authenticate.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Login ou mot de passe incorrecte";
            currentUser = new Personnel();
            return "authenticate.xhtml?faces-redirect=true";
        }
    }

    /*
    Cette fonction permet à un utilisateur de modifier son profil
    son fonctionnement est simple.
    lors de la connexion de l'utilisateur, 
    ces données sont stockées dans l'objet currentUser de type personnel.
    ensuite grace à au formulaire qui se trouve sur la page moncompte, 
    je récupère les information modifier de l'utilisateur et j'édite ces information dans la bd
     */
    public void modifyProfil() {
        try {
            currentPersonnel.setIdpers(currentUser.getIdpers());
            currentPersonnel.setEmail(currentUser.getEmail());
            currentPersonnel.setTel(currentUser.getTel());
            currentPersonnel.setQuartier(currentUser.getQuartier());
            currentPersonnel.setLogin(currentUser.getLogin());
            currentPersonnel.setPassword(((Integer) currentUser.getPassword().hashCode()).toString());
            currentUser.setPassword(((Integer) currentUser.getPassword().hashCode()).toString());
            personnelFacade.edit(currentUser);
            logFile("Modifier mon profil", currentPersonnel.getMatricule() + currentPersonnel.getNompers() + currentPersonnel.getPrenompers());
            msg = "Modification effectuée avec succès!";
            RequestContext.getCurrentInstance().execute("PF('wv_mon_profil').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération!";
        }
    }

    /*
    Cette fonction permet de réinitialiser les mots de passe des utilisateurs
     */
    public String reinitialiseIdentifiant() {
        try {
            currentUser = personnelFacade.findByMatriculeEmail(currentUser.getEmail(), currentUser.getMatricule());
            if (currentUser != null) {
                msg = "";
                loginReinilize = "New_KSI_Login_" + currentUser.getIdpers();
                passwordReinilize = "New_KSI_Password" + currentUser.getIdpers();
                currentUser.setLogin(loginReinilize);
                currentUser.setPassword(passwordReinilize);
                currentUser.setPassword(((Integer) currentUser.getPassword().hashCode()).toString());
                personnelFacade.edit(currentUser);
                mail = "Salut Monsieur/Madame " + currentUser.getNompers() + " " + currentUser.getPrenompers() + " "
                        + "Voici vos nouveaux parametres de connexion. Login : " + loginReinilize + ", Mot de passe : " + passwordReinilize + ". Une fois connecté, veillez réinitialiser"
                        + " vos parametres de connexion via votre espace personnel."
                        + " Si c'est pas vous qui avez initier ce processus veillez contacter l'administrateur. Merci";
                sendmail.sendEmail("hrististoikov@gmail.com", "Kevin's Services International", "borisarroga", currentUser.getEmail(), "Réinitialisation des paramètres de connexion", mail);
                logFile("Réinitialiser ses parametres", currentUser.getMatricule() + currentUser.getNompers() + currentUser.getPrenompers());
                msg = "Connectez-vous pour récupérer vos identifiants";
                return "authenticate.xhtml?faces-redirect=true";
            } else {
                msg = "Matricule ou Email incorrecte";
                currentUser = new Personnel();
                return "reinitialize.xhtml?faces-redirect=true";
            }
        }catch (Exception e) {
            e.printStackTrace();
            msg = "Login ou mot de passe incorrecte";
            currentUser = new Personnel();
            return "reinitialize.xhtml?faces-redirect=true";
        }
    }

    public String logOut() {
        try {
            msg = "";
            logFile("Deconnexion", "Système");
            currentUser.setLastlogout(new Date(System.currentTimeMillis()));
            personnelFacade.edit(currentUser);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("currentUser");
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "authenticate.xhtml?faces-redirect=true";
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

    public void handleFileUpload(FileUploadEvent event) {
        fileName = "D:\\Dossier Application Stage 2016-2017\\KSIMANAGERAPP\\KSIMANAGERAPP\\KSIMANAGERAPP-war\\web\\resources\\images\\photo_profil\\" + event.getFile().getFileName();    // chemin d'accés au fichier 
        //fileName = "D:\\Dossier Application Stage 2016-2017\\KSIMANAGERAPP\\KSIMANAGERAPP\\KSIMANAGERAPP-war\\web\\resources\\images\\photo_profil" + event.getFile().getFileName();
        currentUser.setPhotos(event.getFile().getFileName());
        personnelFacade.edit(currentUser);
        try {

            File result = new File(fileName);

            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[8192];

            int bulk;
            InputStream inputStream = event.getFile().getInputstream();

            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            FacesMessage message = new FacesMessage("Success", event.getFile().getFileName() + " a été chargé.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            RequestContext.getCurrentInstance().execute("PF('wv_ma_photo').hide()");

        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "le(s) fichiers n'a pas été chargé(s)");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String english() {
        langue = "en";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }

    public String french() {
        langue = "fr";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public PersonnelFacadeLocal getPersonnelFacade() {
        return personnelFacade;
    }

    public void setPersonnelFacade(PersonnelFacadeLocal personnelFacade) {
        this.personnelFacade = personnelFacade;
    }

    public Personnel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Personnel currentUser) {
        this.currentUser = currentUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getPers() {
        return pers;
    }

    public void setPers(Boolean pers) {
        this.pers = pers;
    }

    public Boolean getMiss() {
        return miss;
    }

    public void setMiss(Boolean miss) {
        this.miss = miss;
    }

    public Boolean getConge() {
        return conge;
    }

    public void setConge(Boolean conge) {
        this.conge = conge;
    }

    public Boolean getPerm() {
        return perm;
    }

    public void setPerm(Boolean perm) {
        this.perm = perm;
    }

    public Boolean getRegl() {
        return regl;
    }

    public void setRegl(Boolean regl) {
        this.regl = regl;
    }

    public Boolean getListepersonnel() {
        return listepersonnel;
    }

    public void setListepersonnel(Boolean listepersonnel) {
        this.listepersonnel = listepersonnel;
    }

    public Boolean getGerercar() {
        return gerercar;
    }

    public void setGerercar(Boolean gerercar) {
        this.gerercar = gerercar;
    }

    public Boolean getListemission() {
        return listemission;
    }

    public void setListemission(Boolean listemission) {
        this.listemission = listemission;
    }

    public Boolean getAjoutermiss() {
        return ajoutermiss;
    }

    public void setAjoutermiss(Boolean ajoutermiss) {
        this.ajoutermiss = ajoutermiss;
    }

    public Boolean getListeconge() {
        return listeconge;
    }

    public void setListeconge(Boolean listeconge) {
        this.listeconge = listeconge;
    }

    public Boolean getMesconge() {
        return mesconge;
    }

    public void setMesconge(Boolean mesconge) {
        this.mesconge = mesconge;
    }

    public Boolean getListeperm() {
        return listeperm;
    }

    public void setListeperm(Boolean listeperm) {
        this.listeperm = listeperm;
    }

    public Boolean getMespermission() {
        return mespermission;
    }

    public void setMespermission(Boolean mespermission) {
        this.mespermission = mespermission;
    }

    public Boolean getListecompte() {
        return listecompte;
    }

    public void setListecompte(Boolean listecompte) {
        this.listecompte = listecompte;
    }

    public Boolean getMoncompte() {
        return moncompte;
    }

    public void setMoncompte(Boolean moncompte) {
        this.moncompte = moncompte;
    }

    public Boolean getGererpriv() {
        return gererpriv;
    }

    public void setGererpriv(Boolean gererpriv) {
        this.gererpriv = gererpriv;
    }

    public Boolean getJournal() {
        return journal;
    }

    public void setJournal(Boolean journal) {
        this.journal = journal;
    }

    public JournalisationFacadeLocal getJournalisationFacade() {
        return journalisationFacade;
    }

    public void setJournalisationFacade(JournalisationFacadeLocal journalisationFacade) {
        this.journalisationFacade = journalisationFacade;
    }

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
    }

    public Boolean getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Boolean prestataire) {
        this.prestataire = prestataire;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public int getPersT() {
        return persT;
    }

    public void setPersT(int persT) {
        this.persT = persT;
    }

    public int getMissT() {
        return missT;
    }

    public void setMissT(int missT) {
        this.missT = missT;
    }

    public int getCongeT() {
        return congeT;
    }

    public void setCongeT(int congeT) {
        this.congeT = congeT;
    }

    public int getPermT() {
        return permT;
    }

    public void setPermT(int permT) {
        this.permT = permT;
    }

    public int getReglT() {
        return reglT;
    }

    public void setReglT(int reglT) {
        this.reglT = reglT;
    }

    public int getListepersonnelT() {
        return listepersonnelT;
    }

    public void setListepersonnelT(int listepersonnelT) {
        this.listepersonnelT = listepersonnelT;
    }

    public int getGerercarT() {
        return gerercarT;
    }

    public void setGerercarT(int gerercarT) {
        this.gerercarT = gerercarT;
    }

    public int getListemissionT() {
        return listemissionT;
    }

    public void setListemissionT(int listemissionT) {
        this.listemissionT = listemissionT;
    }

    public int getAjoutermissT() {
        return ajoutermissT;
    }

    public void setAjoutermissT(int ajoutermissT) {
        this.ajoutermissT = ajoutermissT;
    }

    public int getListecongeT() {
        return listecongeT;
    }

    public void setListecongeT(int listecongeT) {
        this.listecongeT = listecongeT;
    }

    public int getMescongeT() {
        return mescongeT;
    }

    public void setMescongeT(int mescongeT) {
        this.mescongeT = mescongeT;
    }

    public int getListepermT() {
        return listepermT;
    }

    public void setListepermT(int listepermT) {
        this.listepermT = listepermT;
    }

    public int getMespermissionT() {
        return mespermissionT;
    }

    public void setMespermissionT(int mespermissionT) {
        this.mespermissionT = mespermissionT;
    }

    public int getListecompteT() {
        return listecompteT;
    }

    public void setListecompteT(int listecompteT) {
        this.listecompteT = listecompteT;
    }

    public int getMoncompteT() {
        return moncompteT;
    }

    public void setMoncompteT(int moncompteT) {
        this.moncompteT = moncompteT;
    }

    public int getGererprivT() {
        return gererprivT;
    }

    public void setGererprivT(int gererprivT) {
        this.gererprivT = gererprivT;
    }

    public int getJournalT() {
        return journalT;
    }

    public void setJournalT(int journalT) {
        this.journalT = journalT;
    }

    public int getPermanentT() {
        return permanentT;
    }

    public void setPermanentT(int permanentT) {
        this.permanentT = permanentT;
    }

    public int getPrestataireT() {
        return prestataireT;
    }

    public void setPrestataireT(int prestataireT) {
        this.prestataireT = prestataireT;
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

    public List<PrivilegesPK> getListPrivilegesPK() {
        return listPrivilegesPK;
    }

    public void setListPrivilegesPK(List<PrivilegesPK> listPrivilegesPK) {
        this.listPrivilegesPK = listPrivilegesPK;
    }

    public PrivilegesPK getPrivilegespk() {
        return privilegespk;
    }

    public void setPrivilegespk(PrivilegesPK privilegespk) {
        this.privilegespk = privilegespk;
    }

    public Personnel getCurrentPersonnel() {
        return currentPersonnel;
    }

    public void setCurrentPersonnel(Personnel currentPersonnel) {
        this.currentPersonnel = currentPersonnel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MailSender getSendmail() {
        return sendmail;
    }

    public void setSendmail(MailSender sendmail) {
        this.sendmail = sendmail;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLoginReinilize() {
        return loginReinilize;
    }

    public void setLoginReinilize(String loginReinilize) {
        this.loginReinilize = loginReinilize;
    }

    public String getPasswordReinilize() {
        return passwordReinilize;
    }

    public void setPasswordReinilize(String passwordReinilize) {
        this.passwordReinilize = passwordReinilize;
    }

}
