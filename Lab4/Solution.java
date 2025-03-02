package Lab4;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    static String direction = "DLRU"; // Down (+1,0) Left (0,-1) Right(0,1) Up(-1,0)
    static int [] rowwise = {1,0,0,-1};
    static int [] colwise = {0,-1,1,0};
    public static void main(String[] args) {
        sub_1();
    }
    static void sub_1() {
        System.out.println("sub_1");
        int [][] maze = {   {1,0,0,0}, // _ x x x
                            {1,1,0,1}, // _ _ x _
                            {1,1,0,0}, // _ _ x x
                            {0,1,1,1}}; // x _ _ _

        int n = maze.length;
        List<String> result = new ArrayList<>();
        StringBuilder currentPath = new StringBuilder();
        if (maze[0][0] != 0 && maze[n-1][n-1] != 0) {
            Solution s = new Solution();
            s.findPath(0, 0, maze, n, result, currentPath);
    
        }
        System.out.println(result);
        
    }

    private boolean isValid(int row, int col, int n, int [][] maze) {
        return row >= 0 && col >= 0 && row < n && col < n && maze[row][col] == 1;
    }
    public void findPath(int row, int col, int [][] maze, int n, List<String> ans, StringBuilder currentPath) {
    if (row == n -1 && col == n - 1) { // destination reached
        ans.add(currentPath.toString());
        return;
    }
    // mark current cell as blocked
    maze[row][col] = 0;
    for (int dir = 0; dir < 4; dir++) { // Down Left Right Up
        /* your code */
        int newRow = row + rowwise[dir];
        int newCol = col + colwise[dir];

            if (isValid(newRow, newCol, n, maze)) {
                currentPath.append(direction.charAt(dir));
                findPath(newRow, newCol, maze, n, ans, currentPath);
                currentPath.deleteCharAt(currentPath.length() - 1);
            }
    } // for
    
    // mark current cell as unblocked
    maze[row][col] = 1;
    }
}
