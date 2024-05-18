package guru.springframework.spring5webapp.bootstrap;


import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book book = new Book("Domain Drive Design", "1233432");
        Publisher euro = new Publisher("EuroPub", "AvenPort Street 177", "London", "Yorkshire", "3345923");
        publisherRepository.save(euro);

        eric.getBooks().add(book);
        book.getAuthors().add(eric);
        book.setPublisher(euro);
        euro.getBooks().add(book);

        authorRepository.save(eric);
        bookRepository.save(book);

        publisherRepository.save(euro);

        Author john = new Author("John", "Doe");
        Book book2 = new Book("J2EE Development", "3238352");
        john.getBooks().add(book2);
        book2.getAuthors().add(john);
        book2.setPublisher(euro);

        euro.getBooks().add(book2);
        authorRepository.save(john);
        bookRepository.save(book2);
        publisherRepository.save(euro);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Euro number of books: " + euro.getBooks().size());
    }
}
