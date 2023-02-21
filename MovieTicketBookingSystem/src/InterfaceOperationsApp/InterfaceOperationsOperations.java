package InterfaceOperationsApp;


/**
* InterfaceOperationsApp/InterfaceOperationsOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./Interface/InterfaceOperations.idl
* Sunday, February 19, 2023 6:40:16 PM EST
*/

public interface InterfaceOperationsOperations 
{
  String cancelMovieTickets (String customerID, String movieID, String movieName, int numberOfTickets);

  /**
       * Permission Set - Admin Only.
       * This method add movie for particular movie if exist in hash map
       * and if the movie does not exist it will create a new movie entry.
       * @param movieId
       * @param movieName
       * @param bookingCapacity
       * @return string If operation successful or not
       */
  String addMovieSlots (String movieId, String movieName, int bookingCapacity);

  /**
       * Permission Set - Admin Only.
       * This method removes the movie if existed.    
       * If movie show exist and customer has booked a ticket then
       * movie gets deleted and will book the same movie in next available slot for that customer.
       * @param movieId
       * @param movieName
       * @return string If operation successful or not
       */
  String removeMovieSlots (String movieId, String movieName);

  /**
       * Permission Set - Admin Only.
       * This method shows the availability for a particular movie in all theaters.
       * @param movieName
       * @return string If operation successful or not
       */
  String listMovieShowsAvailability (String movieName);

  /**
       * Permission Set - Admin and User.
       * This method is used tp book movie tickets.
       * @param customerID
       * @param movieId
       * @param movieName
       * @param numberOfTickets
       * @return string If operation successful or not
       */
  String bookMovieTickets (String customerID, String movieId, String movieName, int numberOfTickets);

  /**
       * Permission Set - Admin and User.
       * This method is used tp book movie tickets.
       * @param customerID
       * @return string If operation successful or not
       */
  String getBookingSchedule (String customerID);
} // interface InterfaceOperationsOperations
