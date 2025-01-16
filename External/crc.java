import java.util.*;

public class crc {
    public static String crcValue(String input,String generator){
        StringBuilder res = new StringBuilder(input);
        for(int i = 0;i<=input.length()-generator.length();i++){
            if(res.charAt(i)=='1'){
                for(int j = 0;j<generator.length();j++){
                    res.setCharAt(i+j,res.charAt(i+j)==generator.charAt(j) ? '0' : '1');
                }
            }
        }
        return res.substring(input.length()-(generator.length()-1));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter data to be transmitted : ");
        String data = sc.next();
        System.out.print("Enter the generator polynomial : ");
        String generator = sc.next();

        String paddedData = data + "0".repeat(generator.length()-1);

        String remainder = crcValue(paddedData, generator);
        System.out.println("CRC Value : " + remainder);

        String transmittedData = data + remainder;
        System.out.println("Transmitted Data : " + transmittedData);

        System.out.print("Enter received data : ");
        String receivedData = sc.next();

        remainder = crcValue(receivedData, generator);

        if(remainder.contains("1")){
            System.out.println("Error detected.");
        }
        else{
            System.out.println("No error detected");
        }
        sc.close();
    }
}
