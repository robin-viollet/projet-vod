package server.stubs;

import client.IClientBox;
import server.Bill;
import server.MovieDesc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVODService extends Remote {
    List<IMovieDesc> viewCatalog() throws RemoteException;
    Bill playmovie(String isbn, IClientBox box) throws RemoteException;
}
