package algorithmstudy2nd;

import java.util.Scanner;

public class Lucky {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String number = scan.nextLine(); // 입력받을 숫자 문자열

        int middle = number.length() / 2; // 중간 자릿수
        int leftSum = 0; // 중간 자릿수 기준 왼쪽 합
        int rightSum = 0; // 중간 자릿수 기준 오른쪽 합
        
        // 왼쪽 부분의 합 계산
        for(int i = 0; i < middle; ++i) {
            leftSum += Character.getNumericValue(number.charAt(i)); // 문자를 숫자로 변환
        }
        
        // 오른쪽 부분의 합 계산
        for(int i = middle; i < number.length(); ++i) {
            rightSum += Character.getNumericValue(number.charAt(i)); // 문자를 숫자로 변환
        }

        // 결과 출력
        if(leftSum == rightSum) {
            System.out.println("LUCKY");
        } else {
            System.out.println("READY");
        }
    }
}
