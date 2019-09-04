class Prefix {
    String[] t;

    public final static String start = "<START>", end = "<END>", par = "<PAR>";

    Prefix(int n){
        t = new String[n];
        for (int i = 0; i < n; i++) t[i] = start;
    }

    static boolean eq(Prefix p1, Prefix p2){
        if(p1.t.length != p2.t.length) return false;
        for (int i = 0; i<p1.t.length; i++)
            if (!p1.t[i].equals(p2.t[i])) return false;
        return true;
    }
    
    Prefix addShift(String w){
        Prefix newP = new Prefix(t.length);
        for (int i = 0; i< t.length - 1; i++) newP.t[i] = t[i+1];
        newP.t[t.length - 1] = w;
        return newP;
    }

    public int hashCode(){
        int h = 0;
        for (int i = 0; i<t.length; i++) h = 37 * h + t[i].hashCode();
        return h;
    }
    
    int hashCode(int n){
        int diviseur = hashCode()%n;
        if (diviseur < 0) return diviseur + n;
        else return diviseur;
    }


    public static void main(String[] args){
        //Prefix t = new Prefix(0);
        System.out.println(5%-3);
    }
  }