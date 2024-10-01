package algorithmstudy3rd;

import java.io.*;
import java.util.*;

public class Virus {

    static final int dx[] = {0, 0, 1, -1};  // 상하좌우 방향 설정
    static final int dy[] = {1, -1, 0, 0};  // 상하좌우 방향 설정
    static int originalMap[][]; // 연구소 지도
    static int n, m; // 세로, 가로 크기
    static int maxSafeZone = Integer.MIN_VALUE; // 최대값을 찾기 위한 최소값 설정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 연구소 크기 입력받아서 배열에 저장
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        originalMap = new int[n][m];

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; ++j) {
                originalMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(maxSafeZone);
    }

    static void dfs(int wallCnt) {
        // 벽이 3개가 설치 된 경우 bfs 탐색 시작
        if(wallCnt == 3) {
            bfs();
            return;
        }
        
        // 연구소의 모든 칸을 확인하면서 빈칸(0)인 곳에 벽을 세운다
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(originalMap[i][j] == 0) { // 연구소의 빈칸 위치인 경우
                    originalMap[i][j] = 1; // 1로 설정해 벽을 세움
                    dfs(wallCnt + 1); // 벽을 세웠기 때문에 개수 증가시키기
                    
                    // 벽을 세운 후 다시 빈칸으로 돌리는 과정(백트랙킹)
                    originalMap[i][j] = 0; // 다른 모든 벽 설치 조합 가능
                }
            }
        }
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        
        // 바이러스(2)가 있는 위치를 모두 큐에 추가한다
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(originalMap[i][j] == 2) { // 연구소의 바이러스 위치인 경우
                    q.add(new Node(i, j)); // 큐에 추가
                }
            }
        }

        // 원본 연구소를 변경하지 않기 위한 복사본 생성
        int copyMap[][] = new int[n][m];

        for (int i = 0; i < n; ++i) {
            copyMap[i] = originalMap[i].clone();
        }

        // BFS 탐색 시작
        while(!q.isEmpty()) { // 큐가 비어있지 않을 때 반복
            Node now = q.poll(); // 값 꺼내기
            int x = now.x;
            int y = now.y;

            // 상하좌우로 이동하며 바이러스를 퍼트린다
            for(int k = 0; k < 4; ++k) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                // 연구소 범위 밖이 아니고 빈칸일 경우에만 바이러스를 퍼트린다
                if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if(copyMap[nx][ny] == 0) {
                        q.add(new Node(nx,ny)); // 새로운 좌표 큐에 추가
                        copyMap[nx][ny] = 2; // 값을 2(바이러스)로 설정
                    }
                }
            }
        }

        // 바이러스가 퍼진 후 안전 구역 계산
        funcSafeZone(copyMap);
    }

    private static void funcSafeZone(int[][] copyMap) {
        int safeZone = 0;
        for(int i = 0; i < n ; ++i) {
            for(int j = 0; j < m; ++j) {
                if(copyMap[i][j] == 0) { // 바이러스가 퍼지지 않은 빈칸(0)인 경우
                    safeZone++; // 안전구역이기 때문에 값 증가시키기
                }
            }
        }
        
        if (maxSafeZone < safeZone) { // 기존 안전구역(maxSafeZone)보다 계산된 안전구역(safeZone)이 크다면 갱신시켜주기
            maxSafeZone = safeZone;
        }
    }

    // Queue에 좌표값 x,y를 넣기 위함.
    static class Node {
        int x;
        int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}