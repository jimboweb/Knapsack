import java.util.*;

public class Knapsack {
	static boolean debug = false;
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int result = 0;
        for (int i = 0; i < w.length; i++) {
          if (result + w[i] <= W) {
            result += w[i];
          }
        }
        return result;
    }
    
    static int dynamicWeight(int W, int[] w){
    	int[] wWithZero = new int[w.length+1];
    	wWithZero[0] = 0;
    	System.arraycopy(w, 0, wWithZero, 1, w.length);
    	int[][] optimalWeights = new int[W+1][wWithZero.length];
    	int i, j;
    	int val;
    	for(i=0;i<W+1;i++){
    		optimalWeights[i][0] = 0;
    	}
    	for(i=0;i<wWithZero.length;i++){
    		optimalWeights[0][i] = 0;
    	}
    	for(i=1;i<wWithZero.length;i++){
			for(j=0; j<=W; j++){
	    		optimalWeights[j][i] = optimalWeights[j][i-1];
    			if(wWithZero[i]<=j){
    				val = optimalWeights[j-wWithZero[i]][i-1] + wWithZero[i];
    				if(optimalWeights[j][i]<val){
    					optimalWeights[j][i]=val;
    				}
    			}
            	if(debug){
            		System.out.printf("optimalWeights[%d][%d] is %d%n", j, i,optimalWeights[j][i]);
            	}
    		}
    	}
    	return optimalWeights[W][wWithZero.length-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(dynamicWeight(W, w));
    }
}

