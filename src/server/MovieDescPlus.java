package server;

public class MovieDescPlus extends MovieDesc {
    public byte[] teaser;

    public MovieDescPlus(String movieName, String isbn, String synopsis, byte[] teaser){
        super(movieName, isbn, synopsis);

        this.teaser = teaser;
    }
}
