/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Privileges;
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
public class PrivilegesFacade extends AbstractFacade<Privileges> implements PrivilegesFacadeLocal {

    @PersistenceContext(unitName = "KSIMANAGERAPP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrivilegesFacade() {
        super(Privileges.class);
    }
    
    @Override
    public Privileges findByPersMenu(int idpers, int idmenu) {
	    try {
            Query query = em.createNamedQuery("Privileges.findByPersMenu");
            query.setParameter("idpers", idpers).setParameter("idmenu", idmenu);
            return (Privileges) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Privileges> findByIdpers(int idpers) {
	    try {
            Query query = em.createNamedQuery("Privileges.findByIdpers");
            query.setParameter("idpers", idpers);
            return query.getResultList();
        } catch (Exception e ) {
            return null;
        }
    }
    
    @Override
    public List<Privileges> findByIdPers(int idpers) {
	    try {
            Query query = em.createNamedQuery("Privileges.findByIdPers");
            query.setParameter("idpers", idpers);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}

