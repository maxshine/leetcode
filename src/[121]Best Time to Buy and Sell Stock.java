class Solution {
    
    // mine
    public int maxProfitMine(int[] prices) {
        int maxProfit = 0;
        int minCost = prices[0];
        for (int i=1; i<prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i]-minCost);
            minCost = Math.min(minCost, prices[i]);
        }
        return maxProfit;
    }
    
    // one pass
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}