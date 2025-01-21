public class Recursion {
    static int ans = 0;
    public static void main(String[] args) {
        int [] nums = {3,6,5,1,8};
        int sum = 0;
        int idx = 0;
        maxSumOdd(nums, sum, idx);
        System.out.println("ans: " + ans);
    }
    public static void maxSumOdd(int [] nums, int sum, int idx){
        if (idx == nums.length){
            if (sum % 3 == 0 && sum > ans){
                ans = sum;
            }
            return;
        }
        
        maxSumOdd(nums, sum + nums[idx], idx + 1);
        maxSumOdd(nums, sum, idx + 1);
    }
}