import java.util.Random;

public class gobackn2 {

    private static final int WINDOW_SIZE = 4;
    private static final int TOTAL_FRAMES = 10;

    private static int nextFrameToSend = 0;
    private static int nextExpectedAck = 0;

    public static void main(String[] args) {
        System.out.println("[Sender] Starting Go-Back-N Protocol Simulation...");
        simulateGoBackN();
        System.out.println("[Sender] Transmission Complete!");
    }

    public static void simulateGoBackN() {
        Random random = new Random();

        while (nextExpectedAck < TOTAL_FRAMES) {
            for (int i = nextFrameToSend; i < nextExpectedAck + WINDOW_SIZE && i < TOTAL_FRAMES; i++) {
                System.out.println("[Sender] Sending Frame " + i);
            }
            nextFrameToSend = Math.min(nextExpectedAck + WINDOW_SIZE, TOTAL_FRAMES);
            if (random.nextInt(5) == 0) {
                System.out.println("[Receiver] Acknowledgment lost! Resending frames starting from " + nextExpectedAck);
            } else {
                System.out.println("[Receiver] Acknowledgment received for Frame " + nextExpectedAck);
                nextExpectedAck++;
            }
        }
    }
}