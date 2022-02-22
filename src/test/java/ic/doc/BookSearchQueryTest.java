package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;

public class BookSearchQueryTest {

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    List<Book> books = new QueryBuilder( ).withSurname("dickens").build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    List<Book> books = new QueryBuilder().withForename("Jane").build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    List<Book> books = new QueryBuilder().withTitle("Two Cities").build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books = new QueryBuilder().withPublishedBefore(1700).build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books = new QueryBuilder().withPublishedAfter(1950).build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books = new QueryBuilder().withSurname("dickens").withPublishedAfter(1840).build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    List<Book> books = new QueryBuilder().withTitle("of").withPublishedAfter(1800).withPublishedBefore(2000).build().execute();

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
