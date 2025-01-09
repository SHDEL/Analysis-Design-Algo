package Lab1;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_660019 {
    public static void main(String[] args) {
        int data [][] = {{3,3}, {5,-1}, {-2,4}};
        int k = 1;
        int ans [][] = kClosest(data, k);

        System.out.println(Arrays.deepToString(ans));
    }
    public static int[][] kClosest(int[][] points, int k) {
        // return pq(points,k);s
        return partition_caller(points, k);
    }
    private static int[][] pq(int [][] points, int K) {
        // expected runtime is ______
        int [][] ans = new int[K][2];
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        /* your code */
        for (int i = 0; i < points.length; i++) {
            heap.offer(points[i]);
            if (heap.size() == K + 1){
                heap.poll();
            }
        }
        for (int i = 0; i < ans.length; i++){
            ans[i] = heap.poll();
        }
        return ans;
    }
    private static int[][] partition_caller(int [][] points, int K) {
        // expected average runtime is ______
        int left = 0, right = points.length - 1;
        while (left <= right) {
            int pivot = partition(points, left, right);
            if (pivot < K){
                left = pivot + 1;
            }
            else if (K < pivot){
                right = pivot - 1;
            }
            else{
                break;
            }
            
        }
        return Arrays.copyOfRange(points, 0, K);
    }
    private static int partition(int [][] points, int left, int right) {
        /* your code */
        int pivotIndex = right;
        int[] pivot = points[pivotIndex];

        for (int i = left; i <= right; i++) {
            if (compare(points[i], pivot) < 0) {
                pivotIndex = i;
                pivot = points[pivotIndex];
            }
        }
        int[] temp = points[left];
        points[left] = points[pivotIndex];
        points[pivotIndex] = temp;
        return left; 
    }
    private static int compare(int [] p1, int [] p2) {
        return (p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
    }
}
