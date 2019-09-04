class Node{
    String head;
    Node next;

    Node (String head, Node next){
        this.head = head;
        this.next = next;
    }

    
    static int length_rec(Node l){
        if (l == null) return 0;
        else return 1+length_rec(l.next);
    }

    static int length(Node l){
        if (l == null) return 0;
        int length = 1;
        while (l.next != null) {
            l = l.next;
            length++;
        }
        return length;
    }

    static String printNodes(Node l){
        String result = "[";
        while (l != null){
            result += l.head;
            if (l.next != null) result += ", ";
            l = l.next;
        }
        result += "]";
        return result;
    }

    static void addLast(String s, Node l){
        while (l.next != null) l = l.next;
        l.next = new Node (s,null);
    }

    Node copy(){
        Node newlist = new Node(head, next);
        return newlist;
    }

    static Node insert(String s, Node l){
        if (l == null) return new Node(s,null);
        Node newlist = l.copy(), curr = newlist;
        if (s.compareTo(l.head) < 0) {
            newlist = new Node (s,newlist);
            return newlist;
        }
        while (curr.next != null && s.compareTo(curr.next.head) >= 0) curr = curr.next;
        curr.next = new Node(s,curr.next);
        return newlist;
    }

    static Node insertionSort(Node l){
        Node newlist = null, curr = l;
        if (l == null) return newlist;
        newlist = new Node(l.head,null);
        while (curr.next != null ){
            curr = curr.next;
            newlist = insert(curr.head,newlist);
        }
        return newlist;
    }

    static Node merge(Node l1, Node l2){
        Node p = l1, q = l2, newlist;
        if (p == null) return l2;
        if (q == null) return l1;
        if (p.head.compareTo(q.head) < 0) {
            newlist = new Node(p.head,null);
            p = p.next;
        }
        else{
            newlist = new Node(q.head,null);
            q = q.next;
        }

        while(p != null && q != null){
            if (p.head.compareTo(q.head) < 0) {
                addLast(p.head, newlist);
                p = p.next;
            }
            else {
                addLast(q.head, newlist);
                q = q.next;
            }
        }
        while (p != null) {
            addLast(p.head, newlist);
            p = p.next;
        }
        while (q != null) {
            addLast(q.head, newlist);
            q = q.next;
        }
        return newlist;
    }
    
    static Node mergeSort(Node l){
        int len = length(l);
        if (len <= 2) return(insertionSort(l));
        int num = len/2;
        Node curr = l;
        for (int i = 1; i<= num; i++) curr = curr.next;
        Node right = curr.next.copy();
        curr.next = null;
        Node left = l;
        Node newleft = mergeSort(left);
        Node newright = mergeSort(right);
        return merge(newleft, newright);
    }
    public static void main(String[] args){
        Node foobar = new Node("foo",new Node("bar", new Node("baz", null)));
        //Node test = new Node("bka",new Node("akb", new Node("kba", null)));
        Node single = new Node("akb",null);
        //foobar = foobar.next;
        //foobar = null;
        //Node haha = foobar.copy();
        //String s1 = "abc", s2 = "abc";
        Node a = insertionSort(single);
        //System.out.println(printNodes(insertionSort(test)));
        //System.out.println(printNodes(insertionSort(foobar)));
        System.out.println(printNodes(a));
    }
}
