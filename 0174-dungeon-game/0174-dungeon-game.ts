function calculateMinimumHP(dungeon: number[][]): number {
    const m = dungeon.length;
    const n = dungeon[0].length;
    const dp = new Int32Array(n + 1);
    
    dp.fill(2147483647);
    dp[n - 1] = 1;
    
    for (let i = m - 1; i >= 0; i--) {
        const row = dungeon[i];
        for (let j = n - 1; j >= 0; j--) {
            let needed = (dp[j] < dp[j + 1] ? dp[j] : dp[j + 1]) - row[j];
            dp[j] = needed <= 0 ? 1 : needed;
        }
    }
    
    return dp[0];
}