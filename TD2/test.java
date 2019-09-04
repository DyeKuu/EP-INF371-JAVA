class test{
    int head;
    test tail;
    test (int n, test l){
        head = n; tail = l;
    }
    public static void main(String[] args){
        test b = new test(1,null);
        test a = b;
        while (true){
            b.tail = new test(1,null);
            b = b.tail;
            b.tail = new test(1,null);
            b = b.tail;
            a = a.tail;
        }
    }
}