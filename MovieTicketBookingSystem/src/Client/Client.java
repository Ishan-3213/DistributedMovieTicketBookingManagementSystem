package Client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.*;
import Interface.InterfaceOperations;

public class Client {
    static String user_id;
    static Integer RMIPortNumber;
    public static void main(String[] args) {
        try (Scanner read = new Scanner(System.in);){
            System.out.println("\nPlease enter your UserID: ");
            user_id = (read.nextLine()).toUpperCase();
            while(user_id.isBlank() | user_id.length()!=8 ){
                System.out.println("\nPlease enter valid UserId !!");
                user_id = (read.nextLine()).toUpperCase();
            }
            System.out.println(user_id);
            if (user_id.substring(0,3).equals("ATW")){
                RMIPortNumber = 8001;

            }else if(user_id.substring(0,3).equals("VER")) {
                RMIPortNumber = 8002;
            } else if (user_id.substring(0,3).equals("OUT")){
                RMIPortNumber = 8003;
            }
            else {
                System.out.println("Didn't find the server");
            }
            Registry registry = LocateRegistry.getRegistry(RMIPortNumber);
            InterfaceOperations intOpr = (InterfaceOperations)registry.lookup("RegistryTest");
            intOpr.userData(user_id);
            AvailableOptions(intOpr, user_id);
        }catch (Exception e) {
           System.err.println("Server exception: " + e.toString());
           e.printStackTrace();
        }
    }
    public static String AvailableOptions(InterfaceOperations intOpr, String user_id) throws RemoteException{
        Integer user_choice;
        boolean is_admin;
        String movieName;
        String movieID;
        Integer capacity;
        boolean login = true;
        boolean choice = false;
        // List<String> movieList = Arrays.asList("AVATAR", "AVENGER", "TITANIC");
        try (Scanner read = new Scanner(System.in);){

        while(login){

            is_admin = user_id.substring(0,4).endsWith("A") ? (!user_id.substring(0,4).endsWith("C") ? true : false) : false;
            choice = true;
            // Customer Options CLI
            if(!is_admin){
                System.out.println();
                System.out.println("\t Hey there Customer - " + user_id);
                while (choice) {
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
                        String movie_shows =intOpr.listMovieShowsAvailability(movieName);
                        if(movie_shows.isEmpty()){
                            System.out.println("Sorry there is no show available for " + movieName);
                        }
                        else{
                            System.out.println();
                            System.out.println("Here is the movie shows available for the "+movieName);
                            System.out.println(movie_shows.replace("<>", "-"));
                        }
                        System.out.println();
                        System.out.println("Enter the movieId you want to book.");
                        movieID = (read.nextLine()).toUpperCase();

                        System.out.println();
                        System.out.println("Please enter number of tickets for the movie " + movieName + "-" +movieID);
                        capacity = Integer.parseInt(read.nextLine());
                        String data = intOpr.bookMovieTickets(user_id, movieID, movieName, capacity);
                        System.out.println(data);
                        break;
                    case 2:
                        System.out.println();
                        // System.out.println("Enter UserId: ");
                        // String userId_booking = (read.nextLine()).toUpperCase();
                        // while(userId_booking.isBlank() | userId_booking.length()!=8){
                            // System.out.println("Please enter valid UserId !!");
                            // userId_booking = (read.nextLine()).toUpperCase();
                        // }
                        String booking_schedule = intOpr.getBookingSchedule(user_id);
                        if (booking_schedule.isEmpty()){
                            System.out.println("There is no booked movie tickets found with the ID - " + user_id);
                        }else{
                            System.out.println("Here is your booking schedule..!!");
                            System.out.println(booking_schedule);
                        }
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Please Enter UserId again: ");
                        String userId_cancel = (read.nextLine()).toUpperCase();
                        while(userId_cancel.isBlank() | userId_cancel.length() > 8){
                            System.out.println("Please enter valid UserId !!");
                            userId_cancel = (read.nextLine()).toUpperCase();
                        }
                        while(!user_id.equals(userId_cancel)){
                            System.out.println("Please enter valid UserId !!");
                            userId_cancel = (read.nextLine()).toUpperCase();
                        }
                        System.out.println("Enter movie name you want to cancel from the option.");
                        System.out.println("AVATAR \t AVENGER \t TITANIC");
                        movieName = (read.nextLine()).toUpperCase();
                        String booked_movie =intOpr.getBookingSchedule(user_id);
                        if(booked_movie.isEmpty()){
                            System.out.println("There is no booked movie tickets found with the ID -" + user_id);
                            break;
                        }
                        else{
                            System.out.println();
                            // booked_movie.get(userId_cancel);
                            System.out.println("Here is the booked shows with the userID - "+user_id);
                            System.out.println(booked_movie);
                            // boolean found_movie = true;
                            // for (String innerKey : booked_movie.keySet()) {
                            //     String[] split_value = innerKey.split("-");
                            //     if (split_value[0].equals(movieName)){
                            //         System.out.println(split_value[1] + "-" + booked_movie.get(innerKey));
                            //         found_movie = true;
                            //     }else{
                            //         System.out.println();
                            //         System.out.println("No tickets found for " + movieName);
                            //         found_movie =false;
                            //         break;
                            //     }
                            // }
                            // if(!found_movie){
                            //     break;
                            // }
                        }
                        System.out.println();
                        System.out.println("Enter the movieId you want to cancel.");
                        movieID = (read.nextLine()).toUpperCase();
                        System.out.println();
                        System.out.println("Please enter number of tickets for the movie " + movieName + "-" +movieID);
                        capacity = Integer.parseInt(read.nextLine());
                        String reply = intOpr.cancelMovieTickets(user_id, movieID, movieName, capacity);
                        System.out.println(reply);
                        break;
                    case 4:
                        choice = false;
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
                while (choice) {
                    System.out.println("*******\tSelect the choice given below\t*******");
                    System.out.println();
                    // String region = user_id.substring(0, 3);
                    System.out.println("1. Add movie slots.");
                    System.out.println("2. Remove movie slots.");
                    System.out.println("3. List out movie shows available.");
                    System.out.println("4. Book movie tickets.");
                    System.out.println("5. List your booked movie tickets.");
                    System.out.println("6. Cancel movie tickets. ");    
                    System.out.println("7. Exit");
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
                            if(movieID.substring(0,3).equals(user_id.substring(0,3))){

                                while(movieID.isBlank() | movieID.length()<11 | movieName.isBlank()){
                                    System.out.println("\nPlease enter valid movie details..!!");
                                    System.out.println();
                                    System.out.println("Enter movie name you want to add from the option.");
                                    System.out.println("AVATAR\nAVENGER\nTITANIC");
                                    movieName = (read.nextLine()).toUpperCase();
                                    System.out.println();
                                    System.out.println("Enter movieId for the movie - " + movieName);
                                    movieID = (read.nextLine()).toUpperCase();
                                  }
                                System.out.println();
                                System.out.println("Enter capacity for the Movie: " + movieName + " with the MovieId: "+ movieID);
                                capacity = Integer.parseInt(read.nextLine());
                                String response = intOpr.addMovieSlots(movieID, movieName, capacity);
                                System.out.println(response);
                                break;
                            }else{
                                System.out.println("You have no permission to add movies in region! " + movieID.substring(0,3));
                                break;
                            }
                        case 2:
                            System.out.println("Enter movie name you want to remove from the option.");
                            System.out.println("AVATAR \t AVENGER \t TITANIC");
                            movieName = (read.nextLine()).toUpperCase();
                            System.out.println();
                            System.out.println("Enter movieId for the movie - " + movieName);
                            movieID = (read.nextLine()).toUpperCase();
                            while(movieID.isBlank() | movieID.length() < 11 | movieName.isBlank()){
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
                            String movie_shows = intOpr.listMovieShowsAvailability(movieName);
                            // String [] splitted = movie_shows.split("<>");
                            // System.out.println("splitted------>>>>>>>>>>" + splitted);
                            if(movie_shows.isEmpty()){
                                System.out.println();
                                System.out.println("Sorry there is no show available for-> " + movieName);
                                System.out.println();
                            }
                            else{
                                System.out.println();
                                System.out.println("Here is the movie shows available for the "+movieName);
                                System.out.println(movie_shows.replace("<>", "-"));
                            }
                            break;
                        case 4:
                            choice = false;
                            login = false;
                            break;
                            // System.out.println();
                            // System.out.println("Enter UserId: ");
                            // String user_booking_id = (read.nextLine()).toUpperCase();
                            // while(user_booking_id.isBlank() | user_booking_id.length()!=8){
                            //     System.out.println("Please enter valid UserId !!");
                            //     user_booking_id = (read.nextLine()).toUpperCase();
                            // }
                            // System.out.println("Enter movie name you want to add from the option.");
                            // System.out.println("AVATAR \t AVENGER \t TITANIC");
                            // movieName = (read.nextLine()).toUpperCase();
                            // HashMap<String, Integer> list_movie_shows =intOpr.listMovieShowsAvailability(movieName);
                            // if(list_movie_shows.isEmpty()){
                            //     System.out.println("Sorry there is no show available for " + movieName);
                            // }
                            // else{
                            //     System.out.println("Here is the movie shows available for "+movieName);
                            //     System.out.println(list_movie_shows);
                            // }
                            // System.out.println();
                            // System.out.println("Enter the movieId you want to book.");
                            // movieID = (read.nextLine()).toUpperCase();
    
                            // System.out.println();
                            // System.out.println("Please enter number of tickets for the movie " + movieName + "-" +movieID);
                            // capacity = Integer.parseInt(read.nextLine());
                            // String reply = intOpr.bookMovieTickets(user_booking_id, movieID, movieName, capacity);
                            // System.out.println(reply);
                            // break;
                        case 5:
                            System.out.println();
                            System.out.println("Enter UserId: ");
                            String userId_booking = (read.nextLine()).toUpperCase();
                            while(userId_booking.isBlank() | userId_booking.length()>8){
                                System.out.println("Please enter valid UserId !!");
                                userId_booking = (read.nextLine()).toUpperCase();
                            }
                            String booking_schedule = intOpr.getBookingSchedule(userId_booking);
                            if (booking_schedule.isEmpty()){
                                System.out.println("There is no booked movie tickets found with the ID - " + userId_booking);
                            }else{
                            System.out.println("Here is your booking schedule..!!");
                            System.out.println(booking_schedule);
                            }
                            break;
                        case 6:
                            System.out.println("Logging out from the - " + user_id);
                            choice = false;
                            login = false;
                            break;
                            // System.out.println();
                            // System.out.println("Enter UserId: ");
                            // String userId_cancel = (read.nextLine()).toUpperCase();
                            // while(userId_cancel.isBlank() | userId_cancel.length() > 8){
                            //     System.out.println("Please enter valid UserId !!");
                            //     userId_cancel = (read.nextLine()).toUpperCase();
                            // }
                            // System.out.println("-------" + user_id + "<----->" + userId_cancel);
                            // while(user_id == userId_cancel){
                            //     System.out.println("User id didn't match .. !!");
                            //     userId_cancel = (read.nextLine()).toUpperCase();
                            // }
                            // System.out.println("Enter movie name you want to cancel from the option.");
                            // System.out.println("AVATAR \t AVENGER \t TITANIC");
                            // movieName = (read.nextLine()).toUpperCase();
                            // HashMap<String, Integer> booked_movie =intOpr.getBookingSchedule(userId_cancel);
                            // if(booked_movie.isEmpty()){
                            //     System.out.println("There is no booked movie tickets found with the ID -" + user_id);
                            //     break;
                            // }
                            // else{
                            //     System.out.println();
                            //     booked_movie.get(userId_cancel);
                            //     System.out.println("Here is the booked shows with the userID - "+user_id);
                            //     for (String innerKey : booked_movie.keySet()) {
                            //         String[] split_value = innerKey.split("-");
                            //         String splited_movie_name = split_value[0];
                            //         if (splited_movie_name.trim().equals(movieName)){
                            //             System.out.println(split_value[1] + " " + booked_movie.get(innerKey));
                            //         }else{
                            //             System.out.println();
                            //             System.out.println(split_value[1] + "/-/" + booked_movie.get(innerKey));
                            //             System.out.println("No tickets found for " + movieName);
                            //         }
                            //     }
                            // }
                            // System.out.println();
                            // System.out.println("Enter the movieId you want to cancel.");
                            // movieID = (read.nextLine()).toUpperCase();
                            // System.out.println();
                            // System.out.println("Number of tickets for the movie " + movieName + "-" +movieID);
                            // capacity = Integer.parseInt(read.nextLine());
                            // String data_response = intOpr.cancelMovieTickets(user_id, movieID, movieName, capacity);
                            // System.out.println(data_response);
                            // break;
                        case 7:
                            choice = false;
                            login = false;
                            break;
                        default:
                            System.out.println();
                            System.out.println("!!..Invalid Choice..!!");
                            break;
                        }
                    }
                }
            }
        return "Thank you for your time..!!";
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
