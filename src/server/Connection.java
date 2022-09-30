package server;

import server.exceptions.InvalidCredentialsException;
import server.exceptions.SignInFailed;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Connection extends UnicastRemoteObject implements IConnection {
    private final CredentialsManager credentialsManager = new CredentialsManager();
    private final IVODService vodService = new VODService();

    protected Connection() throws RemoteException{}

    public boolean signUp(String mail, String pwd) throws SignInFailed { //(eg, if a client with this email already exists)
        // If the email is already taken, throw an error
        if (credentialsManager.checkUsername(mail)){
            throw new SignInFailed();
        }

        // Save the credentials in the manager
        credentialsManager.addCredential(mail, pwd);

        System.out.println("Client signed up with email " + mail + ". There is " + credentialsManager.getNumberOfUsers() + " who signed up.");

        return true;
    }

    public IVODService login(String mail, String pwd) throws InvalidCredentialsException { //(if mail/pwd don't match)
        // If the credentials differ from the saved ones, throw an error
        if (!credentialsManager.checkCredentials(mail, pwd)){
            throw new InvalidCredentialsException();
        }

        System.out.println("User " + mail + " logged in.");

        return vodService;
    }
}
