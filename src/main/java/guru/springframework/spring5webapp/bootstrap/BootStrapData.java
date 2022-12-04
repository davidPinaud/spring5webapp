package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Code to generate a bit of data

@Component //tells Spring that this is a Spring managed Component (it scans for them,  Instantiate them and inject any specified dependencies into them and inject them wherever needed)
public class BootStrapData implements CommandLineRunner {
    //The "Command Line Runner" interface forces the implementation of run() method that is called in the web app entry point Spring5webappApplication.java
    //From spring doc : CommandLineRunner is a simple Spring Boot interface with a run method. Spring Boot will automatically call the run method of all beans implementing this interface after the application context has been loaded. https://www.baeldung.com/spring-boot-console-app
    //Most console applications will only have a single class that implements CommandLineRunner. If our application has multiple classes that implement CommandLineRunner, the order of execution can be specified using Spring's @Order annotation.

    //use of dependency injection :
    //the spring managed component BootStrapData has three properties (authorRepository,bookRepository,publisherRepository) that are final. They are initialized inside the
    //constructor --> that tells the Spring Framework to when it constructs this bean that it has to inject an instance of author repository, book repository and publisher repository.

    //Basically Spring5webappApplication main class calls run(), to do so it must construct an object (bean) that is BootStrapData because this class implements CommandLineRunner (I think...).
    //By constructing this object, it instantiates book, author and publisher repositories... which is weird because they are all interfaces...

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric); //saves into the H2 database AuthorRepository
        bookRepository.save(ddd); //saves into the H2 database BookRepository

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "321651651");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod); //saves into the H2 database AuthorRepository
        bookRepository.save(noEJB); //saves into the H2 database BookRepository

        Publisher publisher = new Publisher();
        publisher.setName("RandomPublisher");
        publisher.setCity("Paris");
        publisher.setState("Ile de France");
        publisher.setZip("750013");
        publisher.setAddressLine1("1 Avenue d'Italie");


        ddd.setPublisher(publisher);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(ddd);
        publisher.getBooks().add(noEJB);

        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books : "+bookRepository.count());
        System.out.println("Number of Publisher : "+publisherRepository.count());
        System.out.println("Number of books by publisher : "+publisher.getBooks().size());
    }
}
