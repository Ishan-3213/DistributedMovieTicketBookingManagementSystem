package Server;

import java.rmi.RemoteException;

import Interface.InterfaceOperations;

public class ServerInstance {
    public static void main(String args[]) throws RemoteException {
        Server server_instance_atwater = new Server(8001, "ATW");
        Server server_instance_verdnum = new Server(8002, "VER");
        Server server_instance_outremont = new Server(8003, "OUT");
    }
}