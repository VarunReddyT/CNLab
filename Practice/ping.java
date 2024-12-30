import java.net.*;
import java.io.*;
public class ping {
    public static void main(String[] args) {
        String hostname = args[0];
        try{
            InetAddress inetAddress = InetAddress.getByName(hostname);
            boolean isReachable = inetAddress.isReachable(5000);
            if(isReachable){
                System.out.println(inetAddress.getHostAddress());
            }
            else{
                System.out.println("Not reachable");
            }
        }
        catch(UnknownHostException e){
            System.out.println(e);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
