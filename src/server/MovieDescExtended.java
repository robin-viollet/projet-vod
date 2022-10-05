package server;

import server.stubs.IMovieDescExtended;

public class MovieDescExtended extends MovieDesc implements IMovieDescExtended {
    protected byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[] teaser){
        super(movieName, isbn, synopsis);

        this.teaser = teaser;
    }

    public byte[] getTeaser(){
        return teaser;
    }
}
