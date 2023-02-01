package Implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import Interface.InterfaceOperations;
import utils.*;

public class ImplementationOperations extends UnicastRemoteObject implements InterfaceOperations {
    
    public ImplementationOperations() throws RemoteException{
        super();
    }
    // Outer HashMap initialization 
    HashMap<String, HashMap<String, Integer>> data = new HashMap<>();

    //Inner Avatar Hashmap initialization
    HashMap<String, Integer> avatar = new HashMap<>();
    avatar.put("ATWA201222", 50);
    avatar.put("VERM210123", 150);
    avatar.put("OUTE2201223", 510);

    //Inner Avenger Hashmap initialization
    HashMap<String, Integer> avenger = new HashMap<>();
    avenger.put("ATWM201222", 50);
    avenger.put("OUTA210123", 150);
    avenger.put("VERE2201223", 510);

    //Inner Titanic Hashmap initialization
    HashMap<String, Integer> titanic = new HashMap<>();
    titanic.put("ATWE201222", 50);
    titanic.put("OUTM210123", 150);
    titanic.put("VERA2201223", 510);

    // Inner Maps added in the main outer Map
    data.put("Avatar", avatar);
    data.put("Avenger", avenger);
    data.put("Titanic", titanic);
    
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
    public String CustomerOrAdmin(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getBookingSchedule(String customerID) {
        // TODO Auto-generated method stub
        return null;
    }
}
