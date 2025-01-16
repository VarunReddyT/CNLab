import java.util.*;
public class broadcast {
    public static int ipToInt(String s){
        String[] parts = s.split("\\.");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);
        int c = Integer.parseInt(parts[2]);
        int d = Integer.parseInt(parts[3]);

        return (a<<24) | (b<<16) | (c<<8) | d;
    }
    public static String intToIp(int ip){
        return String.format("%d.%d.%d.%d", (ip>>24)&0xFF,(ip>>16)&0xFF,(ip>>8)&0xFF,ip&0xFF);
    }
    public static int calculateSubnetMask(int prefixLength){
        return prefixLength == 0 ? 0 : ~((1<<(32-prefixLength))-1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ip = sc.next();
        int prefixLength = sc.nextInt();
        int newPrefixLength = prefixLength+1;

        int ipInt = ipToInt(ip);

        int subnetMask = calculateSubnetMask(prefixLength);
        int newSubnetMask = calculateSubnetMask(newPrefixLength);

        int hostsPerSubnet = (1<<(32-newPrefixLength)) - 2;
        System.out.println("Hosts per subnet : " + hostsPerSubnet);

        for(int i = 0;i<2;i++){
            int network = (ipInt&subnetMask) | (i<<(32-newPrefixLength));
            int broadcast = network | ~newSubnetMask;
            int firstHost = network+1;
            int lastHost = broadcast-1;

            System.out.println("Subnet " + (i+1) + " : ");
            System.out.println("Network Address : " + intToIp(network));
            System.out.println("Broadcast Address : " + intToIp(broadcast));
            System.out.println("Subnet Mask : " + intToIp(newSubnetMask));
            System.out.println("First Host : " + intToIp(firstHost));
            System.out.println("Last Host : " + intToIp(lastHost));
        }
        sc.close();
    }
}
