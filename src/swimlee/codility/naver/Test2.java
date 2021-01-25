package swimlee.codility.naver;

import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) {
        int[] A = {3,4,5,3,7};
//        int[] A = {1,2,3,4};

        Test2 test2 = new Test2();
        int solution = test2.solution(A);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] A) {
        int answer = 0;

        int flag = 0;
        
        for (int i = 1; i < A.length; i++) {
            if (i == 1) { //처음 하는 경우 flag 초기화
                if(A[i-1] < A[i]) flag = 1; //증가한 경우
                else if(A[i-1] > A[i]) flag = -1; //감소한 경우
                else {
                    flag = 0;
                    break;
                }
            } else {
                if (flag == 1) {//전에 증가한 경우 -> 이제 감소할차례
                    if(A[i-1] > A[i]) flag = -1;
                    else {
                        flag = 0;
                        break;
                    }
                } else if (flag == -1) {
                    if(A[i-1] < A[i]) flag = 1;
                    else {
                        flag = 0;
                        break;
                    }
                }
            }
        }

        if(flag != 0) return 0;

        ArrayList<Integer> arrayList = new ArrayList<>(A.length);
        for (int i : A) {
            arrayList.add(i);
        }

        for (int i = 0; i < A.length; i++) {
            ArrayList<Integer> tmp = new ArrayList<>(arrayList);
            tmp.remove(i);
            for (int j = 1; j < tmp.size(); j++) {
                if (j == 1) {
                    if(tmp.get(j-1) < tmp.get(j)) flag = 1; //증가한 경우
                    else if(tmp.get(j-1) > tmp.get(j)) flag = -1; //감소한 경우
                    else {
                        flag = 0;
                        break;
                    }
                } else {
                    if (flag == 1) {//전에 증가한 경우 -> 이제 감소할차례
                        if(tmp.get(j-1) > tmp.get(j)) flag = -1;
                        else {
                            flag = 0;
                            break;
                        }
                    } else if (flag == -1) {
                        if(tmp.get(j-1) < tmp.get(j)) flag = 1;
                        else {
                            flag = 0;
                            break;
                        }
                    }
                }
            }
            if(flag != 0) answer++;
        }

        if(answer == 0) return -1;
        
        return answer;
    }
}
