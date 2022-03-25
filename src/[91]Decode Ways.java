import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
    int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int two_back = 1;
        int one_back = 1;

        for (int i = 1; i < n; i++) {
            int current = 0;
            if (s.charAt(i) != '0') {
                current = one_back;
            }
            int two_digit = Integer.parseInt(s.substring(i - 1, i+1));
            if (two_digit >= 10 && two_digit <= 26) {
                current += two_back;
            }

            two_back = one_back;
            one_back = current;
        }
        return one_back;
    }

    int numDecodingsWithDP(String s) {
        // DP array to store the subproblem results
        int[] dp = new int[s.length()+1];
        dp[0] = 1;

        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i < dp.length; i++) {
            // Check if successful single digit decode is possible.
            if (s.charAt(i-1) != '0') {
                dp[i] = dp[i - 1];
            }

            // Check if successful two digit decode is possible.
            int two_digit = Integer.parseInt(s.substring(i - 2, i));
            if (two_digit >= 10 && two_digit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    int recursiveWithMemo(int index, String str) {
        // Have we already seen this substring?
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        // If you reach the end of the string
        // Return 1 for success.
        if (index == str.length()) {
            return 1;
        }

        // If the string starts with a zero, it can't be decoded
        if (str.charAt(index) == '0') {
            return 0;
        }

        if (index == str.length() - 1) {
            return 1;
        }

        int ans = recursiveWithMemo(index + 1, str);
        if (Integer.parseInt(str.substring(index, index+2)) <= 26) {
            ans += recursiveWithMemo(index + 2, str);
        }

        // Save for memoization
       memo.put(index, ans);

        return ans;
    }

    int numDecodingsWithMemo(String s) {
        return recursiveWithMemo(0, s);
    }

    public int numDecodingsMine(String s) {
        int sum = 0;
        if (numDecodingsHelper(s.substring(0, 1)) == 1) {
            sum = sum + numDecodingsSummer(s.substring(1));;
        }
        if (s.length()>1 && numDecodingsHelper(s.substring(0, 2)) == 1) {
            sum = sum + numDecodingsSummer(s.substring(2));
        }
        return sum;
    }

    int numDecodingsHelper(String s) {
        if (s.length() == 0) {
            return 1;
        }
        int temp = Integer.parseInt(s);
        if (temp >=1 && temp <= 26) {
            return 1;
        }
        return 0;
    }

    int numDecodingsSummer(String s) {
        if (s.length() <= 1) {
            return numDecodingsHelper(s);
        }
        return (numDecodingsHelper(s.substring(0,1)) * numDecodingsSummer(s.substring(1)))
                + (numDecodingsHelper(s.substring(0,2)) * numDecodingsSummer(s.substring(2)));
    }
}