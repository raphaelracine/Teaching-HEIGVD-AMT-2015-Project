package ch.heigvd.amt.gamification.services;

import javax.ejb.Local;
import ch.heigvd.amt.gamification.model.Account;

/**
 *
 * @author Raphaël Racine
 */
@Local
public interface LoginServiceLocal {    
    public Account login(String email, String password);            
}
