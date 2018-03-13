import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int port;
    private static ServerSocket MyServer;   // The socket that receives connection requests from clients
    static DataInputStream input;       // Used to read data from the communication socket (comSocket)
    static DataOutputStream output;     // Used to write data to the communication socket
    private static boolean keepGoing;   // Used to keep the server running

    public Server(int port) throws IOException {
        this.port = port;
        MyServer = new ServerSocket(4321);
        keepGoing = true;
    }

    public static void main(String[] args) {
        try {
            Server s = new Server(4321);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        while(keepGoing) {
            String message = null;
            try {
                Socket comSocket = MyServer.accept();
                input = new DataInputStream(comSocket.getInputStream());
                output = new DataOutputStream(comSocket.getOutputStream());
                System.out.println("Waiting for Client to connect");
                message = input.readUTF();
                System.out.println("Client connected");
                output.writeUTF("The following message was received: " + message);
                System.out.println("Message sent: " + message);
                //s.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (message.equals(("exit"))) {
                // when "exit" is received by the server is shuts down
                keepGoing = false;
            }
        }
    }

    public void closeConnection(){
        try
        {
            output.close();
            input.close();
            MyServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
