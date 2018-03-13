import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int port;
    private static ServerSocket MyServer;
    static DataInputStream input;
    static DataOutputStream output;


    public Server(int port) throws IOException {
        this.port = port;
        MyServer = new ServerSocket(4321);
    }

    public static void main(String[] args) {

        try
        {
            Server s = new Server(4321);
            System.out.println("Waiting for Client to connect");
            Socket comSocket =  MyServer.accept();
            input = new DataInputStream(comSocket.getInputStream());
            String message = input.readUTF();
            output = new DataOutputStream(comSocket.getOutputStream());
            System.out.println("Client connected");
            output.writeUTF("The following message was received: "+message);
            System.out.println("Message sent: "+message);
            s.closeConnection();

        }
        catch (IOException e)
        {
            e.printStackTrace();
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
