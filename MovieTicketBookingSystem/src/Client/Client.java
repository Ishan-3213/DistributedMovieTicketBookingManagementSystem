package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
           System.err.println("Server exception: " + e.toString());
           e.printStackTrace();
        }
     }
    // switch case to create the values
    public static String AvailableOptions(InterfaceOperations intOpr) throws RemoteException{
        InputStreamReader inpstrm = new InputStreamReader(System.in);
        BufferedReader read = new BufferedReader(inpstrm);
        int user_choice;
        boolean is_admin;
        String movieName;
        String movieID;
        Integer capacity;
        String user_id;
        try {
            System.out.println();
            System.out.println("Please enter your ID: ");
            user_id = (read.readLine()).toUpperCase();
            while(user_id.isBlank()){
                    is_admin = false;
                    System.out.println("Please enter valid UserId !!");
                    user_id = (read.readLine()).toUpperCase();
            }
            is_admin = user_id.substring(0,4).endsWith("A") ? (!user_id.substring(0,4).endsWith("C") ? true : false) : false;
            if(!is_admin){
                System.out.println();
                System.out.println("\t \t Hey there Customer- " + user_id);
                while (true) {
                System.out.println("Select the choice given below: ");
                System.out.println();
                System.out.println("1. List your booked movie tickets.");
                System.out.println("2. Book movie tickets.");
                System.out.println("3. Cancel movie tickets. ");
                user_choice = Integer.parseInt(read.readLine());
                switch (user_choice) {
                    case 1:
                        System.out.println("Enter movie name you want to add from the option.");
                        System.out.println("AVATAR \t AVENGER \t TITANIC");
                        movieName = (read.readLine()).toUpperCase();
                        System.out.println();
                        System.out.println("Enter movieId for the movie - " + movieName);
                        movieID = (read.readLine()).toUpperCase();
                        System.out.println();
                        System.out.println("Enter capacity for the Movie: " + movieName + " with the MovieId: "+ movieID);
                        capacity = Integer.parseInt(read.readLine());
                        intOpr.addMovieSlots(movieID, movieName, capacity);
                        System.out.println(movieName + " Movie slot has been updated.");
                        return movieName + " Movie slot has been updated.";
                        // break;
                    // if(movieID == null | movieID == ""){
                    //   System.out.println("Please enter valid movieId..!!");
                    //   String movieID = read.nextLine();
                    // }else if(movieID.length()>=10){
                    //   System.out.println("Movie Id");
                    // }
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid Choice..!!");
                        break;
                    }
                }
            }else if(is_admin){
                System.out.println();
                System.out.println("---------------\tHey there Admin("+user_id+") ---------------");
                while (true) {
                    System.out.println("*******\tSelect the choice given below\t*******");
                    System.out.println();
                    String region = user_id.substring(0, 3);
                    System.out.println("1. Add movie slots in " + region);
                    System.out.println("2. Remove movie slots.");
                    System.out.println("3. List out movie shows available.");
                    System.out.println("4. Exit");
                    user_choice = Integer.parseInt(read.readLine());
                    switch (user_choice) {
                        case 1:
                            System.out.println("Enter movie name you want to add from the option.");
                            System.out.println("AVATAR \t AVENGER \t TITANIC");
                            movieName = (read.readLine()).toUpperCase();
                            System.out.println();
                            System.out.println("Enter movieId for the movie - " + movieName);
                            movieID = (read.readLine()).toUpperCase();
                            System.out.println();
                            System.out.println("Enter capacity for the Movie: " + movieName + " with the MovieId: "+ movieID);
                            capacity = Integer.parseInt(read.readLine());
                            intOpr.addMovieSlots(movieID, movieName, capacity);
                            System.out.println(movieName + " Movie slot has been updated.");
                            break;
                        // if(movieID == null | movieID == ""){
                        //   System.out.println("Please enter valid movieId..!!");
                        //   String movieID = read.nextLine();
                        // }else if(movieID.length()>=10){
                        //   System.out.println("Movie Id");
                        // }
                        case 2:
                            System.out.println("Enter movie name you want to remove from the option.");
                            System.out.println("AVATAR \t AVENGER \t TITANIC");
                            movieName = (read.readLine()).toUpperCase();
                            System.out.println();
                            System.out.println("Enter movieId for the movie - " + movieName);
                            movieID = (read.readLine()).toUpperCase();
                            String data = intOpr.removeMovieSlots(movieID, movieName);
                            System.out.println(data);
                            break;
                        case 3:
                            System.out.println("Here are the movie slots available in your area.");
                            System.out.println();
                            // System.out.println("AVATAR \t AVENGER \t TITANIC");
                            // movieName = (read.readLine()).toUpperCase();
                            // System.out.println("Enter movieId for the movie - " + movieName);
                            // movieID = (read.readLine()).toUpperCase();
                            // System.out.println();
                            // System.out.println("Enter capacity for the Movie: " + movieName + " with the MovieId: "+ movieID);
                            // capacity = Integer.parseInt(read.readLine());
                            // intOpr.addMovieSlots(movieID, movieName, capacity);
                            // System.out.println(movieName + " Movie slot has been updated.");
                            break;
                        default:
                            System.out.println("Invalid Choice..!!");
                            break;
                        }
                    }
                }
        return "Thank you for your time..!!";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
