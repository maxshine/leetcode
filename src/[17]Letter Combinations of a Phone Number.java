import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// @solution-sync:begin
class Solution {
    List<String> combination = new LinkedList<String>();
    Map<Character, String> keyboard = new HashMap<Character, String>() {{
        this.put('2', "abc");
        this.put('3', "def");
        this.put('4', "ghi");
        this.put('5', "jkl");
        this.put('6', "mno");
        this.put('7', "pqrs");
        this.put('8', "tuv");
        this.put('9', "wxyz");
    }};
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return combination;
        }
        backtrace(digits, 0, new StringBuffer());
        return combination;
    }
    
    void backtrace(String digits, int index, StringBuffer path) {
        if (index == digits.length()) {
            combination.add(path.toString());
            return;
        }
        String keys = keyboard.get(digits.charAt(index));
        for (int i=0; i<keys.length(); i++) {
            path.append(keys.charAt(i));
            backtrace(digits, index+1, new StringBuffer(path));
            path.deleteCharAt(path.length() - 1);
        }
    }
}