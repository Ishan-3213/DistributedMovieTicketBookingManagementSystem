package Implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import Interface.InterfaceOperations;

public class ImplementationOperations extends UnicastRemoteObject implements InterfaceOperations {
    HashMap<String, HashMap<String, Integer>> datastorage;
    HashMap<String, HashMap<String, Integer>> user_data;
    HashMap<String, Integer> booking_hashmap;


    public ImplementationOperations() throws RemoteException {
        super();
        datastorage = new HashMap<>();
        datastorage.put("AVATAR", new HashMap<String, Integer>());
        datastorage.put("AVENGER", new HashMap<String, Integer>());
        datastorage.put("TITANIC", new HashMap<String, Integer>());
        booking_hashmap = new HashMap<String, Integer>();
        booking_hashmap.put("ATWM23022023", 50);
        booking_hashmap.put("ATWA23022023", 50);
        booking_hashmap.put("VERM23022023", 50);
        booking_hashmap.put("OUTM23022023", 50);


    }

    @Override
    public String addMovieSlots(String movieId, String movieName, Integer bookingCapacity) {
        try {
            if (datastorage.containsKey(movieName)){
                if (datastorage.get(movieName).containsKey(movieId)) {
                    datastorage.get(movieName).put(movieId, bookingCapacity + datastorage.get(movieName).get(movieId));
                    System.out.println();
                    System.out.println("Movie's slot with the ID " + movieId + " has been updated!");
                    System.out.println(datastorage);
                    return "Movie slot updated.";
                }else{
                    datastorage.get(movieName).put(movieId, bookingCapacity);
                    System.out.println("data-->>" + datastorage);
                    return "Movie slot added.";
                }
            } else {
                System.out.println("Movie is not there..!!");
                booking_hashmap.put(movieId, bookingCapacity);
                datastorage.put(movieName, booking_hashmap);
                System.out.println("Movie slot has been added..!!" + datastorage);
                return "Movie slot has been added..!!";
            }

        } catch (Exception e) {
        throw new RuntimeException(e);
        }
    }
   @Override
   public String removeMovieSlots(String movieId, String movieName) {
       if (datastorage.get(movieName).containsKey(movieId)){
           datastorage.get(movieName).remove(movieId);
           System.out.println();
           System.out.println(datastorage.get(movieName) +" after removal..!! ");
           return "Movie slot for " + movieName + " has been removed";
       }else {
        System.out.println("there is no movei slot for this movie..!!!");
        return "No movie slot found for the movie " + movieName + " at " + movieId.substring(0, 3) + " region";
       }
   }

   @Override
   public String userData(String customoerID){
    // To initiate the customer hash map for the logged in user.
        user_data.put(customoerID, new HashMap<String, Integer>());
        System.out.println(user_data);
        return "Done";
   }
//
   @Override
   public HashMap<String, Integer> listMovieShowsAvailability(String movieName) {
       if (datastorage.containsKey(movieName)){
           System.out.println();
           System.out.println("Here is the shows available for the movie " + movieName);
           System.out.println(datastorage.get(movieName));
       }
       return datastorage.get(movieName);
   }
//
//    @Override
//    public String bookMovieTickets(String customerID, String movieId, String movieName, String numberOfTickets) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public String getBookingSchedule(String customerID){
//        return null;
//    }
//
//    @Override
//    public String cancelMoveTickets(String customerID) {
//        // TODO Auto-generated method stub
//        return null;
//    }
    }