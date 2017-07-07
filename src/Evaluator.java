/**
 * Created by Zach P on 7/5/2017.
 *
 * Class created to store and evaluate arithmetic expression strings
 */
public class Evaluator {

    private String expression;
    private int position = -1;
    private int currentChar;

    public Evaluator(String expression) {
        this.expression = expression.replaceAll("\\s","");
    }

    /**
     * Increments currentChar member variable along the expression string
     */
    private void increment() {
        ++this.position;
        if (this.position < this.expression.length())
            this.currentChar = this.expression.charAt(this.position);
        else
            this.currentChar = -1;
    }

    /**
     * Checks if currentChar is same as passed char
     * @param nextChar
     * @return true if equal, false otherwise
     */
    private boolean nextIs(int nextChar) {
        if (this.currentChar == nextChar) {
            this.increment();
            return true;
        }
        return false;
    }

    /**
     * Parses arithmetic expression and evaluates
     * @return final sum as double value
     */
    double parse() {
        this.increment();
        return this.parseAddSub();
    }

    /**
     * Parses addition and subtraction operators
     * @return value after operation
     */
    private double parseAddSub() {
        double val = this.parseMultDiv();
        while (true) {
            if (this.nextIs('+'))
                val += this.parseMultDiv();
            else if (this.nextIs('-'))
                val -= this.parseMultDiv();
            else
                return val;
        }
    }

    /**
     * Parses multiplication and division operators
     * @return value after operation
     */
    private double parseMultDiv() {
        double val = this.parseParen();
        while (true) {
            if (this.nextIs('*'))
                val *= this.parseParen();
            else if (this.nextIs('/'))
                val /= this.parseParen();
            else
                return val;
        }
    }

    /**
     * Parses initial operator, parentheses and confirms other characters are valid
     * @return value after operation/parsing
     */
    private double parseParen() {
        double value;

        if (this.nextIs('-'))
            return -this.parseParen();
        if (this.nextIs('(')) {
            value = parseAddSub();
            this.nextIs(')');
        } else if ((this.currentChar >= '0' && this.currentChar <= '9') || this.currentChar == '.') {
            String tempNum = "";
            while ((this.currentChar >= '0' && this.currentChar <= '9') || this.currentChar == '.') {
                tempNum += (char)this.currentChar;
                this.increment();
            }
            value = Double.parseDouble(tempNum);
        } else {
            throw new RuntimeException("Error. Invalid character.");
        }
        return value;
    }
}
