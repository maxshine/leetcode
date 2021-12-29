class Solution {
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
