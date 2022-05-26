import java.util.*;
import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {

    HashMap<String, List<String>> adjList;
    List<String> currPath;
    List<List<String>> shortestPaths;

    List<String> findNeighbors(String word, List<String> wordList) {
        List<String> neighbors = new ArrayList<>();
        char[] sa = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char oldChar = sa[i];

            // replace the i-th character with all letters from a to z except the original character
            for (char c = 'a'; c <= 'z'; c++) {
                sa[i] = c;
                // skip if the character is same as original or if the word is not present in the wordList
                if (c == oldChar || !wordList.contains(new String(sa))) {
                    continue;
                }
                neighbors.add(new String(sa));
            }
            sa[i] = oldChar;
        }
        return neighbors;
    }

    void backtrack(String source, String destination) {
        // store the path if we reached the endWord
        if (source.equals(destination)) {
            shortestPaths.add(new ArrayList<>(currPath));
            return;
        }
        for (int i = 0; i < adjList.get(source).size(); i++) {
            currPath.add(adjList.get(source).get(i));
            backtrack(adjList.get(source).get(i), destination);
            currPath.remove(currPath.size()-1);
        }
    }

    void bfs(String beginWord, String endWord, List<String> wordList) {
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        // remove the root word which is the first layer
        if (wordList.contains(beginWord)) {
            wordList.remove(wordList.indexOf(beginWord));
        }

        HashMap<String, Integer> isEnqueued = new HashMap<>();
        isEnqueued.put(beginWord, 1);

        while (!q.isEmpty())  {
            // visited will store the words of current layer
            List<String> visited = new ArrayList<>();

            for (int i = q.size() - 1; i >= 0; i--) {
                String currWord = q.peek();
                q.poll();

                // findNeighbors will have the adjacent words of the currWord
                List<String> l = adjList.getOrDefault(currWord, new ArrayList<>());
                adjList.put(currWord, l);
                List<String> neighbors = findNeighbors(currWord, wordList);
                for (int k=0; k<neighbors.size(); k++) {
                    String word = neighbors.get(k);
                    visited.add(word);
                    // add the edge from currWord to word in the list
                    adjList.get(currWord).add(word);
                    if (!isEnqueued.containsKey(word)) {
                        q.add(word);
                        isEnqueued.put(word, 1);
                    }
                }
            }
            // removing the words of the previous layer
            for (int i = 0; i < visited.size(); i++) {
                if (wordList.contains(visited.get(i))) {
                    wordList.remove(visited.get(i));
                }
            }
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        adjList = new HashMap<>();
        currPath = new ArrayList<>();
        this.shortestPaths = new ArrayList<>();
        // copying the words into the set for efficient deletion in BFS
        List<String> copiedWordList = new ArrayList<>();
        copiedWordList.addAll(wordList);
        // build the DAG using BFS
        bfs(beginWord, endWord, copiedWordList);

        // every path will start from the beginWord
        currPath.add(beginWord);
        // traverse the DAG to find all the paths between beginWord and endWord
        backtrack(beginWord, endWord);

        return shortestPaths;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        List<List<String>> result = new Solution().findLadders(beginWord, endWord, wordList);
        System.out.println(toString(result));
    }

    private static String listToString(String[] array) {
        return listToString(Arrays.asList(array));
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

    private static String toString(List<List<String>> list) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0)
                buf.append(",");
            buf.append(listToString(list.get(i)));
        }
        buf.append("]");
        return buf.toString();
    }

}
