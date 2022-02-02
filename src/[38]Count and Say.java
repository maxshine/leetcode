import java.lang.StringBuffer;

// @solution-sync:begin
class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String lastCountAndSay = countAndSay(n-1);
        int cnt = 1;
        StringBuffer result = new StringBuffer();
        char[] sa = lastCountAndSay.toCharArray();
        int i;
        for (i=1; i<sa.length; i++) {
            if (sa[i] != sa[i-1]) {
                result.append(String.valueOf(cnt)).append(sa[i-1]);
                cnt = 1;
            } else {
                cnt++;
            }
        }
        result.append(String.valueOf(cnt)).append(sa[i-1]);
        return result.toString();
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int n = 4;

        String result = new Solution().countAndSay(n);
        System.out.println(result);
    }

}
