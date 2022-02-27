class Solution {
    public int[] plusOneMine(int[] digits) {
        int carry = 1;
        for (int i=digits.length-1; i>=0; i--) {
            int sum = digits[i] + carry;
            carry = sum / 10;
            digits[i] = sum % 10;
        }
        if (carry == 0) {
            return digits;
        }
        int[] ans = new int[digits.length+1];
        ans[0] = carry;
        for (int i=1; i<ans.length; i++) {
            ans[i] = digits[i-1];
        }
        return ans;
    }
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i=digits.length-1; i>=0; i--) {
            if (digits[i] == 0) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }
        int[] ans = new int[n+1];
        ans[0] = 1;
        return ans;
    }
}