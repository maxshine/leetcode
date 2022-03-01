import java.util.ArrayList;
import java.util.List;

// @solution-sync:begin
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int length = 0;
        List<String> ans = new ArrayList<String>();
        List<String> lineWords = new ArrayList<String>();
        for (String w : words) {
            if (length + w.length() + (lineWords.size()-1) >= maxWidth) {
                formatLine(ans, lineWords, length, maxWidth, false);
                length = 0;
                lineWords.clear();
            }
            lineWords.add(w);
            length += w.length();
        }
        formatLine(ans, lineWords, length, maxWidth, true);
        return ans;
    }

    public void formatLine(List<String> ans, List<String> lineWords, int initialLength, int maxWidth, boolean lastLine) {
        int wordCount = lineWords.size();
        int spaceCount = wordCount==1?(maxWidth-initialLength):(maxWidth-initialLength) / (wordCount-1);
        int remainder = wordCount==1?0:(maxWidth-initialLength)%(wordCount-1);
        StringBuilder sb = new StringBuilder();
        if (lastLine) {
            spaceCount = 1;
            remainder = 0;
        }

        for (String w: lineWords) {
            sb.append(w);
            if (remainder > 0) {
                sb.append(' ');
                remainder--;
            }
            for (int i=0; i<spaceCount && sb.length()<maxWidth; i++) {
                sb.append(' ');
            }
        }
        for (; sb.length()<maxWidth;) {
            sb.append(' ');
        }
        ans.add(sb.toString());
    }

    public List<String> fullJustifyOld(String[] words, int maxWidth) {
        List<String> op = new ArrayList<String>();
        List<String> cWords = new ArrayList<String>();
        int length = 0;

        // Iterate and check if the current words exceed maxwidth.
        for(String w: words) {
            // LengthSoFar + new word length + gaps exceeds or equal to maxwidth
            if((length + w.length() + (cWords.size()-1)) >= maxWidth) {
                // Add paddings and move this to op
                indent(op, cWords, length, maxWidth, false);
                length = 0;
                cWords.clear();
            }

            length += w.length();
            cWords.add(w);
        }

        // Last line indentation to be left justified
        indent(op, cWords, length, maxWidth, true);

        return op;
    }

    private void indent(List<String> op, List<String> cWords, int wLength, int maxWidth, boolean lastLine) {
        // Find the number of spaces needed for maxwidth
        StringBuilder sb = new StringBuilder();
        int spaces = maxWidth - wLength;
        int wCount = cWords.size();
        int spaceBetween = (wCount == 1) ? spaces : spaces/(wCount-1);
        int rem = (wCount == 1) ? 0 : spaces%((wCount-1));

        // If last line, then reset the values to left justified
        if(lastLine) { spaceBetween = 1; rem = 0; }

        // Iterate from first word and append spaces and add this to op list
        for(String w: cWords) {
            sb.append(w);
            if(rem-- > 0) sb.append(" ");
            for(int i=0; i<spaceBetween && sb.length() < maxWidth; i++) sb.append(" ");
        }

        // if last line, then add the remaining characters as empty.
        for(int i=sb.length(); i<maxWidth; i++) sb.append(" ");

        op.add(sb.toString());
    }
}