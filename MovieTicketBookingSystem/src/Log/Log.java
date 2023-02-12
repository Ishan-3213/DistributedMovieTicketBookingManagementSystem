package src.Log;

import src.Client.Client;

import java.io.*;
import java.lang.System.Logger.Level;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    // public Logger logger;
//    private static Logger logClass = Logger.getLogger("Logs");
    public Logger logger;
    File file;

    public Log(String fname){
        super();
        try {
            file = new File("D:/Academic/Concordia/Sem-1/DSD/Assignemnt/DMTMS/DistributedMovieTicketBookingManagementSystem/MovieTicketBookingSystem/src/Log/"+ fname + ".txt");
            if(file.exists()){
                FileHandler file = new FileHandler(fname, true);
                logger = Logger.getAnonymousLogger();
                logger.setUseParentHandlers(false);
                logger.addHandler(file);
                SimpleFormatter format = new SimpleFormatter();
                file.setFormatter(format);
            }
            file.createNewFile();
        } catch (IOException | SecurityException ex) {
            ex.getStackTrace();
        }
    }
}
