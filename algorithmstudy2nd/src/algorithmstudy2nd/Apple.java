package algorithmstudy2nd;

import java.io.*;
import java.util.*;

public class Apple {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 보드 크기
        int K = Integer.parseInt(br.readLine()); // 사과 개수

        int[][] map = new int[N+1][N+1]; // 사과 위치 저장

        // 사과 위치는 1로 초기화
        for(int i = 0; i < K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            map[row][col] = 1; // 사과가 있다면 1로 초기화
        }

        int L = Integer.parseInt(br.readLine()); // 뱀 방향 변환 횟수

        // 시간에 따라 회전시킬 방향 queue 생성
        Queue<spin> spin = new LinkedList<>();
        for(int i = 0; i < L; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken()); // 방향 전환 시점
            String dir = st.nextToken(); // 회전 방향

            spin.offer(new spin(time, dir));
        }

        // 0:북, 1:동, 2:남, 3:서
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        // 초기 (1, 1)에서 시작하고 동쪽을 보고 시작
        int row = 1;
        int col = 1;
        int time = 0;
        int dir = 1;
        Queue<Node> q  = new LinkedList<>();
        q.offer(new Node(row, col));
        map[row][col] = 2;

        while(true){
        	// dir 방향으로 이동, 방향에 따른 값 만큼 이동
            int dR = row + dr[dir];
            int dC = col + dc[dir];

            time++; // 시간 증가

            if(dR < 1 || dC < 1 || dR > N || dC > N) // 뱀이 보드의 경계를 넘어가는지 체크
                break;
            if(map[dR][dC] == 2) // 뱀이 자기 자신의 몸에 부딪혔는지 체크
                break;

            if(map[dR][dC] == 0){ // 사과가 없는 경우 꼬리 이동시키기
                Node node = q.poll();
                map[node.row][node.col] = 0;
            }
            
            // 방향 변환 체크
            if(!spin.isEmpty()) {
                if (time == spin.peek().time) { // 회전 시간과 일치할 경우 방향 전환
                    spin s = spin.poll(); // 방향 변환 정보 획득

                    if (s.dir.equals("L"))
                        dir = dir - 1 < 0 ? 3 : dir - 1;
                    if (s.dir.equals("D"))
                        dir = dir + 1 > 3 ? 0 : dir + 1;
                }
            }

            // 뱀 위치 업데이트
            map[dR][dC] = 2;
            q.offer(new Node(dR, dC));
            row = dR;
            col = dC;
        }

        System.out.println(time);
    }
}

class spin{
    int time;
    String dir;

    spin(int time, String dir){
        this.time = time;
        this.dir = dir;
    }
}

class Node{
    int row, col;

    Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}