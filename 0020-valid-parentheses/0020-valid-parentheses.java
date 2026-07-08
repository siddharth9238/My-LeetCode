class Solution {
    public boolean isValid(String s) {
        // Allocate primitive array for our bare-metal stack
        char[] stack = new char[s.length()];
        int head = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack[head++] = ')';
            } else if (c == '{') {
                stack[head++] = '}';
            } else if (c == '[') {
                stack[head++] = ']';
            } else if (head == 0 || stack[--head] != c) {
                // If we hit a closing bracket but the stack is empty, 
                // or it doesn't match the expected bracket we pushed, fail fast.
                return false;
            }
        }
        
        // If the head pointer is back to 0, all brackets were perfectly closed
        return head == 0;
    }
}