package client;

import server.IConnection;
import server.IVODService;
import server.MovieDesc;
import server.MovieDescPlus;
import server.exceptions.InvalidCredentialsException;
import server.exceptions.SignInFailed;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String email;
    private static String password;

    private static void prompt(){
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the email: ");
        email = in.nextLine();

        System.out.print("Enter the password: ");
        password = in.nextLine();
    }

    public static void main(String[] args){
        try {
            Registry reg = LocateRegistry.getRegistry(10001);
            IConnection c = (IConnection) reg.lookup("connection");

            Scanner in = new Scanner(System.in);

            boolean signup = true;

            System.out.print("Do you want to signup (Y/n) ? ");

            if (in.nextLine().equalsIgnoreCase("n")){
                signup = false;
            }

            while (signup){
                System.out.println("You are signing up.");
                prompt();

                try {
                    c.signUp(email, password);
                    signup = false;
                } catch (SignInFailed e){
                    System.err.println("Email is already used.");
                }
            }

            boolean connected;

            IVODService v;

            do {
                System.out.println("Please log in.");
                prompt();

                try {
                    v = c.login(email, password);

                    connected = true;

                    System.out.println();

                    System.out.println("Movies catalog: ");

                    int i = 0;
                    List<MovieDesc> catalog = v.viewCatalog();

                    for (MovieDesc m : catalog){
                        System.out.print("- [" + i + "] " + m.movieName + " (" + m.isbn + "): " + m.synopsis);

                        if (m instanceof MovieDescPlus mdp){
                            System.out.print(" teaser: " + Arrays.toString(mdp.teaser));
                        }

                        System.out.println();

                        ++i;
                    }

                    System.out.print("Which movie do you want to see ? ");

                    int index = in.nextInt();

                    System.out.println("You have to pay " + v.playmovie(catalog.get(index).isbn, new ClientBox()) + "€");

                } catch (InvalidCredentialsException e){
                    connected = false;
                    System.err.println("Invalid credentials. Try again.");
                    System.err.flush();
                }

            } while (!connected);

        } catch (RemoteException e){
            System.err.println("Could not connect to server");
            e.printStackTrace();
        } catch (NotBoundException e){
            System.err.println("Could not find class.");
            e.printStackTrace();
        }
    }
}