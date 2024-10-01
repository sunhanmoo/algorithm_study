package algorithmstudy1st;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Greedy {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		
		char[] inputArray = bufferedReader.readLine().toCharArray(); // 입력받는 문자열
		int numZero = 0; // 숫자 0의 개수
		int numOne = 0; // 숫자 1의 개수
		char currentNum = inputArray[0]; // 현재 읽고 있는 문자열
		
		// 0번째 문자열의 숫자 알아내기
		if(currentNum == '0') {
			numZero++; // 현재 문자열이 0이라면 0의 개수 증가
		}
		else if(currentNum == '1') {
			numOne++; // 현재 문자열이 1이라면 1의 개수 증가
		}
		else {
			System.out.println("0과 1에 해당하지 않습니다.");
		}
		
		// 나머지 숫자를 순회하며 0과 1의 개수 알아내기
		for(int i=1; i<inputArray.length; ++i) {
			if(currentNum != inputArray[i]) {
				currentNum = inputArray[i];
				if(currentNum == '0') {
					numZero++;
				}
				else if(currentNum == '1') {
					numOne++;
				}
				else {
					System.out.println("0과 1에 해당하지 않습니다.");
				}
			}
		}
		
		// 둘 중 하나라도 0이 나오면 0 출력
		if(numZero == 0 || numOne == 0) {
			bufferedWriter.write(0 + "\n");
		}
		// 둘의 개수가 같은 경우
		else if(numZero == numOne) {
			bufferedWriter.write(numZero + "\n");
		}
		// 둘의 개수가 다른 경우
		else {
			if(numZero > numOne) {
				bufferedWriter.write(numOne + "\n");
			}
			else if(numZero < numOne) {
				bufferedWriter.write(numZero + "\n");
			}
		}
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}

}
