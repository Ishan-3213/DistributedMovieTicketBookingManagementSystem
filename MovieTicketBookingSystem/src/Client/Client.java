package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import Interface.InterfaceOperations;
public class Client {
    public static void main(String[] args) {
      try {
        Registry registry = LocateRegistry.getRegistry(8001);
        InterfaceOperations intOpr = (InterfaceOperations)registry.lookup("RegistryTest");
//          System.out.println("Lookup has been done!");
          AvailableOptions(intOpr);
//           String message = intOpr.addMovieSlots("Donald Duck", "test" , 12);
          // System.out.println("");
        }catch (Exception e) {
           System.err.println("Server 1 exception: " + e.toString());
           e.printStackTrace();
        }
     }
    // switch case to create the values
    public static String AvailableOptions(InterfaceOperations intOpr) throws RemoteException{
        InputStreamReader inpstrm = new InputStreamReader(System.in);
        BufferedReader read = new BufferedReader(inpstrm);
        try {
            while (true) {
                System.out.println();
                System.out.println("Select the choice given below: ");
                System.out.println();
                System.out.println("1. Add movie slots...");
                System.out.println("2. Remove movie slots...");
                System.out.println("3. Exit");
                int user_choice = Integer.parseInt(read.readLine());
                switch (user_choice) {
                    case 1:
                        System.out.println("Enter movie name you want to add from the option.");
                        System.out.println("AVATAR \t AVENGER \t TITANIC");
                        String movieName = (read.readLine()).toUpperCase();
                        System.out.println();
                        System.out.println("Enter movieId for the movie - " + movieName);
                        String movie_id = (read.readLine()).toUpperCase();
                        System.out.println();
                        System.out.println("Enter capacity for the Movie: " + movieName + " with the MovieId: "+ movie_id);
                        int capacity = Integer.parseInt(read.readLine());
                        intOpr.addMovieSlots(movie_id, movieName, capacity);
                        System.out.println(movieName + " Movie slot has been updated.");
                        break;
                    // if(movie_id == null | movie_id == ""){
                    //   System.out.println("Please enter valid movieId..!!");
                    //   String movie_id = read.nextLine();
                    // }else if(movie_id.length()>=10){
                    //   System.out.println("Movie Id");
                    // }
                    case 2:
                        return "Avenger";
                    case 3:
                        return "Titanic";
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
