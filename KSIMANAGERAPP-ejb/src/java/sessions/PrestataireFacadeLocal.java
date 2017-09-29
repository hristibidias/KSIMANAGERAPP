/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Prestataire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface PrestataireFacadeLocal {

    void create(Prestataire prestataire);

    void edit(Prestataire prestataire);

    void remove(Prestataire prestataire);

    Prestataire find(Object id);

    List<Prestataire> findAll();

    List<Prestataire> findRange(int[] range);

    int count();
    
    Integer nextId();
    
}
