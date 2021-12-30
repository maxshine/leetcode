class Solution {
    public boolean isPalindrome(int x) {
        int revertNumber = 0;
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        while (revertNumber < x) {
            revertNumber = revertNumber * 10 + x % 10;
            x = x / 10;
        }
         return (revertNumber == x || revertNumber / 10 == x); 
    }
}