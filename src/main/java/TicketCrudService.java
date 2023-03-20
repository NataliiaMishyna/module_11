import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TicketCrudService {
    private final SessionFactory sessionFactory;

    public TicketCrudService() {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    public void saveTicket(Ticket ticket) throws Exception {
        if (ticket.getClient() == null || ticket.getClient().getId() == null) {
            throw new Exception("Cannot save ticket for non-existent or null client");
        }
        if (ticket.getFromPlanet() == null || ticket.getFromPlanet().getId() == null) {
            throw new Exception("Cannot save ticket for non-existent or null from planet");
        }
        if (ticket.getToPlanet() == null || ticket.getToPlanet().getId() == null) {
            throw new Exception("Cannot save ticket for non-existent or null to planet");
        }

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        }
    }

    public Ticket getTicketById(Long id) {
        Ticket ticket = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            ticket = session.get(Ticket.class, id);
            transaction.commit();
        }
        return ticket;
    }

    public void deleteTicket(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
            }
            transaction.commit();
        }
    }

}
