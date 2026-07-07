function sumAndMultiply(n: number): number {
    let x = 0;
    let sum = 0;
    let multiplier = 1;
    
    while (n > 0) {
        const digit = n % 10;
        
        if (digit !== 0) {
            x += digit * multiplier;
            multiplier *= 10;
            sum += digit;
        }
        
        // Bitwise OR 0 is the fastest way to truncate floats to ints in JS
        n = (n / 10) | 0; 
    }
    
    return x * sum;
}