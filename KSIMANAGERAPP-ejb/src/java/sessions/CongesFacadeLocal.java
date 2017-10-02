/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Conges;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface CongesFacadeLocal {

    void create(Conges conges);

    void edit(Conges conges);

    void remove(Conges conges);

    Conges find(Object id);

    List<Conges> findAll();

    List<Conges> findRange(int[] range);

    int count();
    
    Integer nextId();
    
    List<Conges> FindByMesconges(Integer idpers);
    
}
