package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books") //why ??
    public String getBooks(Model model){ //why ??
        model.addAttribute("books", bookRepository.findAll()); //why ?? --> populates the model to pass it to the view layer
        return "books/list"; //why ?? --> tells the view layer (in MVC) to look in templates/books/list.html
    }
}
/*RECAP OF WHAT IS GOING ON HERE (Use of Spring MVC + Thymeleaf)
* We are asking the browser localhost:8080/books i.e. we (the client) are asking to see the "/books".
* When we (the client) do that request, it is handled by the servlet that passes it to this controller, specifically
* To the getBooks method thanks to the mapping : Spring finds the method because we've mapped it in the
* @RequestMapping annotation (if there was no mapping we would have gotten : "his application has no explicit mapping for /error, so you are seeing this as a fallback." error)
* This method changes/populates the model and the view layer + Thymeleaf generates the HTML using the
* returned String "books/list" by appending it to "templates" (i.e. view layer uses templates/books/list.html (a Thymeleaf template) and the model)
* The HTML is written dynamically using Thymeleaf
* The HTMl is passed back to the browser to display.
* */