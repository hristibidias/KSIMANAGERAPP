/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Cotations;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface CotationsFacadeLocal {

    void create(Cotations cotations);

    void edit(Cotations cotations);

    void remove(Cotations cotations);

    Cotations find(Object id);

    List<Cotations> findAll();

    List<Cotations> findRange(int[] range);

    int count();
    
}
