import java.net.*;
import java.io.*;
import java.io.Serializable;

public class CommandProtocol {
    private static final int WAITING = 0;
    private static final int PROCESSING_COMMAND = 1;

    private int state = WAITING;


    public String processCommand(Command command){
        
        Boolean isProcessed = false;
        String theOutput = null;

        if (state == WAITING){
            theOutput = "Connected";
            state = PROCESSING_COMMAND;
        }else if (state == PROCESSING_COMMAND){
            String instructions = command.getInstructions();
            
            switch (command.getCommandName()){
                case "openApp":
                    if (openApp(instructions)){
                        command.setResponse("App " + instructions + " opened");
                        isProcessed = true;
                    }else{
                        command.setResponse("App " + instructions + " not opened");
                    }
                    break;
                case "status":
                    if(status_check()){
                        command.setResponse("Client Active");
                        isProcessed = true;
                    }else{
                        command.setResponse("Client inactive");
                    }
                    break;
                case "selfDestruct":
                    if(self_destruct()){
                        command.setResponse("Self Destructed Client");
                        isProcessed = true;
                    }else{
                        command.setResponse("Client Failed to set destruct");
                    }
                    break;
                default:
                    isProcessed = false;
                
                }
                if (!isProcessed){
                    state = WAITING;
                }
                theOutput = command.getResponse();
        }
        return theOutput;
    }

    private boolean openApp(String appName){
        return true;
    }
    private boolean status_check(){
        return true;
    }
    private boolean self_destruct(){
        return true;
    }
    
}
