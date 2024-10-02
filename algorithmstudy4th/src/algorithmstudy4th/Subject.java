package algorithmstudy4th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Subject {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int studentNum = Integer.parseInt(br.readLine()); // 학생 수 입력받기

        String[][] arr = new String[studentNum][4]; // 학생 이름 및 성적 저장 배열
        for(int i = 0; i < studentNum; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = st.nextToken(); // 이름
            arr[i][1] = st.nextToken(); // 국어 점수
            arr[i][2] = st.nextToken(); // 영어 점수
            arr[i][3] = st.nextToken(); // 수학 점수

        }

        Arrays.sort(arr, new Comparator<String[]>() {
            @Override
            public int compare(String[] stu1, String[] stu2) {
                if (stu2[1].equals(stu1[1])) { // 국어 점수가 동일하다면
                	
                    if (stu1[2].equals(stu2[2])) { // 영어 점수가 동일하다면
                    	
                        if (stu1[3].equals(stu2[3])) { // 수학 점수가 동일하다면
                            return stu1[0].compareTo(stu2[0]); // (수학 점수가 동일하다면) 이름 사전순 정렬
                        }
                        
                        return Integer.parseInt(stu2[3]) - Integer.parseInt(stu1[3]); // (영어 점수가 동일하다면) 수학 점수 내림차순
                    }
                    
                    return Integer.parseInt(stu1[2]) - Integer.parseInt(stu2[2]); // (국어 점수가 동일하다면) 영어 점수 오름차순
                }
                return Integer.parseInt(stu2[1]) - Integer.parseInt(stu1[1]); // 국어 점수 내림차순
            }
        });

        for(int i = 0; i < arr.length; ++i){
            System.out.println(arr[i][0]);
        }

    }
}
