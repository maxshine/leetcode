import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    private Map<Character, Character> mapping = new HashMap<Character, Character>() {{
        this.put(')', '(');
        this.put(']', '[');
        this.put('}', '{');
    }};
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (mapping.containsKey(c)) {
                char d = stack.isEmpty()?'#': stack.pop();
                if (mapping.get(c) != d) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}