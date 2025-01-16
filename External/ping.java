import java.util.*;
import java.io.IOException;
import java.net.*;

public class ping {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Hostname : ");
        String host = sc.next();
        try{
            InetAddress inetaddress = InetAddress.getByName(host);
            boolean isReachable = inetaddress.isReachable(5000);
            if(isReachable){
                System.out.println("Host " + host + " is reachable");
                System.out.println("IP Address : " + inetaddress.getHostAddress());
            }
        }
        catch(UnknownHostException e){
            System.out.println(e);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
