/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Personnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hristi
 */
@Local
public interface PersonnelFacadeLocal {

    void create(Personnel personnel);

    void edit(Personnel personnel);

    void remove(Personnel personnel);

    Personnel find(Object id);

    List<Personnel> findAll();

    List<Personnel> findRange(int[] range);

    int count();
    
    public Integer nextId();
    
    Personnel findByLoginMdp(String login, String password);
    
    public List<Personnel> findByLogin(String login);
    
    Integer nextNbrConnect();
    
    Personnel findByMatriculeEmail(String email, String matricule);
    
}
