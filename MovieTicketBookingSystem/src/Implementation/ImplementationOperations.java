
package Implementation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import Interface.InterfaceOperations;

public class ImplementationOperations extends UnicastRemoteObject implements InterfaceOperations {
    HashMap<String, HashMap<String, Integer>> datastorage;
    HashMap<String, HashMap<String, Integer>> user_data;
    HashMap<String, Integer> booking_hashmap;
    HashMap<String, Integer> customer_booking_hashmap;
    String server_name;


    public ImplementationOperations(String server_name) throws RemoteException {
        super();
        this.server_name = server_name;
        datastorage = new HashMap<>();
        user_data = new HashMap<>();
        datastorage.put("AVATAR", new HashMap<String, Integer>());
        datastorage.put("AVENGER", new HashMap<String, Integer>());
        datastorage.put("TITANIC", new HashMap<String, Integer>());

        user_data.put("AWTC1234", new HashMap<String, Integer>());
        user_data.put("VERA4321", new HashMap<String, Integer>());
        user_data.put("OUTA4321", new HashMap<String, Integer>());

        booking_hashmap = new HashMap<String, Integer>();
        customer_booking_hashmap = new HashMap<String, Integer>();

        customer_booking_hashmap = user_data.get("AWTC1234");
        customer_booking_hashmap.put("AVATAR-ATWA23022023", 5);
        user_data.put("AWTC1234", customer_booking_hashmap);

        customer_booking_hashmap = user_data.get("VERA4321");
        customer_booking_hashmap.put("TITANIC-VERM23022023", 10);
        user_data.put("VERA4321", customer_booking_hashmap);
        
        customer_booking_hashmap = user_data.get("OUTA4321");
        customer_booking_hashmap.put("AVENGER-OUTM23022023", 10);
        user_data.put("OUTA4321", customer_booking_hashmap);

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
        System.out.println("there is no movie slot for this movie..!!!");
        return "No movie slot found for the movie " + movieName + " at " + movieId.substring(0, 3) + " region";
       }
   }

   @Override
   public String userData(String customoerID){
    // To initiate the customer hash map for the logged in user.
    System.out.println("User hashmap initiated!");
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
                String movie_string = movieName + "-" + movieId;
                if (user_data.containsKey(customerID)){
                    if (user_data.get(customerID).containsKey(movie_string) ){
                        user_data.get(customerID).put(movie_string, user_data.get(customerID).get(movie_string) + numberOfTickets);
                        System.out.println(user_data.get(customerID) + " --LastIf-- " + customer_booking_hashmap);
                        return numberOfTickets + " tickets booked for the movie " + movieName + "-" + movieId;
                        // if (customerID.substring(0, 3) == movieId.substring(0, 3)){
                            // // TO-DO something with that same region thing...!!!
                            // }
                    }else{
                        // add data to the existing cutomer id in hashmap
                        user_data.get(customerID).put(movie_string, numberOfTickets);
                        System.out.println("Customer-ID --->>"+ customerID + " ---" + user_data.get(customerID) + " --last Else-- " + customer_booking_hashmap);
                        return numberOfTickets + " tickets booked for the movie " + movieName + "-" + movieId;
                    }
                }else{
                    // create new customer id in hasmap
                    customer_booking_hashmap.put(movie_string, numberOfTickets);
                    user_data.put(customerID, customer_booking_hashmap);
                    System.out.println(user_data.get(customerID) + " --second last else-- " + customer_booking_hashmap);
                    return numberOfTickets + " tickets booked for the movie " + movieName + "-" + movieId;
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
            System.out.println(user_data + "----->>>" + user_data.keySet());
            return user_data.get(customerID);
        }
        else{
            return new HashMap<>();
        }
   }

   @Override
   public String cancelMovieTickets(String customerID, String movieID, String movieName, Integer numberOfTickets){

        if(user_data.containsKey(customerID)){
            String movie_string = movieName + "-" + movieID;
            if(user_data.get(customerID).containsKey(movie_string)){
                if(user_data.get(customerID).get(movie_string) >= numberOfTickets){
                    user_data.get(customerID).put(movie_string, user_data.get(customerID).get(movie_string) - numberOfTickets);
                    datastorage.get(movieName).put(movieID, datastorage.get(movieName).get(movieID) + numberOfTickets);
                    System.out.println(user_data.get(customerID).get(movie_string) + " IF---Here is the user data " + "\n------>>" + "\n...custome id"+user_data.get(customerID)+"\n ehole user data" + user_data);
                    return numberOfTickets + " Movie tickets for " + movieName + " has been removed";
                }
                else{
                    datastorage.get(movieName).put(movieID, user_data.get(customerID).get(movieID));
                    user_data.get(customerID).remove(movie_string);
                    user_data.get(customerID).remove(movie_string, user_data.get(customerID).get(movie_string));
                    System.out.println(user_data.get(customerID).get(movie_string) + " ELSE Here is the user data " + "\n------>>" + "\n...custome id"+user_data.get(customerID)+"\n ehole user data" + user_data);
                    return numberOfTickets + " Movie tickets for " + movieName + " has been removed";
                }
            }else{
                return "No movie found with the movieID- " + movieID;
            }
        }else{
            return "There is no userdata found with the id " + customerID;
    }
       
   }
   
   public void Sending_Message(String args[]){ 
    // args give message contents and destination hostname
    DatagramSocket datasocket = null;
    try{
        datasocket = new DatagramSocket();
        int PortNumber = 8002;
        byte[] btarray = args[0].getBytes();
        InetAddress host_name = InetAddress.getLocalHost();
        DatagramPacket request = new DatagramPacket(btarray, args[0].length(), host_name, PortNumber);
        System.out.println(request);
        datasocket.send(request);

        byte[] response = new byte[1000];
        DatagramPacket reply = new DatagramPacket(response, response.length);
        datasocket.receive(reply);
        System.out.println("Here is the data you send...!! " + reply.getData());
    }catch(SocketException e){ System.out.println("Something went wrong with SKT: " + e.getMessage());
    }catch(IOException e){System.out.println("Something went wrong in IO: " + e.getMessage());
    }finally{if(datasocket != null){datasocket.close();}
    }


    // DatagramSocket aSocket = null;
    // try {
    //     aSocket = new DatagramSocket();    
    //     byte [] m = args[0].getBytes();
    //     InetAddress aHost = InetAddress.getByName(args[1]);
    //     int serverPort = 6789;		                                                 
    //     DatagramPacket request =
    //          new DatagramPacket(m,  args[0].length(), aHost, serverPort);
    //     aSocket.send(request);			                        
    //     byte[] buffer = new byte[1000];
    //     DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
    //     aSocket.receive(reply);
    //     System.out.println("Reply: " + new String(reply.getData()));	
    // }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
    // }catch (IOException e){System.out.println("IO: " + e.getMessage());
    // }finally {if(aSocket != null) aSocket.close();}
}	
}