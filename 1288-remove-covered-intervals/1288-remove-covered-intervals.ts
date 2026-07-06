function removeCoveredIntervals(intervals: number[][]): number {
    const n = intervals.length;
    const packed = new Float64Array(n);
    
    for (let i = 0; i < n; i++) {
        packed[i] = intervals[i][0] * 200000 + (200000 - intervals[i][1]);
    }
    
    packed.sort();
    
    let count = 0;
    let maxRight = -1;
    
    for (let i = 0; i < n; i++) {
        const r = 200000 - (packed[i] % 200000);
        
        if (r > maxRight) {
            count++;
            maxRight = r;
        }
    }
    
    return count;
}