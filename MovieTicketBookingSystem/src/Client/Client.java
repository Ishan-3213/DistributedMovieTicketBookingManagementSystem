package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Interface.InterfaceOperations;

public class Client {
    public static void main(String[] args) {
      try {
         // int RMIPort; 
         // String hostName;
         // InputStreamReader is = new InputStreamReader(System.in);
         // BufferedReader br = new BufferedReader(is);
         // System.out.println("Enter the RMIRegistry host name:");
         // hostName = br.readLine();
         // System.out.println("Enter the RMIregistry port number:");
         // String portNum = br.readLine();
         // RMIPort = Integer.parseInt(portNum);
         // String registryURL = "rmi://" + hostName+ ":" + portNum + "/hello"; 
         Registry registry = LocateRegistry.getRegistry(8001);
         // find the remote object and cast it to an interface object
         InterfaceOperations h = (InterfaceOperations)registry.lookup("RegistryTest");
         System.out.println("Lookup completed " );
         // invoke the remote method
         String message = h.addMovieSlots("Donald Duck", "test" , 12);
         System.out.println("HelloClient: " + message);
         }catch (Exception e) {
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
