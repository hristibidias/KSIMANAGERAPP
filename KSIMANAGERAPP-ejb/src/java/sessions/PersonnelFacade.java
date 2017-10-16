/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Personnel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hristi
 */
@Stateless
public class PersonnelFacade extends AbstractFacade<Personnel> implements PersonnelFacadeLocal {

    @PersistenceContext(unitName = "KSIMANAGERAPP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonnelFacade() {
        super(Personnel.class);
    }
    
    @Override
    public Integer nextId(){
        try {
            Query query = em.createNamedQuery("Personnel.nextId");
            return ((Integer) query.getSingleResult()) + 1;
        } catch (Exception e) {
            return 1;
        }
    }
    
    @Override
    public Integer nextNbrConnect(){
        try {
            Query query = em.createNamedQuery("Personnel.nextNbrConnect");
            return ((Integer) query.getSingleResult()) + 1;
        } catch (Exception e) {
            return 1;
        }
    }
    
    @Override
    public Personnel findByLoginMdp(String login, String password) {
	    try {
            Query query = em.createNamedQuery("Personnel.findByLoginMdp");
            query.setParameter("login", login).setParameter("password", password);
            return (Personnel) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Personnel> findByLogin(String login) {
	    try {
            Query query = em.createNamedQuery("Personnel.findByLogin");
            query.setParameter("login", login);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Personnel findByMatriculeEmail(String email, String matricule) {
	    try {
            Query query = em.createNamedQuery("Personnel.findByMatriculeEmail");
            query.setParameter("email", email).setParameter("matricule", matricule);
            return (Personnel) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
