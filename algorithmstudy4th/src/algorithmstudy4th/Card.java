package algorithmstudy4th;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Card {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 카드 묶음
		int sum = 0; // 최소 비교 횟수
		
		// PriorityQueue: 내부적으로 최소 힙 구조로 동작
		// 즉 가장 작은 값이 큐의 맨 앞에 위치
		// 큐가 자동으로 카드 묶음 크기를 오름차순으로 정렬
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < N; ++i) {
			pq.add(Integer.parseInt(br.readLine())); // 카드 묶음의 크기들이 입력으로 주어지면 하나씩 우선순위 큐에 추가
		}
		
		while(pq.size() > 1) { // 큐에 2개 이상의 카드 묶음이 있을 때만 실행
			// 큐에서 가장 작은 두 개의 카드 묶음을 꺼내기
			int first = pq.poll();
			int twice = pq.poll();
			
			sum = sum + first + twice; // 두 묶음을 합친 카드 묶음 크기만큼 비교 횟수를 증가
			pq.add(first + twice); // 다시 큐에 넣기
		}
		System.out.println(sum);
	}
}