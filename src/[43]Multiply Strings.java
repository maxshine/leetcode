import java.lang.StringBuffer;

// @solution-sync:begin
import java.util.ArrayList;
import java.util.List;

class Solution {

    String sumResultsElementMath(List<List<Integer>> results) {
        // Initialize answer as a number from results.
        List<Integer> answer = results.get(results.size()-1);

        results.remove(results.size()-1);

        // Sum each digit from answer and result
        for (List<Integer> result : results) {
            List<Integer> newAnswer = new ArrayList<Integer>();
            int carry = 0;

            for (int i = 0; i < answer.size() || i < result.size(); ++i) {
                // If answer is shorter than result or vice versa, use 0 as the current digit.
                int digit1 = i < result.size() ? result.get(i) : 0;
                int digit2 = i < answer.size() ? answer.get(i) : 0;
                // Add current digits of both numbers.
                int sum = digit1 + digit2 + carry;
                // Set carry equal to the tens place digit of sum.
                carry = sum / 10;
                // Append the ones place digit of sum to answer.
                newAnswer.add(sum % 10);
            }

            if (carry != 0) {
                newAnswer.add(carry);
            }
            answer = newAnswer;
        }

        // Convert answer to a string.
        StringBuffer finalAnswer = new StringBuffer();
        for (int digit : answer) {
            finalAnswer.append(digit);
        }
        return finalAnswer.toString();
    }

    // Multiply the current digit of secondNumber with firstNumber.
    List<Integer> multiplyOneDigitElementMath(String firstNumber, char secondNumberDigit, int numZeros) {
        // Insert zeros at the beginning based on the current digit's place.
        List<Integer> currentResult = new ArrayList<Integer>();
        int carry = 0;
        while (numZeros >0) {
            currentResult.add(0);
            numZeros--;
        }
        // Multiply firstNumber with the current digit of secondNumber.
        for (char firstNumberDigit : firstNumber.toCharArray()) {
            int multiplication = (secondNumberDigit - '0') * (firstNumberDigit - '0') + carry;
            // Set carry equal to the tens place digit of multiplication.
            carry = multiplication / 10;
            // Append last digit to the current result.
            currentResult.add(multiplication % 10);
        }

        if (carry != 0) {
            currentResult.add(carry);
        }
        return currentResult;
    }

    public String multiplyElementMath(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        if (num1.equals("1")) {
            return num2;
        }

        if (num2.equals("1")) {
            return num1;
        }

        // Reverse both numbers.
        String a = reverse(num1);
        String b = reverse(num2);

        // For each digit in secondNumber, multipy the digit by firstNumber and
        // store the multiplication result (reversed) in results.
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        for (int i = 0; i < b.length(); ++i) {
            results.add(multiplyOneDigitElementMath(a, b.charAt(i), i));
        }

        // Add all the results in the results array, and store the sum in the answer string.
        String answer = sumResultsElementMath(results);

        // answer is reversed, so reverse it to get the final answer.
        return reverse(answer);
    }
    // solution 2
    List<Integer> addStringsIterative(List<Integer> result1, List<Integer> result2) {
        List<Integer> answer = new ArrayList<Integer>();
        // Sum each digit from answer and result
        int carry = 0;
        for (int i = 0; i < result1.size() || i < result2.size(); ++i) {
            // If answer is shorter than result or vice versa, use 0 as the current digit.
            int digit1 = i < result1.size() ? result1.get(i) : 0;
            int digit2 = i < result2.size() ? result2.get(i) : 0;
            // Add current digits of both numbers.
            int sum = digit1 + digit2 + carry;
            // Set carry equal to the tens place digit of sum.
            carry = sum / 10;
            // Append the ones place digit of sum to answer.
            answer.add(sum % 10);
        }

        if (carry != 0) {
            answer.add(carry);
        }

        // Convert answer to a string.
        return answer;
    }

    // Multiply the current digit of secondNumber with firstNumber.
    List<Integer> multiplyOneDigitIterative(String firstNumber, char secondNumberDigit, int numZeros) {
        // Insert zeros at the beginning based on the current digit's place.
        List<Integer> currentResult = new ArrayList<Integer>();
        int carry = 0;
        while (numZeros >0) {
            currentResult.add(0);
            numZeros--;
        }
        // Multiply firstNumber with the current digit of secondNumber.
        for (char firstNumberDigit : firstNumber.toCharArray()) {
            int multiplication = (secondNumberDigit - '0') * (firstNumberDigit - '0') + carry;
            // Set carry equal to the tens place digit of multiplication.
            carry = multiplication / 10;
            // Append last digit to the current result.
            currentResult.add(multiplication % 10);
        }

        if (carry != 0) {
            currentResult.add(carry);
        }
        return currentResult;
    }

    public String multiplyIterative(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        if (num1.equals("1")) {
            return num2;
        }
        if (num2.equals("1")) {
            return num1;
        }
        // Reverse both numbers.
        String a = reverse(num1);
        String b = reverse(num2);

        // For each digit in secondNumber, multipy the digit by firstNumber and
        // store the multiplication result (reversed) in results.
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < b.length(); ++i) {
            results = addStringsIterative(results, multiplyOneDigitElementMath(a, b.charAt(i), i));
        }
        StringBuffer sb = new StringBuffer();
        for (int d : results) {
            sb.append(d);
        }

        // answer is reversed, so reverse it to get the final answer.
        return sb.reverse().toString();
    }

    public String reverse(String s) {
        StringBuffer sb = new StringBuffer(s);
        return sb.reverse().toString();
    }

    // Solution 3
    String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        
        if (num1.equals("1")) {
            return num2;
        }
        if (num2.equals("1")) {
            return num1;
        }

        // Reverse num1 and num2.
        num1 = reverse(num1);
        num2 = reverse(num2);

        // Initialize answer as a string of zeros of length N.
        int N = num1.length() + num2.length();
        int[] multiplications = new int[N];

        for (int place2 = 0; place2 < num2.length(); place2++) {
            int digit2 = num2.charAt(place2) - '0';

            // For each digit in num2 multiply the digit by all digits in num1.
            for (int place1 = 0; place1 < num1.length(); place1++) {
                int digit1 = num1.charAt(place1) - '0';

                // The number of zeros from multiplying to digits depends on the
                // place of digit2 in num2 and the place of the digit1 in num1.
                int numZeros = place1 + place2;

                // The digit currently at position numZeros in the answer string
                // is carried over and summed with the current result.
                int carry = multiplications[numZeros];
                int multiplication = digit1 * digit2 + carry;

                // Set the ones place of the multiplication result.
                multiplications[numZeros] = (multiplication % 10);

                // Carry the tens place of the multiplication result by
                // adding it to the next position in the answer array.
                multiplications[numZeros + 1] += (multiplication / 10);
            }
        }

        StringBuffer sb = new StringBuffer();
        boolean leadingZeroSkipped = false;
        for (int i=multiplications.length-1; i>=0; i--) {
            if (leadingZeroSkipped) {
                sb.append(multiplications[i]);
            } else if (!leadingZeroSkipped && multiplications[i] != 0) {
                leadingZeroSkipped = true;
                sb.append(multiplications[i]);
            }
        }
        return sb.toString();
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";

        String result = new Solution().multiply(num1, num2);
        System.out.println(result);
    }

}
