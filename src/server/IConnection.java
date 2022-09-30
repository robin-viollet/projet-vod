package server;

import server.exceptions.InvalidCredentialsException;
import server.exceptions.SignInFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {
    boolean signUp(String mail, String pwd) throws RemoteException, SignInFailed; //(eg, if a client with this email already exists)

    IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException;//(if mail/pwd don't match)
}
