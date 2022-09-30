package client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class ClientBox extends UnicastRemoteObject implements IClientBox {

    private Process vlcProcess = null;

    protected ClientBox() throws RemoteException {
        // Start VLC in order to display the received video
        try {
            vlcProcess = new ProcessBuilder("vlc", "-").start();
        } catch (IOException e){
            System.err.println("Couldn't start vlc. Defaulting to console.");
        }
    }

    @Override
    public void stream(byte[] chunk) {
        // If VLC is available, send it the received bytes
        // Else print them to the console

        System.out.println("Received " + chunk.length + " bytes.");
        if (vlcProcess != null && vlcProcess.isAlive()){
            try {
                vlcProcess.getOutputStream().write(chunk);
            } catch (IOException e){
                System.err.println("Couldn't write to vlc.");
            }
        } else {
            System.out.println(Arrays.toString(chunk));
        }
    }
}
