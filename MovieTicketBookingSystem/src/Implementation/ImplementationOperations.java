package Implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import Interface.InterfaceOperations;

public class ImplementationOperations extends UnicastRemoteObject implements InterfaceOperations {
    
    public ImplementationOperations() throws RemoteException{
        super();
    }

    HashMap<String, HashMap<String, String>> data = new HashMap<>();

    @Override
    public String addMovieSlots(String movieId, String movieName, Integer bookingCapacity) {
        // slot should be updated;
        System.out.println("Movie's slot with the ID " + movieId + " has been updated");
        return null;
    }

    @Override
    public String removeMovieSlots(String movieId, String movieName) {
        // TODO Auto-generated method stub
        return null;
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
