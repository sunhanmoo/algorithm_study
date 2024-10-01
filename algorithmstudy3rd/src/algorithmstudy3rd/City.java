package algorithmstudy3rd;

import java.util.*;

public class City {
	
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int n, m, k, x; // 차례대로 도시 개수, 도로 개수, 거리 정보, 출발 도시 번호
	static int[] distance = new int[300001]; // 최단거리 저장 배열
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		n = scan.nextInt();
		m = scan.nextInt();
		k = scan.nextInt();
		x = scan.nextInt();
		
		// 연결리스트에 노드 추가
		for(int i = 0; i <= n; ++i) {
			graph.add(new ArrayList<Integer>());
			distance[i] = -1; // 최단거리 -1(방문하지 않은 상태 표시)로 초기화
		}
		
		// 간선 정보 입력 
		for(int i = 0; i  < m; ++i) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			
			graph.get(a).add(b);
		}
		
		
		// BFS 알고리즘 
		// 모든 도로의 거리가 1이기 때문에 BFS를 사용하여 
        // 최단거리를 구할 수 있다. 
		
		distance[x] = 0; // 시작지점의 최단거리 값 0으로 초기화(자기 자신까지의 거리는 당연히 0)
		Queue<Integer> q = new LinkedList<>();
		q.offer(x); // 시작 도시 x를 큐에 추가
		while(!q.isEmpty()) { // 큐가 비어 있지 않을 때 반복
			int now = q.poll(); // 큐에서 가장 먼저 들어온 도시 꺼내기
			
			for(int i = 0; i < graph.get(now).size(); ++i) {
				int next = graph.get(now).get(i); // 현재 도시 now와 연결된 도시들 중 하나를 next로 가져온다
				
				if(distance[next] == -1) { // next 도시가 아직 방문되지 않은 경우에만 실행
					distance[next] = distance[now] + 1; // next 도시까지의 최단 거리는 현재 도시 now까지의 최단 거리 distance[now]에 1을 더한 값
					q.offer(next); // next 도시를 큐에 추가
				}
			}
		}
		
		// 최단거리가 k인 노드 찾기 
		boolean check = false;
		for(int i = 1; i <= n; ++i) {
			if(distance[i] == k) {
				System.out.println(i);
				check = true;
			}
		}
		
		// 최단거리가 k인 노드가 없다면 -1 출력 
		if(check == false)
			System.out.println(-1);
	}

}
