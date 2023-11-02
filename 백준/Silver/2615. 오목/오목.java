import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static int[][] map = new int[19][19];
	public static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
	public static int[] dy = {1, 1, 1, 0, -1, -1, -1, 0};
	public static int result = 0;
	public static boolean flag = false; //6목인지 확인하기 위한 flag
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    	for(int i=0;i<19;i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());	
    		for(int j=0;j<19;j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	
    	for(int i=0;i<19;i++) {
    		
    		for(int j=0;j<19;j++) {
    			if(map[i][j] != 0) {
    				
    				for(int dir=0;dir<4;dir++) {
    					flag = false;
    					simulate(new Node(i, j, map[i][j]), 1, dir);
    					
    					
    					//6목이 아닌경우에 넘어옴
    					if(flag == false) {
    						//반대방향에서 1칸 확인해야함, +4 한값이 해당 방향의 반대값입니다
    			    		int nr = i + dx[dir + 4];
    			    		int nc = j + dy[dir + 4];
    			    		int color = map[i][j];
    			    		
    			    		//만약 다음칸으로 이동해도 범위를 벗어날시 성공한것으로 처리가능.
    			    		if(nr >= 0 && nr < 19 && nc >= 0 && nc < 19) {
        			    		//만약 다음칸이 같은색이라면 6목이므로 실패처리하기 위해 flag = true
        			    		if(map[nr][nc] == color) {
        			    			result = 0;
        			    		}
    			    		}
    			    		
    						if(result != 0) {
    							System.out.println(result);
    							System.out.println((i+1)+" "+(j+1));
    							return ;
    						}
    					}
    				
    				}
    			}	
    		}	
    	}
    	System.out.println(result);
    	

		
    }
    
    public static void simulate(Node start, int level, int dir) {
    	if(level == 5) {
    		int nr = start.r + dx[dir];
    		int nc = start.c + dy[dir];
    		int color = start.color;
    		
    		//만약 다음칸으로 이동해도 범위를 벗어날시 성공한것으로 처리가능.
    		if(nr < 0 || nr >= 19 || nc < 0 || nc >= 19) {
    			result = start.color;
    			return ;
    		}    		
    		
    		//만약 다음칸이 같은색이라면 6목이므로 실패처리하기 위해 flag = true
    		if(map[nr][nc] == color) {
    			flag = true;
    			return ;
    		}
    		
    		//다음것 존재하는지 확인하여 flag = false로 유지
    		if(map[nr][nc] != color) {
    			result = start.color;
    			return ;
    		}
    	}
    	
		int nr = start.r + dx[dir];
		int nc = start.c + dy[dir];
		int color = start.color;
		if(nr < 0 || nr >= 19 || nc < 0 || nc >= 19) return ;
		if(map[nr][nc] != color) return ;
		
		simulate(new Node(nr, nc, color), level + 1, dir);

		
    }

}


class Node{
	int r;
	int c;
	int color;
	public Node(int r, int c, int color) {
		this.r=r;
		this.c=c;
		this.color = color;
	}
}