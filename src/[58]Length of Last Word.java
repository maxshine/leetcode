class Solution {
    public int lengthOfLastWordMine(String s) {
        int lastWordLength = 0;
        int temp = lastWordLength;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (temp > 0) {
                    lastWordLength = temp;
                }
                temp = 0;
            } else {
                temp++;
            }
        }
        lastWordLength = temp>0?temp:lastWordLength;
        return lastWordLength;
    }
    public int lengthOfLastWord(String s) {
        int lastWordLength = 0;
        int p = s.length();
        while (p>0) {
            p--;
            if (s.charAt(p) != ' ') {
                lastWordLength++;
            } else if (lastWordLength>0) {
                return lastWordLength;
            }
        }
        return lastWordLength;


    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        String s = "luffy is still joyboy";

        int result = new Solution().lengthOfLastWord(s);
        System.out.println(result);
    }

}
