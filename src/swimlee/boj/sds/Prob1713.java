package swimlee.boj.sds;

import java.util.*;

public class Prob1713 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //사진 틀의 개수
        int M = sc.nextInt(); //전체 학생 총 추천횟수

        ArrayList<Student> arr = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int rec = sc.nextInt();

            boolean already = false;

            for (int k = 0; k < arr.size(); k++) {
                if(arr.get(k).num == rec) {
                    arr.get(k).rec++;
                    already = true;
                    break;
                }
            }

            if(!already) {
                if (arr.size() >= N) {
                    arr.sort((a, b) -> {
                        if (a.rec == b.rec) return a.time - b.time;
                        else return a.rec - b.rec;
                    });
                    arr.remove(0);
                    arr.add(0, new Student(rec, 1, i));
                } else {
                    arr.add(new Student(rec, 1, i));
                }
            }
        }

        arr.sort((a, b) -> a.num - b.num);
        for (Student student : arr) {
            System.out.print(student.num + " ");
        }
    }

    static class Student {
        int num;
        int rec;
        int time;

        public Student(int num, int rec, int time) {
            this.num = num;
            this.rec = rec;
            this.time = time;
        }
    }
}
