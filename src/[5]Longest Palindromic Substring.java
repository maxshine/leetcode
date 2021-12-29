class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return s;
        }
        int start=0;
        int end=0;
        for (int i=0; i<s.length(); i++) {
            int len1 = expandCenterAround(s, i, i);
            int len2 = expandCenterAround(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len-1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start, end+1);
    }

    public String longestPalindrome2(String s) {
        int start=0;
        int end=0;
        int len=0;
        if (s.length() == 0) {
            return s;
        }
        boolean[][] p = new boolean[s.length()][s.length()];
        for (int i=0; i<s.length(); i++) {
            p[i][i] = true;
            if (len < 1) {
                start=i;
                end=i;
                len=1;
            }
            if (i<s.length()-1 && s.charAt(i) == s.charAt(i+1)) {
                p[i][i+1] = true;
                start=i;
                end=i+1;
                len=2;
            }
        }
        for (int i=s.length()-1; i>=0; i--) {
            for (int j=i+2; j<s.length(); j++) {
                p[i][j] = p[i+1][j-1] && s.charAt(i) == s.charAt(j);
                if (p[i][j] && (j-i+1)>len) {
                    len = j-i+1;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end+1);
    }

    public int expandCenterAround(String s, int l, int r) {
        int L=l, R=r;
        while (L>=0 && R<=s.length()-1 && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R-L-1;
    }
}