package com.example.spring6app.bootstrap;

import com.example.spring6app.domain.Author;
import com.example.spring6app.domain.Book;
import com.example.spring6app.domain.Publisher;
import com.example.spring6app.repository.AuthorRepository;
import com.example.spring6app.repository.BookRepository;
import com.example.spring6app.repository.PublisherRepository;
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
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Publisher pub = new Publisher();
        pub.setPublisherName("Unknown");
        pub.setAddress("Main Street");
        pub.setCity("Sim City");
        pub.setZip("048560");
        pub.setState("Some state");

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);
        Publisher pubSaved = publisherRepository.save(pub);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        dddSaved.setPublisher(pubSaved);
        noEJBSaved.setPublisher(pubSaved);

        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Books from Eric: " + ericSaved.getBooks());
        System.out.println("Books from Rod: " + rodSaved.getBooks());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
