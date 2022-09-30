package server;

import java.io.Serializable;

public class MovieDesc implements Serializable {
    public String movieName;
    public String isbn;
    public String synopsis;

    public MovieDesc(String movieName, String isbn, String synopsis){
        this.movieName = movieName;
        this.isbn = isbn;
        this.synopsis = synopsis;
    }

    @Override
    public String toString(){
        return isbn + ": " + synopsis;
    }
}
