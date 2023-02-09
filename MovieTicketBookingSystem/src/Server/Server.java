package src.Server;

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

    Server(int RMIPortNum, String server_name, String[] args) throws RemoteException{
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
            Runnable task = () -> {serve_listener(args);};
            Thread t1 = new Thread(task);
            Thread t2 = new Thread(task);
            Thread t3 = new Thread(task);
            t1.start();
            t2.start();
            t3.start();
            }catch (Exception re) {
            System.out.println("Exception in Server.main: " + re);
        }
    }

    public void serve_listener(String args[]){
        DatagramSocket datasocket = null;
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
                
                ImplementationOperations impobj = new ImplementationOperations(this.server_name);
                String method = splitted[0];
                String movie_name = splitted[1];
                String movie_id = splitted[2];
                String customer_id = splitted[3];
                System.out.println(data+ "\n" +  method + "\n" + movie_name);
                switch(method){
                    case "list_movie":
                        String received_data = impobj.list_movie();
                        System.out.println("----------" + received_data + "----------");
                        byte [] byte_data = received_data.getBytes();
                        DatagramPacket reply = new DatagramPacket(byte_data, received_data.length() ,received.getAddress(), received.getPort());
                        System.out.println("MEssage from the server " + server_name + " at the port " +RMIPortNum);
                        datasocket.send(reply);
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



