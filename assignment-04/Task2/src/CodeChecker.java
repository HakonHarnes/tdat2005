public class CodeChecker {
    private static String startChars = "([{";
    private static String endChars   = ")]}";

    public static boolean checkCode(String code){
        Stack stack = new Stack(code.length());

        for(int i = 0; i < code.length(); i++){
            char c = code.charAt(i);

            if(startChars.contains(String.valueOf(c))) stack.push(c);

            if(endChars.contains(String.valueOf(c))){
                if(stack.empty()) return false;

                switch(c){
                    case ')': if(stack.pop() != '(') return false; break;
                    case '[': if(stack.pop() != '[') return false; break;
                    case '}': if(stack.pop() != '{') return false; break;
                }
            }
        }

        return stack.empty();
    }
}
