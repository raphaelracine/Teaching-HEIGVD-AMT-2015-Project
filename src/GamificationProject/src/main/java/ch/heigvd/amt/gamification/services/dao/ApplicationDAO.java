/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.model.entities.Account;
import ch.heigvd.amt.gamification.model.entities.ApiKey;
import ch.heigvd.amt.gamification.model.entities.Application;
import ch.heigvd.amt.gamification.model.entities.EndUser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author parfait
 */
@Stateless
public class ApplicationDAO extends GenericDAO<Application, Long> implements ApplicationDAOLocal {

    @PersistenceContext
    EntityManager em;
    
    @EJB
    private ApiKeyDAOLocal apikeyDAO;
    
    @Override
    public void assignApplicationToAccount(Application app, Account acc) {
        ApiKey key = apikeyDAO.getNewApiKey();

        app.setAcount(acc);
        acc.getApps().add(app);

        app.setApiKey(key);
        key.setApplication(app);

        create(app);
    }

    @Override
    public long numberOfApplicationsManaged() {
        return (long) em.createNamedQuery("Application.numberOfApplications").getSingleResult();
    }

    @Override
    public void assignApplicationToEndUser(Application application, EndUser endUser) {
        application.getEndUsers().add(endUser);
        endUser.setApplication(application);        
        
        try {
            update(application);
        } catch (GamificationDomainEntityNotFoundException ex) {
            Logger.getLogger(ApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public List<Application> applicationsOfAnAccountWithEndUsersNumber(Account account) {
        return em.createNamedQuery("Application.findByAccountWithEndUsersNumber")
                .setParameter("idAccount", account.getId()).getResultList();
    }

    @Override
    public long nbEndUsersOfApplication(Application application) {
        return (long) em.createNamedQuery("Application.nbEndUsersOfApplication")
                .setParameter("idApplication", application.getId())
                .getSingleResult();
    }
    
    

}