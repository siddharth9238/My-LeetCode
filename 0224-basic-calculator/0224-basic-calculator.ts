function calculate(s: string): number {
    let sum = 0;
    let sign = 1;
    let p = 0;
    let stackPtr = 0;
    const len = s.length;
    const stack = new Int32Array(len); 
    
    while (p < len) {
        const c = s.charCodeAt(p);
        
        if (c === 32) {
            p++;
        } else if (c >= 48 && c <= 57) {
            let num = 0;
            while (p < len) {
                const d = s.charCodeAt(p);
                if (d >= 48 && d <= 57) {
                    num = num * 10 + (d - 48);
                    p++;
                } else {
                    break;
                }
            }
            sum += sign * num;
        } else if (c === 43) {
            sign = 1;
            p++;
        } else if (c === 45) {
            sign = -1;
            p++;
        } else if (c === 40) {
            stack[stackPtr++] = sum;
            stack[stackPtr++] = sign;
            sum = 0;
            sign = 1;
            p++;
        } else if (c === 41) {
            sign = stack[--stackPtr];
            const prevSum = stack[--stackPtr];
            sum = prevSum + sign * sum;
            p++;
        } else {
            p++;
        }
    }
    
    return sum;
}