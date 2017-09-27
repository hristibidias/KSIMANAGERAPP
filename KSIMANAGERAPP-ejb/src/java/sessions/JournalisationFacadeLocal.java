/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Journalisation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface JournalisationFacadeLocal {

    void create(Journalisation journalisation);

    void edit(Journalisation journalisation);

    void remove(Journalisation journalisation);

    Journalisation find(Object id);

    List<Journalisation> findAll();

    List<Journalisation> findRange(int[] range);

    int count();
    
}
