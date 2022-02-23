import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public boolean isNumberRule(String s) {
        boolean digitSeen = false;
        boolean dotSeen = false;
        boolean exponentSeen = false;
        
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-' || c == '+') {
                if (i>0 && (s.charAt(i-1) != 'e' && s.charAt(i-1) != 'E')) {
                    return false;
                }
            } else if (Character.isDigit(c)) {
                digitSeen = true;
            } else if (c == 'e' || c == 'E') {
                if (exponentSeen || !digitSeen) {
                    return false;
                }
                exponentSeen = true;
                digitSeen = false;
            } else if (c == '.') {
                if (dotSeen || exponentSeen) {
                    return false;
                }
                dotSeen = true;
            } else {
                return false;
            }
        }
        return digitSeen;
    }

    // This is the DFA we have designed above
    private static final List<Map<String, Integer>> dfa = List.of(
            Map.of("digit", 1, "sign", 2, "dot", 3),
            Map.of("digit", 1, "dot", 4, "exponent", 5),
            Map.of("digit", 1, "dot", 3),
            Map.of("digit", 4),
            Map.of("digit", 4, "exponent", 5),
            Map.of("sign", 6, "digit", 7),
            Map.of("digit", 7),
            Map.of("digit", 7)
    );

    // These are all of the valid finishing states for our DFA.
    private static final Set<Integer> validFinalStates = Set.of(1, 4, 7);

    public boolean isNumberDFA(String s) {
        int currentState = 0;
        String group = "";

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                group = "digit";
            } else if (curr == '+' || curr == '-') {
                group = "sign";
            } else if (curr == 'e' || curr == 'E') {
                group = "exponent";
            } else if (curr == '.') {
                group = "dot";
            } else {
                return false;
            }

            if (!dfa.get(currentState).containsKey(group)) {
                return false;
            }

            currentState = dfa.get(currentState).get(group);
        }

        return validFinalStates.contains(currentState);
    }
}