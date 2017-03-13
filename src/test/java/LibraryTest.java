
import library.Library;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Created by Krzysztof Dudziak on 2017-03-09.
 */
public class LibraryTest {
    private static Library library;

    @Before
    public void shouldCreateTestEnviroment() {
        library = new Library();
    }

    @Test
    public void shouldShowEmptyLibrary() {
        System.out.println(library.showAllBooksDistinctly());
        assertEquals("At the beginig there are no books in library", "", library.showAllBooksDistinctly());
    }

    @Test
    public void shouldAddBookToLibrary() {
        library.addNewBook("Metro", 2004, "Dimitry Glukovsky");
        System.out.println(library.showAllBooksDistinctly());
        assertEquals("Id:1 Title:Metro Year:2004 Author:Dimitry Glukovsky Available: 1 Lent: 0\n", library.showAllBooksDistinctly());
    }

    @Test
    public void shouldListAllBooksDistinctly() {
        library.addNewBook("Metro", 2004, "Dimitry Glukovsky");
        library.addNewBook("Metro", 2003, "Dimitry Glukovsky");
        library.addNewBook("Stalker", 2004, "Michał Głogowski");
        library.addNewBook("Game of Thrones", 2005,"George R.R. Martin");
        library.addNewBook("Game of Thrones", 2005, "George R.R. Martin");
        library.addNewBook("Metro", 2004, "Dimitry Glukovsky");
        System.out.println(library.showAllBooksDistinctly());
    }

    @Test
    public void shouldLentBookAndPassName(){
        library.addNewBook("Metro",2004,"Dimitry Glukovsky");
        System.out.println(library.showAllDetailsById(1));
        assertTrue(library.lentBookById(1,"Krzysztof Dudziak"));
        System.out.println(library.showAllDetailsById(1));
        assertFalse(library.lentBookById(1,"Krzysztof Dudziak"));
    }
    @Test
    public void shouldRemoveBookByIdIfExistsAndIfNotLent(){
        library.addNewBook("Metro",2004,"Dimitry Glukovsky");
        assertTrue(library.removeBookById(1));
        assertFalse(library.removeBookById(2));
    }

    @Test
    public void shouldShowAllBookDetailsById(){
        library.addNewBook("Metro", 2004, "Dimitry Glukovsky");
        System.out.println(library.showAllDetailsById(1));
        System.out.println(library.showAllDetailsById(2));
        assertTrue(library.showAllDetailsById(1)!=null);
        assertTrue(library.showAllDetailsById(2).equals("Book with id:2 is not in library"));

    }
    @Test
    public void shouldSearchBookWithWrongArguments(){
        library.addNewBook("Metro", 2004, "Dimitry Glukovsky");
        library.addNewBook("Metro", 2002, "Dimitry Glukovsky");
        library.addNewBook("Game of Thrones", 2005, "George R.R. Martin");
        library.addNewBook("Metro", 2005, "Dimitry Glukovsky");
        library.addNewBook("Stalker", 2004, "Michał Głogowski");
        library.addNewBook("Metro", 2003, "Dimitry Glukovsky");
        library.addNewBook("Game of Thrones", 2005, "George R.R. Martin");
        System.out.println(library.searchWithAllArgs('c'));
        System.out.println(library.searchWithAllArgs("Metro",2004,"Dimitry Glukovsky","Stalker"));
        System.out.println(library.searchWithAllArgs());
        System.out.println(library.searchWithAllArgs("Java"));
        assertEquals("Wrong arguments type",library.searchWithAllArgs('c',2004));
        assertEquals("Pass 3 or less arguments",library.searchWithAllArgs("Metro",2003,"Michał Głogowski",2004,'c'));
        assertEquals("You passed 0 arguments",library.searchWithAllArgs());
        assertEquals("Not found",library.searchWithAllArgs("Java"));
    }
    @Test
    public void shouldSearchBookWithDifferentArguments(){
        library.addNewBook("Metro", 2004, "Dimitry Glukovsky");
        library.addNewBook("Metro", 2002, "Dimitry Glukovsky");
        library.addNewBook("Game of Thrones", 2005, "George R.R. Martin");
        library.addNewBook("Metro", 2005, "Dimitry Glukovsky");
        library.addNewBook("Stalker", 2004, "Michał Głogowski");
        library.addNewBook("Metro", 2003, "Dimitry Glukovsky");
        library.addNewBook("Game of Thrones", 2004, "George R.R. Martin");
        System.out.println(library.searchWithAllArgs("Metro"));
        System.out.println(library.searchWithAllArgs("Metro",2004));
        System.out.println(library.searchWithAllArgs(2004));
        System.out.println(library.searchWithAllArgs("George R.R. Martin"));
        System.out.println(library.searchWithAllArgs("George R.R. Martin",2004));
        System.out.println(library.searchWithAllArgs("Stalker",2004,"Michał Głogowski"));

    }
}
