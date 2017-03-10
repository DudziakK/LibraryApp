package library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Cptonduty on 2017-03-10.
 */
public class DistinctBook {
    private int lentCount=0;
    private int availableCount=0;
    private String title,author;
    private int year;
    private List<Book> singleBooks = new ArrayList<>();

    public void addToList(Book book){
        singleBooks.add(book);
    }
    public void listAllBooks(){
        Iterator<Book> iter = singleBooks.iterator();
        if(iter.hasNext()){
            System.out.println(iter.next().toString()+countBooks());
        }


    }
    private String countBooks(){
        lentCount=0;
        availableCount=0;
        for (Book books:singleBooks){
            if(books.getIsLent()){
                lentCount++;
            }else {
                availableCount++;
            }
        }
        return " Available: "+availableCount+" Lent: "+lentCount;
    }
    public void removeBookFromCatalog(long bookId){
        Iterator<Book> iter = singleBooks.iterator();
        if(iter.hasNext()){
            Book book= iter.next();
            if(book.getId()==bookId){
                singleBooks.remove(book);
                System.out.println("Book with Id: "+ bookId+"is removed");
            }

        }
    }
}
