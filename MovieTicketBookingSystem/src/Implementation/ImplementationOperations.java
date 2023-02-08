
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

        user_data.put("AWTC1234", new HashMap<String, Integer>());
        user_data.put("VERA4321", new HashMap<String, Integer>());

        booking_hashmap = new HashMap<String, Integer>();
        customer_booking_hashmap = new HashMap<String, Integer>();

        customer_booking_hashmap = user_data.get("AWTC1234");
        customer_booking_hashmap.put("ATWA23022023", 5);
        user_data.put("AWTC1234", customer_booking_hashmap);

        customer_booking_hashmap = user_data.get("VERA4321");
        customer_booking_hashmap.put("VERM23022023", 10);
        user_data.put("VERA4321", customer_booking_hashmap);

        booking_hashmap = datastorage.get("AVATAR");
        booking_hashmap.put("OUTA23022023", 100);
        datastorage.put("AVATAR", booking_hashmap);
        
        booking_hashmap = datastorage.get("AVENGER");
        booking_hashmap.put("ATWA23022023", 50);
        datastorage.put("AVENGER", booking_hashmap);

        booking_hashmap = datastorage.get("TITANIC");
        booking_hashmap.put("VERM23022023", 50);
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
    user_data.putIfAbsent(customoerID, new HashMap<>());
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
    
    if(datastorage.containsKey(movieName)){
        if(datastorage.get(movieName).containsKey(movieId)){
            if(datastorage.get(movieName).get(movieId) > numberOfTickets){
                datastorage.get(movieName).put(movieId, datastorage.get(movieName).get(movieId) - numberOfTickets);
                // Tickets are available 
                if (user_data.containsKey(customerID)){
                    if (user_data.get(customerID).containsKey(movieId) ){
                        user_data.get(customerID).put(movieId, user_data.get(customerID).get(movieId) + numberOfTickets);
                        System.out.println(user_data.get(customerID) + " ---- " + customer_booking_hashmap);
                        return numberOfTickets + "tickets booked for the movie " + movieName + "-" + movieId;
                        // if (customerID.substring(0, 3) == movieId.substring(0, 3)){
                            // // TO-DO something with that same region thing...!!!
                            // }
                        }else{
                            // add data to the existing cutomer id in hashmap
                            user_data.get(customerID).put(movieId, numberOfTickets);
                            return numberOfTickets + "tickets booked for the movie " + movieName + "-" + movieId;
                        }
                    }else{
                        // create new customer id in hasmap
                        customer_booking_hashmap.put(movieId, numberOfTickets);
                        user_data.put(customerID, customer_booking_hashmap);
                        return numberOfTickets + "tickets booked for the movie " + movieName + "-" + movieId;
                    }
                }else{
                return numberOfTickets + " Seats are not available for the " + movieName + " - " + movieId;
            }
        }else{
            return "No movie found with the ID" + movieId;        
        }
    }else{
            return "No movie found with the name " + movieName;
        }
   }

   @Override
   public HashMap<String, Integer> getBookingSchedule(String customerID){
        if(user_data.containsKey(customerID)){
            return user_data.get(customerID);
        }
        else{
            return new HashMap<>();
        }
   }

   @Override
   public String cancelMovieTickets(String customerID, String movieID, String movieName, Integer numberOfTickets){

        if(user_data.containsKey(customerID)){
            if(user_data.get(customerID).containsKey(movieID)){
                if(user_data.get(customerID).get(movieID) < numberOfTickets){
                    user_data.get(customerID).put(movieID, user_data.get(customerID).get(movieID) - numberOfTickets);
                    datastorage.get(movieName).put(movieID, datastorage.get(movieName).get(movieID) + numberOfTickets);
                    return numberOfTickets + " Movie tickets for " + movieName + " has been removed";
                }
                else{
                    datastorage.get(movieName).put(movieID, user_data.get(customerID).get(movieID));
                    user_data.get(customerID).remove(movieID);
                    return numberOfTickets + " Movie tickets for " + movieName + " has been removed";
                }
            }else{
                return "No movie found with the movieID- " + movieID;
            }
        }else{
            return "There is no userdata found with the id " + customerID;
    }
       
   }
}