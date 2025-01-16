import java.util.*;

public class gobackn {
    static final int WINDOW_SIZE = 3;
    static final int TOTAL_FRAMES = 5;
    static int nextFrameToSend = 0;
    static int nextFrameToReceive = 0;
    static int ackReceived = -1;
    static boolean[] acknowledgement = new boolean[TOTAL_FRAMES];

    public static void sender(Random rand){
        while(ackReceived < TOTAL_FRAMES-1){
            for(int i = nextFrameToSend;i<nextFrameToSend+WINDOW_SIZE && i<TOTAL_FRAMES;i++){
                if(!acknowledgement[i]){
                    System.out.println("[Sender] Sending frame " + i);
                    receiver(i, rand);
                }
            }
            int ack = nextFrameToReceive - 1;
            System.out.println("[Sender] ACK for frame " + ack + " received successfully");
            if(ack>ackReceived){
                for(int j = ackReceived+1;j<=ack;j++){
                    acknowledgement[j] = true;
                }
                ackReceived = ack;
                nextFrameToSend = ack+1;
            }
            else{
                System.out.println("[Sender] No new frames, resending frames from " + nextFrameToSend);
            }
        }
    }
    public static void receiver(int frame, Random rand){
        int randFailure = rand.nextInt(5);
        if(randFailure == 0){
            System.out.println("[Receiver] Frame " + frame + " lost during transmission");
        }
        else if(frame == nextFrameToReceive){
            System.out.println("[Receiver] Frame " + frame + " received successfully");
            nextFrameToReceive++;
        }
        else{
            System.out.println("[Receiver] Frame " + frame + " discarded");
        }
    }
    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println("[Sender] Starting Sliding Window Protocol with Go Back N");
        sender(rand);
    }
}
