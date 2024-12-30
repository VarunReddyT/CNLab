import java.util.Scanner;

class CRC {

    static String data;
    static String generator;
    static String remainder;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter data to be transmitted: ");
        data = scanner.nextLine();
        System.out.print("Enter the generator polynomial: ");
        generator = scanner.nextLine();

        String paddedData = data + "0".repeat(generator.length() - 1);
        System.out.println("\nData padded with zeros: " + paddedData);

        remainder = calculateCRC(paddedData);
        System.out.println("CRC value: " + remainder);

        String transmittedData = data + remainder;
        System.out.println("Transmitted data: " + transmittedData);

        System.out.print("\nEnter received data: ");
        String receivedData = scanner.nextLine();
        String remainder = calculateCRC(receivedData);

        if (remainder.contains("1")) {
            System.out.println("Error detected in received data.");
        } else {
            System.out.println("No error detected in received data.");
        }

        scanner.close();
    }

    static String calculateCRC(String input) {
        StringBuilder result = new StringBuilder(input);

        for (int i = 0; i <= input.length() - generator.length(); i++) {
            if (result.charAt(i) == '1') {
                for (int j = 0; j < generator.length(); j++) {
                    result.setCharAt(i + j, result.charAt(i + j) == generator.charAt(j) ? '0' : '1');
                }
            }
        }

        return result.substring(input.length() - (generator.length() - 1));
    }
}