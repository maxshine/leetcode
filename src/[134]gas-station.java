class Solution {
    // Mine
    public int canCompleteCircuitMine(int[] gas, int[] cost) {
        for (int i=0; i<=gas.length-1; i++) {
            int remainder = 0;
            for (int j=0; j<=gas.length-1; j++) {
                remainder += gas[(i+j)%gas.length]-cost[(i+j)%gas.length];
                if (remainder >= 0 && j==gas.length-1) {
                    return i;
                }
                if (remainder < 0) {
                    break;
                }
            }
        }
        return -1;
    }
    // Single loop
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;
        int currentTank = 0;
        int startIndex = 0;
        for (int i=0; i<=gas.length-1; i++) {
            totalTank += gas[i] - cost[i];
            currentTank += gas[i] - cost[i];
            if (currentTank < 0) {
                startIndex = i + 1;
                currentTank = 0;
            }
            
        }
        return totalTank>=0?startIndex:-1;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};

        int result = new Solution().canCompleteCircuit(gas, cost);
        System.out.println(result);
    }

}
