package Server;
import java.rmi.*;
// import java.rmi.server.*;
import Implementation.ImplementationOperations;
import java.rmi.registry.LocateRegistry;
import java.io.*;
/* This class represents the object server for a distributed object of class 
Hello, which implements the remote interface HelloInterface. */

public class Server {
    public static void main(String args[]) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String portNum, registryURL;
        try{ 
            System.out.println("Enter the RMIregistry port number:");
            portNum = (br.readLine()).trim();
            int RMIPortNum = Integer.parseInt(portNum);
            LocateRegistry.getRegistry(RMIPortNum);
            ImplementationOperations exportedObj = new ImplementationOperations();
            registryURL = "rmi://localhost:" + portNum + "/hello";
            Naming.rebind(registryURL, exportedObj);
            System.out.println("Hello Server ready.");
        }catch (Exception re) {
            System.out.println("Exception in HelloServer.main: " + re);
        }
    }
}

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
