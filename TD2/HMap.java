class HMap{
    EntryList[] t; // le tableau d’entrées
    int size;  //le nombre d’entrées stockées dans la table

    HMap(int n){
        t = new EntryList[n];
    }
    HMap(){
        t = new EntryList[20];
    }

    WordList find(Prefix key){
        int i = key.hashCode(t.length);
        EntryList curr = t[i];
        while (curr != null){
            if (Prefix.eq(curr.head.key, key)) return curr.head.value;
            curr = curr.next;
            }
        return null;
    }

    void addSimple(Prefix key, String w){
        WordList located = find(key);
        if (located != null) {
            located.addLast(w);
            return;
        }
        else {
            size++;
            String[] slist = new String[1];
            slist[0] = w;
            Entry newEntry = new Entry(key, new WordList(slist));
            
            int i = key.hashCode(t.length);
            EntryList curr = t[i];
            if (t[i] == null) {
                t[i] = new EntryList(newEntry,null);
                return;
            }
            while (curr.next != null) curr = curr.next;
            curr.next = new EntryList(newEntry,null);
       }
    }
    
    void rehash(int n){
        EntryList[] newT = new EntryList[n];
        for (int i = 0; i< n; i++) newT[i] = null;
        
        for (int i = 0; i< t.length; i++){
            EntryList curr = t[i];
            
            while (curr != null){
                int j = curr.head.key.hashCode(n);
                if (newT[j] == null) 
                    newT[j] = new EntryList(curr.head, null);
                else{
                    EntryList now = newT[j]; 
                    while (now.next != null) now = now.next;
                    now.next = new EntryList(curr.head, null);
                }
                curr = curr.next;
            }
        }
        t = newT;
    }

    void add(Prefix key, String w){
        addSimple(key, w);
        if (size > 3.0/4.0*t.length) rehash(2*t.length);
    }
}