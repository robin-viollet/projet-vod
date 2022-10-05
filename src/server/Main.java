package server;

import server.stubs.IConnection;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args){
        try {
            int port = 10001;
            Registry reg = LocateRegistry.createRegistry(port);

            System.out.println("Server is listening on port " + port + ".");

            IConnection c = new Connection();

            reg.bind("connection", c);
        } catch (RemoteException | AlreadyBoundException e){
            e.printStackTrace();
        }
    }
}