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
            allBooks.get(bookId).setLentByUser(userName);
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
    public void showAllDetailsById(long bookId){
        if(allBooks.containsKey(bookId)){
            System.out.println(allBooks.get(bookId).showAllBooksDetails());
        }
    }
    public void performSearchWithAllArgsv2(Object ... params){
        int count=0;
        String s1=null;
        String s2=null;
        int y=0;
        short stringCount=0;
        short yearCount=0;
        List<Object> objects = new ArrayList<>();
        for (Object o:params){
            if (count<3){
                if(o instanceof String){
                    if(s1!=null){
                        s2=(String)o;
                        stringCount++;
                    }else {
                        if(s1==null) {
                            s1 = (String) o;
                            stringCount++;
                        }else{
                            System.out.println("Wrong args");
                            return;
                        }
                    }
                }else if(o instanceof Integer){
                    if (y==0){
                        y=(Integer)o;
                        yearCount++;
                    }else{
                        System.out.println("Invalid args");
                        return;
                    }
                }else{
                    System.out.println("Wrong arguments type2");
                    return;
                }
                objects.add(o);
                count++;
            }else{
                System.out.println("Wrong args");
                return;
            }
        }
    }
    public void checkMyObjects(Object obj){

    }

    public void performSearchWithAllArgs(Object ... params) {
        Map<Long, Book> booksFound = new HashMap<>();
        String s1 = null;
        String s2 = null;
        int y = 0, count = 1;
        short stringCount = 0;
        short yearCount = 0;

        for (Object o : params) {
            if(count<3){
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
            System.out.println("Pass arguments");
            return;
        }
        System.out.println("Counts: "+count);
        System.out.println("stringcount: "+stringCount +"yearCount: "+yearCount);
        //put all to the table that will require only 3 cheeks
        if(s1!=null&&s2!=null&&y!=0){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(((entry.getValue().getTitle().equals(s1)|| entry.getValue().getAuthor().equals(s1))&&
                entry.getValue().getYear()==y)&&((entry.getValue().getTitle().equals(s2)|| entry.getValue().getAuthor().equals(s2))&&
                        entry.getValue().getYear()==y)){
                    booksFound.put(entry.getKey(),entry.getValue());
                }
            }
        }
        if(s1!=null&&s2!=null&&y==0){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(((entry.getValue().getTitle().equalsIgnoreCase(s1)|| entry.getValue().getAuthor().equalsIgnoreCase(s1))&&
                        ((entry.getValue().getTitle().equalsIgnoreCase(s2)|| entry.getValue().getAuthor().equalsIgnoreCase(s2))))){
                    booksFound.put(entry.getKey(),entry.getValue());
                }
            }
        }
        if (y!=0&&s1!=null&&s2==null){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(((entry.getValue().getTitle().equalsIgnoreCase(s1)|| entry.getValue().getAuthor().equalsIgnoreCase(s1)))&&
                        entry.getValue().getYear()==y){
                    booksFound.put(entry.getKey(),entry.getValue());
                }
            }
        }
        if (y!=0&&s1==null&&s2!=null){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(((entry.getValue().getTitle().equalsIgnoreCase(s2)|| entry.getValue().getAuthor().equalsIgnoreCase(s2)))&&
                        entry.getValue().getYear()==y){
                    booksFound.put(entry.getKey(),entry.getValue());
                }
            }
        }
        if (y!=0&&s1==null&&s2==null){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(entry.getValue().getYear()==y){
                    booksFound.put(entry.getKey(),entry.getValue());
                }
            }
        }
        if (y==0&&s1!=null&&s2==null){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(entry.getValue().getAuthor().equalsIgnoreCase(s1)|| entry.getValue().getTitle().equalsIgnoreCase(s1)){
                    booksFound.put(entry.getKey(),entry.getValue());
                }
            }
        }
        if (y==0&&s1==null&&s2!=null){
            for (Map.Entry<Long,Book> entry:allBooks.entrySet()){
                if(entry.getValue().getAuthor().equalsIgnoreCase(s2)|| entry.getValue().getTitle().equalsIgnoreCase(s2)){
                    booksFound.put(entry.getKey(),entry.getValue());
                }
            }
        }
        for (Map.Entry<Long,Book> entry: booksFound.entrySet()){
            showAllDetailsById(entry.getValue().getId());
        }
        System.out.println("s1: "+s1+" s2:"+s2+" year: "+y);
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

