import java.net.*;
import java.io.*;

public class tcp_reverse_server {
    public static void main(String[] args) {
        int port = 65432;
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server listening on port " + port);
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output,true);
                String text;
                while((text = reader.readLine()) != null){
                    System.out.println("Received text : " + text);
                    String reversedText = new StringBuilder(text).reverse().toString();
                    System.out.println("Sending : " + reversedText);
                    writer.println(reversedText);
                }
                socket.close();
                System.out.println("Client Disconnected");
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
