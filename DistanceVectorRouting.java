import java.util.Scanner;

public class DistanceVectorRouting {

    static final int INF = 9999;
    static final int MAX_NODES = 10;

    public static void initialize(int numNodes, int[][] costMatrix, int[][] distVector, int[][] nextHop) {
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                distVector[i][j] = costMatrix[i][j];
                if (costMatrix[i][j] != INF && i != j) {
                    nextHop[i][j] = j;
                } else {
                    nextHop[i][j] = -1;
                }
            }
        }
    }

    public static void printRoutingTable(int numNodes, int[][] distVector, int[][] nextHop) {
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Routing table for node " + i + ":");
            System.out.println("Destination\tNext Hop\tDistance");
            for (int j = 0; j < numNodes; j++) {
                if (distVector[i][j] == INF) {
                    System.out.println(j + "\t\t-\t\tINF");
                } else {
                    System.out.println(j + "\t\t" + nextHop[i][j] + "\t\t" + distVector[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void distanceVectorRouting(int numNodes, int[][] costMatrix, int[][] distVector, int[][] nextHop) {
        boolean updated;
        do {
            updated = false;
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    for (int k = 0; k < numNodes; k++) {
                        if (distVector[i][k] + distVector[k][j] < distVector[i][j]) {
                            distVector[i][j] = distVector[i][k] + distVector[k][j];
                            nextHop[i][j] = nextHop[i][k];
                            updated = true;
                        }
                    }
                }
            }
        } while (updated);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numNodes;
        int[][] costMatrix = new int[MAX_NODES][MAX_NODES];
        int[][] distVector = new int[MAX_NODES][MAX_NODES];
        int[][] nextHop = new int[MAX_NODES][MAX_NODES];

        System.out.print("Enter the number of nodes: ");
        numNodes = scanner.nextInt();

        System.out.println("Enter the cost matrix (use " + INF + " for INF):");
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                costMatrix[i][j] = scanner.nextInt();
            }
        }

        initialize(numNodes, costMatrix, distVector, nextHop);
        distanceVectorRouting(numNodes, costMatrix, distVector, nextHop);
        printRoutingTable(numNodes, distVector, nextHop);

        scanner.close();
    }
}
