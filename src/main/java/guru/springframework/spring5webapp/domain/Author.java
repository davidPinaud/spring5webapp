package guru.springframework.spring5webapp.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity //for JPA (java persistence API/Jakarta persistence) and hibernate
public class Author {
    @Id //tells JPA that this property is an ID (because Author is an Entity)
    @GeneratedValue(strategy = GenerationType.AUTO) //tells hibernate that the strategy to generate the ID is AUTO
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy = "authors") //mappedBy="authors"  means that in the "Book" class, the name of the property used to link with the authors is “authors”. mappedBy element is specified in the non-owner side.
    //This annotation is read by Hibernate
    // @ManyToMany means the relationship between the entities is bidirectional --> the mappedBy element must be used to specify the relationship ownership.
    private Set<Book> books= new HashSet<>(); //hashset are equivalent to sets in Python, dictionaries are hashmaps

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author() { //JPA requires no args constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
