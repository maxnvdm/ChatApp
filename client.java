import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.net.Socket;  

public class client
{
    
    Socket MyClient;
    DataOutputStream output;
    DataInputStream input;

    client(String server, int port, String username)
    {
        try {
            MyClient = new Socket("MachineName", PortNumber); //portnumber should be >1023
            output = new DataOutputStream(MyClient.getOutputStream());
            input = new DataInputStream(MyClient.getInputStream());

        }
            catch (IOException e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args)
    {
        
        client(server, port, username);
    }
    
}