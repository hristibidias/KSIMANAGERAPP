/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Prestatairemission;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface PrestatairemissionFacadeLocal {

    void create(Prestatairemission prestatairemission);

    void edit(Prestatairemission prestatairemission);

    void remove(Prestatairemission prestatairemission);

    Prestatairemission find(Object id);

    List<Prestatairemission> findAll();

    List<Prestatairemission> findRange(int[] range);

    int count();
    
}
