package ic.doc;

import ic.doc.catalogues.BritishLibraryCatalogue;
import ic.doc.catalogues.Library;

public class QueryBuilder {

  private String name1 = null;
  private String name2 = null;
  private String title = null;
  private Integer date1 = null;
  private Integer date2 = null;
  private Library library = BritishLibraryCatalogue.getInstance();

  public QueryBuilder withForename(String name) {
    this.name1 = name;
    return this;
  }

  public QueryBuilder withSurname(String name) {
    this.name2 = name;
    return this;
  }

  public QueryBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public QueryBuilder withPublishedAfter(int date) {
    this.date1 = date;
    return this;
  }

  public QueryBuilder withPublishedBefore(int date) {
    this.date2 = date;
    return this;
  }

  public QueryBuilder withLibrary(Library library) {
    this.library = library;
    return this;
  }

  public BookSearchQuery build() {
    return new BookSearchQuery(name1, name2, title, date1, date2, library);
  }
}
