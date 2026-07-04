function getSkyline(buildings: number[][]): number[][] {
    const n = buildings.length;
    const hHeight = new Int32Array(10005);
    const hRight = new Int32Array(10005);
    let sz = 0;
    
    const res: number[][] = [];
    let i = 0;
    let curH = 0;
    
    while (i < n || sz > 0) {
        let curX = 0;
        
        if (sz === 0) {
            curX = buildings[i][0];
        } else if (i < n && buildings[i][0] <= hRight[0]) {
            curX = buildings[i][0];
        } else {
            curX = hRight[0];
        }
        
        while (i < n && buildings[i][0] === curX) {
            let idx = sz++;
            hHeight[idx] = buildings[i][2];
            hRight[idx] = buildings[i][1];
            
            while (idx > 0) {
                const p = (idx - 1) >> 1;
                if (hHeight[idx] > hHeight[p]) {
                    const tmpH = hHeight[idx]; 
                    hHeight[idx] = hHeight[p]; 
                    hHeight[p] = tmpH;
                    
                    const tmpR = hRight[idx]; 
                    hRight[idx] = hRight[p]; 
                    hRight[p] = tmpR;
                    
                    idx = p;
                } else {
                    break;
                }
            }
            i++;
        }
        
        while (sz > 0 && hRight[0] <= curX) {
            sz--;
            if (sz > 0) {
                hHeight[0] = hHeight[sz];
                hRight[0] = hRight[sz];
                let idx = 0;
                
                while ((idx << 1) + 1 < sz) {
                    let max = idx;
                    const left = (idx << 1) + 1;
                    const right = left + 1;
                    
                    if (hHeight[left] > hHeight[max]) {
                        max = left;
                    }
                    if (right < sz && hHeight[right] > hHeight[max]) {
                        max = right;
                    }
                    
                    if (max !== idx) {
                        const tmpH = hHeight[idx]; 
                        hHeight[idx] = hHeight[max]; 
                        hHeight[max] = tmpH;
                        
                        const tmpR = hRight[idx]; 
                        hRight[idx] = hRight[max]; 
                        hRight[max] = tmpR;
                        
                        idx = max;
                    } else {
                        break;
                    }
                }
            }
        }
        
        const newH = sz > 0 ? hHeight[0] : 0;
        if (newH !== curH) {
            res.push([curX, newH]);
            curH = newH;
        }
    }
    
    return res;
}