
public class CommandProtocol {
    //STATES
    private static final int WAITING = 0;
    private static final int PROCESSING_COMMAND = 1;

    private int state = PROCESSING_COMMAND;


    /**
     * processCommand - run the command of the input and mutate the commands response
     * @param command - the command to process
     * @return - the mutated command 
     */
    public String processCommand(Command command){    
        Boolean isProcessed = false;
        String theOutput = null;

        if (state == WAITING){
            command.setResponse("Connected");;
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
                case "exit":
                    command.setResponse("exiting");
                    isProcessed = true;
                    break;
                default:
                    isProcessed = false;   
            }
            if (!isProcessed){
                state = WAITING;
            }
        }

        if (isProcessed){
            theOutput = command.getResponse();
            command.setIsExecutedTrue();
            command.setErrorFalse();
        }
        
        return theOutput;
    }

    /**
     * openApp - open the app of the commands instructions on the client side
     * @param appName - the name of the app to open
     * @return - true if executed false if not
     */
    private boolean openApp(String appName){
        return true;
    }
    
    /**
     * status_check - sends the clients OS and uptime status to the server
     * @return - true if executed false if not
     */
    private boolean status_check(){
        return true;
    }
    
}
