package swimlee.realtest.saramin;

import java.util.*;
import java.io.*;

public class Prob2 {

    public static Scanner scanner = new Scanner(System.in);

    public static void testCase(int caseNum) {
        int L = scanner.nextInt();// 바이트 수
        String[] UID = new String[3];
        for (int i = 0; i < 3; i++) {
            UID[i] = scanner.next();
        }

        String[][] temp = new String[3][L];

        for (int i = 0; i < UID.length; i++) {
            temp[i] = UID[i].split("-");
        }

        HashSet<Integer>[] hsList = new HashSet[L];
        for (int i = 0; i < L; i++) {
            hsList[i] = new HashSet<>();
        }

        // 저-고 비교
        for (int i = 0; i < L; i++) {
            String low = temp[0][i];
            String high = temp[1][i];

            int lowNum = Integer.parseInt(low);
            int highNum = Integer.parseInt(high);

            int bit = lowNum ^ highNum;
            String binaryString = String.format("%08d", Integer.parseInt(Integer.toBinaryString(bit)));

            for (int l = 0; l < binaryString.length(); l++) {
                if (binaryString.charAt(l) == '1') {
                    hsList[i].add(l);
                }
            }
        }

        // 저-상 비교
        for (int i = 0; i < L; i++) {
            String low = temp[0][i];
            String middle = temp[2][i];

            int lowNum = Integer.parseInt(low);
            int middleNum = Integer.parseInt(middle);

            int bit = lowNum ^ middleNum;
            String binaryString = String.format("%08d", Integer.parseInt(Integer.toBinaryString(bit)));

            for (int l = 0; l < binaryString.length(); l++) {
                if (binaryString.charAt(l) == '1') {
                    hsList[i].add(l);
                }
            }
        }

        // 고-상 비교
        for (int i = 0; i < L; i++) {
            String high = temp[1][i];
            String middle = temp[2][i];

            int highNum = Integer.parseInt(high);
            int middleNum = Integer.parseInt(middle);

            int bit = highNum ^ middleNum;
            String binaryString = String.format("%08d", Integer.parseInt(Integer.toBinaryString(bit)));

            for (int l = 0; l < binaryString.length(); l++) {
                if (binaryString.charAt(l) == '1') {
                    hsList[i].add(l);
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < L; i++) {
            sum += hsList[i].size();
        }

        System.out.println(sum);
    }

    public static void main(String[] args) {
        int tn = scanner.nextInt();
        for (int caseNum = 1; caseNum <= tn; caseNum++) {
            testCase(caseNum);
        }
    }
}
