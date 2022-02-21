import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

// @solution-sync:begin
class Solution {
    public String getPermutation(int n, int k) {
        int[] factorials = new int[n];
        List<Integer> nums = new ArrayList() {{add(1);}};

        factorials[0] = 1;
        for(int i = 1; i < n; ++i) {
            // generate factorial system bases 0!, 1!, ..., (n - 1)!
            factorials[i] = factorials[i - 1] * i;
            // generate nums 1, 2, ..., n
            nums.add(i + 1);
        }

        // fit k in the interval 0 ... (n! - 1)
        --k;

        // compute factorial representation of k
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i > -1; --i) {
            int idx = k / factorials[i];
            k -= idx * factorials[i];

            sb.append(nums.get(idx));
            nums.remove(idx);
        }
        return sb.toString();
    }
    public void swapArray(int[] array, int src, int dest) {
        int temp = array[dest];
        while (dest > src) {
            array[dest] = array[dest-1];
            dest--;
        }
        array[src] = temp;
    }
    public String getPermutationMine(int n, int k) {
        int[] permutation = new int[n];
        int[] combinations = new int[n];
        for (int i=0;i<=n-1;i++) {
            permutation[i] = i+1;
            int j=n-i;
            int factorial = 1;
            while(j>0) {
                factorial *= j;
                j--;
            }
            combinations[i] = factorial;
        }
        int temp = combinations[0];
        int idx = 0;
        while(idx<combinations.length-1) {
            combinations[idx] = combinations[idx+1];
            idx++;
        }
        combinations[combinations.length-1] = -1;
        idx = 0;
        while(k>0) {
            while (k<=combinations[idx]) {
                idx++;
            }
            if (idx == combinations.length-1) {
                break;
            }
            int quotient = (k-1) / combinations[idx];
            if (quotient>0 && idx+quotient <= n-1) {
                swapArray(permutation, idx, idx+quotient);
            }
            k -= quotient*combinations[idx];
        }
        StringBuilder sb = new StringBuilder();
        for (int i: permutation) {
           sb.append(i);
        }
        return sb.toString();
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int n = 3;
        int k = 6;

        String result = new Solution().getPermutation(n, k);
        System.out.println(result);
    }

}
