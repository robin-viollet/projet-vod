package server;

import client.IClientBox;
import server.stubs.IMovieDesc;
import server.stubs.IVODService;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {
    private final Streamer streamer = new Streamer();

    private List<IMovieDesc> catalog = List.of(
            new MovieDesc("Inception", "i-nce-ption0", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster."),
            new MovieDescExtended("The Batman", "t-heb-atman0", "When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.", new byte[]{0x0}),
            new MovieDesc("Inglourious Basterds", "i-ngl-baste-s", "In Nazi-occupied France during World War II, a plan to assassinate Nazi leaders by a group of Jewish U.S. soldiers coincides with a theatre owner's vengeful plans for the same."),
            new MovieDescExtended("Shutter Island", "0-688-16317-3", "In 1954, a U.S. Marshal investigates the disappearance of a murderer who escaped from a hospital for the criminally insane.", new byte[]{0x0}));

    public VODService() throws RemoteException {}

    public List<IMovieDesc> viewCatalog(){
        return catalog;
    }

    public Bill playmovie(String isbn, IClientBox box){
        streamer.stream(box);

        return new Bill(isbn, BigInteger.TEN);
    }
}
