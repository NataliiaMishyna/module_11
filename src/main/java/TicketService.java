import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.util.List;

public class TicketService {

    private EntityManagerFactory entityManagerFactory;

    public TicketService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void close() {
        entityManagerFactory.close();
    }

    public Ticket create(Ticket ticket) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(ticket);
        entityManager.getTransaction().commit();
        entityManager.close();
        return ticket;
    }

    public Ticket read(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Ticket ticket = entityManager.find(Ticket.class, id);
        entityManager.close();
        return ticket;
    }

    public void update(Ticket ticket) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(ticket);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Ticket ticket = entityManager.find(Ticket.class, id);
        entityManager.remove(ticket);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Ticket> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Ticket> tickets = entityManager.createQuery("FROM Ticket", Ticket.class).getResultList();
        entityManager.close();
        return tickets;
    }
}