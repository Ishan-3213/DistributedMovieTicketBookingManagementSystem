package Client;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import Interface.InterfaceOperations;
import Server.Server;

public class Client {
    public static void main(String[] args) {
        try {
           Server obj = new Server();
           InterfaceOperations stub = (InterfaceOperations) UnicastRemoteObject.exportObject((Remote) obj, 0);
  
           // Bind the stub in the registry
           Registry registry = LocateRegistry.getRegistry();
           registry.rebind("Server1", (Remote) stub);
  
           System.out.println("Server 1 ready");
        } catch (Exception e) {
           System.err.println("Server 1 exception: " + e.toString());
           e.printStackTrace();
        }

        // Admin's Login here

        // public String CustomerOrAdmin(String userId){
        //     boolean is_admin;
        //     userId = null;
    
        //     if (is_admin) {
                
        //     }
    
        //     return is_admin;
        // }


        //
        
        // switch case to create the values
// public class Main {
//    public static void main(String[] args) {
//       utils today =utils.MONDAY;
//        switch (today) {
//            case MONDAY:
//                System.out.println("Today is Monday");
//                break;
//            case TUESDAY:
//                System.out.println("Today is Tuesday");
//                break;
//            case WEDNESDAY:
//                System.out.println("Today is Wednesday");
//                break;
//            case THURSDAY:
//                System.out.println("Today is Thursday");
//                break;
//            case FRIDAY:
//                System.out.println("Today is Friday");
//                break;
//            case SATURDAY:
//                System.out.println("Today is Saturday");
//                break;
//            case SUNDAY:
//                System.out.println("Today is Sunday");
//                break;
//        }
//    }
// }

     }
}
