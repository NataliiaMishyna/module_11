import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "planet")
@Data
public class Planet {

    @Id
    private String id;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @OneToMany(mappedBy = "toPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> ticketsTo;

    @OneToMany(mappedBy = "fromPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> ticketFrom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
