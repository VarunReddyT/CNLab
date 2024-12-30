import java.net.*;
import java.util.*;

public class dns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String domain = sc.nextLine();
        try{
            InetAddress[] inetAddresses = InetAddress.getAllByName(domain);
            for(InetAddress address : inetAddresses){
                System.out.println(address.getHostAddress());
            }
        }
        catch(UnknownHostException e){
            System.out.println(e);
        }
        sc.close();
    }
}
