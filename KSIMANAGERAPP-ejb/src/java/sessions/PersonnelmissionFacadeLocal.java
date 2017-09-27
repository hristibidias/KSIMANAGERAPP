/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Personnelmission;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface PersonnelmissionFacadeLocal {

    void create(Personnelmission personnelmission);

    void edit(Personnelmission personnelmission);

    void remove(Personnelmission personnelmission);

    Personnelmission find(Object id);

    List<Personnelmission> findAll();

    List<Personnelmission> findRange(int[] range);

    int count();
    
}
