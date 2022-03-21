import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<String,Boolean> mp = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        if(s1.length()!=s2.length())return false;
        return solve(s1,s2);
    }
    public boolean solve(String s1,String s2){
        String key=s1+" "+s2;
        if(mp.containsKey(key))return mp.get(key);
        if(s1.equals(s2)){
            mp.put(key, true);
            return true;
        }
        if(s1.length()<=1){
            mp.put(key, false);
            return false;
        }
        int n = s1.length();
        boolean flag = false;
        for(int i=1;i<=n-1;i++){
            boolean c1= (solve(s1.substring(0,i),s2.substring(n-i,n))
                    && solve(s1.substring(i,n),s2.substring(0,n-i)));
            boolean c2= (solve(s1.substring(0,i),s2.substring(0,i))
                    && solve(s1.substring(i,n),s2.substring(i,n)));

            if(c1 || c2){
                flag = true;
                break;
            }
        }
        mp.put(key,flag);
        return flag;
    }
    public boolean isScrambleRecursive(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if(n != m) return false;
        if(n == 0) return true;
        if(s1.equals(s2)) return true;

        int[] freq = new int[26];
        for(int i = 0; i < n; i++){
            freq[s1.charAt(i) - 97]++;
            freq[s2.charAt(i) - 97]--;
        }
        // if freq[i] != 0  => different characters, hence node equality condition is violated.
        for(int i = 0; i < n; i++)
            if(freq[s1.charAt(i) - 97] != 0) return false;

        // The following loop has conditions similar to what we use for mirror trees.
        for(int i = 1; i < n; i++){
            if(isScrambleRecursive(s1.substring(0,i),s2.substring(0,i))
                    && isScrambleRecursive(s1.substring(i),s2.substring(i)))
                return true;
            if(isScrambleRecursive(s1.substring(0,i),s2.substring(n - i))
                    && isScrambleRecursive(s1.substring(i),s2.substring(0,n - i)))
                return true;
        }
        return false;
    }
}