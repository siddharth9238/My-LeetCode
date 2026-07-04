function shortestPalindrome(s: string): string {
    const n = s.length;
    if (n <= 1) {
        return s;
    }

    const lps = new Int32Array(n);
    let len = 0;
    let i = 1;

    while (i < n) {
        if (s.charCodeAt(i) === s.charCodeAt(len)) {
            lps[i++] = ++len;
        } else if (len !== 0) {
            len = lps[len - 1];
        } else {
            lps[i++] = 0;
        }
    }

    let j = 0;
    let k = n - 1;
    
    while (k >= 0) {
        if (s.charCodeAt(j) === s.charCodeAt(k)) {
            j++;
            k--;
        } else if (j !== 0) {
            j = lps[j - 1];
        } else {
            k--;
        }
    }

    if (j === n) {
        return s;
    }

    return s.substring(j).split('').reverse().join('') + s;
}