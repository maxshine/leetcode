import java.util.HashMap;

class Solution {
    private static final HashMap<Character, Integer> romanMap = new HashMap<Character, Integer>() {{
       this.put('M', 1000);
       this.put('D', 500);
       this.put('C', 100);
       this.put('L', 50);
       this.put('X', 10);
       this.put('V', 5);
       this.put('I', 1);
    }};
    public int romanToInt(String s) {
        char[] symbolsArray = s.toCharArray();
        if (symbolsArray.length == 0) {
            return 0;
        }
        Character lastSymbol = symbolsArray[symbolsArray.length-1];
        int lastValue = romanMap.get(lastSymbol);
        int total = lastValue;

        for (int i = s.length() - 2; i >= 0; i--) {
            Character currentSymbol = symbolsArray[i];
            int currentValue = romanMap.get(currentSymbol);
            if (currentValue < lastValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }
            lastValue = currentValue;
        }
        return total;
    }
    public int romanToInt2(String s) {
        int result = 0;
        char[] symbolsArray = s.toCharArray();
        int i = 0;
        while (i < symbolsArray.length) {
            if (i<symbolsArray.length-1 && romanMap.get(symbolsArray[i])<romanMap.get(symbolsArray[i+1])) {
                result = result + romanMap.get(symbolsArray[i+1]) - romanMap.get(symbolsArray[i]);
                i = i+2;
            } else {
                result = result + romanMap.get(symbolsArray[i]);
                i = i+1;
            }
        }
        return result;
    }
}