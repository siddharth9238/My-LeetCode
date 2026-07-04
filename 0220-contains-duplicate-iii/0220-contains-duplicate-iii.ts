function containsNearbyAlmostDuplicate(nums: number[], indexDiff: number, valueDiff: number): boolean {
    if (indexDiff <= 0 || valueDiff < 0) return false;

    // 2^18 capacity guarantees a max load factor of 0.38 (100,000 / 262,144) 
    // ensuring lightning-fast open addressing insertions without resizing.
    const capacity = 262144; 
    const mask = capacity - 1;
    const keys = new Float64Array(capacity).fill(NaN);
    const vals = new Int32Array(capacity);
    const idxs = new Int32Array(capacity);

    const bucketSize = valueDiff + 1;

    for (let i = 0; i < nums.length; i++) {
        const num = nums[i];
        const bucketId = Math.floor(num / bucketSize);

        // Check current bucket and immediately adjacent buckets
        for (let offset = -1; offset <= 1; offset++) {
            const targetId = bucketId + offset;
            
            // Inline MurmurHash3 Mix for 32-bit integers
            let h = targetId | 0;
            h ^= h >>> 16;
            h = Math.imul(h, 0x85ebca6b);
            h ^= h >>> 13;
            h = Math.imul(h, 0xc2b2ae35);
            h ^= h >>> 16;
            let hash = (h >>> 0) & mask;

            // keys[hash] === keys[hash] is the fastest NaN check in V8
            while (keys[hash] === keys[hash]) {
                if (keys[hash] === targetId) {
                    if (idxs[hash] >= i - indexDiff) {
                        const diff = num - vals[hash];
                        if ((diff >= 0 ? diff : -diff) <= valueDiff) {
                            return true;
                        }
                    }
                    break;
                }
                hash = (hash + 1) & mask;
            }
        }

        // Insert or update the current bucket
        let h = bucketId | 0;
        h ^= h >>> 16;
        h = Math.imul(h, 0x85ebca6b);
        h ^= h >>> 13;
        h = Math.imul(h, 0xc2b2ae35);
        h ^= h >>> 16;
        let hash = (h >>> 0) & mask;

        while (keys[hash] === keys[hash]) {
            if (keys[hash] === bucketId) {
                break; // Found existing, prepare to overwrite
            }
            hash = (hash + 1) & mask;
        }
        
        keys[hash] = bucketId;
        vals[hash] = num;
        idxs[hash] = i;
    }

    return false;
}