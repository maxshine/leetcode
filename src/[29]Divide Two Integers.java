import java.util.List;
import java.util.ArrayList;

// @solution-sync:begin
class Solution {
    int HALF_INT_MIN = -1073741824;
    public int divideBrutal(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int negative = 0;
        int quote = 0;
        if (dividend > 0) {
            negative ++;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negative ++;
            divisor = -divisor;
        }
        while (dividend <= divisor) {
            dividend -= divisor;
            quote++;
        }
        return negative==1?-quote:quote;
    }
    public int divideBinarySearch(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int negative = 0;
        int quote = 0;
        if (dividend > 0) {
            negative ++;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negative ++;
            divisor = -divisor;
        }
        while (divisor >= dividend) {
            int powerOfTwo = -1;
            int value = divisor;
            while (value >= HALF_INT_MIN && value + value > dividend) {
                value = value + value;
                powerOfTwo += powerOfTwo;
            }
            dividend -= value;
            quote += powerOfTwo;
        }

        return negative!=1?-quote:quote;
    }
    public int divideBinarySearchMemo(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int negative = 0;
        int quote = 0;
        if (dividend > 0) {
            negative ++;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negative ++;
            divisor = -divisor;
        }
        List<Integer> powerOfTwoSet = new ArrayList<Integer>();
        List<Integer> powerOfTwoValueSet = new ArrayList<Integer>();
        int powerOfTwo = -1;
        int value = divisor;
        while (value >= dividend) {
            powerOfTwoSet.add(powerOfTwo);
            powerOfTwoValueSet.add(value);
            if (value < HALF_INT_MIN) {
                break;
            }
            value += value;
            powerOfTwo += powerOfTwo;
        }
        for (int i=powerOfTwoValueSet.size()-1; i>=0; i--) {
            if (dividend<=powerOfTwoValueSet.get(i)) {
                dividend -= powerOfTwoValueSet.get(i);
                quote += powerOfTwoSet.get(i);
            }
        }
        return negative!=1?-quote:quote;
    }

    public int divideBinarySearchWithoutMemo(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int negative = 0;
        int quote = 0;
        if (dividend > 0) {
            negative ++;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negative ++;
            divisor = -divisor;
        }
        int highestDouble = divisor;
        int highestPowerOfTwo = -1;
        while (highestDouble >= HALF_INT_MIN && dividend <= highestDouble + highestDouble) {
            highestPowerOfTwo += highestPowerOfTwo;
            highestDouble += highestDouble;
        }

        /* In the second loop, we work out which powers of two fit in, by
         * halving highestDouble and highestPowerOfTwo repeatedly.
         * We can do this using bit shifting so that we don't break the
         * rules of the question :-) */
        int quotient = 0;
        while (dividend <= divisor) {
            if (dividend <= highestDouble) {
                quotient += highestPowerOfTwo;
                dividend -= highestDouble;
            }
            /* We know that these are always even, so no need to worry about the
             * annoying "bit-shift-odd-negative-number" case. */
            highestPowerOfTwo >>= 1;
            highestDouble >>= 1;
        }

        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negative != 1) {
            return -quotient;
        }
        return quotient;
    }
    public int divide(int dividend, int divisor) {
        // Special cases: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE;
        }

        /* We need to convert both numbers to negatives.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        /* We want to find the largest doubling of the divisor in the negative 32-bit
         * integer range that could fit into the dividend.
         * Note if it would cause an overflow by being less than HALF_INT_MIN,
         * then we just stop as we know double it would not fit into INT_MIN anyway. */
        int maxBit = 0;
        while (divisor >= HALF_INT_MIN && divisor + divisor >= dividend) {
            maxBit += 1;
            divisor += divisor;
        }

        int quotient = 0;
        /* We start from the biggest bit and shift our divisor to the right
         * until we can't shift it any further */
        for (int bit = maxBit; bit >= 0; bit--) {
            /* If the divisor fits into the dividend, then we should set the current
             * bit to 1. We can do this by subtracting a 1 shifted by the appropriate
             * number of bits. */
            if (divisor >= dividend) {
                quotient -= (1 << bit);
                /* Remove the current divisor from the dividend, as we've now
                 * considered this part. */
                dividend -= divisor;
            }
            /* Shift the divisor to the right so that it's in the right place
             * for the next position we're checking at. */
            divisor = (divisor + 1) >> 1;
        }

        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negatives != 1) {
            quotient = -quotient;
        }
        return quotient;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int dividend = -2147483648;
        int divisor = 1;

        int result = new Solution().divide(dividend, divisor);
        System.out.println(result);
    }

}
