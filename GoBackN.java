import java.util.Random;

public class GoBackN {

    static final int WINDOW_SIZE = 4;
    static final int TOTAL_FRAMES = 10;

    static int nextFrameToReceive = 0;
    static int nextFrameToSend = 0;
    static int ackReceived = -1;
    static boolean[] frameAcknowledged = new boolean[TOTAL_FRAMES];

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println("[Sender] Starting Sliding Window Protocol with Go-Back-N.");
        sender(rand);
        System.out.println("[Sender] Transmission completed.");
    }

    public static void sender(Random rand) {
        while (ackReceived < TOTAL_FRAMES - 1) {
            for (int i = nextFrameToSend; i < nextFrameToSend + WINDOW_SIZE && i < TOTAL_FRAMES; i++) {
                if (!frameAcknowledged[i]) {
                    System.out.println("[Sender] Sending Frame " + i);
                    receiver(i, rand);
                }
            }

            int ack = nextFrameToReceive - 1;
            System.out.println("[Sender] ACK for Frame " + ack + " received successfully.");
            if (ack > ackReceived) {
                for (int j = ackReceived + 1; j <= ack; j++) {
                    frameAcknowledged[j] = true;
                }
                ackReceived = ack;
                nextFrameToSend = ack + 1;
            } else {
                System.out.println("[Sender] No new acknowledgment, resending frames from " + nextFrameToSend);
            }
        }
    }

    public static void receiver(int frame, Random rand) {
        int randFailure = rand.nextInt(5);
        if (randFailure == 0) {
            System.out.println("[Receiver] Frame " + frame + " lost during transmission.");
        } else if (frame == nextFrameToReceive) {
            System.out.println("[Receiver] Frame " + frame + " received");
            nextFrameToReceive++;
        } else {
            System.out.println("[Receiver] Frame " + frame + " discarded");
        }
    }
}
