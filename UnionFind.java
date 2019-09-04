public class UnionFind{
    static int[] equiv = new int[Percolation.length];
    static int[] height = new int[Percolation.length];

    static int find(int x){
        if (equiv[x] != x) equiv[x] = find(equiv[x]);
        return equiv[x];
    }
    static void union(int x, int y){
        int xroot = find(x);
        int yroot = find(y);
        if (xroot == yroot) return;
        if (height[xroot] > height[yroot]) 
            equiv[yroot] = xroot;
        else if (height[xroot] < height[yroot]) equiv[xroot] = yroot;
           else {
                equiv[xroot] = yroot;
                height[yroot] ++;
            }
    }
} 