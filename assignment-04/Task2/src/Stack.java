public class Stack {
    private char[] chars;
    private int amount = 0;

    public Stack(int size){
        chars = new char[size];
    }

    public boolean empty(){
        return (amount == 0);
    }

    public boolean full(){
        return (amount == chars.length);
    }

    public void push(char c){
        if(full()) return;

        chars[amount++] = c;
    }

    public char pop(){
        if(empty()) return '_';

        return chars[--amount];
    }

    public char checkStack(){
        if(empty()) return '_';

        return chars[amount - 1];
    }
}
