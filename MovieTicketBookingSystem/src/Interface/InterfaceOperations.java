package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Operation Interface has method declaration for both admin and
 * user operations on Booking Movie Ticket Facility
 */
public interface InterfaceOperations extends Remote{


    String userData(String customerID) throws RemoteException;


//    String cancelMoveTickets(String customerID);
    /**
     * Permission Set - Admin Only.
     * This method add movie for particular movie if exist in hash map
     * and if the movie does not exist it will create a new movie entry.
     * @param movieId
     * @param movieName
     * @param bookingCapacity
     * @return String If operation successful or not
     */
    String addMovieSlots(String movieId, String movieName, Integer bookingCapacity) throws RemoteException;

    /**
     * Permission Set - Admin Only.
     * This method removes the movie if existed.
     * If movie show exist and customer has booked a ticket then
     * movie gets deleted and will book the same movie in next available slot for that customer.
     * @param movieId
     * @param movieName
     * @return String If operation successful or not
     */
   String removeMovieSlots(String movieId, String movieName) throws RemoteException;

    /**
     * Permission Set - Admin Only.
     * This method shows the availability for a particular movie in all theaters.
     * @param movieName
     * @return String If operation successful or not
     */
   HashMap<String, Integer> listMovieShowsAvailability(String movieName) throws RemoteException;

    /**
     * Permission Set - Admin and User.
     * This method is used tp book movie tickets.
     * @param customerID
     * @param movieId
     * @param movieName
     * @param numberOfTickets
     * @return String If operation successful or not
     */
   String bookMovieTickets(String customerID, String movieId, String movieName, Integer numberOfTickets) throws RemoteException;


   /**
     * Permission Set - Admin and User.
     * This method is used tp book movie tickets.
     * @param customerID
     * @return String If operation successful or not
     */
    HashMap<String, Integer> getBookingSchedule(String customerID) throws RemoteException;
}
