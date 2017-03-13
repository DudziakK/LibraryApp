package library;

import java.nio.ShortBuffer;
import java.util.*;

/**
 * Created by Krzysztof Dudziak on 2017-03-09.
 */
public class Library {
    private static long id = 1;
    private Map<DistinctBookKey, DistinctBook> libraryMap = new HashMap<>();
    private Map<Long, Book> allBooks = new HashMap<>();

    //Allows to add new book to the library with three arguments, if book already exists in library method adds book to the DistinctBook instance,
    // otherwise new catalog is created and book is assigned to newly created DistinctBook object
    public void addNewBook(String title, int year, String author) {
        DistinctBook distBook = new DistinctBook();
        Book book = new Book(id, title, year, author);
        DistinctBookKey db = new DistinctBookKey(title, year, author);
        if (!libraryMap.containsKey(db)) {
            libraryMap.put(new DistinctBookKey(title, year, author), distBook);
            distBook.addToList(book);
        } else {
            libraryMap.get(db).addToList(book);
        }
        allBooks.put(id, book);
        id++;
    }
    //Allows to list all books distinctly with information about how many is curently available or lent
    public String showAllBooksDistinctly() {
        String allBooks="";
        for (Map.Entry<DistinctBookKey, DistinctBook> entry : libraryMap.entrySet()) {
            allBooks+=entry.getValue().listAllBooks()+"\n";
        }
        return allBooks;
    }

    public boolean removeBookById(long bookId) {
        if(allBooks.containsKey(bookId)&& !(allBooks.get(bookId).getIsLent())){
            DistinctBookKey dbkTemp = new DistinctBookKey(allBooks.get(bookId).getTitle(),allBooks.get(bookId).getYear(),allBooks.get(bookId).getAuthor());
            if(libraryMap.containsKey(dbkTemp)){
                libraryMap.get(dbkTemp).removeBook(bookId);
            }
            allBooks.remove(bookId);
            return true;
        }else{
            return false;
        }
    }
    public boolean lentBookById(long bookId,String userName){
        if(allBooks.containsKey(bookId)&&!allBooks.get(bookId).getIsLent()){
            allBooks.get(bookId).setIsLent();
            allBooks.get(bookId).setLentByUser(userName);
            return true;
        }else
        {
            System.out.println("Book is not in Library");
            return false;
        }
    }
    public void showAllBooks() {
       for(Map.Entry<Long,Book> entry: allBooks.entrySet()){
           System.out.println(entry.getValue().toString());
       }
    }
    public String showAllDetailsById(long bookId){
        String bookDetails="";
        if(allBooks.containsKey(bookId)){
            bookDetails+=allBooks.get(bookId).showAllBooksDetails();
        }else{
            bookDetails+="Book with id:"+bookId+" is not in library";
        }
        return bookDetails;
    }

    public void searchWithAllArgs(Object ... params) {
        Map<Long, Book> booksFound = new HashMap<>();
        String s1 = null;
        String s2 = null;
        int foundCount=0;
        int y = 0, count = 1;
        short stringCount = 0;
        short yearCount = 0;

        for (Object o : params) {
            if(count<4){
            if (o instanceof String) {
                if (s1 != null) {
                    s2 = (String) o;
                    stringCount++;
                } else {
                    if (s1 == null) {
                        s1 = (String) o;
                        stringCount++;
                    } else {
                        System.out.println("Wrong args");
                        return;
                    }
                }
            } else if (o instanceof Integer) {
                if (y == 0) {
                    y = (Integer) o;
                    yearCount++;
                } else {
                    System.out.println("Invalid args");
                    return;
                }
            } else {
                System.out.println("Wrong arguments type2");
                return;
            }
            count++;
        }else{
            System.out.println("Wrong args");
            return;
        }
        }
        if(count==1){
            System.out.println("You passed 0 arguments");
            return;
        }
        System.out.println("Counts: "+count);
        System.out.println("stringCount: "+stringCount +"yearCount: "+yearCount);
        //put all to the table that will require only 3 cheeks
        if(s1!=null&&s2!=null&&y!=0){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(((entry.getValue().getTitle().equals(s1)|| entry.getValue().getAuthor().equals(s1))&&
                entry.getValue().getYear()==y)&&((entry.getValue().getTitle().equals(s2)|| entry.getValue().getAuthor().equals(s2))&&
                        entry.getValue().getYear()==y)){
                    booksFound.put(entry.getKey(),entry.getValue());
                    foundCount++;
                }
            }
        }
        if(s1!=null&&s2!=null&&y==0){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(((entry.getValue().getTitle().equalsIgnoreCase(s1)|| entry.getValue().getAuthor().equalsIgnoreCase(s1))&&
                        ((entry.getValue().getTitle().equalsIgnoreCase(s2)|| entry.getValue().getAuthor().equalsIgnoreCase(s2))))){
                    booksFound.put(entry.getKey(),entry.getValue());
                    foundCount++;
                }
            }
        }
        if (y!=0&&s1!=null&&s2==null){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(((entry.getValue().getTitle().equalsIgnoreCase(s1)|| entry.getValue().getAuthor().equalsIgnoreCase(s1)))&&
                        entry.getValue().getYear()==y){
                    booksFound.put(entry.getKey(),entry.getValue());
                    foundCount++;
                }
            }
        }
        if (y!=0&&s1==null&&s2!=null){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(((entry.getValue().getTitle().equalsIgnoreCase(s2)|| entry.getValue().getAuthor().equalsIgnoreCase(s2)))&&
                        entry.getValue().getYear()==y){
                    booksFound.put(entry.getKey(),entry.getValue());
                    foundCount++;
                }
            }
        }
        if (y!=0&&s1==null&&s2==null){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(entry.getValue().getYear()==y){
                    booksFound.put(entry.getKey(),entry.getValue());
                    foundCount++;
                }
            }
        }
        if (y==0&&s1!=null&&s2==null){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(entry.getValue().getAuthor().equalsIgnoreCase(s1)|| entry.getValue().getTitle().equalsIgnoreCase(s1)){
                    booksFound.put(entry.getKey(),entry.getValue());
                    foundCount++;
                }
            }
        }
        if (y==0&&s1==null&&s2!=null){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(entry.getValue().getAuthor().equalsIgnoreCase(s2)|| entry.getValue().getTitle().equalsIgnoreCase(s2)){
                    booksFound.put(entry.getKey(),entry.getValue());
                    foundCount++;
                }
            }
        }
        if(foundCount==0){
            System.out.println("Not found");
            return;
        }else{
            for (Map.Entry<Long,Book> entry: booksFound.entrySet()){
                showAllDetailsById(entry.getValue().getId());
            }
        }
        System.out.println("s1: "+s1+" s2:"+s2+" year: "+y);
    }
}