import java.math.BigInteger;

class Solution {
    public String addBinaryMine(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;
        int sum = 0;
        while (i>=0 || j>=0) {
            sum = (i>=0?a.charAt(i--)-'0':0) + (j>=0?b.charAt(j--)-'0':0) + carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        sb.reverse();
        return sb.toString();
    }

    public String addBinary(String a, String b) {
        int n = a.length(), m = b.length();
        if (n < m) return addBinary(b, a);
        int L = Math.max(n, m);

        StringBuilder sb = new StringBuilder();
        int carry = 0, j = m - 1;
        for(int i = L - 1; i > -1; --i) {
            if (a.charAt(i) == '1') ++carry;
            if (j > -1 && b.charAt(j--) == '1') ++carry;

            if (carry % 2 == 1) sb.append('1');
            else sb.append('0');

            carry /= 2;
        }
        if (carry == 1) sb.append('1');
        sb.reverse();

        return sb.toString();
    }
    
    public String addBinaryBits(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        return x.toString(2);
    }
}