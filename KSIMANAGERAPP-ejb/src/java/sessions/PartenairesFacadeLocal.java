/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Partenaires;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface PartenairesFacadeLocal {

    void create(Partenaires partenaires);

    void edit(Partenaires partenaires);

    void remove(Partenaires partenaires);

    Partenaires find(Object id);

    List<Partenaires> findAll();

    List<Partenaires> findRange(int[] range);

    int count();
    
}
