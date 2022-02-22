package ic.doc.catalogues;

import ic.doc.Book;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ic.doc.catalogues.QueryParser.*;

public class ImperialCollegeLibraryCatalogue implements Library{

    // imagine that each new instance of this object uses more than 500MB of RAM

    private static ic.doc.catalogues.ImperialCollegeLibraryCatalogue instance;

    private final Collection<Book> catalogue = allTheBooks();

    public static synchronized ic.doc.catalogues.ImperialCollegeLibraryCatalogue getInstance(){
      if ( instance == null ){
        instance = new ic.doc.catalogues.ImperialCollegeLibraryCatalogue();
      }
      return instance;
    }

    @Override
    public List<Book> searchFor(String query) {
      return catalogue.stream()
          .filter(book -> book.matchesAuthor(lastNameFrom(query)))
          .filter(book -> book.matchesAuthor(firstNameFrom(query)))
          .filter(book -> book.matchesTitle(titleFrom(query)))
          .filter(book -> book.publishedSince(publishedAfterFrom(query)))
          .filter(book -> book.publishedBefore(publishedBeforeFrom(query)))
          .collect(Collectors.toList());
    }


    // Assume Imperial Library has same book collection as British Library
    private Collection<Book> allTheBooks() {

      System.out.println("Memory Usage: 500MB...");

      return Arrays.asList(
          new Book("A Tale of Two Cities", "Charles Dickens", 1859),
          new Book("Pride and Prejudice", "Jane Austen", 1813),
          new Book("Pride and Prejudice", "Jane Austen", 1813),
          new Book("The Picture of Dorian Gray", "Oscar Wilde", 1890),
          new Book("Oliver Twist", "Charles Dickens", 1838),
          new Book("Frankenstein", "Mary Shelley", 1817),
          new Book("Brave New World", "Aldous Huxley", 1932),
          new Book("Lord of the Flies", "William Golding", 1954),
          new Book("Hamlet", "William Shakespeare", 1603),
          new Book("The Life and Opinions of Tristram Shandy, Gentleman", "Laurence Sterne", 1759));

      // and so on... Imagine that this list is very large and therefore uses a lot of memory.

    }
}

