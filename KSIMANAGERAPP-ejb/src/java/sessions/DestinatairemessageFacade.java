/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Destinatairemessage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hristi
 */
@Stateless
public class DestinatairemessageFacade extends AbstractFacade<Destinatairemessage> implements DestinatairemessageFacadeLocal {

    @PersistenceContext(unitName = "KSIMANAGERAPP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DestinatairemessageFacade() {
        super(Destinatairemessage.class);
    }
    
}
