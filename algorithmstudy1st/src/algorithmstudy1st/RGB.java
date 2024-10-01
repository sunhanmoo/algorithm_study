package algorithmstudy1st;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class RGB {
    public static void main(String[] args) throws IOException {
        new RGB().solution();
    }

    private void solution() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int result = 0;
        String[] inputRGB = bufferedReader.readLine().split(" ");
        Integer[] ballArray = new Integer[inputRGB.length];
        
        for (int i = 0; i < inputRGB.length; i++) {
            ballArray[i] = Integer.parseInt(inputRGB[i]);
        }

        for (int i = 0; i < ballArray.length; i++) {
            if (ballArray[i] >= 3) {
                result += ballArray[i] / 3;
                ballArray[i] = ballArray[i] % 3;
            }
        }

        Arrays.sort(ballArray);
        Integer[] temp = ballArray.clone();

        int oneColor = 0;
        Queue<Integer> boxQueue = new LinkedList<>();
        while (true) {
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] >= 1) {
                    boxQueue.offer(ballArray[i]);
                    temp[i] -= 1;
                }
            }

            if (boxQueue.size() >= 1 && boxQueue.size() <= 3) {
                boxQueue.clear();
                oneColor++;
            } else
                break;
        }

        Arrays.sort(ballArray, Collections.reverseOrder());
        temp = ballArray.clone();
        int twoColor = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 2) {
                twoColor++;
            } else if (temp[i] == 1) {
                boxQueue.offer(temp[i]);
            }
        }
        if (boxQueue.size() >= 1 && boxQueue.size() <= 3) {
            twoColor++;
        }
        result += Math.min(oneColor, twoColor);

        bufferedWriter.write(result + "\n");
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
