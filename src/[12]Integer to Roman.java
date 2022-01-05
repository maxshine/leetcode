class Solution {
    private static final Integer[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final String[] thousands = {"", "M", "MM", "MMM"};
    private static final String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    public String intToRoman2(int num) {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<numbers.length; i++) {
            while (num >= numbers[i]) {
                num = num - numbers[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
    public String intToRoman(int num) {
        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 1000 % 100 / 10] + ones[num % 10];
    }
}