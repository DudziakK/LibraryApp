package library;

import java.util.*;

/**
 * Created by Krzysztof Dudziak on 2017-03-09.
 */
public class Library {
    private static long id = 1;
    private Map<DistinctBookKey, DistinctBook> libraryMap = new HashMap<>();
    private Map<Long, Book> allBooks = new HashMap<>();


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

    public void showAllBooks() {
        for (Map.Entry<DistinctBookKey, DistinctBook> entry : libraryMap.entrySet()) {
            entry.getValue().listAllBooks();
        }
    }

    public void removeBookById(long bookId) {
        if(allBooks.containsKey(bookId)&& !(allBooks.get(bookId).getIsLent())){
            DistinctBookKey dbkTemp = new DistinctBookKey(allBooks.get(bookId).getTitle(),allBooks.get(bookId).getYear(),allBooks.get(bookId).getAuthor());
            if(libraryMap.containsKey(dbkTemp)){
                libraryMap.get(dbkTemp).removeBookFromCatalog(bookId);
            }
            allBooks.remove(bookId);

        }
    }
    public void lentBookById(long bookId,String userName){
        if(allBooks.containsKey(bookId)){
            allBooks.get(bookId).setIsLent();
            LibraryUser libUser =new LibraryUser(bookId,userName);
        }else
        {
            System.out.println("Book is not in Library");
        }

    }

    public void showAllBooksAgain() {
       for(Map.Entry<Long,Book> entry: allBooks.entrySet()){
           System.out.println(entry.getValue().toString());
       }
    }



    /*

    private static long id=1;
    private static long id2=1;
    private Map<Long,Book> libraryMap = new HashMap<>();
    private Map<Long,Book> libraryMap2 = new HashMap<>();

    public void addNewBook(String title, int year, String author){
        Book book = new Book(id,title,year,author);
        libraryMap.put(id,book);
        id++;
    }

    public void addNewBookWithCounting(String title,int year,String author){
        Book bookToCheck = new Book(id,title,year,author);
        for (Map.Entry<Long,Book> entry: libraryMap.entrySet()){
            if (!(bookToCheck.equals(entry.getValue()))){
                libraryMap2.put(id2,bookToCheck);
                id2++;
                System.out.println("is it working?");

            }
        }
    }

    public void getBookById(long bookId){
        if(libraryMap.containsKey(bookId)) {
            System.out.println(libraryMap.get(bookId));
        }else{
            System.out.println("There is no such a book");
        }
    }

    public boolean removeBook(long bookId){
        if (libraryMap.containsKey(bookId)&&!libraryMap.get(bookId).getIsLent()){
            libraryMap.remove(bookId);
            System.out.println("Book is removed from catalog");
            return true;
        }else{
            System.out.println("This book is not in catalog");
            return false;
        }
    }
    public boolean lentBook(long bookId,String userName){
        if (libraryMap.containsKey(bookId)&&!libraryMap.get(bookId).getIsLent()){
           libraryMap.get(bookId).setIsLent();
            System.out.println("Book is lent to - "+userName);
            LibraryUser lb = new LibraryUser(userName,libraryMap.get(bookId));
            return true;
        }else{
            System.out.println("Book is already lent or doesn't exist in database");
            return false;
        }
    }

    public void listAllBooks(){
        int count=0;
        List<Book> uniqueList = new ArrayList<>();
        for (Map.Entry<Long,Book> entry: libraryMap.entrySet()){
            count=0;
            for (Map.Entry<Long,Book> check: libraryMap.entrySet()) {
                if (entry.getValue().equals(check.getValue())){
                    count++;
                }
            }
            System.out.println(entry.getValue().toString()+count);

        }
    }
    public boolean simpletest(long first, long second){
        boolean b=false;
        b=libraryMap.get(first).equals(libraryMap.get(second));
        return b;
    }
    */
}
