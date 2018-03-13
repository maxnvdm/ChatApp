import java.io.DataInputStream;  
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    
    static Socket clientSocket;
    static DataOutputStream output;
    static DataInputStream input;

    Client(int port, String username)
    {
        try {
            clientSocket = new Socket(InetAddress.getLocalHost(), port); //portnumber should be >1023
            output = new DataOutputStream(clientSocket.getOutputStream());
            input = new DataInputStream(clientSocket.getInputStream());

        }
            catch (IOException e){
                System.out.println(e);
            }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Client myClient = new Client(4321, "Max");
        System.out.println("Enter your message:");
        String message = sc.nextLine();
        output.writeUTF(message);
        output.flush();
        String temp;
        temp = input.readUTF();
        System.out.println(temp);

        myClient.closeConnection();
    }

    public void closeConnection(){
        try
        {
            output.close();
            input.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}