import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {

    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new LinkedList<String>();
        backtrace(new StringBuilder(), 0, 0, combinations, n);
        return combinations;
    }
    void backtrace(StringBuilder current, int open, int close, List<String> result, int max) {
        if (current.length() == 2*max) {
            result.add(current.toString());
            return;
        }
        if (open < max) {
            current.append('(');
            backtrace(current, open+1, close, result, max);
            current.deleteCharAt(current.length()-1);
        }
        if (close < open) {
            current.append(')');
            backtrace(current, open, close+1, result, max);
            current.deleteCharAt(current.length()-1);
        }
    }
    
    
    public List<String> generateParenthesis1(int n) {
        List<String> combinations = new LinkedList<String>();
        generateAll(new char[2*n], 0, combinations);
        return combinations;
    }
    void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (isValid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }
    boolean isValid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int n = 3;

        List<String> result = new Solution().generateParenthesis(n);
        System.out.println(listToString(result));
    }

    private static String listToString(List<String> list) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0)
                buf.append(",");
            buf.append(list.get(i));
        }
        buf.append("]");
        return buf.toString();
    }

}
