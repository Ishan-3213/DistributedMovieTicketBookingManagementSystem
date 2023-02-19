package Server;

import java.rmi.RemoteException;
import java.util.logging.Logger;
import Logs.Log;

public class ServerInstance {
    public static void main(String args[]) throws RemoteException {
        Log logger_atw;
        logger_atw = new Log("ATW", false, true);
        Logger Logobj_atw = Logger.getLogger("ATW");
        Logobj_atw = logger_atw.attachFileHandlerToLogger(Logobj_atw);
        Server server_instance_atwater = new Server(8001, "ATW", Logobj_atw);

        Log logger_ver;
        logger_ver = new Log("VER", false, true);
        Logger logObj_ver = Logger.getLogger("VER");
        logObj_ver = logger_ver.attachFileHandlerToLogger(logObj_ver);
        Server server_instance_verdnum = new Server(8002, "VER", logObj_ver);

        Log logger_out;
        logger_out = new Log("OUT", false, true);
        Logger logobJ_out = Logger.getLogger("OUT");
        logobJ_out = logger_out.attachFileHandlerToLogger(logobJ_out);
        Server server_instance_outremont = new Server(8003, "OUT",logobJ_out );
    }
}