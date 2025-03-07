package Lab9;

import java.util.Arrays;

public class Lab09_2_660019 {
    public static void main(String[] args) {
        int n = 7;
        int c = 19;
        int[] w = {4, 10, 6, 9, 8, 9, 8};
        int[] p = {11, 15, 14, 7, 16, 8, 15};

        System.out.println("N : " + n);
        System.out.println("C : " + c);
        System.out.println("W : " + Arrays.toString(w));
        System.out.println("P : " + Arrays.toString(p));
        
        knapsack_01(n, c, w, p);
    }

    public static void knapsack_01(int n, int C, int[] w, int[] p) {
        int[][] matrix = new int[n + 1][C + 1];

        // Set row 0 to 0
        for (int i = 0; i <= C; i++) {
            matrix[0][i] = 0;
        }

        // Fill DP Table
        for (int j = 1; j <= n; j++) {
            for (int d = 0; d <= C; d++) {
                if (w[j - 1] <= d) {
                    matrix[j][d] = Math.max(matrix[j - 1][d - w[j - 1]] + p[j - 1], matrix[j - 1][d]);
                } else {
                    matrix[j][d] = matrix[j - 1][d];
                }
            }
        }

        // Print DP Table
        for (int i = 0; i <= C; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int a = 0; a <= n; a++) {
            System.out.print(a);
            for (int b = 0; b <= C; b++) {
                System.out.print("\t" + matrix[a][b]);
            }
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }

        // Tracking selected items
        trackItems(matrix, w, p, n, C);
    }

    public static void trackItems(int[][] matrix, int[] w, int[] p, int n, int C) {
        System.out.println("\nSelected Items:");

        int j = n, d = C;
        while (j > 0 && d > 0) {
            if (matrix[j][d] != matrix[j - 1][d]) {
                System.out.println("Item " + j + " (Weight: " + w[j - 1] + ", Profit: " + p[j - 1] + ")");
                d -= w[j - 1];
            }
            j--;
        }
    }
}
