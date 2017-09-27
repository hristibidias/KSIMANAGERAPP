/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Inventaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface InventaireFacadeLocal {

    void create(Inventaire inventaire);

    void edit(Inventaire inventaire);

    void remove(Inventaire inventaire);

    Inventaire find(Object id);

    List<Inventaire> findAll();

    List<Inventaire> findRange(int[] range);

    int count();
    
}
