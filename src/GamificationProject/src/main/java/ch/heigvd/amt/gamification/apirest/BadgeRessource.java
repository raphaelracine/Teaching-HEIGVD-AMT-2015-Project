package ch.heigvd.amt.gamification.apirest;

import ch.heigvd.amt.gamification.dto.BadgeDTO;
import ch.heigvd.amt.gamification.model.Application;
import ch.heigvd.amt.gamification.model.Badge;
import ch.heigvd.amt.gamification.services.ApplicationsManagerLocal;
import ch.heigvd.amt.gamification.services.BadgesManagerLocal;
import ch.heigvd.amt.gamification.services.BadgesProcessorLocal;
import ch.heigvd.amt.gamification.services.dao.GamificationDomainEntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Raphaël Racine
 */
@Stateless
@Path("badges")
public class BadgeRessource {

    @EJB
    private BadgesManagerLocal badgesManager;

    @EJB
    private ApplicationsManagerLocal applicationsManager;
    
    @EJB
    private BadgesProcessorLocal badgesProcessor;

    @GET
    @Produces("application/json")
    public List<BadgeDTO> getBadges(String apiKey) {
        List<BadgeDTO> dto = new ArrayList<>();
        List<Badge> badges = badgesManager.findByApiKey(apiKey);

        for (Badge badge : badges) {
            dto.add(toDTO(badge));
        }
        return dto;
    }

    @POST
    @Consumes("application/json")
    public void postBadge(BadgeDTO badgeDTO) {
        badgesProcessor.processPostBadge(badgeDTO);
    }

    @PUT
    @Consumes("application/json")
    public void putBadge(@PathParam(value = "badgeID") Long badgeID, BadgeDTO badgeDTO) {
        badgesProcessor.processPutBadge(badgeID, badgeDTO);
    }

    @DELETE
    @Consumes("application/json")
    public void deleteBadge(@PathParam(value = "badgeID") Long badgeID, String apiKey) {
    }

    public BadgeDTO toDTO(Badge badge) {

        BadgeDTO dto = new BadgeDTO();
        dto.setName(badge.getName());
        dto.setApiKey(badge.getApplication().getApiKey().getKey());
        dto.setId(badge.getId());

        return dto;
    }

}
