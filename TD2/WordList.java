class WordList{
    Node content;

    WordList(Node l){
        content = l;
    }
    WordList(){
        content = null;
    }
    WordList(String[] t){
        WordList newlist = new WordList();
        for (int i = 0; i< t.length; i++){
            newlist.addLast(t[i]);
        }
        content = newlist.content;
    }

    int length(){
        if (content == null) return 0;
        int result = 0;
        Node curr = content;
        while (curr != null){
            result++;
            curr = curr.next;
        }
        return result;
    }

    String print(){
        String result = "[";
        Node curr = content;
        while (curr != null){
            result += curr.head;
            if (curr.next != null) result += ", ";
            curr = curr.next;
        }
        result += "]";
        return result;
    }

    
    String randomfind(){
        int orderOfWord = (int)Math.floor(Math.random()*length());
        Node curr = content;
        String result = null;
        for (int i = 0; i<= orderOfWord ; i++){
            result = curr.head;
            curr = curr.next;
        }
        return result;
    }

    
    void printBook(){
        Node curr = content;
        while (curr != null){
            if (curr.head==Prefix.par) System.out.println();
            else System.out.print(curr.head+" ");
            curr = curr.next;
        }
    }
    

    void addFirst(String w){
        content = new Node(w,content); 
    }

    void addLast(String w){
        if (content == null) {
            content = new Node(w,content);
            return;
        }
        Node curr = content;
        while (curr.next != null) curr = curr.next;
        curr.next = new Node(w, null);
    }

    String removeFirst(){
        if (content == null) return null;
        String result = content.head;
        content = content.next;
        return result;
    }
    
    String removeLast(){
        if (content == null) return null;
        Node curr = content;
        String result;
        if (curr.next == null){
            result = curr.head;
            content = null;
            return result;
        }
        while (curr.next.next != null) curr = curr.next;
        result = curr.next.head;
        curr.next = null;
        return result;
    }
   
    void insert(String s){
        if (content == null || s.compareTo(content.head) < 0) {
            content = new Node(s,content);
            return;
        }
        Node curr = content;
        while (curr.next != null && s.compareTo(curr.next.head) >= 0) curr = curr.next;
        curr.next = new Node(s,curr.next);
    }

    void insertionSort(){
        if (content == null) return;
        WordList newlist = new WordList();
        while (content != null) {
            newlist.insert(content.head);
            content = content.next;
        }
        content = newlist.content;
        /*
        WordList newlist = new WordList(Node.insertionSort(content));
        content = newlist.content;
        */
        
    }
    
    void mergeSort(){
        WordList newlist = new WordList(Node.mergeSort(content));
        content = newlist.content;
    }
    
    String[] toArray(){
        String[] s = new String[length()];
        Node curr = content;
        for (int i = 0; i< length(); i++){
            s[i] = curr.head;
            curr = curr.next;
        }
        return s;
    }
    public static void main(String[] args){
        WordList foobar = new WordList(new Node("foo",new Node("foo", new Node("foa", null))));
        //foobar = new WordList();
        foobar.mergeSort();
        //String[] s = foobar.toArray();
        //foobar = new WordList();
        //foobar.addLast("akb");
        //foobar.insert("zoo");
        //WordList test = new WordList(s);
        //System.out.println(s[]);
        System.out.println(foobar.print());
    }
}