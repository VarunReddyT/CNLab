import java.util.*;

public class dvr {
    static final int MAX_NODES = 10;
    static final int INF = 9999;

    public static void initialize(int[][] costMatrix, int[][] distanceVector, int[][] nextHop, int nodes){
        for(int i = 0;i<nodes;i++){
            for(int j = 0;j<nodes;j++){
                distanceVector[i][j] = costMatrix[i][j];
                if(costMatrix[i][j] != INF && i!=j){
                    nextHop[i][j] = j;
                }
                else{
                    nextHop[i][j] = -1;
                }
            }
        }
    }
    public static void distanceVectorRouting(int[][] costMatrix, int[][] distanceVector, int[][] nextHop, int nodes){
        boolean updated;
        do{
            updated = false;
            for(int i = 0;i<nodes;i++){
                for(int j = 0;j<nodes;j++){
                    for(int k = 0;k<nodes;k++){
                        if(distanceVector[i][k] + distanceVector[k][j] < distanceVector[i][j]){
                            distanceVector[i][j] = distanceVector[i][k] + distanceVector[k][j];
                            nextHop[i][j] = nextHop[i][k];
                            updated = true;
                        }
                    }
                }
            }
        }while(updated);
    }
    public static void printRoutingTable(int[][] distanceVector, int[][] nextHop, int nodes){
        for(int i = 0;i<nodes;i++){
            System.out.println("Routing table for node " + i + " : ");
            System.out.println("Destination\tNext Hop\tDistance");
            for(int j = 0;j<nodes;j++){
                if(distanceVector[i][j] == INF){
                    System.out.println(j + "\t\t-\t\tINF");
                }
                else{
                    System.out.println(j + "\t\t" + nextHop[i][j] + "\t\t" + distanceVector[i][j]);
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] costMatrix = new int[MAX_NODES][MAX_NODES];
        int[][] distanceVector = new int[MAX_NODES][MAX_NODES];
        int[][] nextHop = new int[MAX_NODES][MAX_NODES];

        System.out.print("Enter the number of the nodes : ");
        int nodes = sc.nextInt();

        System.out.println("Enter the cost matrix (use " + INF + " for INF) : ");
        for(int i = 0;i<nodes;i++){
            for(int j = 0;j<nodes;j++){
                costMatrix[i][j] = sc.nextInt();
            }
        }

        initialize(costMatrix,distanceVector,nextHop,nodes);
        distanceVectorRouting(costMatrix,distanceVector,nextHop,nodes);
        printRoutingTable(distanceVector,nextHop,nodes);

        sc.close();
    }
}
