package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import Interface.InterfaceOperations;
import Server.Server;

public class Client {
    public static void main(String[] args) {
        try {
           Server obj = new Server();
           InterfaceOperations stub = (InterfaceOperations) UnicastRemoteObject.exportObject(obj, 0);
  
           // Bind the stub in the registry
           Registry registry = LocateRegistry.getRegistry();
           registry.bind("Server1", stub);
  
           System.out.println("Server 1 ready");
        } catch (Exception e) {
           System.err.println("Server 1 exception: " + e.toString());
           e.printStackTrace();
        }
        // public String CustomerOrAdmin(String userId){
        //     boolean is_admin;
        //     userId = null;
    
        //     if (is_admin) {
                
        //     }
    
        //     return is_admin;
        // }
     }
}
