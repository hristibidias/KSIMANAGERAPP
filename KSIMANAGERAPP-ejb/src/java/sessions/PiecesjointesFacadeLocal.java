/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Piecesjointes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface PiecesjointesFacadeLocal {

    void create(Piecesjointes piecesjointes);

    void edit(Piecesjointes piecesjointes);

    void remove(Piecesjointes piecesjointes);

    Piecesjointes find(Object id);

    List<Piecesjointes> findAll();

    List<Piecesjointes> findRange(int[] range);

    int count();
    
}
