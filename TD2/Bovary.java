class Bovary{
    static HMap buildTable(String[] files, int n){
        HMap newHMap = new HMap();
        
        for (int j = 0; j<files.length;j++){
            WordReader wr = new WordReader(files[j]);
            String nowend = Prefix.start;
        
            Prefix currPre = new Prefix(n);
            for (String next = wr.read(); next != null; next = wr.read()) {
            
                //System.out.println("[" + next + "]"+"[" + nowend + "]");
                currPre = currPre.addShift(nowend);
                //System.out.println((newHMap.t.length)+" "+(newHMap.size));
                newHMap.add(currPre, next); 
                nowend = next;
            }
        
            currPre.addShift(nowend);
            newHMap.add(currPre, Prefix.end);
            //System.out.println(Prefix.end+"[" + nowend + "]");
        }
        return newHMap;
    }

    static void generate(HMap t, int n){
        WordList newbook = new WordList();
        Prefix currPre = new Prefix(n);
        
        
        for(WordList nowWordList = t.find(currPre); nowWordList != null ; nowWordList = t.find(currPre)){
            String newWord = nowWordList.randomfind();
            newbook.addLast(newWord);
            currPre = currPre.addShift(newWord);
            if (newWord == Prefix.end) break;
        }
        //System.out.println(newbook.removeLast());
        newbook.printBook();
    }

    public static void main(String[] args) {
        String[] files = new String[35];
        for(int ind = 1;ind <= 9; ind ++) files[ind-1] = "bovary/0"+Integer.toString(ind)+".txt";        
        for(int ind = 10;ind <= 35; ind ++) files[ind-1] = "bovary/"+Integer.toString(ind)+".txt";
        //String test ="bovary/0"+ind+".txt";
        //System.out.println(test);
        int n = 100;
        generate(buildTable(files, n),n);    
    }

}