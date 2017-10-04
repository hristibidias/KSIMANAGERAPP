/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Conges;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hristi
 */
@Stateless
public class CongesFacade extends AbstractFacade<Conges> implements CongesFacadeLocal {

    @PersistenceContext(unitName = "KSIMANAGERAPP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CongesFacade() {
        super(Conges.class);
    }
    
    @Override
    public Integer nextId(){
        try {
            Query query = em.createNamedQuery("Conges.nextId");
            return ((Integer) query.getSingleResult()) + 1;
        } catch (Exception e) {
            return 1;
        }
    }
    
}
