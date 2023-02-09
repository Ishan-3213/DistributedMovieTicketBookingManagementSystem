package src.Server;

import Implementation.ImplementationOperations;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    static int RMIPortNum;

    Server(int RMIPortNum, String server_name) throws RemoteException{
        super();
        try{ 
//            int RMIPortNum = 8001;
            LocateRegistry.getRegistry(RMIPortNum);
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
            ImplementationOperations impobj = new ImplementationOperations(server_name);
            registry.rebind("RegistryTest", impobj);
            System.out.println("Server is started at the PORT- "+ RMIPortNum);
            }catch (Exception re) {
            System.out.println("Exception in Server.main: " + re);
        }
//        RMIPortNum = 8001;
        // int RMIPortNum;
    }
//    public Server(int port) {
//    }


    public void serve_listener(String args[]){
        DatagramSocket datasocket = null;
        try {
            datasocket = new DatagramSocket(8002);
            byte [] buffer = new byte[1000];
            while(true){
                DatagramPacket received = new DatagramPacket(buffer, buffer.length);
                datasocket.receive(received);
                DatagramPacket reply = new DatagramPacket(received.getData(), received.getLength(),received.getAddress(), received.getPort());
                datasocket.send(reply);
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


// import java.rmi.server.UnicastRemoteObject;

// import Interface.InterfaceOperations;

// import java.net.DatagramPacket;
// import java.net.DatagramSocket;
// import java.net.InetAddress;

// public class Server implements InterfaceOperations {
//    public Server() {}

//       // Do some processing
//       String result = "Something done by Server 1";

//       // Send a message to Server 2 using UDP/IP
//       try {
//          DatagramSocket socket = new DatagramSocket();
//          InetAddress address = InetAddress.getByName("localhost");
//          byte[] buffer = "Message from Server 1".getBytes();
//          DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5555);
//          socket.send(packet);
//          socket.close();
//       } catch (Exception e) {
//          System.err.println("Server 1 UDP exception: " + e.toString());
//          e.printStackTrace();
//       }

//       return result;
// }
