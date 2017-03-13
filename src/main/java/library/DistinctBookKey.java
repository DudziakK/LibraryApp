package library;

/**
 * Created by Krzysztof Dudziak on 2017-03-10.
 */
class DistinctBookKey {
    private String title,author;
    private int year;
    DistinctBookKey(String title, int year,String author){
        this.title=title;
        this.year=year;
        this.author=author;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistinctBookKey that = (DistinctBookKey) o;

        if (year != that.year) return false;
        if (!title.equals(that.title)) return false;
        return author.equals(that.author);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + year;
        return result;
    }
}
