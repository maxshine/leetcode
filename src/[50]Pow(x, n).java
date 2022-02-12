class Solution {
    public double myPowBrutal(double x, int n) {
        double ans = 1.0;
        long N = n;
        if (x == 0.0 || x == 1.0) {
            return x;
        }

        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        for (int i=0; i<N; i++) {
            ans = ans * x;
        }
        return ans;
    }

    public double recursivePow(double x, long N) {
        if (N == 0) {
            return 1;
        }
        if (N == 1) {
            return x;
        }
        double half = recursivePow(x, N/2);
        if (N % 2 == 0) {
            return half * half;
        }
        return half * half * x;
    }
    public double myPowRecursive(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return recursivePow(x, N);
    }

    public double myPow(double x, int n) {
        long N = n;
        double ans = 1.0;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double current = x;
        for (long i=N; i>0; i/=2) {
            if (i % 2 == 1) {
                ans *= current;
            }
            current *= current;
        }
        return ans;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        double x = 2.0;
        int n = 10;
        //int n = -2147483648;

        double result = new Solution().myPow(x, n);
        System.out.println(result);
    }
}
