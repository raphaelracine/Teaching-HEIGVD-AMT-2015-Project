package ch.heigvd.amt.gamification.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Parfait Noubissi
 */
@Entity

@NamedQueries({
    @NamedQuery(name = "EndUser.countEndUsersCreatedBetweenTwoDates",
            query = "SELECT COUNT(e) FROM EndUser e WHERE e.regDate BETWEEN :date1 AND :date2"),
    @NamedQuery(name = "EndUser.findByNo",
            query = "SELECT e FROM EndUser e WHERE e.userID = :no")
})
public class EndUser extends AbstractDomainModelEntity<Long> {

    @ManyToOne
    private Application application;

    @OneToMany(mappedBy = "endUser")
    private List<Event> events;

    @OneToMany(mappedBy = "endUser")
    private List<AwardPoint> points;

    public EndUser() {
    }

    private String userID;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<AwardPoint> getPoints() {
        return points;
    }

    public void setPoints(List<AwardPoint> points) {
        this.points = points;
    }
}
