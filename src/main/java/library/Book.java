package library;

/**
 * Created by Krzysztof Dudziak on 2017-03-09.
 */
 class Book {
    private String lentByUser;
    private String title,author;
    private int year;
    private long id;
    private boolean isLent;

    Book(long id,String title, int year, String author){
        this.id=id;
        this.title=title;
        this.year=year;
        this.author=author;
    }
    public long getId() {
        return id;
    }
    private String isAvailable(){
        if(!isLent){
            return " Available";
        }else{
            return " Lent by: ";
        }
    }

    public void setLentByUser(String userName){
        lentByUser=userName;
    }
    private String getLentByUser(){
        if (lentByUser == null) {
            return " YES";
        }else{
            return lentByUser;
        }
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
        return toString()+isAvailable()+" "+getLentByUser();
    }

    @Override
    public String toString() {
        return ("Id:"+id+" Title:"+title+" Year:"+year+" Author:"+author);
    }
}
