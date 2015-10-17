package ch.heigvd.amt.gamification.services;

import ch.heigvd.amt.gamification.model.Account;
import ch.heigvd.amt.gamification.model.ApiKey;
import ch.heigvd.amt.gamification.model.Application;
import ch.heigvd.amt.gamification.model.EndUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.UUID;

/**
 *
 * @author Raphaël Racine
 */
@Stateless
public class ApplicationsManager implements ApplicationsManagerLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public long numberOfApplicationsManaged() {
        return em.createQuery("SELECT a FROM Application a")
                .getResultList().size();
    }

    /**
     * Get a new ApiKey which doesnt exists yet
     * @return A new ApiKey
     */
    private ApiKey getNewApiKey() {
        
        String generated = UUID.randomUUID().toString();
        
        while(keyExists(generated)) {
            generated = UUID.randomUUID().toString();
        }
        
        ApiKey key = new ApiKey();
        key.setKey(generated);
        return key;
    }
    
    private boolean keyExists(String newkey) {
                        em.createQuery("SELECT a FROM ApiKey a WHERE a.key= :newkey")
                .setParameter("newkey", newkey).getResultList();
        
        return false;
    }

    @Override
    public void assignApplicationToAccount(Application app, Account acc) {       
            app.setAcount(acc);
            acc.getApps().add(app);
            
            ApiKey key = getNewApiKey();
            app.setApiKey(key);
            key.setApplication(app);
            
            em.persist(app);
            em.flush();
    }

    @Override
     public void editApplicationDetails(Application application) {
         throw new UnsupportedOperationException("Not supported yet.");
     }

    @Override
    public List<EndUser> endUsersOfApplication(long idApplication) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
