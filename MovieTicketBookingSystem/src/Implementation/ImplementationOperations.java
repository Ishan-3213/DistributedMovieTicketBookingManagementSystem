package Implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.*;
import Interface.InterfaceOperations;
//import utils.*;

public class ImplementationOperations extends UnicastRemoteObject implements InterfaceOperations {
    HashMap<String, HashMap<String, Integer>> data;
    HashMap<String, Integer> avenger;
    HashMap<String, Integer> avatar;
    HashMap<String, Integer> titanic;

    public ImplementationOperations() throws RemoteException{
        super();
        data = new HashMap<>();
        avenger = new HashMap<>();
        avenger.put("VERA2201223", 50);
        avenger.put("VERM2201223", 150);
        avenger.put("VERE2201223", 50);

        avatar = new HashMap<String,Integer>();
        avatar.put("ATWA201222", 50);
        avatar.put("ATWE201222", 150);
        avatar.put("ATWM201222", 50);

        //Inner Titanic Hashmap initialization....Out...?
        titanic = new HashMap<>();
        titanic.put("OUTM210123", 50);
        titanic.put("OUTA210123", 150);
        data.put("Avatar" , avatar);
        data.put("Avenger", avenger);
        data.put("Titanic", titanic);
        int value = getValueForKey(data, "Main");
        System.out.println("Value for mainData: " + value);
    }
    // Outer HashMap initialization 


    //Inner Avatar Hashmap initialization....Atwater ?


    //Inner Avenger Hashmap initialization....Vernam ?

    
    @Override
    public String addMovieSlots(String movieId, String movieName, Integer bookingCapacity) {
        // slot should be updated;
        if (data.get(movieName).containsKey(movieId)) {
           data.get(movieName).put(movieId, bookingCapacity + data.get(movieName).get(movieId));
           System.out.println();
           System.out.println("Movie's slot with the ID " + movieId + " has been updated!");
        }else{
            data.get(movieName).put(movieId, bookingCapacity);
            System.out.println();
            System.out.println("Movie's slot with the ID " + movieId + " has been added!");
        }
        return "Movie Slot Added/Updated";
    }

    @Override
    public String removeMovieSlots(String movieId, String movieName) {
        // TODO Auto-generated method stub
        if (data.get(movieName).containsKey(movieId)){
            data.remove(movieId);
        }else {
            return "No Movie Slot available for this movie";
        }
        return "Slot has been removed for the Movie " + movieName;
    }

    @Override
    public String listMovieShowsAvailability(String movieName) {
        // TODO Auto-generated method stub
        if (data.containsKey(movieName)){
            System.out.println();
            System.out.println("Here is the shows available for the movie " + movieName);
            System.out.println(data.get(movieName));
        }
        return null;
    }

    @Override
    public String bookMovieTickets(String customerID, String movieId, String movieName, String numberOfTickets) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getBookingSchedule(String customerID){
        return null;
    }

    @Override
    public String cancelMoveTickets(String customerID) {
        // TODO Auto-generated method stub
        return null;
    }
}
