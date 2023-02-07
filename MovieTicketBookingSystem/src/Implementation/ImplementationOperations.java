package Implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import Interface.InterfaceOperations;

public class ImplementationOperations extends UnicastRemoteObject implements InterfaceOperations {
    HashMap<String, HashMap<String, Integer>> datastorage;
    HashMap<String, HashMap<String, Integer>> user_data;
    HashMap<String, Integer> booking_hashmap;
    HashMap<String, Integer> customer_booking_hashmap;


    public ImplementationOperations() throws RemoteException {
        super();
        datastorage = new HashMap<>();
        user_data = new HashMap<>();
        datastorage.put("AVATAR", new HashMap<String, Integer>());
        datastorage.put("AVENGER", new HashMap<String, Integer>());
        datastorage.put("TITANIC", new HashMap<String, Integer>());
        booking_hashmap = new HashMap<String, Integer>();
        customer_booking_hashmap = new HashMap<String, Integer>();

        datastorage.put("AVATAR", booking_hashmap);

        booking_hashmap.put("VERM23022023", 50);
        booking_hashmap.put("ATWA23022023", 50);
        datastorage.put("AVENGER", booking_hashmap);

        booking_hashmap.put("OUTM23022023", 50);
        datastorage.put("TITANIC", booking_hashmap);

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
                System.out.println();
                System.out.println("Movie is not there..!!");
                booking_hashmap.put(movieId, bookingCapacity);
                datastorage.put(movieName, booking_hashmap);
                System.out.println("Movie slot has been added..!!" + datastorage);
                return "New movie slot has been added..!!";
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
    System.out.println("test...!!!");
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
           return datastorage.get(movieName);
       }
       else{
        return new HashMap<>();
       }
   }
//
   @Override
   public String bookMovieTickets(String customerID, String movieId, String movieName, Integer numberOfTickets) {
       // TODO Auto-generated method stub
       if (user_data.containsKey(customerID)){
        if (user_data.get(customerID).containsKey(movieId)){
            user_data.get(customerID).put(movieId, user_data.get(customerID).get(movieId) + numberOfTickets);
            return numberOfTickets + " Movie tickets are updated for the movieID " + movieId;
        }
        else if (user_data.containsKey(customerID)){
            user_data.get(customerID).put(movieId, numberOfTickets);
            return numberOfTickets + " Movie tickets are booked for the movieID " + movieId;
        }
        else{
            customer_booking_hashmap.put(movieId, numberOfTickets);
            user_data.put(customerID, customer_booking_hashmap);
            return numberOfTickets + " Movie tickets are booked for the movieID " + movieId;
        }
       }
       customer_booking_hashmap.put(movieId, numberOfTickets);
       user_data.put(customerID, customer_booking_hashmap);
       System.out.println(user_data.get(customerID) + " ---- " + customer_booking_hashmap);
       return null;
   }
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