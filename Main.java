import java.util.Arrays;

// SKILL CHECK LEVEL3 02
public class Main {

	public static void main(String[] args) {
		int n = 4;
		int[] works = {4,3,3};
		// return 12
		
//		int n = 4;
//		int[] works = {4,1,3};
		// return 12
		
//		int n = 3;
//		int[] works = {1,1};
		// return 12
		
		System.out.println(new Solution().solution(n, works));
	}

}
class Solution {
    public long solution(int n, int[] works) {
    	long answer = 0;
    	
    	if (Arrays.stream(works).sum() <= n)
    		return 0;
    	
    	Arrays.sort(works);
        
        int diff;
        int maxCount;
        
        while (true) {
    		int idx = works.length - 1;
    		int max = works[idx];
    		int nextMax = Arrays.stream(works).filter(i -> i < max).max().orElse(-1);
    		
    		if (nextMax == -1) {
    			maxCount = works.length;
    			int sub = 0;
				while (n >= maxCount) {
					n -= maxCount;
					sub++;
				}
				for (int i = 0; i < maxCount; i++) {
					works[idx - i] -= sub;
				}
				for (int i = 0; i < n; i++) {
					works[idx - i]--;
				}
				
    			break;
    		} else {
    			diff = max - nextMax;
    			maxCount = (int) Arrays.stream(works).filter(i -> i == max).count();
    			if (diff * maxCount <= n) {
    				n -= maxCount * diff;
    				for (int i = 0; i < maxCount; i++) {
    					works[idx - i] -= diff;
    				}
    			} else {
    				int sub = 0;
    				while (n >= maxCount) {
    					n -= maxCount;
    					sub++;
    				}
    				for (int i = 0; i < maxCount; i++) {
    					works[idx - i] -= sub;
    				}
    				for (int i = 0; i < n; i++) {
    					works[idx - i]--;
    				}
    				
    				break;
    			}
    		}
    	}
        
        
        for (int i = 0; i < works.length; i++) {
        	answer += works[i]*works[i];
        }
        
        return answer;
    }
}