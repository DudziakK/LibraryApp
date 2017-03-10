package library;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by Cptonduty on 2017-03-09.
 */
public class Book {
    private String lentByUser;
    private String title,author;
    private int year;
    private int count;
    private long id;
    private boolean isLent;
    private List<Long> myCopies = new ArrayList<>();


    Book(long id,String title, int year, String author){
        this.id=id;
        this.title=title;
        this.year=year;
        this.author=author;
    }
    public long getId() {
        return id;
    }
    public String isAvaiable(){
        if(isLent==false){
            return " Available";
        }else{
            return " Lent by: ";
        }
    }
    public void updateMyCopies(Long bookId){

    }
    public void setLentByUser(String userName){
        lentByUser=userName;
    }
    public String getLentByUser(){
        return lentByUser;
    }
    public boolean getIsLent(){
        return isLent;
    }
    public void setIsLent(){
        isLent=true;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public int getYear(){
        return year;
    }

    public String showAllBooksDetails(){
        return toString()+isAvaiable()+" "+getLentByUser();
    }

    @Override
    public String toString() {
        return ("Id:"+id+"Title:"+title+"  Year:"+year+"  Author:"+author);
    }
}
