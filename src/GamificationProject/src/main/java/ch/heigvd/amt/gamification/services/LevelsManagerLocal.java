package ch.heigvd.amt.gamification.services;

import ch.heigvd.amt.gamification.model.Application;
import ch.heigvd.amt.gamification.model.Level;
import ch.heigvd.amt.gamification.services.dao.GamificationDomainEntityNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raphaël Racine
 */
@Local
public interface LevelsManagerLocal {
    
    /**
     * Edit a level
     * @param level
     * @throws GamificationDomainEntityNotFoundException 
     */
    public void editLevel(Level level) throws GamificationDomainEntityNotFoundException;
    
    /**
     * Delete a level
     * @param level
     * @throws GamificationDomainEntityNotFoundException 
     */
    public void deleteLevel(Level level) throws GamificationDomainEntityNotFoundException;
    
    /**
     * @param apikey
     * @return All levels of an application which have this apikey
     */
    public List<Level> findLevelsByApiKey(String apikey);
    
    /**
     * 
     * @param application Have to be managed
     * @param level Doesn't have to be managed (cascade)
     */
    public void assignLevelToApplication(Application application, Level level);
    
    public Level findById(Long id) throws GamificationDomainEntityNotFoundException;

    public Level findCurrentLevel(Application app, long nbPointsEndUser);

    public Level findNextLevel(Application app, long nbPointsEndUser);
    
}
