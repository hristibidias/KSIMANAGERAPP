/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mission;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hristi
 */
@Stateless
public class MissionFacade extends AbstractFacade<Mission> implements MissionFacadeLocal {

    @PersistenceContext(unitName = "KSIMANAGERAPP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MissionFacade() {
        super(Mission.class);
    }
    
    @Override
    public Integer nextId(){
        try {
            Query query = em.createNamedQuery("Mission.nextId");
            return ((Integer) query.getSingleResult()) + 1;
        } catch (Exception e) {
            return 1;
        }
    }
    
}
