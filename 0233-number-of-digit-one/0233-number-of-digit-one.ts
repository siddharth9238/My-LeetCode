function countDigitOne(n: number): number {
    if (n <= 0) {
        return 0;
    }
    
    let count = 0;
    let k = 1;
    
    while (k <= n) {
        const nextK = k * 10;
        const divider = Math.floor(n / nextK);
        const remainder = n % nextK;
        
        count += divider * k;
        
        if (remainder >= k) {
            count += remainder < k * 2 ? remainder - k + 1 : k;
        }
        
        k = nextK;
    }
    
    return count;
}