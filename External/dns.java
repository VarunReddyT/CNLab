import java.util.*;
import java.net.*;
public class dns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Domain : ");
        String domain = sc.next();
        try{
            InetAddress[] inetAddress = InetAddress.getAllByName(domain);
            for(InetAddress address : inetAddress){
                System.out.println(address.getHostAddress());
            }
        }
        catch(UnknownHostException e){
            System.out.println(e);
        }
    }
}
