import java.util.Arrays;
import java.util.List;
import java.lang.StringBuilder;

// @solution-sync:begin
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4); 
 */

class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    char[] prevBuf = new char[4];
    int prevSize = 0;
    int prevIndex = 0;

    public int read(char[] buf, int n) {
        int counter = 0;

        while (counter < n) {
            if (prevIndex < prevSize) {
                buf[counter++] = prevBuf[prevIndex++];
            } else {
                prevSize = read4(prevBuf);
                prevIndex = 0;
                if (prevSize == 0) {
                    // no more data to consume from stream
                    break;
                }
            }
        }
        return counter;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        String buf = "abc";
        int[] n = new int[]{1, 2, 1};

        int result = new Solution().read(buf, n);
        System.out.println(listToString(result));
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

}
