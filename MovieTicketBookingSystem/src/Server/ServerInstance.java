package src.Server;

import java.rmi.RemoteException;

public class ServerInstance {
    public static void main(String args[]) throws RemoteException {
        Server server_instance_atwater = new Server(8001, "Atwater");
        Server server_instance_verdnum = new Server(8002, "Verdun");
        Server server_instance_outremont = new Server(8003, "Outremont");
    }
}
