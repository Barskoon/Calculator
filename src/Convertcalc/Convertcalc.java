package Convertcalc;

public class Convertcalc {

    public static void main(String[] args) {
        String temp = "";
        InFix a = new InFix("1/3");
        if (!a.hasError()) {
            PostFix b = new PostFix(a.getValue());
            temp = b.getValue();

            if (!b.hasError()) {
                double d = Double.parseDouble(temp);
                temp = (int) d == d ? String.valueOf((int) d) : String.valueOf(d);
            } else {
                temp = b.getValue();
            }
        } else temp = a.getValue();
    }
}
// This class convert normal notation to Reverse Polish notation.
class InFix {
    private String input = "";
    private String output = "";
    private boolean error = false;
    public InFix(String input) {
        this.input = input;
        try {
            this.calculate();
        } catch (ArrayIndexOutOfBoundsException e) {
            error = true;
            output = "Max length is 100";
        }
    }

    public void calculate() {
        char[] in = input.toCharArray();
        Stack stack = new Stack(100);

        for (int i = 0; i < in.length; i++) {
            switch (in[i]) {
                case '*':
                case '/':
                case '+':
                case '-':
                    output += " ";
                    try {
                        stack.push(in[i]);
                    } catch (UnsupportedOperationException e){
                        output += stack.pop();
                        stack.push(in[i]);
                    }
                    output += " ";
                    break;
                default:
                    output += in[i];
                    break;
            }
        }
        while (!stack.isEmpty()) {
            output += " ";
            output += stack.pop();
        }
    }

    public boolean hasError(){
        return error;
    }
    // Getter.
    public String getValue(){
        return output;
    }

    // Char Stack.
    private class Stack {
        private char[] stackArray;
        private int top;
        private int maxSize;
        private char element;
        private String prior1 = "*/";
        private String prior2 = "+-";


        public Stack(int maxSize) {
            this.maxSize = maxSize;
            stackArray = new char[maxSize];
            top = -1;
        }

        public boolean isFull() {
            return maxSize == -1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void push(char element) {
            this.element = element;
            if (isFull()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (isEmpty()) {
                stackArray[++top] = element;
            } else {
                if (prior1.contains("" + stackArray[top]) && prior2.contains("" + element)) {
                    throw new UnsupportedOperationException();
                } else stackArray[++top] = element;
            }
        }

        public char pop() {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            return stackArray[top--];
        }
    }
}

// This class get input in Reverse Polish notation and calculate.
class PostFix {
    private String input = "";
    private boolean error = false;
    private Stack stack = new Stack(100);

    public PostFix(String in) {
        this.input = in;
        try{
            this.calculate();
        } catch (ArithmeticException e){
            error = true;
            this.input = "Divide by zero";
        } catch (IllegalArgumentException e){
            error = true;
            this.input = "Incorrect input";
        } catch (ArrayIndexOutOfBoundsException e){
            error = true;
            this.input = "Max length is 100";
        } catch (RuntimeException e){
            error = true;
            this.input = "Last input is not num";
        }
    }

    public boolean hasError(){
        return error;
    }

    void calculate() {
        double doubledVal;
        input.replaceAll("  ", " ");
        String[] value = input.split(" ");

        for(String s : value) {
            try {
                doubledVal  = Double.parseDouble(s);
                stack.push(doubledVal);
            } catch (Exception e) {
                switch (s) {
                    case "+": {
                        stack.push(stack.pop() + stack.pop());
                        break;
                    }
                    case "-": {
                        stack.push((stack.pop() - stack.pop()) * -1);
                        break;
                    }
                    case "*": {
                        stack.push(stack.pop() * stack.pop());
                        break;
                    }
                    case "/": {
                        double temp = stack.pop(), temp1 = stack.pop();
                        temp1 /= temp;
                        stack.push(temp1);
                        break;
                    }
                }
            }
        }
        if (stack.size() == 0) {
            doubledVal = stack.pop();
        } else {
            throw new IllegalArgumentException();
        }
        if (doubledVal == Double.POSITIVE_INFINITY || doubledVal == Double.NEGATIVE_INFINITY || Double.isNaN(doubledVal)) {
            throw new ArithmeticException();
        }
            input = "" + doubledVal;
    }
    // Getter.
    public String getValue(){
        return input;
    }

    // String Stack.
    private class Stack {
        private double[] stackArray;
        private int top;
        private int maxSize;

        public Stack(int maxSize) {
            this.maxSize = maxSize;
            stackArray = new double[maxSize];
            top = -1;
        }
        public boolean isFull() {
            return  maxSize == - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void push(double element) {
            if(isFull()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            stackArray[++top] = element;
        }

        public double pop() {
            if(isEmpty()) {
                throw new RuntimeException();
            }
            return stackArray[top--];
        }

        public int size() {
            return (top);
        }
    }
}
