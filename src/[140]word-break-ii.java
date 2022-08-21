import java.util.*;

// @solution-sync:begin
class Solution {
    // Topdown
    protected Set<String> wordSet;
    protected HashMap<String, List<List<String>>> memo;

    public List<String> wordBreak1(String s, List<String> wordDict) {
        wordSet = new HashSet<>();
        for (String word : wordDict) {
            wordSet.add(word);
        }
        memo = new HashMap<String, List<List<String>>>();

        _wordBreak_topdown(s);

        // chain up words together
        List<String> ret = new ArrayList<String>();
        for (List<String> words : memo.get(s)) {
            StringBuffer sentence = new StringBuffer();
            for (String word : words) {
                sentence.insert(0, word);
                sentence.insert(0, " ");
            }
            ret.add(sentence.toString().strip());
        }

        return ret;
    }

    protected List<List<String>> _wordBreak_topdown(String s) {
        if (s.equals("")) {
            List<List<String>> solutions = new ArrayList<List<String>>();
            solutions.add(new ArrayList<String>());
            return solutions;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        } else {
            List<List<String>> solutions = new ArrayList<List<String>>();
            memo.put(s, solutions);
        }

        for (int endIndex = 1; endIndex <= s.length(); ++endIndex) {
            String word = s.substring(0, endIndex);
            if (wordSet.contains(word)) {
                List<List<String>> subsentences = _wordBreak_topdown(s.substring(endIndex));
                for (List<String> subsentence : subsentences) {
                    List<String> newSentence = new ArrayList<String>(subsentence);
                    newSentence.add(word);
                    memo.get(s).add(newSentence);
                }
            }
        }
        return memo.get(s);
    }

    // Bottom up

    private void updateCharSet(String s, HashSet<Character> charSet) {
        for (int i = 0; i < s.length(); ++i)
            charSet.add(s.charAt(i));
    }

    public List<String> wordBreak2(String s, List<String> wordDict) {
        HashSet<Character> stringCharSet = new HashSet<Character>();
        updateCharSet(s, stringCharSet);

        HashSet<Character> wordCharSet = new HashSet<Character>();
        wordSet = new HashSet<>();
        for (String word : wordDict) {
            wordSet.add(word);
            updateCharSet(word, wordCharSet);
        }

        // quick check on the sets of characters
        if (!wordCharSet.containsAll(stringCharSet))
            return new ArrayList();

        ArrayList<ArrayList<String>> dp = new ArrayList<ArrayList<String>>(s.length() + 1);
        for (int i = 0; i < s.length() + 1; ++i) {
            ArrayList<String> emptyList = new ArrayList<String>();
            dp.add(emptyList);
        }
        dp.get(0).add("");

        for (int endIndex = 1; endIndex < s.length() + 1; ++endIndex) {
            ArrayList<String> sublist = new ArrayList<String>();

            // fill up the values in the dp array.
            for (int startIndex = 0; startIndex < endIndex; ++startIndex) {
                String word = s.substring(startIndex, endIndex);
                if (wordSet.contains(word))
                    for (String subsentence : dp.get(startIndex))
                        sublist.add((subsentence + " " + word).strip());
            }
            dp.set(endIndex, sublist);
        }

        return dp.get(s.length());
    }
    
    // 3
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<Character> stringCharSet = new HashSet<Character>();
        updateCharSet(s, stringCharSet);

        HashSet<Character> wordCharSet = new HashSet<Character>();
        wordSet = new HashSet<>();
        for (String word : wordDict) {
            wordSet.add(word);
            updateCharSet(word, wordCharSet);
        }

        // quick check on the sets of characters
        if (!wordCharSet.containsAll(stringCharSet))
            return new ArrayList();

        ArrayList<ArrayList<ArrayList<Integer>>> dp =
                new ArrayList<ArrayList<ArrayList<Integer>>>(s.length() + 1);
        for (int i = 0; i < s.length() + 1; ++i) {
            ArrayList<ArrayList<Integer>> emptyList = new ArrayList<ArrayList<Integer>>();
            dp.add(emptyList);
        }
        ArrayList<Integer> start = new ArrayList<Integer>();
        start.add(0);
        dp.get(0).add(start);

        for (int endIndex = 1; endIndex < s.length() + 1; ++endIndex) {
            ArrayList<ArrayList<Integer>> stops = new ArrayList<ArrayList<Integer>>();

            // fill up the values in the dp array.
            for (int startIndex = 0; startIndex < endIndex; ++startIndex) {
                String word = s.substring(startIndex, endIndex);
                if (wordSet.contains(word)) {
                    for (List<Integer> subsentence : dp.get(startIndex)) {
                        ArrayList<Integer> copy = new ArrayList(subsentence);
                        copy.add(endIndex);
                        stops.add(copy);
                    }
                }
            }
            dp.set(endIndex, stops);
        }

        // reconstruct the words list from the positions of breaks/stops
        ArrayList<String> ret = new ArrayList<String>();
        for (List<Integer> stops : dp.get(s.length())) {
            StringBuffer sentence = new StringBuffer();
            for (int i = 0; i < stops.size() - 1; ++i) {
                sentence.append(s.substring(stops.get(i), stops.get(i + 1)) + " ");
            }
            ret.add(sentence.toString().strip());
        }

        return ret;
    }
}