function maxProfit(k: number, prices: number[]): number {
    const n = prices.length;
    
    if (n <= 1 || k === 0) {
        return 0;
    }

    if (k >= n >> 1) {
        let profit = 0;
        for (let i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    const buy = new Int32Array(k + 1);
    const sell = new Int32Array(k + 1);
    
    buy.fill(-1000000);

    for (let i = 0; i < n; i++) {
        const p = prices[i];
        
        for (let j = 1; j <= k; j++) {
            const newBuy = sell[j - 1] - p;
            if (newBuy > buy[j]) {
                buy[j] = newBuy;
            }
            
            const newSell = buy[j] + p;
            if (newSell > sell[j]) {
                sell[j] = newSell;
            }
        }
    }

    return sell[k];
}