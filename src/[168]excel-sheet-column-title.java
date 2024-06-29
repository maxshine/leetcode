import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    public String convertToTitle(int columnNumber) {
        int mode = 26;
        int start = 65;
        StringBuilder ans = new StringBuilder();

        while (columnNumber > 0) {
            ans.append((char)((columnNumber - 1) % mode + start));
            columnNumber = (columnNumber - 1) / 26;
        }
        return ans.reverse().toString();
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int columnNumber = 701;

        String result = new Solution().convertToTitle(columnNumber);
        System.out.println(result);
    }

}
