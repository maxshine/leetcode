class Solution {
    int myAtoiRule(String input) {
        int sign = 1;
        int result = 0;
        int index = 0;
        int n = input.length();

        // Discard all spaces from the beginning of the input string.
        while (index < n && input.charAt(index) == ' ') {
            index++;
        }

        // sign = +1, if it's positive number, otherwise sign = -1.
        if (index < n && input.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < n && input.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        // Traverse next digits of input and stop if it is not a digit.
        // End of string is also non-digit character.
        while (index < n && Character.isDigit(input.charAt(index))) {
            int digit = input.charAt(index) - '0';

            // Check overflow and underflow conditions.
            if ((result > Integer.MAX_VALUE / 10) || (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // Append current digit to the result.
            result = 10 * result + digit;
            index++;
        }

        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        return sign * result;
    }
    public int myAtoi(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int result = 0;
        int sign = 0;
        char[] ca = s.toCharArray();
        int i = 0;
        char c=ca[i];
        while (i < ca.length) {
            if (!Character.isWhitespace(c)) {
                i = i>0?i-1:i;
                break;
            }
            c = ca[i];
            i++;
        }
        while (i<ca.length) {
            c = ca[i];
            i++;
            if (sign == 0 && c == '+') {
                sign = 1;
                continue;
            } else if (sign == 0 && c == '-'){
                sign = -1;
                continue;
            }
            if (c < '0' || c > '9') {
                break;
            }
            if (sign == 0) {
                sign = 1;
            }
            if (result > Integer.MAX_VALUE / 10 && sign == 1 || result == Integer.MAX_VALUE/10 && c > '7' && sign == 1) {
                return Integer.MAX_VALUE;
            }
            if (result > Integer.MAX_VALUE / 10 && sign == -1 || result == Integer.MAX_VALUE/10 && c > '8' && sign == -1) {
                return Integer.MIN_VALUE;
            }
            result = result * 10 + (c - '0');
        }
        return sign * result;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        String s = "42";

        int result = new Solution().myAtoi(s);
        System.out.println(result);
    }

}
