/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Destinatairemessage;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface DestinatairemessageFacadeLocal {

    void create(Destinatairemessage destinatairemessage);

    void edit(Destinatairemessage destinatairemessage);

    void remove(Destinatairemessage destinatairemessage);

    Destinatairemessage find(Object id);

    List<Destinatairemessage> findAll();

    List<Destinatairemessage> findRange(int[] range);

    int count();
    
}
