package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {//We create our own repository
    //CrudRepository is a Spring Framework.Data lib
    //<Class of entity (here author) to be in repository, Class/Type of ID (must extends serializable (i.e. be serializable))> that's why the ID is Long and not long
    //https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html <-- IMPORTANT
    //The central interface in Spring Data repository abstraction is Repository (probably not that much of a surprise).
    // It takes the domain class to manage as well as the id type of the domain class as type arguments.
    // This interface acts primarily as a marker interface to capture the types to work with and to help you to discover interfaces that extend this one.
    // The CrudRepository provides sophisticated CRUD functionality for the entity class that is being managed.


}
