package Implementation;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import Interface.InterfaceOperations;
//import utils.*;

public class ImplementationOperations extends UnicastRemoteObject implements InterfaceOperations {
    HashMap<String, HashMap<String, Integer>> data_hashmap;
    HashMap<String, Integer> booking_hashmap;

    // HashMap<String, HashMap<String, Integer>> vernam;

    public ImplementationOperations() throws RemoteException {
        super();
        data_hashmap = new HashMap<>();
        data_hashmap.put("AVATAR", new HashMap<String, Integer>());
        data_hashmap.put("AVENGER", new HashMap<String, Integer>());
        data_hashmap.put("TITANIC", new HashMap<String, Integer>());
        booking_hashmap = new HashMap<String, Integer>();
        // avenger.put("VERA2201223", 50);

        // avatar.put("ATWA201222", 50);

        // //Inner Titanic Hashmap initialization....Out...?
        // titanic = new HashMap<>();
        // titanic.put("OUTM210123", 50);
        // atwater.put("Titanic", titanic);
    }

    @Override
    public String addMovieSlots(String movieId, String movieName, Integer bookingCapacity) {
        // slot should be updated;
        try {
            if (data_hashmap.containsKey(movieName)){
                if (data_hashmap.get(movieName).containsKey(movieId)) {
                    data_hashmap.get(movieName).put(movieId, bookingCapacity + data_hashmap.get(movieName).get(movieId));
                    System.out.println();
                    System.out.println("Movie's slot with the ID " + movieId + " has been updated!");
                    System.out.println(data_hashmap);
                    return "Movie slot updated.";
                }else{
                    data_hashmap.get(movieName).put(movieId, bookingCapacity);
                    System.out.println("data-->>" + data_hashmap);
                    return "Movie slot added.";
                }
            } else {
                System.out.println("Movie is not there..!!");
                booking_hashmap.put(movieId, bookingCapacity);
                data_hashmap.put(movieName, booking_hashmap);
                System.out.println("Movie slot has been added..!!" + data_hashmap);
                return "Movie slot has been added..!!";
            }

        } catch (Exception e) {
        throw new RuntimeException(e);
    }
//            else{
            // atwater.get(movieName).put(movieId, bookingCapacity);
//                System.out.println();
            //System.out.println("Movie's slot with the ID " + movieId + " has been added!");
//        return "No slots a";
    }
   @Override
   public String removeMovieSlots(String movieId, String movieName) {
       // TODO Auto-generated method stub
       if (data_hashmap.get(movieName).containsKey(movieId)){
           System.out.println(data_hashmap.get(movieName) +" befor removal..!! ");
           System.out.println();
           data_hashmap.get(movieName).remove(movieId);
           System.out.println(data_hashmap.get(movieName) +" after removal..!! ");
           return "Movie slot for " + movieName + " has been removed";
       }else {
        System.out.println("there is no movei slot for this movie..!!!");
        return "No movie slot found for the movie " + movieName + " at " + movieId.substring(0, 3) + " region";
       }
   }
//
//    @Override
//    public String listMovieShowsAvailability(String movieName) {
//        // TODO Auto-generated method stub
//        if (atwater.containsKey(movieName)){
//            System.out.println();
//            System.out.println("Here is the shows available for the movie " + movieName);
//            System.out.println(atwater.get(movieName));
//        }
//        return "";
//    }
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