package server;

import server.stubs.IMovieDesc;

import java.io.Serializable;

public class MovieDesc implements IMovieDesc, Serializable {
    protected String movieName;
    protected String isbn;
    protected String synopsis;

    public MovieDesc(String movieName, String isbn, String synopsis){
        this.movieName = movieName;
        this.isbn = isbn;
        this.synopsis = synopsis;
    }

    public String getMovieName(){
        return movieName;
    }

    public String getIsbn(){
        return isbn;
    }

    public String getSynopsis(){
        return synopsis;
    }

    @Override
    public String toString(){
        return isbn + ": " + synopsis;
    }
}
