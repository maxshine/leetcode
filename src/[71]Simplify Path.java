import java.util.Stack;

class Solution {
    public String simplifyPath(String path) {
        Stack<String> paths = new Stack<>();
        String[] components = path.split("/");
        
        for (String s : components) {
            if (s.equals("/") || s.equals(".") || s.isEmpty()) {
                continue;
            } else if (s.equals("..")) {
                if (!paths.empty()) {
                    paths.pop();
                }
            } else {
                paths.push(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : paths) {
            sb.append("/");
            sb.append(s);
        }
        return sb.length()==0?"/":sb.toString();
    }
}