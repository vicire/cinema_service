package philharmonic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "concert_session_id")
    private ConcertSession concertSession;
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConcertSession getConcertSession() {
        return concertSession;
    }

    public void setConcertSession(ConcertSession concertSession) {
        this.concertSession = concertSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{Ticket: "
                + "id " + id
                + ", concert session: " + concertSession
                + ", user: " + user + "}";
    }
}
