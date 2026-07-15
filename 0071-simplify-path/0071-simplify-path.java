class Solution {
    public String simplifyPath(String path) {
        int len = path.length();
        // The output can never exceed the length of the input string
        char[] res = new char[len];
        int top = 0;
        
        int i = 0;
        while (i < len) {
            // 1. Skip consecutive slashes
            while (i < len && path.charAt(i) == '/') {
                i++;
            }
            
            if (i == len) break;
            
            // 2. Find the bounds of the next directory segment
            int start = i;
            while (i < len && path.charAt(i) != '/') {
                i++;
            }
            int end = i;
            int segmentLen = end - start;
            
            // 3. Evaluate the segment
            if (segmentLen == 1 && path.charAt(start) == '.') {
                // Current directory: do nothing
            } else if (segmentLen == 2 && path.charAt(start) == '.' && path.charAt(start + 1) == '.') {
                // Parent directory: Simulate a 'pop' by moving the pointer backward
                if (top > 0) {
                    // Retreat until we hit the slash of the previous directory
                    while (top > 0 && res[top - 1] != '/') {
                        top--;
                    }
                    // Remove the slash itself to leave the buffer clean for the next append
                    if (top > 0) {
                        top--;
                    }
                }
            } else {
                // Valid directory or file name: 'push' to the buffer
                res[top++] = '/';
                for (int j = start; j < end; j++) {
                    res[top++] = path.charAt(j);
                }
            }
        }
        
        // If the pointer retreated all the way to 0, we are at the root
        if (top == 0) {
            return "/";
        }
        
        // Return exactly the populated segment of the buffer
        return new String(res, 0, top);
    }
}