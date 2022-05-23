import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    //Mine
    public boolean isPalindromeMine(String s) {
        String temp = s.toLowerCase();
        char[] sa = temp.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : sa) {
            if (Character.isAlphabetic(c)) {
                sb.append(c);
            }
        }
        s = sb.toString();
        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
        int left, right;
        if (s.length() % 2 == 0) {
            right = s.length() / 2 ;
            left = right - 1;
        } else {
            right = (s.length() + 1) / 2;
            left = right - 2;
        }

        while (left >=0 && right <= s.length()-1) {
            if (s.charAt(left--) != s.charAt(right++)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i)))
                i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j)))
                j--;

            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
        }

        return true;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        s = "0P";

        boolean result = new Solution().isPalindrome(s);
        System.out.println(result);
    }

}
