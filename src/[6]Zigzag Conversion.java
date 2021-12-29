import java.lang.StringBuffer;

// @solution-sync:begin
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() == 0) {
            return s;
        }
        StringBuffer result = new StringBuffer();
        int doubleLen = 2 * numRows - 2;
        for (int i=0; i<numRows; i++) {
            for (int j=0; j+i < s.length(); j+=doubleLen) {
                result.append(s.charAt(i+j));
                if (i!=0 && i!=numRows-1 && j + doubleLen - i < s.length()) {
                    result.append(s.charAt(j+doubleLen-i));
                }
            }
        }
        return result.toString();
    }

    public String convert1(String s, int numRows) {
        if (numRows == 1 || s.length() == 0) {
            return s;
        }
        StringBuffer[] sbs = new StringBuffer[numRows];
        StringBuffer result = new StringBuffer();
        for (int i=0; i<numRows; i++) {
            sbs[i] = new StringBuffer();
        }
        int rowIdx = 0;
        boolean godown = false;
        for (int i=0; i<s.length(); i++) {
            sbs[rowIdx].append(s.charAt(i));
            if (rowIdx == 0 || rowIdx == numRows-1) {
                godown = !godown;
            }
            rowIdx += godown?1:-1;
        }
        for (int i=0; i<numRows; i++) {
            result.append(sbs[i]);
        }
        return result.toString();
    }
}