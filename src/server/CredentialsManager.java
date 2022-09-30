package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class CredentialsManager {
    private final Map<String, Long> credentials = new HashMap<>();

    public CredentialsManager(){
        load();
    }

    public boolean checkUsername(String username){
        // Check if email is already used
        return credentials.containsKey(username);
    }

    public void addCredential(String username, String password){
        // Add credentials to the map
        // The password is saved as a hash for safety

        Checksum crc32 = new CRC32();
        crc32.update(password.getBytes(), 0, password.length());
        credentials.put(username, crc32.getValue());

        save();
    }

    public boolean checkCredentials(String username, String password){
        // Check the password is valid
        // The password needs to be hashed to be compared to the saved one

        if (!credentials.containsKey(username)){
            return false;
        }

        Checksum crc32 = new CRC32();
        crc32.update(password.getBytes(), 0, password.length());
        return credentials.get(username) == crc32.getValue();
    }

    public int getNumberOfUsers(){
        return credentials.size();
    }

    public void save(){
        // Save credentials to disk

        try(PrintWriter out = new PrintWriter(new FileOutputStream("creds.txt"))){
            for (Map.Entry<String, Long> entry : credentials.entrySet()){
                out.println(entry.getKey() + ':' + entry.getValue());
            }
        } catch (FileNotFoundException e){
            throw new RuntimeException("Could not save creds.");
        }
    }

    public void load(){
        // Load credentials from disk

        try(Scanner in = new Scanner(new FileInputStream("creds.txt"))){
            while (in.hasNext()){
                String[] readCredentials = in.nextLine().split(":");

                try {
                    credentials.put(readCredentials[0], Long.parseLong(readCredentials[1]));
                } catch (NumberFormatException e){
                    System.err.println("Could not read password for user " + readCredentials[0]);
                }
            }

            System.out.println("Loaded " + credentials.size() + " users.");
        } catch (FileNotFoundException e){
            System.out.println("Could not read creds file.");
        }
    }
}
