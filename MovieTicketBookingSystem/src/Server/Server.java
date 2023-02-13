package Server;

import Implementation.ImplementationOperations;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class Server {
    Integer RMIPortNum;
    String server_name;

    Server(int RMIPortNum, String server_name) throws RemoteException{
        super();
        try{ 
            this.server_name = server_name;
            this.RMIPortNum = RMIPortNum;
//            int RMIPortNum = 8001;
            LocateRegistry.getRegistry(RMIPortNum);
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
            ImplementationOperations impobj = new ImplementationOperations(server_name);
            registry.rebind("RegistryTest", impobj);
            System.out.println("Server is started at the PORT- "+ RMIPortNum);
            Runnable task = () -> {serve_listener(impobj);};
            Thread t1 = new Thread(task);
            t1.start();
            }catch (Exception re) {
            System.out.println("Exception in Server.main: " + re);
        }
    }

    public void serve_listener(ImplementationOperations impobj){
        DatagramSocket datasocket = null;
        String customer_id;
        String movie_id;
        String movie_name;
        String method;
        try {
            datasocket = new DatagramSocket(this.RMIPortNum);
            byte [] buffer = new byte[1024];
            StringBuilder sb = new StringBuilder();
            System.out.println("server-->>side " + this.server_name +"\n" + "Port ->" + this.RMIPortNum);

            while(true){
                DatagramPacket received = new DatagramPacket(buffer, buffer.length);
                datasocket.receive(received);
                
                String data = new String(buffer, 0, received.getLength());
                // String data = Arrays.toString(received.getData());
                String [] splitted = data.split("<>");
                
//                ImplementationOperations impobj = new ImplementationOperations(this.server_name);
                Integer tickets = Integer.parseInt(splitted[4]);
                customer_id = splitted[3];
                movie_id = splitted[2];
                movie_name = splitted[1];
                method = splitted[0];
                System.out.println(data+ "\n" +  method + "\n" + movie_name);
                switch(method){
                    case "list_movie":
                        String received_data = impobj.list_movie(movie_name);
                        System.out.println("----------" + received_data + "----------");
                        byte [] byte_data = received_data.getBytes();
                        DatagramPacket reply = new DatagramPacket(byte_data, received_data.length() ,received.getAddress(), received.getPort());
                        System.out.println("Message from the server " + server_name + " at the port " +RMIPortNum);
                        datasocket.send(reply);
                        break;
                    case "bookMovieTickets":
                        String data_received = impobj.bookMovieTickets(customer_id, movie_id, movie_name, tickets);
                        System.out.println("----------" + data_received + "----------");
                        byte [] data_byte = data_received.getBytes();
                        DatagramPacket response = new DatagramPacket(data_byte, data_received.length() ,received.getAddress(), received.getPort());
                        System.out.println("Message from the server " + server_name + " at the port " +RMIPortNum);
                        datasocket.send(response);
                        break;
                    case "booking_schedule":
                        String received_str = impobj.booking_schedule(customer_id);
                        System.out.println("----------" + received_str + "----------");
                        byte [] data_byt = received_str.getBytes();
                        DatagramPacket answer = new DatagramPacket(data_byt, received_str.length() ,received.getAddress(), received.getPort());
                        System.out.println("Message from the server " + server_name + " at the port " +RMIPortNum);
                        datasocket.send(answer);
                        break;
                    case "cancelMovieTickets":
                        String str_received = impobj.cancelMovieTickets(customer_id, movie_id, movie_name, tickets);
                        System.out.println("----------" + str_received + "----------");
                        byte [] byt_data = str_received.getBytes();
                        DatagramPacket acknowledgment = new DatagramPacket(byt_data, str_received.length() ,received.getAddress(), received.getPort());
                        System.out.println("Message from the server " + server_name + " at the port " +RMIPortNum);
                        datasocket.send(acknowledgment);
                        break;
                    default:
                        System.out.println("Not working...!");
                }

            }
        }catch (SocketException e) {System.out.println("Something wrong with the SKT-ServerSide: " + e.getMessage());
        }catch(IOException e){System.out.println("Somthing went wrong in IO: " + e.getMessage());
        }finally{if(datasocket != null) datasocket.close();}
    }
}


    //     DatagramSocket aSocket = null;
    //     try{
    //         aSocket = new DatagramSocket(6789);
    //                 // create socket at agreed port
    //         byte[] buffer = new byte[1000];
    //          while(true){
    //              DatagramPacket request = new DatagramPacket(buffer, buffer.length);
    //               aSocket.receive(request);     
    //             DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
    //                 request.getAddress(), request.getPort());
    //             aSocket.send(reply);
    //         }
    //     }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
    //     }catch (IOException e) {System.out.println("IO: " + e.getMessage());
    //     }finally {if(aSocket != null) aSocket.close();}
    // }



