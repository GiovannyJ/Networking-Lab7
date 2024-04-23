# Botnet Server Client

## How to run
* compile the server
    ```
    javac BotnetServer.java Command.java CommandProtocol.java CommandQueue.java
    ```
    or using makefile
    ```
    make server
    ```
* compile the client
   ```
    javac BotnetClient.java Command.java CommandProtocol.java
    ```
    or using makefile
    ```
    make client
    ```
* run the server
    ```
    java BotnetServer.java <PORTNUMBER>
    ```
* run the client
    ```
    java BotnetClient.java localhost <PORTNUMBER>
    ```
* using the server: send commands to the client
    ```
    ------
    Enter a Command name 
    1)openApp
    2)status
    3)exit
    ------

    > (ENTER COMMAND NUMBER HERE)
    ```