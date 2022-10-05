package server;

public class MovieDescExtended extends MovieDesc implements IMovieDescExtended {
    public byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[] teaser){
        super(movieName, isbn, synopsis);

        this.teaser = teaser;
    }

    public byte[] getTeaser(){
        return teaser;
    }
}
