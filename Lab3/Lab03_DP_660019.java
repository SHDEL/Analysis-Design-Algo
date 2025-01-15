package Lab3;

public class Lab03_DP_660019 {
    public static void main(String[] args) {
        sub1();
    }
    static int minOperationsMatrixChain(int [] p, int n) {
        int [][] cost = new int[n][n]; // row 0 and col 0 will not be used
        int i,j,k,L,numOps;
        for (i = 0; i < n; i++) {
            cost[i][i] = 0; // cost is zero when multiplying one matrix.
        }
        for (L = 2; L < n; L++) { // L is chain length
            // if (L > 2){
            //     break;
            // }
            System.out.println("-------Round L " + L + " ---------");
            for (i = 1; i < n - L + 1; i++) {
                j = i + L - 1;
                /* your code */
                int min = Integer.MAX_VALUE;
                    for (k = i ; k <= j - 1; k++){
                        System.out.println("--------Round k " + k + "-----------");
                        numOps = (cost[i][k] + cost[k + 1][j]) + (p[i - 1] * p[k] * p[j]);
                        System.out.println("numops: " + numOps);
                        System.out.println("min: " + min);
                        if (numOps < min){
                            cost[i][j] = numOps;
                            min = numOps;
                        }
                        
                    }
            }
        }
        System.out.println("cache content = ");
        for (int r = 1; r < n; r++) {
            for (int c = 1; c < n; c++)
                System.out.print(cost[r][c] + "\t");
            System.out.println();
        }
        return cost[1][n-1];
    }
    static void sub1() {
        // A1 4x10 A2 10x3 A3 3x12 A4 12x20
        int [] P = {4,10,3,12,20};
        System.out.println( minOperationsMatrixChain(P, P.length) );
    }
}
