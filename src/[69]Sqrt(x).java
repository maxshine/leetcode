class Solution {
    public int mySqrtCalculator(int x) {
        if (x<2) {
            return x;
        }
        int left = (int)Math.pow(Math.E, 0.5*Math.log(x));
        int right = left + 1;
        return right * right > x ? left : right;
    }

    public int mySqrtBinarySearch(int x) {
        if (x<2) {
            return x;
        }
        int left = 2;
        int right = x / 2;
        while (left <= right) {
            int pivot = left+(right-left)/2;
            int num = pivot * pivot;
            if (num > x) {
                right = pivot-1;
            } else if (num < x) {
                left = pivot + 1;
            } else {
                return pivot;
            }
        }
        return right;
    }
    public int mySqrtRecursive(int x) {
        if (x < 2) return x;

        int left = mySqrtRecursive(x >> 2) << 1;
        int right = left + 1;
        return (long)right * right > x ? left : right;
    }

    public int mySqrt(int x) {
        if (x < 2) return x;

        double x0 = x;
        double x1 = (x0 + x / x0) / 2.0;
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2.0;
        }

        return (int)x1;
    }
}