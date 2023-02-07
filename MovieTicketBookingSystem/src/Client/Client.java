package Client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.*;

import Interface.InterfaceOperations;
public class Client {
    public static void main(String[] args) {
      try {
        Registry registry = LocateRegistry.getRegistry(8001);
        InterfaceOperations intOpr = (InterfaceOperations)registry.lookup("RegistryTest");
          AvailableOptions(intOpr);
        }catch (Exception e) {
           System.err.println("Server exception: " + e.toString());
           e.printStackTrace();
        }
     }
    public static String AvailableOptions(InterfaceOperations intOpr) throws RemoteException{
        // InputStreamReader inpstrm = new InputStreamReader(System.in);
        int user_choice;
        boolean is_admin;
        String movieName;
        String movieID;
        Integer capacity;
        String user_id;
        boolean login = true;
        // List<String> movieList = Arrays.asList("AVATAR", "AVENGER", "TITANIC");
        try (Scanner read = new Scanner(System.in);){
            System.out.println();
            System.out.println("Please enter your UserID: ");
            user_id = (read.nextLine()).toUpperCase();
            while(user_id.isBlank() | user_id.length()!=8){
                    System.out.println("Please enter valid UserId !!");
                    user_id = (read.nextLine()).toUpperCase();
            }
            System.out.println(user_id);
            intOpr.userData(user_id);
            is_admin = user_id.substring(0,4).endsWith("A") ? (!user_id.substring(0,4).endsWith("C") ? true : false) : false;
            // Customer Options CLI
            if(!is_admin){
                System.out.println();
                System.out.println("\t \t Hey there Customer- " + user_id);
                while (login) {
                System.out.println("Select the choice given below: ");
                System.out.println();
                System.out.println("1. Book movie tickets.");
                System.out.println("2. List your booked movie tickets.");
                System.out.println("3. Cancel movie tickets. ");
                System.out.println("4. Exit.");
                user_choice = Integer.parseInt(read.nextLine()) ;
                switch (user_choice) {
                    case 1:
                        System.out.println("Enter movie name you want to add from the option.");
                        System.out.println("AVATAR \t AVENGER \t TITANIC");
                        movieName = (read.nextLine()).toUpperCase();
                        HashMap<String, Integer> movie_shows =intOpr.listMovieShowsAvailability(movieName);
                        if(movie_shows.isEmpty()){
                            System.out.println("Sorry there is no show available for " + movieName);
                        }
                        else{
                            System.out.println("Here is the movie shows available for the "+movieName);
                            System.out.println(movie_shows);
                        }
                        System.out.println();
                        System.out.println("Enter the movieId you want that you wnat to book.");
                        movieID = (read.nextLine()).toUpperCase();

                        System.out.println();
                        System.out.println("Please enter number of tickets for the movie " + movieName + "-" +movieID);
                        capacity = Integer.parseInt(read.nextLine());
                        String data = intOpr.bookMovieTickets(user_id, movieID, movieName, capacity);
                        System.out.println(data);
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Enter UserId: ");
                        String userId_booking = (read.nextLine()).toUpperCase();
                        while(userId_booking.isBlank() | userId_booking.length()!=8){
                            System.out.println("Please enter valid UserId !!");
                            userId_booking = (read.nextLine()).toUpperCase();
                        }
                        HashMap<String, Integer> booking_schedule = intOpr.getBookingSchedule(userId_booking);
                        if (booking_schedule.isEmpty()){
                            System.out.println("There is no booked movie tickets found with the ID - " + userId_booking);
                        }else{
                        System.out.println("Here is your booking schedule..!!");
                        System.out.println(booking_schedule);
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        login = false;
                        break;
                    default:
                        System.out.println("Invalid Choice..!!");
                        System.out.println();
                        break;
                    }
                }
            }
            // Admin Options CLI
            else if(is_admin){
                System.out.println();
                System.out.println("---------------\tHey there Admin("+user_id+") ---------------");
                while (login) {
                    System.out.println("*******\tSelect the choice given below\t*******");
                    System.out.println();
                    String region = user_id.substring(0, 3);
                    System.out.println("1. Add movie slots in " + region);
                    System.out.println("2. Remove movie slots.");
                    System.out.println("3. List out movie shows available.");
                    System.out.println("4. Exit");
                    user_choice = Integer.parseInt(read.nextLine());
                    switch (user_choice) {
                        case 1:
                            System.out.println("Enter movie name you want to add from the option.");
                            System.out.println("AVATAR \t AVENGER \t TITANIC");
                            movieName = (read.nextLine()).toUpperCase();
                            // if(movieList.contains(movieName)){
                            //     System.out.println("It does contains...!!!!" + movieList);
                            // }
                            System.out.println();
                            System.out.println("Enter movieId for the movie - " + movieName);
                            movieID = (read.nextLine()).toUpperCase();
                            while(movieID.isBlank() | movieID.length()>10 | movieName.isBlank()){
                                System.out.println("Please enter valid movie details..!!");
                                System.out.println();
                                System.out.println("Enter movie name you want to add from the option.");
                                System.out.println("AVATAR \t AVENGER \t TITANIC");
                                movieName = (read.nextLine()).toUpperCase();
                                System.out.println();
                                System.out.println("Enter movieId for the movie - " + movieName);
                                movieID = (read.nextLine()).toUpperCase();
                              }
                            System.out.println();
                            System.out.println("Enter capacity for the Movie: " + movieName + " with the MovieId: "+ movieID);
                            capacity = Integer.parseInt(read.nextLine());
                            intOpr.addMovieSlots(movieID, movieName, capacity);
                            System.out.println(movieName + " Movie slot has been updated.");
                            break;
                        case 2:
                            System.out.println("Enter movie name you want to remove from the option.");
                            System.out.println("AVATAR \t AVENGER \t TITANIC");
                            movieName = (read.nextLine()).toUpperCase();
                            System.out.println();
                            System.out.println("Enter movieId for the movie - " + movieName);
                            movieID = (read.nextLine()).toUpperCase();
                            while(movieID.isBlank() | movieID.length()>10 | movieName.isBlank()){
                                System.out.println("Please enter valid movie details..!!");
                                System.out.println();
                                System.out.println("Enter movie name you want to add from the option.");
                                System.out.println("AVATAR \t AVENGER \t TITANIC");
                                movieName = (read.nextLine()).toUpperCase();
                                System.out.println();
                                System.out.println("Enter movieId for the movie - " + movieName);
                                movieID = (read.nextLine()).toUpperCase();
                              }
                            String data = intOpr.removeMovieSlots(movieID, movieName);
                            System.out.println(data);
                            break;
                        case 3:
                        System.out.println();
                        System.out.println("Enter movie name you want to add from the option.");
                        System.out.println("\nAVATAR\nAVENGER\nTITANIC");
                        movieName = (read.nextLine()).toUpperCase();
                        while(movieName.isBlank()){
                            System.out.println();
                            System.out.println("Enter movie name you want to add from the option.");
                            System.out.println("AVATAR \t AVENGER \t TITANIC");
                            movieName = (read.nextLine()).toUpperCase();
                          }
                        HashMap<String, Integer> movie_shows =intOpr.listMovieShowsAvailability(movieName);
                        if(movie_shows.isEmpty()){
                            System.out.println();
                            System.out.println("Sorry there is no show available for-> " + movieName);
                            System.out.println();
                        }
                        else{
                            System.out.println();
                            System.out.println("Here is the movie shows available for the "+movieName);
                            System.out.println(movie_shows);
                        }
                            break;
                        case 4:
                            login = false;
                            break;
                        default:
                            System.out.println();
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
