class Solution {
    public boolean isMatch(String s, String p) {

    }
    public boolean isMatch2(String s, String p) {
        boolean[][] result = new boolean[s.length()+1][p.length()+1];
        result[s.length()][p.length()] = true;
        for (int i=s.length(); i>=0; i--) {
            for (int j=p.length()-1; j>=0; j--) {
                boolean firstmatch = (i<s.length()) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                if (j+1<p.length() && p.charAt(j+1)=='*') {
                    result[i][j] = result[i][j+2] || firstmatch && result[i+1][j];
                } else {
                    result[i][j] = firstmatch && result[i+1][j+1];
                }
            }
        }
        return result[0][0];
    }

    public boolean isMatch1(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean firstmatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length()>=2 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2)) || firstmatch && isMatch(s.substring(1), p));
        } else {
            return firstmatch && (isMatch(s.substring(1), p.substring(1)));
        }
    }
}