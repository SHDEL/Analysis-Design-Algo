package Lab9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Lab09_660019 {
    public static void main(String[] args) {
        int itemsize[] = {2, 6, 5, 7, 7, 8, 6, 7};
        int binCapacity = 10;
        int n = itemsize.length;
        int m = 4;
        
        System.out.println("-------------First-Fit-------------");
        firstFit(itemsize, n, binCapacity);
        System.out.println("-------------Best-Fit-------------");
        bestFit(itemsize, n, binCapacity);
        System.out.println("-------------First-Fit Decreasing-------------");
        firstFitDecreasing(itemsize, n, binCapacity);
        System.out.println("-------------Job Scheduling-------------");
        jobSchedule(itemsize, m);

        
    }
    public static void firstFit(int itemsize[], int n, int binCapacity){

        ArrayList<ArrayList<Integer>> bins = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean placed = false;
            
            for (ArrayList<Integer> bin : bins) {
                int sum = 0;
                for (int item : bin) {
                    sum += item;
                }
                if (sum + itemsize[i] <= binCapacity) {
                    bin.add(itemsize[i]);
                    placed = true;
                    break;
                }
            }
            
            if (!placed) {
                ArrayList<Integer> newBin = new ArrayList<>();
                newBin.add(itemsize[i]);
                bins.add(newBin);
            }
        }
        
        for (int i = 0; i < bins.size(); i++) {
            int total = 0;
            for (int item : bins.get(i)) {
                total += item;
            }
            System.out.println("Bin " + (i + 1) + ": " + bins.get(i) + " (Total: " + total + ")");
        }
        
    }
    static void bestFit(int itemsize[], int n, int binCapacity) {
        ArrayList<ArrayList<Integer>> bins = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int bestIdx = -1, minSpaceLeft = binCapacity;
            
            for (int j = 0; j < bins.size(); j++) {
                int sum = 0;
                for (int item : bins.get(j)) {
                    sum += item;
                }
                int spaceLeft = binCapacity - sum;
                
                if (itemsize[i] <= spaceLeft && spaceLeft < minSpaceLeft) {
                    bestIdx = j;
                    minSpaceLeft = spaceLeft;
                }
            }
            
            if (bestIdx != -1) {
                bins.get(bestIdx).add(itemsize[i]);
            } else {
                ArrayList<Integer> newBin = new ArrayList<>();
                newBin.add(itemsize[i]);
                bins.add(newBin);
            }
        }
        
        for (int i = 0; i < bins.size(); i++) {
            int total = 0;
            for (int item : bins.get(i)) {
                total += item;
            }
            System.out.println("Bin " + (i + 1) + ": " + bins.get(i) + " (Total: " + total + ")");
        }

    }
    public static void firstFitDecreasing(int itemsize[], int n, int binCapacity) {
        Arrays.sort(itemsize);
        int[] sortedWeights = new int[n];
        for (int i = 0; i < n; i++) {
            sortedWeights[i] = itemsize[n - 1 - i];
        }
        
        firstFit(sortedWeights, n, binCapacity);
    }
    public static void jobSchedule(int[] jobs, int m) {
        ArrayList<ArrayList<Integer>> machines = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            machines.add(new ArrayList<>());
        }
        Integer[] sortedJobs = Arrays.stream(jobs).boxed().toArray(Integer[]::new);
        Arrays.sort(sortedJobs, Collections.reverseOrder());
        for (int job : sortedJobs) {
            int minIndex = 0;
            int minSum = machines.get(0).stream().mapToInt(Integer::intValue).sum();
            for (int i = 1; i < m; i++) {
                int currentSum = machines.get(i).stream().mapToInt(Integer::intValue).sum();
                if (currentSum < minSum) {
                    minSum = currentSum;
                    minIndex = i;
                }
            }
            machines.get(minIndex).add(job);
        }
        for (int i = 0; i < m; i++) {
            int runtimeTotal = machines.get(i).stream().mapToInt(Integer::intValue).sum();
            System.out.print("P" + (i + 1) + ": " + machines.get(i) + " Runtime Total: " + runtimeTotal + "\n");
        }
    }

}
