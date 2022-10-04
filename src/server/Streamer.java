package server;

import client.IClientBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class Streamer {
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    // Stream random bytes to the client
    private final Function<IClientBox, Void> randomBytes = client -> {
        try {
            for (byte b = 0; b < 50; b += 5){
                client.stream(new byte[]{b, (byte) (b + 1), (byte) (b + 2), (byte) (b + 3), (byte) (b + 4)});
            }
        } catch (RemoteException e){
            throw new RuntimeException(e);
        }
        return null;
    };

    // Read bytes from a file and stream them to the client
    private final Function<IClientBox, Void> movieBytes = client -> {
                                                        // Change the path to a video file vlc can read
        try(FileInputStream in = new FileInputStream("/tmp/video.webm")){
            byte[] buffer = new byte[4096];

            while (in.read(buffer) != 0){
                client.stream(buffer);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    };

    // Stream bytes to the client using either one of the two methods above
    // An executor is used in order to handle each client on a separate thread
    public void stream(IClientBox client){
        executor.execute(() -> movieBytes.apply(client));
    }
}
