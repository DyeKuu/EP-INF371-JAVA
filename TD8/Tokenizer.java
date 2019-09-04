class Tokenizer{
    boolean isStart;
    boolean isIntNum;
    double num;
    Calculator calc;
    boolean isNonIntNum;
    int decimalDigits;
    boolean isMinUnary;
    boolean isNeg;
    boolean fuckyou; //true if after a ( or +-*/
    boolean layle; //true if after $

    Tokenizer(){
        isStart = true;
        isIntNum = false;
        isNonIntNum = false;
        decimalDigits = 0;
        num = 0;
        calc = new Calculator();
        fuckyou = false;
        layle = false;
    }
    
    public void readChar(char c){
        if (c == 'C') {
            calc.commandInit();
            isStart = true;
            isIntNum = false;
            isNonIntNum = false;
            decimalDigits = 0;
            num = 0;
            fuckyou = false;
            layle = false;
        }
        else{
            if(Character.isDigit(c)){
                if (!isNonIntNum) num = num*10 + Character.getNumericValue(c);
                else{
                    decimalDigits++;
                    num += (double)Character.getNumericValue(c)*Math.pow(10, -decimalDigits);
                }
                isStart = false;
                isIntNum = true;
                fuckyou = false;
            }
            else{
                if (layle) calc.commandReadMemory((int)num);
                if (isMinUnary) num = -num;
                isNonIntNum = false;
                decimalDigits = 0;
                if (c == '+' || c == '-' || c == '*' || c == '/'){
                    if(isIntNum && !layle) calc.commandDouble(num);
                    switch(c){
                        case '+':calc.commandOperator(Operator.PLUS);break;
                        case '-':
                            if (isStart || fuckyou) isMinUnary = true;
                            else calc.commandOperator(Operator.MINUS);
                            break;
                        case '*':calc.commandOperator(Operator.MULT);break;
                        case '/':calc.commandOperator(Operator.DIV);break;
                    } 
                    num = 0;
                    isIntNum = false;
                    isStart =false;
                    fuckyou = true;
                    layle = false;
                }
                else{
                    if (c == '(' || c==')' || c== '$'){
                        switch (c){
                            case '(':
                                calc.commandLPar();
                                fuckyou = true;
                                break;
                            case ')':
                                fuckyou = false;
                                if (isIntNum && !layle)
                                calc.commandDouble(num);
                            calc.commandRPar();
                            layle = false;
                            break;
                        case '$':
                            layle = true;
                            break;
                        }
                        num = 0;
                        isIntNum = false;
                        isStart = false;
                    } else {
                        if (c == '.') isNonIntNum = true; 
                        else {
                            if (isIntNum && !layle) calc.commandDouble(num);
                            calc.commandEqual();
                            num = 0;
                            isIntNum = false;
                            isStart = true;
                        }
                        fuckyou = false;
                        layle = false;
                    }
                }
            }
        }
    }

    public void readString(String s){
        for(char c:s.toCharArray()) readChar(c);
    }
    public static void main(String[] args) {
        Tokenizer test = new Tokenizer();
        test.readString("5*8=7=9=$1+$2=");
        System.out.println(test.calc.getResult());
    }
}