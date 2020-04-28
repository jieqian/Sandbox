package com.sandbox.algorithm.greedy.stockexchange;

public class Sandbox {

    public static Integer[] stockPrices = new Integer[]{7,1,5,3,6,4}; // element is the price of stock, the index is the date

    public static void main(String[] args) {
        int biggestProfit = getBiggestProfit(stockPrices);
        int maxProfit = getMaxProfit(stockPrices);
        System.out.println("the biggest profit is " + biggestProfit);
        System.out.println("the max profit is " + maxProfit);
    }

    // buy and sell once
    public static int getBiggestProfit(Integer[] price){
        int minPrice = Integer.MAX_VALUE;
        int biggestProfit = 0;
        for (int i=0; i < price.length; i++) {
            if (price[i] < minPrice){
                minPrice = price[i];
            } else if (price[i] - minPrice > biggestProfit){
                biggestProfit = price[i] - minPrice;
            }
        }

        return biggestProfit;
    }

    // no count limit for buy and sell once
    public static int getMaxProfit(Integer[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int value = 0;
        int yestoday = prices[0];
        for (int i = 0; i < prices.length; i++) {
            // 是否卖出？
            int today = prices[i];
            if (i > 0) {
                if (today > yestoday) {
                    value = value + today;
                }
                yestoday = today;
            }

            // 是否买入？
            if (i + 1 < prices.length) {
                int tomorrow = prices[i + 1];
                if (tomorrow > today) {
                    value = value - today;
                }
            }
        }
        return value;
    }
}
