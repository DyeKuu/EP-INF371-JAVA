class Calculator{
    public java.util.Stack<Double> numbers;
    public java.util.Stack<Operator> operators;
    public java.util.LinkedList<Double> results;
    Calculator(){
        numbers = new java.util.Stack<Double>();
        operators = new java.util.Stack<Operator>();
        results = new java.util.LinkedList<Double>();
    }
    @Override
    public String toString(){
        return numbers.toString()+"\n"+operators.toString();
    }
    
    void pushDouble(double d){
        numbers.push(d);
    }

    void pushOperator(Operator o){
        operators.push(o);
    }

    public double getResult(){
        if (results.isEmpty()) throw new RuntimeException();
        else return results.getFirst();
    }

    void executeBinOperator( Operator op ){
        double left,right,result = 0;
        switch (op){
            case PLUS:
                right = numbers.pop();
                left = numbers.pop();
                result = left+right;
                break;
            case MINUS:
                right = numbers.pop();
                left = numbers.pop();
                result = left-right;
                break;
            case MULT:
                right = numbers.pop();
                left = numbers.pop();
                result = left*right;
                break;
            case DIV:
                right = numbers.pop();
                left = numbers.pop();
                result = left/right;
                break;
        }
        numbers.push(result);
    }

    private static int precedence(Operator op){
        switch(op){
            case PLUS: return 0;
            case MINUS: return 0;
            case MULT: return 1;
            case DIV: return 1;
            default: return -1;
        }
    }
    void commandOperator(Operator op){
        while(!operators.empty() && precedence(operators.peek())>=precedence(op)){
            executeBinOperator(operators.pop());
        }
        operators.push(op);
        
    }
    void commandDouble(double d){
        numbers.push(d);
    }
    void commandEqual(){
        while (operators.empty()==false){
            executeBinOperator(operators.pop());
        }
        results.addFirst(numbers.pop());
    }

    void commandLPar(){
        operators.push(Operator.OPEN);
    }
    void commandRPar(){
        while (operators.peek()!=Operator.OPEN){
            executeBinOperator(operators.pop());
        }
        operators.pop();
    }
    void commandInit(){
        numbers.clear();
        operators.clear();
    }

    void commandReadMemory(int i){
        numbers.push(results.get(i-1));
    }
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        cal.commandDouble(42);
        cal.commandOperator(Operator.PLUS);
        cal.commandLPar();
        cal.commandDouble(23);
        cal.commandOperator(Operator.MINUS);
        cal.commandDouble(8);
        cal.commandOperator(Operator.DIV);
        cal.commandDouble(2);
        cal.commandRPar();
        /*cal.pushDouble(1);
        cal.pushDouble(2);
        cal.pushDouble(3);
        cal.pushOperator(Operator.PLUS);
        cal.pushOperator(Operator.MULT);
        */
        System.out.println(cal.toString());
        cal.commandEqual();
        System.out.println(cal.toString());

    }
}