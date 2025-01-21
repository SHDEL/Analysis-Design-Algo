package SmallestElement;

import java.util.Arrays;

public class NewFindSmallest {
    static int [] datal1;
    public static void main(String[] args) {
        int arr[] = {3, 2, 14, 15, 13, 17, 20, 8, 9, 10, 6, 15, 5, 33, 25, 13, 26, 19, 30, 33, 11, 35};
        int k = 3;
        int n = arr.length;
        int r = 1;
        System.out.println(Select(arr, k, n, 1));
                
    }
    public static int Select(int [] arr, int k, int n, int round){
        int group = n / 5;
        int [][] data  = new int[group][5];
        int tmpidx = 0;
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[0].length; j++){
                if (tmpidx < n){
                    data[i][j] = arr[tmpidx];
                }
                tmpidx += 1;
            }
        }
        System.out.println("-------Round" + round + "-------");
        for (int [] a : data){
            System.out.println(Arrays.toString(a));
        }
        for (int[] a : data){
            Arrays.sort(a);
        }
        
        System.out.println("-----------------------");
        for (int [] a : data){
            System.out.println(Arrays.toString(a));
        }
        for (int i = 0; i < data.length - 1; i++){
            if (data[i][data[i].length / 2] > data[i + 1][data[i + 1].length / 2]){
                // System.out.println("med present: " + data[i][data[i].length / 2]);
                // System.out.println("med next: " + data[i][data[i].length / 2]);
                int [] tmp = data[i];
                data[i] = data[i + 1];
                data[i + 1] = tmp;

            }
        }
        System.out.println("----------After Sort-------------");
        for (int [] a : data){
            System.out.println(Arrays.toString(a));
        }
        int medofmed = data[medOfMed(n) - 1][5 / 2];
        System.out.println("MedofMed: " + medofmed);
        

        int cntL1 = countL1(data, medofmed);
        int rofmed = findR(data, medofmed);
        System.out.println("countL1: " + cntL1);
        datal1 = new int [cntL1];
        makeL1(data, medofmed);
        System.out.println(Arrays.toString(datal1));

        if ((cntL1 < k) && (k <= cntL1 + rofmed)){
            return medofmed;
        }
        else if (k <= cntL1){
            Select(datal1, k, cntL1, round + 1);
        }

        
        return 1;
    }
    public static int medOfMed(int n){
        int med = (n / 5) / 2;

        return med;
    }
    public static int countL1(int[][] data, int medofmed){
        int cnt = 0;
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[0].length; j++){
                if (data[i][j] < medofmed){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public static void makeL1(int [][] data, int medofmed){
        int idx = 0;
        for (int i = 0; i < data.length; i++){
            int tmp = 0;
            for (int j = 0; j < data[0].length; j++){
                if (data[i][j] < medofmed){
                    tmp = data[i][j];
                    datal1[idx] = tmp;
                    tmp = 0;
                    idx++;
                }
            }
        }
    }
    public static int findR(int [][] data, int medofmed){
        int cnt = 0;
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[0].length; j++){
                if (data[i][j] == medofmed){
                    cnt++;
                }
            }
        }
        return cnt;
    }

}
