/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Partenairecote;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface PartenairecoteFacadeLocal {

    void create(Partenairecote partenairecote);

    void edit(Partenairecote partenairecote);

    void remove(Partenairecote partenairecote);

    Partenairecote find(Object id);

    List<Partenairecote> findAll();

    List<Partenairecote> findRange(int[] range);

    int count();
    
}
