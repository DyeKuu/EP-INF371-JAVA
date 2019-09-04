import java.lang.Math;
//import java.util.Scanner;

public class Percolation {
	static int size = 10;
	static int length = size * size;
	static boolean[] grid = new boolean[length];
	static boolean[] seen = new boolean[length];
	static void init() {
		for (int i = 0; i<length; i++) grid[i] = false;
		for (int i = 0; i<length; i++) seen[i] = false;
	}
	
	
	static void print() {
		for (int j = 0; j < size; j++) {
			for (int i = j; i<length; i += size) {
				if (grid[i] == true) System.out.print("* ");
				else System.out.print("- ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static int random_shadow(){
		int i = (int) Math.floor( Math.random()*length );
		while (grid[i] == true) i = (int) Math.floor( Math.random()*length );
		grid[i] = true;
		return i;
	}
	
	
	static boolean detect_path(boolean[] seen, int n, boolean up) {
		if (up){
			if( n%size == 0 && grid[n] == true) return true;
		}
		else {
			if( (n+1)%size == 0 && grid[n] == true) return true;
		}


		seen[n] = true;

		if (up) {
			if( n%size == 0 && grid[n] == true) return true;
			else {
				
				if (n%size !=0 && grid[n-1] == true && seen[n-1] == false && detect_path(seen, n-1, true)) return true;
				if (n >= size && grid[n-size] == true && seen[n-size] == false && detect_path(seen,n-size,true)) return true;
				if (n + size < length && grid[n+size] == true && seen[n+size] == false && detect_path(seen,n+size,true)) return true;
				if ((n+1)%size != 0 && grid[n+1] == true && seen[n+1] == false && detect_path(seen,n+1,true)) return true;
			}
			return false;
		}
		else {
			if( (n+1)%size == 0 && grid[n] == true) return true;
			else {
				if ((n+1)%size != 0 && grid[n+1] == true && seen[n+1] == false && detect_path(seen,n+1,false)) return true;
				if (n >= size && grid[n-size] == true && seen[n-size] == false && detect_path(seen,n-size,false)) return true;
				if (n + size < length && grid[n+size] == true && seen[n+size] == false && detect_path(seen,n+size,false)) return true;
				if (n%size !=0 && grid[n-1] == true && seen[n-1] == false && detect_path(seen, n-1, false)) return true;
			}
			return false;
		}
	}
	
	static boolean is_percolation(int n) {

		for (int i = 0; i<length; i++) seen[i] = false;
		
		boolean ifup = detect_path(seen, n, true);
		
		for (int i = 0; i<length; i++) seen[i] = false;
		
		boolean ifdown = detect_path(seen, n, false);
		
		if (ifup && ifdown) return true;
		else return false;
	}
	
	
	static double percolation() {
		int numberofperco = 0;
		double result = 0;
		int numberofblack = 0;
		init();
		while (numberofperco == 0) {
			for (int i = 0; i<length; i++) seen[i] = false;
			int randomPoint = random_shadow();
			numberofblack ++;
			if (is_percolation(randomPoint)) numberofperco++;
		}
		result = ((double)numberofblack)/length;
		return result;
	}

	static double montecarlo(int n) {
		double sum = 0;
		for (int i = 1; i <= n; i++) sum += percolation();
		sum /= n;
		return sum;
	}
	
	
	public static void main(String[] args) {
		//init();
		//random_shadow();
		
		//int[] randomPoint = random_shadow();
		//grid[randomPoint[0]*size+randomPoint[1]] = true;
		
		//this is for test case
		//for (int i=size;i<size*2;i++) grid[i]=true;
		grid[53]=true;grid[33]=true;grid[23]=true;grid[24]=true;grid[25]=true;grid[45]=true;grid[35]=true;grid[44]=true;grid[43]=true;grid[42]=true;grid[41]=true;grid[40]=true;
		print();
		//print();
		//init();
		/*
		try (Scanner sc = new Scanner(System.in)) {
			
			int n = sc.nextInt(); 
			double begintime = System.currentTimeMillis();
			System.out.println("proportion : "+montecarlo(n));
			double endtime = System.currentTimeMillis();
			System.out.println((endtime - begintime)+" ms");
			
		}
		*/ 
		System.out.println(is_percolation(63));
	}

}
