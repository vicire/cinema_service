package cinema.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }

    @Override
    public boolean equals(Object movie) {
        if (this == movie) {
            return true;
        }
        if (movie == null || getClass() != movie.getClass()) {
            return false;
        }
        Movie movie1 = (Movie) movie;
        return Objects.equals(id, movie1.id) && Objects.equals(title, movie1.title)
                && Objects.equals(description, movie1.description);
    }

    @Override
    public String toString() {
        return "{Movie - "
                + "id: " + id
                + ", title: " + title
                + ", description: " + description + "}";
    }
}
