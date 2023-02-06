package Implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import Interface.InterfaceOperations;
//import utils.*;

public class ImplementationOperations extends UnicastRemoteObject implements InterfaceOperations {
    HashMap<String, HashMap<String, Integer>> atwater;
    HashMap<String, HashMap<String, Integer>> vernam;
    HashMap<String, HashMap<String, Integer>> out___;
    HashMap<String, Integer> avenger;
    HashMap<String, Integer> avatar;
    HashMap<String, Integer> titanic;

    public ImplementationOperations() throws RemoteException{
        super();
        atwater = new HashMap<>();
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
        titanic.put("OUTE210123", 150);
        atwater.put("Avatar" , avatar);
        atwater.put("Avenger", avenger);
        atwater.put("Titanic", titanic);
    }
    // Outer HashMap initialization 


    //Inner Avatar Hashmap initialization....Atwater ?


    //Inner Avenger Hashmap initialization....Vernam ?



    //@Override
    public String addMovieSlots(String movieId, String movieName, Integer bookingCapacity) {
        // slot should be updated;

        if (atwater.containsKey(movieName)){
            if(atwater.get(movieName).containsKey(movieId)) {
            atwater.get(movieName).put(movieId, bookingCapacity + atwater.get(movieName).get(movieId));
            System.out.println();
            System.out.println("Movie's slot with the ID " + movieId + " has been updated!");
            return "Movie Slot Updated";
            }else{
            // atwater.get(movieName).put(movieId, bookingCapacity);
                System.out.println();
                //System.out.println("Movie's slot with the ID " + movieId + " has been added!");
                return "Movie Slot Added";
            }
        }
        return "null";
        //return "no";
        // return "Testing";
    }

    @Override
    public String removeMovieSlots(String movieId, String movieName) {
        // TODO Auto-generated method stub
        if (atwater.get(movieName).containsKey(movieId)){
            atwater.remove(movieId);
        }else {
            return "No Movie Slot available for this movie";
        }
        return "Slot has been removed for the Movie: " + movieName;
    }

    @Override
    public String listMovieShowsAvailability(String movieName) {
        // TODO Auto-generated method stub
        if (atwater.containsKey(movieName)){
            System.out.println();
            System.out.println("Here is the shows available for the movie " + movieName);
            System.out.println(atwater.get(movieName));
        }
        return "";
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