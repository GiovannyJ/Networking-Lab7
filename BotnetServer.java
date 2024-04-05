import java.net.*;
import java.io.*;

public class BotnetServer {
    private CommandQueue queue;
    private Socket socket;

    public BotnetServer(Socket socket){
        super();
        this.socket = socket;
    }
    
    private boolean send_command(String commandName){
        return true;
    }

    public void run(){
        
    }
}