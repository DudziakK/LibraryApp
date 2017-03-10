import com.sun.xml.internal.ws.policy.AssertionSet;
import library.Book;
import library.Library;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Cptonduty on 2017-03-09.
 */
public class LibraryTest {
    Library lab = new Library();

    @Test
    public void firstStuff(){

    }
    @Test
    public void listAllBooksDistinctly(){
        Library lab2 = new Library();
        lab2.addNewBook("Metro",2004,"Dimitry Glukovsky");
        lab2.addNewBook("Game of Thrones",2005,"George R.R. Martin");
        lab2.addNewBook("Metro",2004,"Dimitry Glukovsky");
        lab2.addNewBook("Stalker",2004,"Michał Głogowski");
        lab2.addNewBook("Metro",2003,"Dimitry Glukovsky");
        lab2.showAllBooks();

    }
    @Test
    public void showMeEverySingleBook(){
        Library lab2 = new Library();
        lab2.addNewBook("Metro",2004,"Dimitry Glukovsky");
        lab2.addNewBook("Game of Thrones",2005,"George R.R. Martin");
        lab2.addNewBook("Metro",2004,"Dimitry Glukovsky");
        lab2.addNewBook("Stalker",2004,"Michał Głogowski");
        lab2.addNewBook("Metro",2003,"Dimitry Glukovsky");
        lab2.showAllBooksAgain();
        lab2.removeBookById(2);
        //lab2.lentBookById(3);
        lab2.showAllBooksAgain();
    }
    @Test
    public void he(){
        Library lab2 = new Library();
        lab2.addNewBook("Metro",2004,"Dimitry Glukovsky");
        lab2.addNewBook("Game of Thrones",2005,"George R.R. Martin");
        lab2.addNewBook("Metro",2004,"Dimitry Glukovsky");
        lab2.addNewBook("Stalker",2004,"Michał Głogowski");
        lab2.addNewBook("Metro",2003,"Dimitry Glukovsky");
        lab2.showAllBooks();
        lab2.removeBookById(2);
       // lab2.lentBookById(3);
       // lab2.lentBookById(2);
        lab2.showAllBooks();
    }
}
