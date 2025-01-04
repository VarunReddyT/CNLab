#include <stdio.h>

int main() {
    int window, total_frames;

    printf("Enter Window size: ");
    scanf("%d", &window);

    printf("Enter Total Number of Frames: ");
    scanf("%d", &total_frames);

    int sent = 0; 
    int ack = -1; 

    while (sent < total_frames) {
        for (int i = 0; i < window && sent < total_frames; i++) {
            printf("Frame Transmitted: %d\n", sent);
            sent++;
        }

        printf("Enter the last received acknowledgment: ");
        scanf("%d", &ack);
        if (ack >= total_frames - 1) {
            printf("All frames acknowledged. Transmission complete.\n");
            break;
        } else if (ack < sent - window || ack < 0) {
            printf("Invalid acknowledgment. Resending frames from %d.\n", sent - window);
            sent = sent - window;
        } else {
            sent = ack + 1; 
        }
    }

    return 0;
}
