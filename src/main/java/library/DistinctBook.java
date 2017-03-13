package library;

import java.util.*;

/**
 * Created by Krzysztof Dudziak on 2017-03-10.
 */
 class DistinctBook {

    private List<Book> singleBooks = new ArrayList<>();

    void addToList(Book book){
        singleBooks.add(book);
    }
    String listAllBooks(){
        String allbooks="";
        Iterator<Book> iter = singleBooks.iterator();
        if(iter.hasNext()){
            allbooks+=iter.next().toString()+countBooks();
        }
        return allbooks;
    }

    private String countBooks(){
        int lentCount=0;
        int availableCount=0;
        for (Book books:singleBooks){
            if(books.getIsLent()){
                lentCount++;
            }else {
                availableCount++;
            }
        }
        return " Available: "+availableCount+" Lent: "+lentCount;
    }

    void removeBook(long bookId){
        Iterator<Book> iter = singleBooks.iterator();
        if(iter.hasNext()){
            Book book= iter.next();
            if(book.getId()==bookId){
                singleBooks.remove(book);
                System.out.println("Book with Id:"+ bookId+" is removed");
            }
        }
    }
}
