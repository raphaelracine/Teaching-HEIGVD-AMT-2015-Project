package ch.heigvd.amt.concurrentupdategamyclienttest;

import ch.heigvd.amt.gamification.client.dto.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author michelle meguep
 */
public class ExpectedState {

    private static final Map<String, EndUserDTO> endUsers = new HashMap<>();
    private static final Map<String, ApplicationEndUsersDTO> applications = new HashMap<>();

    public synchronized void logEventIntoApplication(EventDTO event) {
        ApplicationEndUsersDTO application = applications.get(event.getApiKey());
        if (application == null) {
            application = new ApplicationEndUsersDTO();
            application.setNbEndUsers(1);
            applications.put(event.getApiKey(), application);
        } else {
            application.setNbEndUsers(application.getNbEndUsers() + 1);
        }
        EndUserDTO enduser = endUsers.get(event.getEndUserNumber());
            if (enduser == null) {
                enduser = new EndUserDTO();
                enduser.setEndUserNumber(event.getEndUserNumber());
                enduser.setApikey(event.getApiKey());
                enduser.setNbPoints(1);
                endUsers.put(event.getEndUserNumber(), enduser);
            } else {
                enduser.setNbPoints(enduser.getNbPoints() + 2);
                if (enduser.getNbPoints() == 100) {
                    enduser.setNbBadges(enduser.getNbBadges() + 1);
                }
                
            }
        application.setEndUsers(enduser);
    }

    public Map<String, ApplicationEndUsersDTO> getApplicationsEndUsers() {
        return applications;
    }

}