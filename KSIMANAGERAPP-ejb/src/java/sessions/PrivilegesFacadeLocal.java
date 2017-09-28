/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Privileges;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface PrivilegesFacadeLocal {

    void create(Privileges privileges);

    void edit(Privileges privileges);

    void remove(Privileges privileges);

    Privileges find(Object id);

    List<Privileges> findAll();

    List<Privileges> findRange(int[] range);

    int count();
    
    Privileges findByPersMenu(int idpers, int idmenu);
    
}
