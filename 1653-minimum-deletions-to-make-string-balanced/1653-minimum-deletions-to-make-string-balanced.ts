function minimumDeletions(s: string): number {
    let bCount = 0;
    let deletions = 0;
    const n = s.length;
    
    for (let i = 0; i < n; i++) {
        if (s.charCodeAt(i) === 98) {
            bCount++;
        } else {
            const nextDeletions = deletions + 1;
            deletions = nextDeletions < bCount ? nextDeletions : bCount;
        }
    }
    
    return deletions;
}